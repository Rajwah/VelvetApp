package com.example.ralha.velvetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class filter extends AppCompatActivity {
CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
Button clearbut, applybut;
    /**
     *
     */
     RadioGroup radiog1,radiog2,radiog3,radiog4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        RadioGroup gender = (RadioGroup) findViewById(R.id.radio_group1);
        final String value =((RadioButton)findViewById(gender.getCheckedRadioButtonId()))
                        .getText().toString();

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });

        ch1=(CheckBox)findViewById(R.id.brand1);
        ch2=(CheckBox)findViewById(R.id.brand2);
        ch3=(CheckBox)findViewById(R.id.brand3);
        ch4=(CheckBox)findViewById(R.id.brand4);
        ch5=(CheckBox)findViewById(R.id.brand5);
        ch6=(CheckBox)findViewById(R.id.brand6);
        ch7=(CheckBox)findViewById(R.id.brand7);
        clearbut=(Button)findViewById(R.id.clear);


        radiog1 = (RadioGroup) findViewById(R.id.radio_group1);
        radiog2 = (RadioGroup) findViewById(R.id.radio_group2);
        radiog3 = (RadioGroup) findViewById(R.id.radio_group3);
        radiog4 = (RadioGroup) findViewById(R.id.radio_group4);

      //  Button clearButton = (Button) findViewById(R.id.clear);
        Button applybut = (Button) findViewById(R.id.apply);
applybut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // startActivity(new Intent(this, MainActivity.class));

    }
});


clearbut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        radiog1.clearCheck();
        radiog2.clearCheck();
        radiog3.clearCheck();
        radiog4.clearCheck();

        //cb1.setChecked(false);
        if(ch1.isChecked()){
            ch1.toggle();
        }

        if(ch2.isChecked()){
            ch2.toggle();
        }

        if(ch3.isChecked()){
            ch3.toggle();
        }
        if(ch4.isChecked()){
            ch4.toggle();
        }
        if(ch5.isChecked()){
            ch5.toggle();
        }
        if(ch6.isChecked()){
            ch6.toggle();
        }
        if(ch7.isChecked()){
            ch7.toggle();
        }    }
});


    }//oncreate
}
