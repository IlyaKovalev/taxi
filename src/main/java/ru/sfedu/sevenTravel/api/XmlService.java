package ru.sfedu.sevenTravel.api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.sevenTravel.beans.*;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class XmlService<T extends Entity> implements DataProvider<T> {

    private File file;
    private Serializer serializer;

    private static Logger logger = LogManager.getLogger(XmlService.class);

    public XmlService(Path path){
        serializer = new Persister();
        logger.debug(path);
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
                logger.info("DIR "+theDir.getName()+" created");
            }
        }
        file = new File(String.valueOf(path));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(T entity) {
        XmlList xmlList = read();
        List<T> entities = xmlList.getEntities();
        entities.add(entity);
        write(xmlList);
        return true;
    }

    @Override
    public void remove(T entity) {
        List<T> entities = read().getEntities();
        entities.remove(entity);
        write(new XmlList<T>(entities));
    }

    @Override
    public void update(T oldEntity, T newEntity) {
        List<T> list = read().getEntities();
        write(new XmlList<T>(list.stream().map(entity ->{
            if (entity.getId().equals(oldEntity.getId())){
                return newEntity;
            }
            return entity;
        }).collect(toList())));

    }

    @Override
    public T selectByID(String id) {
        List<T> list = read().getEntities();
        return list.stream()
                .filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<T> selectByName(String name) {
        List<T> list = read().getEntities();
        return list.stream().filter(entity-> entity.getName().equals(name)).collect(toList());
    }

    public XmlList read(){
        List<T> list = new LinkedList<>();
        XmlList<T> xmlList = new XmlList<>(list);
        try {
            xmlList = serializer.read(XmlList.class, file);
            logger.debug(xmlList.getEntities());
        }catch (XMLStreamException xmlEx){
            logger.debug("Filling file");
            return xmlList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlList;
    }

    public List<T> readAll(){
       XmlList xmlList = read();
       return xmlList.getEntities();
    }

    private void write(XmlList xmlList){
        try {
            serializer.write(xmlList, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Root
    public static class XmlList<T> {

        @ElementList(name = "List")
        private List<T> entities;

        public XmlList(@ElementList(name = "List") List<T> list){
            this.entities = list;
        }

        public List<T> getEntities() {
            return entities;
        }

        public void setEntities(List<T> entities) {
            this.entities = entities;
        }
    }
    
}
