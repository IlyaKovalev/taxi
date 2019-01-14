package ru.sfedu.sevenTravel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class SevenTravelClientTest {

    public static SevenTravelClient client;
    public static Logger log = LogManager.getLogger(SevenTravelClientTest.class);

    @BeforeClass
    public static void setData(){
        log.debug("set Data");
        client = new SevenTravelClient();
    }

    @Test
    public void logBasicSystemInfo() {
        log.debug("test logBasicSystemInfo");
        client.logBasicSystemInfo();
    }
}