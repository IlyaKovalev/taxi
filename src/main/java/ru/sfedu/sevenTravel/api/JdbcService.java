package ru.sfedu.sevenTravel.api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.sfedu.sevenTravel.beans.BeanFactory;
import ru.sfedu.sevenTravel.beans.Entity;
import ru.sfedu.sevenTravel.utils.ConfigurationUtil;
import ru.sfedu.sevenTravel.utils.DB_Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcService<T extends Entity> implements DataProvider<T> {

    private String tableName;
    private String[] columns;
    private Class<T> cls;

    private String url = ConfigurationUtil.readConfig("JDBC_URL");
    private String user = ConfigurationUtil.readConfig("mysql_user");
    private String password = ConfigurationUtil.readConfig("mysql_password");

    private Statement statement;

    private static Logger log = LogManager.getLogger(JdbcService.class);

    public JdbcService(String tableName, Class<T> cls, String[] columns){
        this.cls = cls;
        this.columns = columns;
        this.tableName = tableName;
        this.statement = DB_Connector.getStatement(url, user, password);
    }

    @Override
    public boolean save(T element) {
        String params = Arrays.stream(element.toStringArray()).map(str->String.format("'%s'",str)).map(String::trim).reduce((s1,s2)->String.format("%s, %s", s1,s2)).orElse("nothing");
        log.debug("Params String \n"+params);
        if (params.equals("nothing"))return false;
        String sql = String.format("INSERT INTO %s VALUES(%s)", tableName, params);
        log.debug(sql);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void remove(T element) {
        String sql = String.format("DELETE FROM %s WHERE id='%s'", tableName, element.getId());
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T oldElement, T newElement) {
        remove(oldElement);
        save(newElement);
    }

    @Override
    public T selectByID(String id) {
        String sql = String.format("SELECT * FROM %s WHERE id='%s'",tableName, id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            List<String> params =  Arrays.stream(this.columns).map(col-> {
                try {
                    return resultSet.getString(col);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            String[] par = new String[params.size()];
            for (int i=0; i<par.length; i++) {
                par[i] = params.get(i);
            }
            resultSet.close();
            return (T) BeanFactory.getBean(cls, par);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> selectByName(String name) {
        String sql = String.format("SELECT * FROM %s WHERE name='%s'",tableName, name);
        List<T> entities = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<String> params =  Arrays.stream(this.columns).map(col -> {
                    try {
                        return resultSet.getString(col);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
                String[] par = new String[params.size()];
                for (int i=0; i<par.length; i++) {
                    par[i] = params.get(i);
                }
                entities.add((T) BeanFactory.getBean(cls, par));
            }
            resultSet.close();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T>selectAll(){
        String sql = String.format("SELECT * FROM %s ",tableName);
        List<T> entities = new LinkedList<>();
        log.debug(sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<String> params = Arrays.stream(this.columns).map(col -> {
                    try {
                        return resultSet.getString(col);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
                String[] par = new String[params.size()];
                for (int i=0; i<par.length; i++) {
                    par[i] = params.get(i);
                }
                entities.add((T) BeanFactory.getBean(cls, par));
            }
            resultSet.close();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
