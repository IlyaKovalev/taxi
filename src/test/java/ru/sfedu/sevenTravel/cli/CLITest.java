package ru.sfedu.sevenTravel.cli;

import org.junit.Test;
import ru.sfedu.sevenTravel.api.CsvService;
import ru.sfedu.sevenTravel.api.JdbcService;
import ru.sfedu.sevenTravel.api.XmlService;
import ru.sfedu.sevenTravel.beans.Car;
import ru.sfedu.sevenTravel.beans.Driver;

import static org.junit.Assert.*;
import static ru.sfedu.sevenTravel.utils.Constants.*;
import static ru.sfedu.sevenTravel.utils.Schema.*;

public class CLITest {

    String id = "ae8fe380dd9aa5a7a956d9085fe7cf6b87d0d028";

    String csvCreate= "CREATE Driver Csv Brad_Pitt 3456 5 34 10";
    String csvGet = "GET Driver Csv by_id "+id;
    String csvRemove = "REMOVE Driver Csv by_id "+id;

    String xmlCreate= "CREATE Driver Xml Brad_Pitt 3456 5 34 10";
    String xmlGet = "GET Driver Xml by_id "+id;
    String xmlRemove = "REMOVE Driver Xml by_id "+id;

    String dbCreate= "CREATE Driver DB Brad_Pitt 3456 5 34 10";
    String dbGet = "GET Driver DB by_id "+id;
    String dbRemove = "REMOVE Driver DB by_id "+id;

    CLI cli = new CLI();

    private CsvService<Driver> driverCsvData = new CsvService<>(driverCsv.getPath(), Driver.class);
    private XmlService<Driver> driverXmlData = new XmlService<>(driverXml.getPath());
    private JdbcService<Driver> driverJdbcData = new JdbcService<>(DRIVER_TABLE.getTable(), Driver.class, DRIVER_TABLE.getColumns());

    public String[] convert(String s){
        return s.split(" ");
    }

    @Test
    public void executeCsv() {
        cli.execute(convert(csvCreate));
        cli.execute(convert(csvGet));
        cli.execute(convert(csvRemove));
        assertEquals(0, driverCsvData.readAll().size());
    }

    @Test
    public void executeXml() {
        cli.execute(convert(xmlCreate));
        cli.execute(convert(xmlGet));
        cli.execute(convert(xmlRemove));
        assertEquals(0, driverXmlData.readAll().size());
    }

    @Test
    public void executeDB() {
          cli.execute(convert(dbCreate));
          cli.execute(convert(dbGet));
          cli.execute(convert(dbRemove));
          assertEquals(0, driverJdbcData.selectAll().size());
    }
}