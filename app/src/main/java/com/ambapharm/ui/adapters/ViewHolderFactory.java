package com.ambapharm.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.viewholders.DocumentViewHolder;
import com.ambapharm.ui.adapters.viewholders.FncViewHolder;
import com.ambapharm.ui.adapters.viewholders.ImageDocViewHolder;
import com.ambapharm.ui.adapters.viewholders.MedicationViewHolder;
import com.ambapharm.databinding.ItemFncListBinding;
import com.ambapharm.databinding.ItemImageDocBinding;
import com.ambapharm.databinding.ItemIssueCardBinding;
import com.ambapharm.databinding.ItemIssueDocBinding;

public class ViewHolderFactory {

    public static RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ListItem.TYPE_DOCUMENT:
                return new DocumentViewHolder(ItemIssueDocBinding.inflate(inflater, parent, false));
            case ListItem.TYPE_MEDICATION:
                return new MedicationViewHolder(ItemIssueCardBinding.inflate(inflater, parent, false));
            case ListItem.TYPE_FNC:
                return new FncViewHolder(ItemFncListBinding.inflate(inflater,parent,false));
            case ListItem.TYPE_IMAGE:
                return new ImageDocViewHolder(ItemImageDocBinding.inflate(inflater,parent,false));
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }
}