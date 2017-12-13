package ca.lucasbenninger.prog3210_messenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.lucasbenninger.prog3210_messenger.db.DB;
import ca.lucasbenninger.prog3210_messenger.db.entity.Contact;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {


    EditText usernameEditText;
    EditText displayNameEditText;
    EditText bioEditText;
    EditText dobEditText;
    Button addContactButton;

    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = DB.getDatabase(getApplicationContext());

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        displayNameEditText = (EditText) findViewById(R.id.displayNameEditText);
        bioEditText = (EditText) findViewById(R.id.bioEditText);
        dobEditText = (EditText) findViewById(R.id.dobEditText);
        addContactButton = (Button) findViewById(R.id.addContactButton);

        addContactButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.addContactButton):
                addContact();
                break;
        }
    }

    private void addContact(){
        String username = usernameEditText.getText().toString();
        String displayname = displayNameEditText.getText().toString();
        String bio = bioEditText.getText().toString();
        String dob = dobEditText.getText().toString();

        Contact newContact = new Contact(username, displayname, bio, dob, null);
        db.contactDao().addContact(newContact);
    }
}
