package com.ambapharm.ui.adapters.items;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.ImageDocViewHolder;

/**
 * Represents an image document item used in a RecyclerView. This class extends ListItem and
 * includes the title and image path for an image document, facilitating its display in the UI.
 */
public class ImageDocItem extends ListItem {
    private String title;
    private String imagePath;

    /**
     * Constructs a new ImageDocItem with a specified title and image path.
     *
     * @param title     The title of the image document.
     * @param imagePath The path where the image document is stored.
     */
    public ImageDocItem(String title, String imagePath) {
        this.title = title;
        this.imagePath = imagePath;
    }

    /**
     * Gets the image path of the document.
     *
     * @return The image path of the document.
     */
    public String getImagePath() {
        return imagePath;
    }


    /**
     * Gets the title of the image document.
     *
     * @return The title of the image document.
     */
    public String getTitle() {
        return title;
    }


    /**
     * Returns the type of the ListItem, in this case, TYPE_IMAGE.
     *
     * @return An integer representing the type of the ListItem, specific to ImageDocItem.
     */
    @Override
    public int getType() {
        return TYPE_IMAGE;
    }


    /**
     * Binds the current ImageDocItem to a ViewHolder. This method casts the passed ViewHolder to ImageDocViewHolder
     * and sets the title of the image document.
     *
     * @param viewHolder The ViewHolder which should be an instance of ImageDocViewHolder, used to display the item's data.
     */
    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof ImageDocViewHolder){
            ImageDocViewHolder holder = (ImageDocViewHolder) viewHolder;
            holder.binding.imgTitle.setText(getTitle());
        }
    }
}
