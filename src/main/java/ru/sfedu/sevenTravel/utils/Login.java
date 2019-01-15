package ru.sfedu.sevenTravel.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.sfedu.sevenTravel.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    private static Logger log = LogManager.getLogger(Login.class);

    public Login(){
        log.debug("Login instance was created");
    }

    public User authorize(String fullName, String phoneNumber, String password){
        phoneNumber = processPhoneNumber(phoneNumber);
        password = DigestUtils.sha1Hex(password);
        if(!validPhoneNumber(phoneNumber)){
            log.debug("phone number is not valid");
            return null;
        }
        if (!validFullName(fullName)){
            log.debug("Full name is not valid");
            return null;
        }
        if(password.length()<5){
            log.debug("password is too short");
            return null;
        }

        User user = new User(fullName, phoneNumber, password);
        return user;
    }

    public boolean validFullName(String fullName){
        Pattern pattern = Pattern.compile("^[\\p{L} .'-]+$");
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    public boolean validPhoneNumber(String phoneNumber){
        if (phoneNumber.isEmpty()){
            log.debug("phoneNumber is Empty");
            return false;
        }
        Pattern pattern = Pattern.compile("^(8+([0-9]){10})$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return  matcher.matches();
    }

    public String processPhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("\\s+","");
        phoneNumber = phoneNumber.replaceAll("-","");
        phoneNumber = phoneNumber.replaceAll("\\+7","8");
        return phoneNumber;
    }

}
