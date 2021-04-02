package com.example.todoapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> tasks = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item_layout,parent,false);

        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewTitle.setText(currentTask.getTitle());
        holder.textViewDescription.setText(currentTask.getDescription());
        holder.textViewPriority.setText(currentTask.getPriority());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }
    public Task getTaskAt(int position){
        return tasks.get(position);
    }
    class TaskHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvItem);
            textViewDescription= itemView.findViewById(R.id.tvDueDate);
            textViewPriority = itemView.findViewById(R.id.tvItemPriority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tasks.get(position));
                    }
                }
            });
        }
    }
    public  interface OnItemClickListener{
        void onItemClick(Task task);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }
}
