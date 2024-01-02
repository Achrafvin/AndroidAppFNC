package com.ambapharm.ui.adapters.items;


import androidx.recyclerview.widget.RecyclerView;
import com.ambapharm.ui.adapters.ListItem;
import com.ambapharm.ui.adapters.viewholders.DescriptionViewHolder;

public class DescriptionItem extends ListItem {

    private Long id;
    private String mainTitle, subtitle, comment,num;

    public DescriptionItem(Long id, String mainTitle, String subtitle, String comment, String num) {
        this.id = id;
        this.mainTitle = mainTitle;
        this.subtitle = subtitle;
        this.comment = comment;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return TYPE_DESCRIPTION;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof DescriptionViewHolder){
            DescriptionViewHolder holder = (DescriptionViewHolder) viewHolder;
            holder.binding.mainTitle.setText(getMainTitle());
            holder.binding.subtitle.setText(getSubtitle());
            holder.binding.cardCmt.setText(getComment());
            holder.binding.cardNum.setText(getNum());
        }
    }



}
