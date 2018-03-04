package com.example.ralha.velvetapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.webkit.WebView;
import android.webkit.WebView;

import android.webkit.WebView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister;
    private EditText edittextEmail;
    private EditText edittextPassword;
    private TextView textviewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
   // private EditText Username;
    //private EditText editTextAddress;
  //  private Phone PhoneNum;

    private ImageButton mSelectImage1;
    private ImageButton mSelectImage2;
    private ImageButton mSelectImage3;
    private ImageButton mSelectImage4;
    private ImageButton mSelectImage5;

    private StorageReference mStorage1;
    private StorageReference mStorage2;
    private StorageReference mStorage3;
    private StorageReference mStorage4;
    private StorageReference mStorage5;
    private static final int GALLARY_INTENT1 = 2;
    private static final int GALLARY_INTENT2 = 4;
    private static final int GALLARY_INTENT3 = 6;
    private static final int GALLARY_INTENT4 = 3;
    private static final int GALLARY_INTENT5 = 5;



    private Button submitApplicationButton;
private EditText itemStory;
private EditText itemYear;
private EditText itemBrand;
private EditText itemTitle;
private EditText itemRetailPrice;
private EditText itemRentalPrice;
private Spinner categoryList;
private Spinner colorList;
private Spinner sizeList;

DatabaseReference databaseItems;






    @SuppressLint("WrongViewCast")
    @Override

    //on create
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = firebaseAuth.getInstance(); //****************
        databaseItems= FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser()!= null) {// profile activity here  9 min


            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        }
        progressDialog= new ProgressDialog(this);
        buttonRegister=(Button)findViewById(R.id.buttonRigester);
        edittextEmail=(EditText)findViewById(R.id.editTextEmail);
        edittextPassword=(EditText)findViewById(R.id.editTextPassword);
       // editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        //
      //  Username= (EditText)findViewById(R.id.editTextName);
        //
       // PhoneNum = (EditText)findViewById(R.id.editPhone);
      //  PhoneNum.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        textviewSignin =(TextView)findViewById(R.id.textViewSingIn);

        buttonRegister.setOnClickListener(this);
        textviewSignin.setOnClickListener(this);

        //LendingApplication
        mStorage1= FirebaseStorage.getInstance().getReference();
        mStorage2= FirebaseStorage.getInstance().getReference();
        mStorage3= FirebaseStorage.getInstance().getReference();
        mStorage4= FirebaseStorage.getInstance().getReference();
        mStorage5= FirebaseStorage.getInstance().getReference();

        mSelectImage1 = (ImageButton) findViewById(R.id.photo1);
        mSelectImage2 = (ImageButton) findViewById(R.id.photo2);
        mSelectImage3 = (ImageButton) findViewById(R.id.photo3);
        mSelectImage4 = (ImageButton) findViewById(R.id.photo4);
        mSelectImage5 = (ImageButton) findViewById(R.id.photo5);

        itemStory= (EditText) findViewById(R.id.storyText);
        itemBrand= (EditText) findViewById(R.id.itemBrand);
        itemTitle= (EditText) findViewById(R.id.itemTitle);
        itemRetailPrice= (EditText) findViewById(R.id.itemRetailPrice);
        itemRentalPrice= (EditText) findViewById(R.id.itemRentalPrice);
        itemYear= (EditText) findViewById(R.id.itemYear);
        categoryList= (Spinner) findViewById(R.id.category);
        colorList= (Spinner) findViewById(R.id.color);
        sizeList= (Spinner) findViewById(R.id.size);
        submitApplicationButton = (Button) findViewById(R.id.submitApp);
/*
//IMAGE # 1
mSelectImage1.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         Intent intent = new Intent(Intent.ACTION_PICK);
                                         intent.setType("image/*");
                                         startActivityForResult(intent, GALLARY_INTENT1);
                                     }
                                 });
//IMAGE # 2
        mSelectImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLARY_INTENT2);
            }
        });



        //IMAGE # 3
        mSelectImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLARY_INTENT3);
            }
        });


        //IMAGE # 4
        mSelectImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLARY_INTENT4);
            }
        });


        //IMAGE # 5
        mSelectImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLARY_INTENT5);
            }
        });




        submitApplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItem();

            }
        });*/


    }

    private void AddItem(){
      String story = itemStory.getText().toString().trim();
      String brand = itemBrand.getText().toString().trim();
        String title = itemTitle.getText().toString().trim();
        String retailPrice = itemRetailPrice.getText().toString().trim();
        String rentalPrice = itemRentalPrice.getText().toString().trim();
        String year = itemYear.getText().toString().trim();
        String category = categoryList.getSelectedItem().toString();
        String color = colorList.getSelectedItem().toString();
        String size = sizeList.getSelectedItem().toString();

        if(!TextUtils.isEmpty(story)){
            String id=  databaseItems.push().getKey();
            Items item = new Items (story, brand, title, year, retailPrice, rentalPrice, category, color, size);
            databaseItems.child(id).setValue(item);

            Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "You should enter a story", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(brand)){

        }else{
            Toast.makeText(this, "You should enter a brand name", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(title)){

        }else{
            Toast.makeText(this, "You should enter a title", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(retailPrice)){

        }else{
            Toast.makeText(this, "You should enter retail price", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(rentalPrice)){

        }else{
            Toast.makeText(this, "You should enter rental price", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(year)){

        }else{
            Toast.makeText(this, "You should enter a year of purchase", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(category)){

        }else{
            Toast.makeText(this, "You should enter a category", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(size)){

        }else{
            Toast.makeText(this, "You should enter the size", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(color)){

        }else{
            Toast.makeText(this, "You should enter the color", Toast.LENGTH_SHORT).show();
        }


    }
    // Register Method
private void  registerUser(){
        String email = edittextEmail.getText().toString().trim();
        String password =  edittextPassword.getText().toString().trim();
    // email is empty

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"PLEASE ENTER EMAIL",Toast.LENGTH_SHORT).show();
            //stopping
            return;
        }
        //password is
            if(TextUtils.isEmpty(password))
            { Toast.makeText(this,"PLEASE ENTER A PASSWORD",Toast.LENGTH_SHORT).show();
            return;}
            //if validation OK
  //  progressDialog.setMessage("register user...");
    //progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {



            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {//display msg if its succ
                   Toast.makeText(MainActivity.this, "Creating user please wait", Toast.LENGTH_SHORT).show();
                      // sendEmailVerification();
                        finish();
                      startActivity(new Intent(getApplicationContext(), LogInActivity.class));

                } else {
                    Toast.makeText(MainActivity.this, " This Email is already registered ", Toast.LENGTH_SHORT).show();
                }// should not be already registered
            }

        } ); // compleate information
}// End of Register Method
/*
    private void sendEmailVerification() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user !=null){
   user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
       @Override
       public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful()){
               Toast.makeText(MainActivity.this, "Check your Email for Verifiaction", Toast.LENGTH_SHORT).show();
FirebaseAuth.getInstance().signOut();
           }
       }
       });
   }

     }*/


    @Override
    public void onClick (View view) {
     if(view==buttonRegister)
     {
         registerUser();
     }
     if (view == textviewSignin)
     {
         // open log in

         startActivity(new Intent(this, LogInActivity.class));

     }
    }

}
