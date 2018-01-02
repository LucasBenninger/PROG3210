package ca.lucasbenninger.prog3210_messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.DB;
import ca.lucasbenninger.prog3210_messenger.db.entity.Contact;
import ca.lucasbenninger.prog3210_messenger.db.entity.Conversation;
import ca.lucasbenninger.prog3210_messenger.db.entity.Message;

/**
 * Created by lucas on 11/12/17.
 */

class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {
    List<Message> messageList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public ImageView contactPhoto;
        public TextView contactName;
        public TextView contactBio;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            contactName = (TextView) v.findViewById(R.id.contactName);
            contactBio = (TextView) v.findViewById(R.id.contactBio);
            contactPhoto = (ImageView) v.findViewById(R.id.contactPhoto);
        }
    }

    public MessageListAdapter(List<Message> messageList){
        this.messageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_fragment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String content = messageList.get(position).content;


        holder.contactName.setText(content);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
