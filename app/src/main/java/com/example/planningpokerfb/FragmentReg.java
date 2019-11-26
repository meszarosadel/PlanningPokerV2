package com.example.planningpokerfb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.planningpokerfb.Models.UserRole;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentReg extends Fragment {


    EditText et_email, et_pwd;
    Button btn_reg;
    TextView tv_sign;
    FirebaseAuth mFirebaseAuth;
    private static final String TAG = FragmentReg.class.getSimpleName();
    private DatabaseReference databaseRole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_reg, container, false);
        // Inflate the layout for this fragment
        et_email = v.findViewById(R.id.et_email);
        et_pwd = v.findViewById(R.id.et_pwd);
        btn_reg = v.findViewById(R.id.btn_login);
        tv_sign = v.findViewById(R.id.tv_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = et_email.getText().toString();
                String pwd = et_pwd.getText().toString();
                if(email.isEmpty()){
                    et_email.setError("Please enter email id");
                    et_email.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    et_pwd.setError("Please enter your password");
                    et_pwd.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(getActivity(),"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth = FirebaseAuth.getInstance();
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getActivity(),"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "SignUp Successful!", Toast.LENGTH_SHORT).show();
                                Fragment fragment = new FragmentUser();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_id, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                            if(task.isSuccessful()){
                                //Toast.makeText(getActivity(),"Successful SignUp",Toast.LENGTH_SHORT).show();
                                databaseRole= FirebaseDatabase.getInstance().getReference("UserRoles");
                                String userRole = "USER";
                                addUserRole(email,userRole);
                            }
                        }
                    });

                }


            }
        });

        tv_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentLogin();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_id, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    public void addUserRole(String email, String role){

        String id = mFirebaseAuth.getUid();
        UserRole userRole = new UserRole(id, email, role);
        databaseRole.child(id).setValue(userRole);

    }

}
