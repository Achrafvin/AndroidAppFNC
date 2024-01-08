package com.gapharma.ui.adapters.items;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gapharma.ui.adapters.ListItem;
import com.gapharma.ui.adapters.viewholders.FncViewHolder;

/**
 * Represents an FNC list item used in a RecyclerView to display information about pharmaceutical product anomaly reports.
 * This class extends ListItem and includes details such as the FNC number and the date associated with each FNC.
 */
public class FncListItem extends ListItem {

    private String num_fnc,date;


    /**
     * Constructs a new FncListItem with specified FNC number and date.
     *
     * @param num_fnc The number of the FNC.
     * @param date    The date associated with the FNC.
     */
    public FncListItem(String num_fnc, String date) {
        this.num_fnc = num_fnc;
        this.date = date;
    }


    /**
     * Gets the FNC number.
     *
     * @return The FNC number.
     */
    public String getNum_fnc() {
        return num_fnc;
    }


    /**
     * Gets the date associated with the FNC.
     *
     * @return The date of the FNC.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the type of the ListItem, in this case, TYPE_FNC.
     *
     * @return An integer representing the type of the ListItem, specific to FncListItem.
     */
    @Override
    public int getType() {
        return TYPE_FNC;
    }


    /**
     * Binds the current FncListItem to a ViewHolder. This method casts the passed ViewHolder to FncViewHolder
     * and sets the FNC number, date, and modifies the visibility and background color based on the selection state.
     *
     * @param viewHolder The ViewHolder which should be an instance of FncViewHolder, used to display the item's data.
     */
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
