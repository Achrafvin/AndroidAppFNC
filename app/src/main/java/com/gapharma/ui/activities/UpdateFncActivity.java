package com.gapharma.ui.activities;

import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;

import com.gapharma.R;
import com.gapharma.databinding.ActivityUpdateFncBinding;
import com.gapharma.ui.viewModels.FncListViewModel;
import com.gapharma.ui.adapters.GenericAdapter;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;



/**
 * UpdateFncActivity displays a list of all created FNCs. It allows users to select a date range
 * to filter and view FNCs within that range. This activity extends BaseActivity and uses a RecyclerView
 * to display the list of FNCs.
 */
public class UpdateFncActivity extends BaseActivity{
    private GenericAdapter adapter;
    private FncListViewModel viewModel;
    private ActivityUpdateFncBinding binding;


    /**
     * Called when the activity is starting. This method initializes the activity setup.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }


    /**
     * Initializes the activity by inflating its layout, setting up the ViewModel, and configuring the date range picker.
     */
    private void initializeActivity() {
        binding = ActivityUpdateFncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeViewModel();
        onDateRangePickerClick();

    }

    /**
     * Initializes the ViewModel, sets up the toolbar, header, and RecyclerView for displaying FNC items.
     */
    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(FncListViewModel.class);
        configureToolbarAndHeader();
        initializeRecyclerView();
    }


    /**
     * Sets up a click listener for the date range picker. Allows users to select a start and end date to filter FNCs.
     */
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

    /**
     * Converts a Unix time value to a formatted date string.
     *
     * @param time The Unix time value.
     * @return A string representation of the date.
     */
    private String convertTimeToDate(long time) {
        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utc.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return format.format(utc.getTime());
    }


    /**
     * Configures the toolbar and updates the header with the user's name.
     */
    private void configureToolbarAndHeader() {
        setupToolbar();
        updateHeaderWithUserName();
    }

    /**
     * Initializes the RecyclerView for displaying FNC items and observes changes in the ViewModel.
     */
    private void initializeRecyclerView() {
        setupAdapter();
        setupRecyclerViewLayout();
        observeViewModel();
    }

    /**
     * Sets up the adapter for the RecyclerView.
     */
    private void setupAdapter() {
        adapter = new GenericAdapter(this, new ArrayList<>(),  null);
    }


    /**
     * Configures the RecyclerView layout.
     */
    private void setupRecyclerViewLayout() {
        binding.fncListView.setLayoutManager(new LinearLayoutManager(this));
        binding.fncListView.setAdapter(adapter);
    }


    /**
     * Observes changes in the ViewModel to update the RecyclerView with new FNC items.
     */
    private void observeViewModel() {
        viewModel.getItems().observe(this, adapter::setItems);
    }



}
