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


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private String checkString = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private EditText emailSignin;
    private EditText passwordSignin;
    private Button btnSignin;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    public SignInFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        dontHaveAnAccount = view.findViewById(R.id.link_signup);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);

        emailSignin = view.findViewById(R.id.input_email);
        passwordSignin = view.findViewById(R.id.input_password_signin);
        btnSignin = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.progressBar_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        emailSignin.addTextChangedListener(new TextWatcher() {
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
        passwordSignin.addTextChangedListener(new TextWatcher() {
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
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(emailSignin.getText())) {
            if (!TextUtils.isEmpty(passwordSignin.getText())) {

                btnSignin.setEnabled(true);
                btnSignin.setTextColor(getResources().getColor(R.color.white));
            } else {
                btnSignin.setEnabled(false);
                btnSignin.setTextColor(Color.argb(50, 255, 255, 255));

            }
        } else {
            btnSignin.setEnabled(false);
            btnSignin.setTextColor(Color.argb(50, 255, 255, 255));


        }
    }

    private void checkEmailAndPassword() {
        if ((emailSignin.getText().toString().matches(checkString))) {
            if (passwordSignin.length() >= 8) {

                progressBar.setVisibility(View.INVISIBLE);
                btnSignin.setEnabled(false);
                btnSignin.setTextColor(Color.argb(50, 255, 255, 255));
                firebaseAuth.signInWithEmailAndPassword(emailSignin.getText().toString(), passwordSignin.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getActivity(), home.class);
                                    startActivity(intent);
                                    getActivity().finish();

                                } else {
                                    progressBar.setVisibility(View.VISIBLE);
                                    btnSignin.setEnabled(false);
                                    btnSignin.setTextColor(Color.argb(50, 255, 255, 255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else {
                Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();


        }
    }
}
