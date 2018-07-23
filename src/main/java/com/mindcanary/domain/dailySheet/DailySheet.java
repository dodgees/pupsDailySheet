package com.mindcanary.domain.dailySheet;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DailySheetAb")
public class DailySheet  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date date;

    public DailySheet() {
    }

    public DailySheet(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public DailySheet(Long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DailySheet{" +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
