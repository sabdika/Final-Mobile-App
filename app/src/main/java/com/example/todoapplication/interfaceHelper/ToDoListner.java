package com.example.todoapplication.interfaceHelper;

import android.view.View;

public interface ToDoListner {

    void textOnClick (View view, int adapterPosition);
    void deleteIconClick (View view, int adapterPosition);
    void editOnClick (View view, int adapterPosition);
}
