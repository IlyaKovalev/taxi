package ru.sfedu.sevenTravel.utils;

public enum Schema {

    USER_TABLE("User", new String[]{"id", "fullname", "phonenumber" ,"password" ,"numberOfTravels" ,"status"}),
    DRIVER_TABLE("Driver", new String[]{"id", "name", "passportNumber", "experience", "age", "rating"}),
    CAR_TABLE("Car", new String[]{"id", "name","price", "info", "numberOfSeats", "carNumber", "drive"}),
    HELICOPTER_TABLE("Helicopter", new String[]{"id", "name", "helicopterNumber", "price", "info",
            "numberOfSeats","length","width","maxSpeed","numberOfSeats"});


    private final String table;
    private final String[] columns;

    Schema(String table ,String[] columns){
        this.table = table;
        this.columns = columns;
    }

    public String getTable(){
        return table;
    }

    public String[] getColumns(){
        return columns;
    }

}
