package com.example.todoapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.todoapplication.R;
import com.example.todoapplication.adapter.ToDoAdapter;
import com.example.todoapplication.interfaceHelper.ToDoListner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;

    ToDoAdapter toDoAdapter;
    ArrayList<String> textStrig = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toDoAdapter = new ToDoAdapter(new ToDoListner() {


            @Override
            public void textOnClick(View view, int adapterPosition) {

                showConfirmDialog();
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void deleteIconClick(View view, int adapterPosition) {

            }

            @Override
            public void editOnClick(View view, int adapterPosition) {

            }}, textStrig, this);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(toDoAdapter);

        setTextOnList();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }




    public void showConfirmDialog(){

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

/*                 Specifying a listener allows you to take an action before dismissing the dialog.
                 The dialog is automatically dismissed when a dialog button is clicked.*/
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       showDialog();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void showDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Add your todo")
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .show();
    }

    public  void  setTextOnList(){
//      String newValue =  textFlied.getText();
        textStrig.add("Hello");
        textStrig.add("helllo");
        textStrig.add("helllo");
        textStrig.add("helllo");
        toDoAdapter.notifyDataSetChanged(); //refresh recyclerview
    }

   /*

   --> fragment , recycler (to overlap the parent view or render our another view)

   step - create a recycler view

   step - create item layout view for recycler view (list view


   step - create adapter for recycler view





   * */

}
