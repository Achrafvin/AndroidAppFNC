package com.ambapharm.ui.adapters.items;


import androidx.recyclerview.widget.RecyclerView;
import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.MedicationViewHolder;

public class MedicationItem extends ListItem {
    private String mainTitle, subtitle, comment,num;

    public MedicationItem(String mainTitle, String subtitle, String comment, String num) {
        this.mainTitle = mainTitle;
        this.subtitle = subtitle;
        this.comment = comment;
        this.num = num;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getComment() {
        return comment;
    }

    public String getNum() {
        return num;
    }

    @Override
    public int getType() {
        return TYPE_MEDICATION;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof MedicationViewHolder){
            MedicationViewHolder holder = (MedicationViewHolder) viewHolder;
            holder.binding.mainTitle.setText(getMainTitle());
            holder.binding.subtitle.setText(getSubtitle());
            holder.binding.cardCmt.setText(getComment());
            holder.binding.cardNum.setText(getNum());
        }
    }



}
