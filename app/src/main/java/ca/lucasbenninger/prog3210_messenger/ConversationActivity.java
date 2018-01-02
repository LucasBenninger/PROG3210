package ca.lucasbenninger.prog3210_messenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.DB;
import ca.lucasbenninger.prog3210_messenger.db.entity.Account;
import ca.lucasbenninger.prog3210_messenger.db.entity.Contact;
import ca.lucasbenninger.prog3210_messenger.db.entity.Message;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendButton;
    private EditText editText;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<Message> messageList;

    private DB db;
    private ServerCommunicator sc;
    private String receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        db = DB.getDatabase(getApplicationContext());
        sc = new ServerCommunicator(getApplicationContext());

        sendButton = (Button) findViewById(R.id.sendButton);
        editText = (EditText) findViewById(R.id.editText);
        sendButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null && !extras.isEmpty()){
            if(extras.containsKey("username")){
                String username = extras.getString("username");
                Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
                receiver = username;
            }
        }

        if(receiver != null)
            sc.getMessages(receiver);

        messageList = db.messageDao().getMessagesFrom(receiver);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MessageListAdapter(messageList);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerDecoration);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendButton:
                sc = new ServerCommunicator(getApplicationContext());
                List<Account> accountList = db.accountDao().getAccount();
                sc.sendMessage(accountList.get(0).username, receiver, editText.getText().toString());
                editText.setText("");
                break;
        }
    }
}
