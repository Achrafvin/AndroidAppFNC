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

import com.ambapharm.ui.interfaces.CardActionListener;
import com.ambapharm.DummyData;
import com.ambapharm.data.models.IssueCard;
import com.ambapharm.ui.adapters.IssueCardAdapter;
import com.ambapharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AddCmt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        RecyclerView recyclerView = findViewById(R.id.cardView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<IssueCard> dummyData = DummyData.generateDummyData();
        final IssueCardAdapter adapter = new IssueCardAdapter(dummyData, null);

        adapter.setActionListener(new CardActionListener() {
            @Override
            public void onEditClick(int position) {
                Toast.makeText(AddCmt.this, "Edit clicked for card at position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                dummyData.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });


        recyclerView.setAdapter(adapter);

        FloatingActionButton fabMenu = findViewById(R.id.fab_menu);
        FloatingActionButton fabPj = findViewById(R.id.fab_pj);
        FloatingActionButton fabPh = findViewById(R.id.fab_ph);
        FloatingActionButton fabCam = findViewById(R.id.fab_cam);
        FloatingActionButton fabLr = findViewById(R.id.fab_lr);

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
                // Show the FABs and TextViews with slide-in animation
                fabPj.setVisibility(View.VISIBLE);
                fabPh.setVisibility(View.VISIBLE);
                fabCam.setVisibility(View.VISIBLE);
                fabLr.setVisibility(View.VISIBLE);

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
                // Hide the FABs and TextViews with slide-out animation
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


        CardView noFncCard = findViewById(R.id.fnc_card_empty);
        FrameLayout fncContentContainer = findViewById(R.id.fncContentContainer);

        if (fncDataAvailable()) {
            noFncCard.setVisibility(View.GONE); // Hide the "No FNC available" card
            fncContentContainer.setVisibility(View.VISIBLE); // Show your dynamic FNC content
            // Load your FNC data into the fncContentContainer views
        } else {
            noFncCard.setVisibility(View.VISIBLE); // Show the "No FNC available" card
            fncContentContainer.setVisibility(View.GONE); // Hide your dynamic FNC content
        }
    }

    private boolean fncDataAvailable() {
        return true;
    }

}