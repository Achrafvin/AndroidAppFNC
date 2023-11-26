package com.ambapharm.ui.adapters.items;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.FncViewHolder;


public class FncListItem extends ListItem {

    private String num_fnc,date;

    public FncListItem(String num_fnc, String date) {
        this.num_fnc = num_fnc;
        this.date = date;
    }

    public String getNum_fnc() {
        return num_fnc;
    }

    public void setNum_fnc(String num_fnc) {
        this.num_fnc = num_fnc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getType() {
        return TYPE_FNC;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof FncViewHolder) {
            FncViewHolder holder = (FncViewHolder) viewHolder;
            holder.binding.FncNum.setText(getNum_fnc());
            holder.binding.datePick.setText(getDate());
            holder.binding.fncarrow.setVisibility(isSelected() ? View.VISIBLE : View.GONE);
            holder.itemView.setBackgroundColor(isSelected() ? Color.parseColor("#f7f0fe") : Color.WHITE);
        }
    }

}
