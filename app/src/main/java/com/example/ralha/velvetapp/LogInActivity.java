package com.example.ralha.velvetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
private Button buttonSignIn;
private EditText editTextEmail;
private EditText editTextPassword;
private TextView textViewSignUp;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null)
        {// profile activity here  9 min

            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));


        }//close if
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonLogIn);

        progressDialog= new ProgressDialog(this);
        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }
/*
        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (!isValidEmail(email)) {
                    editTextEmail.setError("Invalid Email");

                }
                if (!isValidPassword(password)) {
                    editTextPassword.setError("Invalid Password");

                }
            }});  } */


    // login Method
    private void  UserLogIn(){
        String email = editTextEmail.getText().toString().trim();
        String password =  editTextPassword.getText().toString().trim();


        // email is empty
        if (TextUtils.isEmpty(email))
        {
            editTextEmail.setError("please enter Email");
            //stopping
            return;
        }

        ////
        //password is
        if(TextUtils.isEmpty(password))
        { editTextPassword.setError("Please enter Password");
            editTextEmail.requestFocus();
            return;}

        if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid Email");
            editTextEmail.requestFocus();
            return;
        }
        if (!isValidPassword(password)) {
            editTextPassword.setError("Invalid Password, must be more than 6 digits");
            editTextEmail.requestFocus();
            return;
        }

        //if validation OK
        progressDialog.setMessage("Log in ..");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(getApplicationContext(), HomePage.class));

                            //start profile activity
                            // finish();
                            // startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                        }//close if
                        else        {
                            //   Toast.makeText(this,"please check your information", Toast.LENGTH_SHORT).show();
                            Toast.makeText(LogInActivity.this, "please check your information, this account does not exist", Toast.LENGTH_SHORT).show();


                        }

                    }// close onComplete

                }); // compleate information
    }// End of login Method

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View view) {
        if(view == buttonSignIn)
        { UserLogIn();}
        if (view == textViewSignUp)
        {finish();
            startActivity(new Intent(this, MainActivity.class));}

    }
}
