package com.ambapharm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ambapharm.R;

public class AddIssue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        Button next = findViewById(R.id.nextCmt);


        next.setOnClickListener(view -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                if (selectedRadioButton != null) {
                    String selectedValue = selectedRadioButton.getText().toString();
                    Intent intent = new Intent(AddIssue.this, AddCmt.class);
                    intent.putExtra("selectedValue", selectedValue);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddIssue.this, "Une erreur s'est produite!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddIssue.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
