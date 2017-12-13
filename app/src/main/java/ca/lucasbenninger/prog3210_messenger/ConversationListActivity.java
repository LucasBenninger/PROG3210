package ca.lucasbenninger.prog3210_messenger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.DB;
import ca.lucasbenninger.prog3210_messenger.db.entity.Conversation;

public class ConversationListActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<Conversation> conversationList;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        db = db.getDatabase(getApplicationContext());

        conversationList = db.conversationDao().getConversations();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //Set RecyclerView Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Set RecyclerView Adapter
        adapter = new ConversationListAdapter(conversationList);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerDecoration);

        for(Conversation item : conversationList){
            System.out.println("Test: "+item.contact);
        }

        //TODO: Fix this
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);



        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.floatingActionButton:
                Intent startConversationIntent = new Intent(this, ContactListActivity.class);
                startActivity(startConversationIntent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.requestLayout();
    }

    @Override
    public void onStart(){
        super.onStart();
        recyclerView.requestLayout();
    }
}
