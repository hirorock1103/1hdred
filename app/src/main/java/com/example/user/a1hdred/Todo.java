package com.example.user.a1hdred;

public class Todo {

    private int id;
    private String title;
    private String freespace;
    private int endCont;
    private int count;
    private int category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFreespace() {
        return freespace;
    }

    public void setFreespace(String freespace) {
        this.freespace = freespace;
    }

    public int getEndCont() {
        return endCont;
    }

    public void setEndCont(int endCont) {
        this.endCont = endCont;
    }
}
