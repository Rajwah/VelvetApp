package com.example.ralha.velvetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView TextviewUserEmail;
    private Button buttonLogout;
    private DatabaseReference databaserefernece;
    private EditText editTextName, editTextAddress, EditTextCCN, EditTextPhoneNumber, EditTextNameOnCard, EditTextEndDate, EditTextCCV;
    private Button ButtonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LogInActivity.class));

        }
        databaserefernece = FirebaseDatabase.getInstance().getReference();
        TextviewUserEmail =  findViewById(R.id.textViewUserEmail);
        editTextName =  findViewById(R.id.editTextName);
        editTextAddress =  findViewById(R.id.editTextAddress);
        EditTextCCN =  findViewById(R.id.EditTextCCN);
        EditTextCCV = findViewById(R.id.EditTextCCV);
        EditTextNameOnCard = findViewById(R.id.EditTextNameOnCard);
        EditTextEndDate =  findViewById(R.id.EditTextEndDate);
        EditTextPhoneNumber =  findViewById(R.id.editTextPhoneNumber);
        ButtonSave = findViewById(R.id.ButtonSave);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        TextviewUserEmail.setText("Welcome  " + user.getEmail().split("@")[0]);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
        ButtonSave.setOnClickListener(this);
    }

    private void saveUserInformation() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String CCN = EditTextCCN.getText().toString().trim();
        String CCV = EditTextCCV.getText().toString().trim();
        String phone = EditTextPhoneNumber.getText().toString().trim();
        String nameOnCard = EditTextNameOnCard.getText().toString().trim();
        String EndDate = EditTextEndDate.getText().toString().trim();

        if (!isValidPhone(phone)) {
            EditTextPhoneNumber.setError("Invalid phone number!");
            EditTextPhoneNumber.requestFocus();
            return;
        }

        if (!isValidCCV(CCV)) {
            EditTextCCV.setError("Invalid CCV");
            EditTextCCV.requestFocus();
            return;
        }
        if (!isValidDate(EndDate)) {
            EditTextEndDate.setError("Invalid date format");
            EditTextEndDate.requestFocus();
            return;
        }


        if (!isValidCCN(EditTextCCN))

        {
            EditTextCCN.setError("Invalid CCN!");
            EditTextCCN.requestFocus();
            return;
        }

        UserInformation userInformation = new UserInformation(name, address, CCN, CCV, nameOnCard, EndDate, phone);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaserefernece.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Saving ..", Toast.LENGTH_SHORT).show();



    }
    private boolean isValidCCV(String ccv) {
        if ((ccv != null) && (ccv.length() == 3))
            return true;
        else
            return false;

    }
    private boolean isValidPhone(String phone) {

        String PHONE_PATTERN = "(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private boolean isValidDate(String date){
        String datePatteren = "([0-9]{2})/([0-9]{2})";
        Pattern pattern = Pattern.compile(datePatteren);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();

    }

    boolean isValidCCN(EditText ccn) {

        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14})|" +
                "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
                "(?<amex>3[47][0-9]{13})|" +
                "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
                "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) ccn);
        return matcher.matches();

    }




    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LogInActivity.class));

        }
        if (view == ButtonSave) {
            saveUserInformation();
        }
    }
}
