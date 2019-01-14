package ru.sfedu.sevenTravel.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Hash {

    public static String hashCode(String data){
        return DigestUtils.sha1Hex(data);
    }

}
