package com.ambapharm.ui.activities;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.R;
import com.ambapharm.data.models.ConstantFncNum;
import com.ambapharm.data.models.ItemFactory;
import com.ambapharm.data.models.ListItem;
import com.ambapharm.ui.adapters.ItemAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddCmt extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        String value = getIntent().getStringExtra(ConstantFncNum.EXTRA_KEY);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.cardView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ListItem> items = generateDummyData(10);

        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabMenu = findViewById(R.id.fab_menu);
        FloatingActionButton fabPj = findViewById(R.id.fab_pj);
        FloatingActionButton fabPh = findViewById(R.id.fab_ph);
        FloatingActionButton fabCam = findViewById(R.id.fab_cam);
        FloatingActionButton fabLr = findViewById(R.id.fab_lr);

        View overlay = findViewById(R.id.overlay);



        TextView pjText = findViewById(R.id.pjText);
        TextView phText = findViewById(R.id.phText);
        TextView caText = findViewById(R.id.caText);
        TextView lrText = findViewById(R.id.lrText);

        Animation slideInTop = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        Animation slideOutTop = AnimationUtils.loadAnimation(this, R.anim.slide_out);

        ObjectAnimator rotateOpen = ObjectAnimator.ofFloat(fabMenu, "rotation", 0f, 45f);
        ObjectAnimator rotateClose = ObjectAnimator.ofFloat(fabMenu, "rotation", 45f, 0f);

        fabMenu.setOnClickListener(view -> {
            if (fabPj.getVisibility() == View.GONE) {
                fabPj.setVisibility(View.VISIBLE);
                fabPh.setVisibility(View.VISIBLE);
                fabCam.setVisibility(View.VISIBLE);
                fabLr.setVisibility(View.VISIBLE);
                overlay.setVisibility(View.VISIBLE);

                pjText.setVisibility(View.VISIBLE);
                phText.setVisibility(View.VISIBLE);
                caText.setVisibility(View.VISIBLE);
                lrText.setVisibility(View.VISIBLE);

                fabPj.startAnimation(slideInTop);
                fabPh.startAnimation(slideInTop);
                fabCam.startAnimation(slideInTop);
                fabLr.startAnimation(slideInTop);

                pjText.startAnimation(slideInTop);
                phText.startAnimation(slideInTop);
                caText.startAnimation(slideInTop);
                lrText.startAnimation(slideInTop);
                rotateOpen.start();
            } else {
                fabPj.startAnimation(slideOutTop);
                fabPh.startAnimation(slideOutTop);
                fabCam.startAnimation(slideOutTop);
                fabLr.startAnimation(slideOutTop);

                pjText.startAnimation(slideOutTop);
                phText.startAnimation(slideOutTop);
                caText.startAnimation(slideOutTop);
                lrText.startAnimation(slideOutTop);

                fabPj.setVisibility(View.GONE);
                fabPh.setVisibility(View.GONE);
                fabCam.setVisibility(View.GONE);
                fabLr.setVisibility(View.GONE);
                overlay.setVisibility(View.GONE);

                pjText.setVisibility(View.GONE);
                phText.setVisibility(View.GONE);
                caText.setVisibility(View.GONE);
                lrText.setVisibility(View.GONE);
                rotateClose.start();
            }
        });



        TextInputEditText textField = findViewById(R.id.cmtTitle);

        String valueFromRadioButton = getIntent().getStringExtra("selectedValue");
        if (valueFromRadioButton != null) {
            textField.setText(valueFromRadioButton);
        }


        if (value != null) {
            toolbar.setTitle(value);
        } else {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }


        CardView noFncCard = findViewById(R.id.fnc_card_empty);
        FrameLayout fncContentContainer = findViewById(R.id.fncContentContainer);

        if (fncDataAvailable()) {
            noFncCard.setVisibility(View.GONE);
            fncContentContainer.setVisibility(View.VISIBLE);
        } else {
            noFncCard.setVisibility(View.VISIBLE);
            fncContentContainer.setVisibility(View.GONE);
        }
    }

    private List<ListItem> generateDummyData(int count) {
        List<ListItem> items = new ArrayList<>();
        ItemFactory factory = new ItemFactory();

        for (int i = 1; i <= count; i++) {
            items.add(factory.createMedication("Medication " + i, "Problem Type " + i, "Lorem ipsum for medication " + i));
        }

        for (int i = 1; i <= count; i++) {
            items.add(factory.createDocument("Document " + i));
        }

        return items;
    }


    private boolean fncDataAvailable() {
        return false;
    }

}