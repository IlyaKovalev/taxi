package ru.sfedu.sevenTravel.model;

public enum Status {
    VIP, STANDART, BAD_REPUTATION;

    public static Status getStatus(String status){
        switch (status){
            case "VIP":
                return VIP;

            case "STANDART":
                return STANDART;

            case "BAD_REPUTATION":
                return BAD_REPUTATION;
        }
        return null;
    }
}
