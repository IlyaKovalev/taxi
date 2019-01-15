package ru.sfedu.sevenTravel.persistence;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.sfedu.sevenTravel.model.BeanFactory;
import ru.sfedu.sevenTravel.model.Entity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CsvService<T extends Entity> implements DataProvider<T> {

    private File file;
    private CSVWriter csvWriter;
    private CSVReader csvReader;

    private Class<T> cls;
    private String[] header;

    private static Logger log = LogManager.getLogger(CsvService.class);

    public CsvService(Path path, Class<T> cls) {


        file = new File(String.valueOf(path));
        header = Arrays.stream(cls.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .map(Field::getName)
                .toArray(String[]::new);
        this.cls = cls;
        log.debug("length "+String.valueOf(file.length()));
        File theDir = new File(String.valueOf(path.getParent()));

        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){

            }
            if(result) {
                log.info("DIR "+theDir.getName()+" created");
            }
        }
        file = new File(String.valueOf(path));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialize();
        if (file.length()==0) {
            log.debug("add header");
            csvWriter.writeNext(header);
        }

        close();
    }

    private void initialize() {
        try {
            this.csvWriter = new CSVWriter(new FileWriter(this.file, true));
            this.csvReader = new CSVReader(new FileReader(this.file),',');
        }catch (IOException e){
            log.error(e);
        }
    }

    @Override
    public boolean save(T element) {
        log.debug("save "+element.getName());
        if (selectByID(element.getId())!=null){
            return false;
        }
        initialize();
        String[] record = element.toStringArray();
        csvWriter.writeNext(record);
        close();
        return true;
    }

    @Override
    public void remove(T element) {
        initialize();
        log.debug("remove "+element.getName());
        try {
            List<String[]> allElements = new LinkedList<>(csvReader.readAll());
            for (int i = 0; i < allElements.size(); i++) {
                if (allElements.get(i)[0].equals(element.getId())) {
                    allElements.remove(i);
                    csvWriter = new CSVWriter(new FileWriter(file));
                    csvWriter.writeAll(allElements);
                    break;
                }
            }
        }catch (IOException e){
            log.debug(e);
        }
        close();
    }

    @Override
    public void update(T oldElement, T newElement) {
        initialize();
        try {
            List<String[]> allElements = csvReader.readAll();
            log.debug(allElements.stream().flatMap(Arrays::stream).collect(Collectors.toList()));
            for (int i = 0; i < allElements.size(); i++) {
                if (allElements.get(i)[0].equals(oldElement.getId())) {
                    allElements.set(i, newElement.toStringArray());
                    log.debug("update "+oldElement.getName());
                    break;
                }
            }
            csvWriter = new CSVWriter(new FileWriter(file));
            csvWriter.writeAll(allElements);
        }catch (IOException e){
            log.error(e);
        }
        close();
    }

    @Override
    public T selectByID(String id) {
        initialize();
        Iterator iterator = csvReader.iterator();
        String[] data;
        Entity element;
        if (!iterator.hasNext())return null;
        while (iterator.hasNext()){
            data = (String[]) iterator.next();
            log.debug( id+ "\n" + data[0]);
            if (data[0].equals(id)){
                element = BeanFactory.getBean(cls, data);
                close();
                return (T) element;
            }
        }
        close();
        return null;
    }

    @Override
    public List<T> selectByName(String name) {
        log.debug("select by name "+name);
        initialize();
        List<T> entities = new LinkedList<>();
        Iterator iterator = csvReader.iterator();
        while (iterator.hasNext()){
            String[] data = (String[]) iterator.next();

            if (data[1].equals(name)){
                T entity =(T) BeanFactory.getBean(cls, data);
                entities.add(entity);
            }
        }
        close();
        return entities;
    }

    public List<T> readAll(){
        initialize();
        List<T> entities = new LinkedList<>();
        Iterator iterator = csvReader.iterator();
        if (iterator.hasNext())iterator.next();
        while (iterator.hasNext()){
            String[] data = (String[]) iterator.next();
            T entity = (T) BeanFactory.getBean(cls, data);
            entities.add(entity);
        }
        close();
        return entities;
    }

    public void close() {
        try{
            csvWriter.close();
            csvReader.close();
        }catch (IOException e){
            log.error(e);
        }
    }


}
