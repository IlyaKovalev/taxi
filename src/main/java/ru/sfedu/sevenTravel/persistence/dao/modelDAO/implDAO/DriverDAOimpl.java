package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import ru.sfedu.sevenTravel.model.Driver;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAOImpl;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.DriverDAO;

public class DriverDAOimpl extends GenericDAOImpl<Driver, Long> implements DriverDAO{

    public DriverDAOimpl() {
        super(Driver.class);
    }
}
