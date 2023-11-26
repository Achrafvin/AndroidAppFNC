package com.ambapharm.ui.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityCommentBinding;
import com.ambapharm.helpers.ConstantFncNum;
import com.ambapharm.ui.viewModels.AddCommentViewModel;
import com.ambapharm.ui.adapters.GenericAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class AddCommentActivity extends BaseActivity implements GenericAdapter.OnItemClickListener {

    private ActivityCommentBinding binding;
    private AddCommentViewModel viewModel;
    private FloatingActionButton fabMenu, fabPj, fabPh, fabCam, fabLr;
    private View overlay;
    private TextInputEditText textField;
    private MaterialToolbar toolbar;
    private CardView noFncCard;
    private FrameLayout fncContentContainer;
    private String toolbarTitle, valueFromRadioButton;
    private static final float ROTATION_START = 0f;
    private static final float ROTATION_END = 45f;
    private TextView pjText, phText, caText, lrText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AddCommentViewModel.class);

        initializeViews();
        retrieveIntentData();
        configureToolbar();
        setupFabMenu();
        setupRecyclerView();
        populateTextField();
        toggleFncContentVisibility();
        setupButtonClickListener();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (fabPj.getVisibility() == View.VISIBLE) {
            hideFabMenu();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        configureMenuItem(menu.findItem(R.id.headerIcon),R.drawable.ic_save);
        return true;
    }

    private void configureMenuItem(MenuItem item,int iconResId) {
        item.setIcon(iconResId);
        item.setTitle(R.string.save);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.headerIcon) {
            hideKeyboard();
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        GenericAdapter adapter = new GenericAdapter(new ArrayList<>(), this);
        binding.cardView.setLayoutManager(new LinearLayoutManager(this));
        binding.cardView.setAdapter(adapter);
        viewModel.getItems().observe(this, adapter::setItems);
    }


    @Override
    public void onItemClick(int position) {
    }
    private void initializeViews() {
        fabMenu =binding.includedToolbar.fabMenu;
        fabPj = binding.includedToolbar.fabPj;
        fabPh = binding.includedToolbar.fabPh;
        fabCam = binding.includedToolbar.fabCam;
        fabLr = binding.includedToolbar.fabLr;
        overlay = binding.overlay;
        textField = binding.cmtTitle;
        noFncCard = binding.fncCardEmpty;
        fncContentContainer = binding.fncContentContainer;
        pjText = binding.includedToolbar.pjText;
        phText = binding.includedToolbar.phText;
        caText = binding.includedToolbar.caText;
        lrText = binding.includedToolbar.lrText;
        toolbar = binding.includedToolbar.appBarLayout.topAppBar;
    }

    private void retrieveIntentData() {
        toolbarTitle = getIntent().getStringExtra(ConstantFncNum.FNC_KEY);
        valueFromRadioButton = getIntent().getStringExtra("selectedValue");
    }

    private void configureToolbar() {
        setSupportActionBar(binding.includedToolbar.appBarLayout.topAppBar);
        toolbar.setTitle(toolbarTitle.isEmpty() ? getString(R.string.numFnc) : toolbarTitle);
        toolbar.setNavigationIcon(null);
    }
    private void setupFabMenu() {
        binding.includedToolbar.fabMenu.setOnClickListener(view -> toggleFabMenu());
    }

    private void populateTextField() {
        if (valueFromRadioButton != null) {
            textField.setText(valueFromRadioButton);
        }
    }

    private void toggleFncContentVisibility() {
        if (fncDataAvailable()) {
            noFncCard.setVisibility(View.GONE);
            fncContentContainer.setVisibility(View.VISIBLE);
        } else {
            noFncCard.setVisibility(View.VISIBLE);
            fncContentContainer.setVisibility(View.GONE);
        }
    }
    private void toggleFabMenu() {
        hideKeyboard();
        if (fabPj.getVisibility() == View.GONE) {
            showFabMenu();
        } else {
            hideFabMenu();
        }
    }

    private void showFabMenu() {
        fabPj.setVisibility(View.VISIBLE);
        fabPh.setVisibility(View.VISIBLE);
        fabCam.setVisibility(View.VISIBLE);
        fabLr.setVisibility(View.VISIBLE);
        overlay.setVisibility(View.VISIBLE);
        pjText.setVisibility(View.VISIBLE);
        phText.setVisibility(View.VISIBLE);
        caText.setVisibility(View.VISIBLE);
        lrText.setVisibility(View.VISIBLE);

        startFabOpenAnimation();
    }

    private void hideFabMenu() {
        fabPj.setVisibility(View.GONE);
        fabPh.setVisibility(View.GONE);
        fabCam.setVisibility(View.GONE);
        fabLr.setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);
        pjText.setVisibility(View.GONE);
        phText.setVisibility(View.GONE);
        caText.setVisibility(View.GONE);
        lrText.setVisibility(View.GONE);
        startFabCloseAnimation();
    }

    private void startFabOpenAnimation() {
        ObjectAnimator rotateOpen = ObjectAnimator.ofFloat(fabMenu, "rotation", ROTATION_START, ROTATION_END);
        rotateOpen.start();
    }

    private void startFabCloseAnimation() {
        ObjectAnimator rotateClose = ObjectAnimator.ofFloat(fabMenu, "rotation", ROTATION_END, ROTATION_START);
        rotateClose.start();
    }


    private boolean fncDataAvailable() {
        return true;
    }

    private void setupButtonClickListener() {
        binding.includedToolbar.fabLr.setOnClickListener(v -> navigateToTargetActivity());
    }

    private void navigateToTargetActivity() {
        startActivity(new Intent(this, AddLigneRActivity.class));
    }
}