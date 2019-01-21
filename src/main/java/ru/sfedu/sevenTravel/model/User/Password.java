package ru.sfedu.sevenTravel.model.User;

import ru.sfedu.sevenTravel.utils.Hash;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Password {

    @Column(length = 40)
    private String password;

    @Transient
    private static final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    private Password(){}

    public Password(String password){
        if (isValid(password)) this.password = Hash.hashCode(password);
    }

    private boolean isValid(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (isValid(password))
        this.password = Hash.hashCode(password);
    }
}
