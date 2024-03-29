package com.example.planningpokerfb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.planningpokerfb.Models.Tasks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class FragmentAddNewTask extends Fragment {

    TextView tv_add_task;
    EditText et_add_task;
    Button btn_add_task;
    private DatabaseReference mDatabase;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        final String groupId = getArguments().getString("groupId");

        tv_add_task = view.findViewById(R.id.tv_add_task);
        et_add_task = view.findViewById(R.id.et_add_task);
        btn_add_task = view.findViewById(R.id.btn_add_task);

        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = et_add_task.getText().toString();
                if (taskName.isEmpty()) {
                    et_add_task.setError("Please enter a task!");
                    et_add_task.requestFocus();
                } else if (!(taskName.isEmpty())) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Tasks task = new Tasks(UUID.randomUUID().toString(),groupId,taskName,false);
                    mDatabase.child("Tasks").child(task.getQuestionId()).setValue(task);
                    Fragment fragment = new FragmentAdminTaskList();
                    Bundle args = new Bundle();
                    args.putString("groupId", groupId);
                    fragment.setArguments(args);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_id, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }


}
