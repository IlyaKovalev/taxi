package ru.sfedu.sevenTravel.beans;

import org.simpleframework.xml.Element;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public interface Entity {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String[] toStringArray();

    default public List<String> getFields(){
        return Arrays.stream(this.getClass().getFields())
                .peek(field -> field.setAccessible(true))
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
