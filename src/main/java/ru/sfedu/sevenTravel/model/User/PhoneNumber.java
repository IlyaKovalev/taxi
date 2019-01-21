package ru.sfedu.sevenTravel.model.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class PhoneNumber {

    @Column(length = 10)
    private String phoneNumber;

    @Transient
    private static final Pattern pattern = Pattern.compile("^(8+([0-9]){10})$");

    private PhoneNumber(){ }

    public PhoneNumber(String phoneNumber){
        processPhoneNumber(phoneNumber);
        if (isValid(phoneNumber))
            this.phoneNumber = phoneNumber;
    }

    private boolean isValid(String phoneNumber){
        Matcher matcher = pattern.matcher(phoneNumber);
        return  matcher.matches();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String processPhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("\\s+","");
        phoneNumber = phoneNumber.replaceAll("-","");
        phoneNumber = phoneNumber.replaceAll("\\+7","8");
        return phoneNumber;
    }

    @Override
    public String toString(){
        return getPhoneNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPhoneNumber());
    }
}
