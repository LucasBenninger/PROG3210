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

import ca.lucasbenninger.prog3210_messenger.db.entity.Contact;
import ca.lucasbenninger.prog3210_messenger.db.entity.Conversation;

/**
 * Created by lucas on 11/12/17.
 */

class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ViewHolder> {
    List<Conversation> conversationList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView contactName;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            contactName = (TextView) v.findViewById(R.id.contactName);
        }
    }

    public ConversationListAdapter(List<Conversation> conversationList){
        this.conversationList = conversationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.conversation_fragment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String userName = conversationList.get(position).contact;

        holder.contactName.setText(userName);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                //Open new conversation Window
                Intent conversationListIntent = new Intent(context, ConversationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", userName);
                conversationListIntent.putExtras(bundle);
                context.startActivity(conversationListIntent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }
}
