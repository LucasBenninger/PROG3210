package ca.lucasbenninger.prog3210_messenger;

import android.content.Context;
import android.content.Intent;
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

/**
 * Created by lucas on 11/12/17.
 */

class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    List<Contact> contactList;

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

    public ContactListAdapter(List<Contact> contactList){
        this.contactList = contactList;
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
        final String userName = contactList.get(position).userName;
        String bio = contactList.get(position).bio;

        holder.contactName.setText(userName);
        holder.contactBio.setText(bio);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                //Create Conversation in database
                DB db = DB.getDatabase(context.getApplicationContext());
                Conversation conversation = new Conversation(userName);
                db.conversationDao().createConversation(conversation);

                //Open new conversation Window
                Intent conversationListIntent = new Intent(context, ConversationActivity.class);
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
        return contactList.size();
    }

    private void startConversation(String username){
    }
}
