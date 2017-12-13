package ca.lucasbenninger.prog3210_messenger;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.DB;
import ca.lucasbenninger.prog3210_messenger.db.entity.Contact;

public class StartConversationActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<Contact> contactList;

    DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_conversation);

        db = db.getDatabase(getApplicationContext());

        contactList = db.contactDao().getContacts();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ContactListAdapter(contactList);
        recyclerView.setAdapter(adapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.addContactFAB);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addContactFAB:
                Toast.makeText(this, "TEst", Toast.LENGTH_SHORT);
                Intent createContactIntent = new Intent(this, AddContactActivity.class);
                startActivity(createContactIntent);
                break;
        }
    }
}
