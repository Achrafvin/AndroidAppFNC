package com.ambapharm.ui.activities;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.ambapharm.R;
import com.ambapharm.ui.viewModels.FncListViewModel;
import com.ambapharm.databinding.ActivityFnclistBinding;
import com.ambapharm.ui.adapters.GenericAdapter;

import java.util.ArrayList;

public class FncListActivity extends BaseActivity implements GenericAdapter.OnItemClickListener {
    private GenericAdapter adapter;
    private FncListViewModel viewModel;
    private ActivityFnclistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fnclist);
        binding = ActivityFnclistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(FncListViewModel.class);

        setupRecyclerView();
        setupToolbar();
        updateHeaderWithUserName();
    }

    private void setupRecyclerView() {
        adapter = new GenericAdapter(new ArrayList<>(), this);
        binding.fncListView.setLayoutManager(new LinearLayoutManager(this));
        binding.fncListView.setAdapter(adapter);
        viewModel.getItems().observe(this, adapter::setItems);
    }

    @Override
    public void onItemClick(int position) {
        adapter.setSelectedItem(position);
    }
}