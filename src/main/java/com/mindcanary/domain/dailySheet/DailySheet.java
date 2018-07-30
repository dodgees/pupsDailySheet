package com.mindcanary.domain.dailySheet;

import com.mindcanary.domain.activities.Activity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "dailysheet")
public class DailySheet  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private ArrayList<Activity> activities;
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

    public Long getId() {
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

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public boolean addActivity(Activity activity){
        return this.activities.add(activity);
    }

    public void removeActivity(Activity activity){
        this.activities.remove(activity);
    }

    @Override
    public String toString() {
        return "DailySheet{" +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
