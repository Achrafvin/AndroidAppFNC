package com.ambapharm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.appbar.MaterialToolbar;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        CardView addFnc = findViewById(R.id.addNfc);
        CardView updateFnc = findViewById(R.id.updateNfc);

        addFnc.setOnClickListener(view -> {
            Intent addIntent = new Intent(Dashboard.this, AddFnc.class);
            startActivity(addIntent);
        });

        updateFnc.setOnClickListener(view -> {
            Intent intent = new Intent(Dashboard.this, UpdateFnc.class);
            startActivity(intent);
        });
    }
}
