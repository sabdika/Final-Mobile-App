package com.example.todoapplication.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;

    private String description;

    private String priority = "High";

    @ColumnInfo(name = "created_date")
    private Date createdDate;

    public Task(String title, String description, String priority, Date createdDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createdDate = createdDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }
}
