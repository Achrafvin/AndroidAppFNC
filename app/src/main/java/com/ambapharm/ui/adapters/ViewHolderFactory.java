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


/**
 * A factory class for creating various types of ViewHolders for a RecyclerView.
 * This class provides a centralized method to create different ViewHolder instances based on
 * the viewType. It simplifies the creation of ViewHolders in the RecyclerView adapter and
 * ensures that each type of item is bound to its corresponding ViewHolder.
 */
public class ViewHolderFactory {


    /**
     * Creates and returns the appropriate ViewHolder for the given viewType.
     *
     * @param parent            The ViewGroup into which the new view will be added after it is bound to an adapter position.
     * @param viewType          The type of view to create. This corresponds to the type constants defined in ListItem.
     * @param viewClickListener Listener for handling view actions on the item.
     * @param deleteClickListener Listener for handling delete actions on the item.
     * @param editClickListener Listener for handling edit actions on the item.
     * @return The created RecyclerView.ViewHolder instance specific to the viewType.
     * @throws IllegalArgumentException if the viewType is not recognized.
     */
    public static RecyclerView.ViewHolder createViewHolder(ViewGroup parent,
                                                           int viewType, OnViewClickListener viewClickListener, OnDeleteClickListener deleteClickListener, OnEditClickListener editClickListener) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ListItem.TYPE_DOCUMENT:
                return new DocumentViewHolder(ItemDocumentCardBinding.inflate(inflater, parent, false),
                        viewClickListener, deleteClickListener);
            case ListItem.TYPE_MEDICATION:
                return new MedicationViewHolder(ItemIssueCardBinding.inflate(inflater, parent, false),
                        editClickListener, deleteClickListener);
            case ListItem.TYPE_FNC:
                return new FncViewHolder(ItemFncListBinding.inflate(inflater, parent, false));
            case ListItem.TYPE_IMAGE:
                return new ImageDocViewHolder(ItemImageCardBinding.inflate(inflater, parent, false),
                        viewClickListener, deleteClickListener);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }

    }
}