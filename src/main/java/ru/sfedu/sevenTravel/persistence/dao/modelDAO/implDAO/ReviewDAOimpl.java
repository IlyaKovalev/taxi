package ru.sfedu.sevenTravel.persistence.dao.modelDAO.implDAO;

import ru.sfedu.sevenTravel.model.Review;
import ru.sfedu.sevenTravel.persistence.dao.genericDAO.GenericDAOImpl;
import ru.sfedu.sevenTravel.persistence.dao.modelDAO.interfaceDAO.ReviewDAO;

public class ReviewDAOimpl extends GenericDAOImpl<Review, Long> implements ReviewDAO {
    protected ReviewDAOimpl() {
        super(Review.class);
    }
}
