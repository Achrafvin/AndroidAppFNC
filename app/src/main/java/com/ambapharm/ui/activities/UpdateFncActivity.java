package com.ambapharm.ui.activities;

import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;

import com.ambapharm.R;
import com.ambapharm.databinding.ActivityUpdateFncBinding;
import com.ambapharm.ui.viewModels.FncListViewModel;
import com.ambapharm.ui.adapters.GenericAdapter;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class UpdateFncActivity extends BaseActivity implements GenericAdapter.OnItemDeleteListener {
    private GenericAdapter adapter;
    private FncListViewModel viewModel;
    private ActivityUpdateFncBinding binding;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }

    private void initializeActivity() {
        binding = ActivityUpdateFncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeViewModel();
        onDateRangePickerClick();

    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(FncListViewModel.class);
        configureToolbarAndHeader();
        initializeRecyclerView();
    }

    private void onDateRangePickerClick() {
        binding.selectedDate.setOnClickListener(v -> {
            MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
            builder.setTheme(R.style.ThemeMaterialCalandar);
            builder.setTitleText("Choisir La date");
            builder.setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds()));

            MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
            picker.show(getSupportFragmentManager(), "datePicker");

            picker.addOnPositiveButtonClickListener(selection -> {
                String startDate = convertTimeToDate(selection.first);
                String endDate = convertTimeToDate(selection.second);
                binding.selectedDate.setText(startDate + " - " + endDate);
            });
        });
    }


    private String convertTimeToDate(long time) {
        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utc.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return format.format(utc.getTime());
    }

    private void configureToolbarAndHeader() {
        setupToolbar();
        updateHeaderWithUserName();
    }

    private void initializeRecyclerView() {
        setupAdapter();
        setupRecyclerViewLayout();
        observeViewModel();
    }


    private void setupAdapter() {
        adapter = new GenericAdapter(this, new ArrayList<>(),  this);
    }

    private void setupRecyclerViewLayout() {
        binding.fncListView.setLayoutManager(new LinearLayoutManager(this));
        binding.fncListView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.getItems().observe(this, adapter::setItems);
    }


    @Override
    public void onDeleteItem(int position) {
    }
}
