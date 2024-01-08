package com.gapharma.ui.adapters.items;

import androidx.recyclerview.widget.RecyclerView;

import com.gapharma.ui.adapters.ListItem;
import com.gapharma.ui.adapters.viewholders.DocumentViewHolder;

/**
 * Represents a document item type used in a RecyclerView within the context of creating or managing FNCs (pharmaceutical product anomaly reports).
 * This class extends ListItem, allowing it to be used with generic adapters designed for RecyclerViews.
 * Each DocumentItem holds information about a document including its title and file path.
 */
public class DocumentItem extends ListItem{
    private String title;
    private String filePath;


    /**
     * Constructs a new DocumentItem with specified title and file path.
     *
     * @param title    The title of the document.
     * @param filePath The file path where the document is stored.
     */
    public DocumentItem(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }

    /**
     * Gets the file path of the document.
     *
     * @return The file path of the document.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the title of the document.
     *
     * @return The title of the document.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the type of the ListItem, in this case, TYPE_DOCUMENT.
     *
     * @return An integer representing the type of the ListItem, specific to DocumentItem.
     */
    @Override
    public int getType() {
        return TYPE_DOCUMENT;
    }


    /**
     * Binds the current DocumentItem to a ViewHolder. This method casts the passed ViewHolder to DocumentViewHolder
     * and sets the title of the document.
     *
     * @param viewHolder The ViewHolder which should be instance of DocumentViewHolder, used to display the item's data.
     */
    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DocumentViewHolder) {
            DocumentViewHolder holder = (DocumentViewHolder) viewHolder;
            holder.binding.docTitle.setText(getTitle());
        }
    }
}
