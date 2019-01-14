package ru.sfedu.sevenTravel.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.sfedu.sevenTravel.utils.ConfigurationUtil.readConfig;

public enum Constants {

    csvDir(readConfig("csvDir")),
        userCsv(csvDir, readConfig("csvUser")),
        driverCsv(csvDir, readConfig("csvDriver")),
        carCsv(csvDir, readConfig("csvCar")),
        helicopterCsv(csvDir, readConfig("csvHelicopter")),

    xmlDir(readConfig("xmlDir")),
        userXml(xmlDir, readConfig("xmlUser")),
        driverXml(xmlDir, readConfig("xmlDriver")),
        carXml(xmlDir, readConfig("xmlCar")),
        helicopterXml(xmlDir, readConfig("xmlHelicopter"));

    Constants(String str){
        this.path = Paths.get(str);
    }

    Constants(Constants con, String str){
        this.path = Paths.get(con.getPath().toString(), str);
    }

    public Path getPath() {
        return path;
    }

    private final Path path;
}
