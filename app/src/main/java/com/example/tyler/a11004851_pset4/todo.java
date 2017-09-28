package com.example.tyler.a11004851_pset4;

import java.io.Serializable;

/**
 * Created by tyler on 25-9-17.
 */

public class todo implements Serializable {
    private String todo;
    private int checked;
    private int id;

    public todo(String contactTodo, int todoChecked){
        todo = contactTodo;
        checked = todoChecked;
    }

    public todo(String contactTodo, int todoChecked, int contactID){
        todo = contactTodo;
        checked = todoChecked;
        id = contactID;
    }

    public int getChecked() {
        return checked;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String newTodo) {
        todo = newTodo;
    }

    public void setChecked(int newChecked) {
        checked = newChecked;
    }

    public int getID() {
        return id;
    }

    public void setID(int newID) {
        id = newID;
    }

    public String toString() {
        return todo;
    }
}
