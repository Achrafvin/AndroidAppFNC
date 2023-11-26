package com.ambapharm.ui.adapters.items;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.DocumentViewHolder;
import com.ambapharm.ui.adapters.BindableItem;

public class DocumentItem extends ListItem implements Parcelable, BindableItem {
    private String title;

    public DocumentItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return TYPE_DOCUMENT;
    }

    protected DocumentItem(Parcel in) {
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

    public static final Creator<DocumentItem> CREATOR = new Creator<DocumentItem>() {
        @Override
        public DocumentItem createFromParcel(Parcel in) {
            return new DocumentItem(in);
        }

        @Override
        public DocumentItem[] newArray(int size) {
            return new DocumentItem[size];
        }
    };

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DocumentViewHolder) {
            DocumentViewHolder holder = (DocumentViewHolder) viewHolder;
            holder.binding.docTitle.setText(getTitle());
        }
    }
}
