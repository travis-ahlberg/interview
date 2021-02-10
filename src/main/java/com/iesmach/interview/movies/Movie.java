package com.iesmach.interview.movies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.YearMonth;

@Entity
public class Movie {

    @Id
    private String title;
    @Column
    private int year;
    @Column
    private int month;

    public Movie() {
    }

    public Movie(String title, YearMonth released) {
        this.title = title;
        this.year = released.getYear();
        this.month = released.getMonthValue();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public YearMonth getYearMonth() {
        return YearMonth.of(year, month);
    }
}
