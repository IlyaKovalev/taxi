package ru.sfedu.sevenTravel.beans;

import java.util.Date;

public class Review {

    private String id;
    private String review;
    private String title;
    private double assessment;
    private Date date;

    public Review(String id, String review, String title, double assessment) {
        this.id = id;
        this.review = review;
        this.title = title;
        this.assessment = assessment;
        this.date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAssessment() {
        return assessment;
    }

    public void setAssessment(double assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Review{" +
                ", title='" + title + '\'' +
                ", assessment=" + assessment +
                '}';
    }
}
