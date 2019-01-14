package ru.sfedu.sevenTravel.utils;

import ru.sfedu.sevenTravel.utils.Login;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.sevenTravel.beans.User;

import static org.junit.Assert.*;

public class LoginTest {

    public static Login login;
    public static Logger log = LogManager.getLogger(LoginTest.class);

    @BeforeClass
    public static void setData(){
        log.debug("set Data");
        login = new Login();
    }

    @Test
    public void authorize(){
        log.debug("test authorize");
        User user = login.authorize("Майкл Фассбендер", "8-800 555 35-35", "qwerty2");
        assertNotNull(user);
        log.debug("test successfully finish");
    }

    @Test
    public void isTelephoneNumber() {
        log.debug("isTelephoneNumber");
        String number = "89281072351";
        boolean value = login.validPhoneNumber(number);
        assertEquals(true,value);
        log.debug("test successfully finish");
    }
    @Test
    public void validFullName(){
        log.debug("test valid full name");
        boolean value = login.validFullName("Питер Паркер");
        assertEquals(true, value);
        log.debug("test successfully finish");
    }
    @Test
    public void processPhoneNumber(){
        log.debug("test process phone number");
        String str = login.processPhoneNumber("+799878 898 9-9");
        System.out.println(str);
        assertEquals("89987889899", str);
        log.debug("test successfully finish");
    }
}