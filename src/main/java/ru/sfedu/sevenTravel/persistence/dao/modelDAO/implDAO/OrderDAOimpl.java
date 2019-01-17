package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import ru.sfedu.sevenTravel.model.Order;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAOImpl;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.OrderDAO;

public class OrderDAOimpl extends GenericDAOImpl<Order, Long> implements OrderDAO {
    protected OrderDAOimpl() {
        super(Order.class);
    }
}
