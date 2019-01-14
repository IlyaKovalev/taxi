package ru.sfedu.sevenTravel.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DB_Connector {

    private static Logger log = LogManager.getLogger(DB_Connector.class);
    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement(String url, String user, String password){
        try {
            log.debug("Connecting to persistence");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            log.debug("Connected persistence successfully");
        } catch (SQLException e) {
            log.error(e);
            e.printStackTrace();
        }
        return statement;
    }
    public static void close(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
