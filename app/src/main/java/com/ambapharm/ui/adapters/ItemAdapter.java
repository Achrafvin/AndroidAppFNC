package com.ambapharm.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.R;
import com.ambapharm.data.models.DocumentItem;
import com.ambapharm.data.models.ListItem;
import com.ambapharm.data.models.MedicationItem;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> items;

    public ItemAdapter(List<ListItem> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) { // Document
            View view = inflater.inflate(R.layout.item_issue_doc, parent, false);
            return new DocumentViewHolder(view);
        } else { // Medication
            View view = inflater.inflate(R.layout.item_issue_card, parent, false);
            return new MedicationViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = items.get(position);
        if (item.getType() == 0) {
            DocumentViewHolder docHolder = (DocumentViewHolder) holder;
            DocumentItem docItem = (DocumentItem) item;
            docHolder.docTitle.setText(docItem.getTitle());
        } else {
            MedicationViewHolder medHolder = (MedicationViewHolder) holder;
            MedicationItem medItem = (MedicationItem) item;
            medHolder.mainTitle.setText(medItem.getMainTitle());
            medHolder.subtitle.setText(medItem.getSubtitle());
            medHolder.comment.setText(medItem.getComment());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DocumentViewHolder extends RecyclerView.ViewHolder {
        TextView docTitle;

        DocumentViewHolder(View itemView) {
            super(itemView);
            docTitle = itemView.findViewById(R.id.docTitle);
        }
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder {
        TextView mainTitle, subtitle, comment;

        MedicationViewHolder(View itemView) {
            super(itemView);
            mainTitle = itemView.findViewById(R.id.mainTitle);
            subtitle = itemView.findViewById(R.id.subtitle);
            comment = itemView.findViewById(R.id.card_cmt);
        }
    }
}

