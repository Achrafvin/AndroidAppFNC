package com.ambapharm.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.ui.interfaces.CardActionListener;
import com.ambapharm.data.models.IssueCard;
import com.ambapharm.R;

import java.util.List;

public class IssueCardAdapter extends RecyclerView.Adapter<IssueCardAdapter.IssueCardViewHolder>{

    private List<IssueCard> items;
    private CardActionListener actionListener;


    public IssueCardAdapter(List<IssueCard> items,CardActionListener actionListener) {
        this.items = items;
        this.actionListener = actionListener;
    }

    public void setActionListener(CardActionListener actionListener) {
        this.actionListener = actionListener;
    }



    @NonNull
    @Override
    public IssueCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_issue_card, parent, false);
        return new IssueCardViewHolder(view,actionListener);    }

    @Override
    public void onBindViewHolder(@NonNull IssueCardViewHolder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class IssueCardViewHolder extends RecyclerView.ViewHolder {

        private TextView mainTitleTextView;
        private TextView cardNumTextView;
        private TextView subtitleTextView;
        private TextView cardCommentTextView;
        private ImageView editImageView;
        private ImageView deleteImageView;

        public IssueCardViewHolder(View itemView, final CardActionListener actionListener) {
            super(itemView);
            editImageView = itemView.findViewById(R.id.edit);
            deleteImageView = itemView.findViewById(R.id.delete);
            mainTitleTextView = itemView.findViewById(R.id.mainTitle);
            cardNumTextView = itemView.findViewById(R.id.card_num);
            subtitleTextView = itemView.findViewById(R.id.subtitle);
            cardCommentTextView = itemView.findViewById(R.id.card_cmt);


            editImageView.setOnClickListener(v->{
                if (actionListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        actionListener.onEditClick(position);
                    }
                }
            });

            deleteImageView.setOnClickListener(v -> {
                if (actionListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        actionListener.onDeleteClick(position);
                    }
                }
            });
        }

        public void bind(IssueCard card) {
            mainTitleTextView.setText(card.getMainTitle());
            cardNumTextView.setText(card.getCardNum());
            subtitleTextView.setText(card.getSubtitle());
            cardCommentTextView.setText(card.getCardComment());
        }
    }


}
