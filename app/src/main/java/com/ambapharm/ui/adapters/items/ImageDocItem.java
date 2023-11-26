package com.ambapharm.ui.adapters.items;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.ImageDocViewHolder;

public class ImageDocItem extends ListItem implements Parcelable {
    private String title;

    public ImageDocItem(String title) {
        this.title = title;
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

    protected ImageDocItem(Parcel in) {
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageDocItem> CREATOR = new Creator<ImageDocItem>() {
        @Override
        public ImageDocItem createFromParcel(Parcel in) {
            return new ImageDocItem(in);
        }

        @Override
        public ImageDocItem[] newArray(int size) {
            return new ImageDocItem[size];
        }
    };
}
