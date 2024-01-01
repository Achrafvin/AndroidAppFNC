package com.ambapharm.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemDocumentCardBinding;
import com.ambapharm.databinding.ItemImageCardBinding;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnEditClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;
import com.ambapharm.ui.adapters.viewholders.DocumentViewHolder;
import com.ambapharm.ui.adapters.viewholders.FncViewHolder;
import com.ambapharm.ui.adapters.viewholders.ImageDocViewHolder;
import com.ambapharm.ui.adapters.viewholders.MedicationViewHolder;
import com.ambapharm.databinding.ItemFncListBinding;
import com.ambapharm.databinding.ItemIssueCardBinding;

public class ViewHolderFactory {

    public static RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType, OnViewClickListener viewClickListener, OnDeleteClickListener deleteClickListener, OnEditClickListener editClickListener) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return switch (viewType) {
            case ListItem.TYPE_DOCUMENT ->
                    new DocumentViewHolder(ItemDocumentCardBinding.inflate(inflater, parent, false), viewClickListener, deleteClickListener);
            case ListItem.TYPE_MEDICATION ->
                    new MedicationViewHolder(ItemIssueCardBinding.inflate(inflater, parent, false), editClickListener, deleteClickListener);
            case ListItem.TYPE_FNC -> new FncViewHolder(ItemFncListBinding.inflate(inflater, parent, false));
            case ListItem.TYPE_IMAGE ->
                    new ImageDocViewHolder(ItemImageCardBinding.inflate(inflater, parent, false), viewClickListener, deleteClickListener);
            default -> throw new IllegalArgumentException("Invalid view type");
        };
    }
}