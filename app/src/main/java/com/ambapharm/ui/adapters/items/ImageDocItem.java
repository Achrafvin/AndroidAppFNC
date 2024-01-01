package com.ambapharm.ui.adapters.items;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.ImageDocViewHolder;
import com.bumptech.glide.Glide;

public class ImageDocItem extends ListItem {
    private String title;
    private String imagePath;

    public ImageDocItem(String title, String imagePath) {
        this.title = title;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return TYPE_IMAGE;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof ImageDocViewHolder){
            ImageDocViewHolder holder = (ImageDocViewHolder) viewHolder;
            holder.binding.imgTitle.setText(getTitle());
        }
    }
}
