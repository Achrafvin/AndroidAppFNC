package com.ambapharm.ui.adapters.items;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.DocumentViewHolder;

public class DocumentItem extends ListItem{
    private String title;
    private String filePath;

    public DocumentItem(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return TYPE_DOCUMENT;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DocumentViewHolder) {
            DocumentViewHolder holder = (DocumentViewHolder) viewHolder;
            holder.binding.docTitle.setText(getTitle());
        }
    }
}
