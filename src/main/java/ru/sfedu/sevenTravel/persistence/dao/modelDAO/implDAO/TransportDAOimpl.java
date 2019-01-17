package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import ru.sfedu.sevenTravel.model.Transport;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAOImpl;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.TransportDAO;

public class TransportDAOimpl extends GenericDAOImpl<Transport, Long> implements TransportDAO {
    protected TransportDAOimpl() {
        super(Transport.class);
    }
}
