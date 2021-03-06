package ru.sfedu.sevenTravel.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Entity {

    public long getId();

    public void setId(long id);

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
