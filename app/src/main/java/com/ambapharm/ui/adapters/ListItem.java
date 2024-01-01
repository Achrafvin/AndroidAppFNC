package com.ambapharm.ui.adapters;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ListItem implements BindableItem {
    private boolean isSelected = false;
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public static final int TYPE_DOCUMENT = 0;
    public static final int TYPE_MEDICATION = 1;
    public static final int TYPE_FNC = 2;
    public static final int TYPE_IMAGE = 3;
    public abstract int getType();
    @Override
    public abstract void bind(RecyclerView.ViewHolder viewHolder);

    }

