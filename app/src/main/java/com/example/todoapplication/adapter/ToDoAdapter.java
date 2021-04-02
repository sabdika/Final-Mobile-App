package com.example.todoapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.interfaceHelper.ToDoListner;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.TodoViewHolder> {


    ToDoListner listner;
    ArrayList<String> textList;
    Context context;

    public ToDoAdapter(ToDoListner listner, ArrayList<String> text, Context context) {
        this.listner = listner;
        this.textList = text;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_layout,parent,false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.textView.setText(textList.get(position));
        holder.textView1.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {



//        CheckBox checkBox;

        TextView textView;
        TextView textView1;
        public TodoViewHolder(@NonNull View itemView) {

            super(itemView);
//            checkBox = itemView.findViewById(R.id.todoCheckbox);
            textView = itemView.findViewById(R.id.text_v);
            textView1 = itemView.findViewById(R.id.text_v1);

//           Button testButton =itemView.findViewById(R.id.todoCheckbox);

            textView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   listner.textOnClick(v,getAdapterPosition());
               }
           });
        }

    }
}
