package com.example.e_mart;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private String  checkString = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText etname;
    private EditText etemail;
    private EditText etpassword;
    private EditText etpassword2;
    private ProgressBar progressBar;
    private Button btnsignup;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);
        alreadyHaveAnAccount = view.findViewById(R.id.link_login);
        etname      =view.findViewById(R.id.input_name);
        etemail     =view.findViewById(R.id.input_email);
        etpassword  =view.findViewById(R.id.input_password);
        etpassword2 =view.findViewById(R.id.input_password2);
        btnsignup   =view.findViewById(R.id.btn_signup);
        progressBar =view.findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etpassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();

            }
        });
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void checkInputs() {
        if (!TextUtils.isEmpty(etemail.getText())) {
            if (!TextUtils.isEmpty(etname.getText())){

                if (!TextUtils.isEmpty(etpassword.getText()) && etpassword.length() >=6){
                    if (!TextUtils.isEmpty(etpassword2.getText())){

                        btnsignup.setEnabled(true);
                        btnsignup.setTextColor(getResources().getColor(R.color.white));
                    }else{
                        btnsignup.setEnabled(false);
                        btnsignup.setTextColor(Color.argb(50,255,255,255));
                    }

                    }
                else{
                    btnsignup.setEnabled(false);
                    btnsignup.setTextColor(Color.argb(50,255,255,255));
                }

                }

            else{
                btnsignup.setEnabled(false);
                btnsignup.setTextColor(Color.argb(50,255,255,255));
            }

            }
         else {
            btnsignup.setEnabled(false);
            btnsignup.setTextColor(Color.argb(50,255,255,255));
        }
    }
    private void checkEmailAndPassword(){
        if(etemail.getText().toString().matches(checkString)){
            if(etpassword.getText().toString().equals(etpassword2.getText().toString())){
                progressBar.setVisibility(View.VISIBLE);
                btnsignup.setEnabled(false);
                btnsignup.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.createUserWithEmailAndPassword(etemail.getText().toString(),etpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Map<Object,String> userData = new HashMap<>();
                            userData.put("FullName", etname.getText().toString() );
                            firebaseFirestore.collection("USERS")
                                    .add(userData)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {

                                            if(task.isSuccessful()){

                                                Intent intent = new Intent(getActivity() , home.class);
                                                startActivity(intent );
                                                getActivity().finish();
                                            }else{
                                                progressBar.setVisibility(View.INVISIBLE);
                                                btnsignup.setEnabled(true);
                                                btnsignup.setTextColor(getResources().getColor(R.color.white));
                                                String error = task.getException().getMessage();
                                                Toast.makeText(getActivity(), error , Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                            Intent intent = new Intent(getActivity() , home.class);
                            startActivity(intent );
                            getActivity().finish();

                        }else{
                            progressBar.setVisibility(View.INVISIBLE);
                            btnsignup.setEnabled(true);
                            btnsignup.setTextColor(getResources().getColor(R.color.white));
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(), error , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                etpassword2.setError("Password doesn't macthed!");

            }
        }else{
            etemail.setError("Invalid Email!");

        }

    }

}
