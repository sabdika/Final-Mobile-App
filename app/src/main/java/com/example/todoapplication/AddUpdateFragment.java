package com.example.todoapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class  AddUpdateFragment extends Fragment{
    private EditText editTextTitle;
    private  EditText editTextDescription;
    private Button cancel;
    private Button addButton;
    RadioGroup priorityRadioGroup;
    RadioButton selectedPriority;
    private fragAddUpdateListener listener;

    public AddUpdateFragment(){

    }
    public interface fragAddUpdateListener{
        void onInputSend(int id, String title, String description, String priority);
    }
    public static AddUpdateFragment newInstance() {
        return new AddUpdateFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_update_fragment,container,false);
        editTextTitle = v.findViewById(R.id.etNewTask);
        editTextDescription = v.findViewById(R.id.etDisplayDescription);
        priorityRadioGroup = v.findViewById(R.id.radioGroup1);
        selectedPriority = (RadioButton) v.findViewById(R.id.radio0);
        addButton = v.findViewById(R.id.addBtn);
        priorityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedPriority = (RadioButton) v.findViewById(checkedId);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        if(getArguments() != null){
            String title = getArguments().getString("title");
            String description = getArguments().getString("description");
            String priority = getArguments().getString("priority");
            editTextTitle.setText(title);
            editTextDescription.setText(description);
        }
        cancel = v.findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).cancelAddUpdate();
            }
        });
        return v;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof fragAddUpdateListener){
            listener = (fragAddUpdateListener) context;
        }
        else{
            throw new RuntimeException(context.toString()
            +" must implement fragment listener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.delete_all_tasks).setVisible(false);
        inflater.inflate(R.menu.add_task_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_task:
                saveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void saveTask(){
        int id = -1;
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String priority = selectedPriority.getText().toString();
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(getActivity(),"Please fill title and description",Toast.LENGTH_SHORT).show();
            return;
        }
        if(getArguments() != null){
            id = getArguments().getInt("id");
        }
        listener.onInputSend(id,title,description,priority);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}