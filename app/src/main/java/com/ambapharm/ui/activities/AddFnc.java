package com.ambapharm.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


import com.ambapharm.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddFnc extends AppCompatActivity {

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfnc);

    textInputLayout=findViewById(R.id.menu);
    autoCompleteTextView=findViewById(R.id.auto);
    Button button = findViewById(R.id.nextIssue);

    String[] items = getResources().getStringArray(R.array.planets_array);
    ArrayAdapter<String> itemAdapter= new ArrayAdapter<>(AddFnc.this,R.layout.item_list_number,items);
    autoCompleteTextView.setAdapter(itemAdapter);


        button.setOnClickListener(v -> {
            Intent intent = new Intent(AddFnc.this, AddIssue.class);
            startActivity(intent);
        });

    }


}