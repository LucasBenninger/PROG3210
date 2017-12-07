package ca.lucasbenninger.prog3210_messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class ProfileEditorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView profileImage;
    private EditText displayName;
    private EditText dob;
    private Button saveProfileButton;

    private Account account;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        profileImage = (ImageView) findViewById(R.id.profileImage);
        displayName = (EditText) findViewById(R.id.displayNameEditText);
        dob = (EditText) findViewById(R.id.dobEditText);
        saveProfileButton = (Button) findViewById(R.id.saveProfileButton);

        profileImage.setOnClickListener(this);
        dob.setOnClickListener(this);
        saveProfileButton.setOnClickListener(this);

        db = db.getDatabase(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profileImage:
                showProfilePhotoSelectorDialog();
                break;
            case R.id.dobEditText:
                showDOBSelectorDialog();
                break;
            case R.id.saveProfileButton:
                saveProfile();
                Intent conversationListIntent = new Intent(this, ConversationListActivity.class);
                startActivity(conversationListIntent);
                break;
        }
    }

    private void showDOBSelectorDialog(){
        //TODO: Date picker dialog
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
    }

    private void showProfilePhotoSelectorDialog(){
        //TODO: Photo selection dialog
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
    }

    private void saveProfile(){
        List<Account> currentAccountList = db.accountDao().getAccount();
        Account currentAccount = currentAccountList.get(0);
        currentAccount.bio = dob.getText().toString();
        currentAccount.displayname = displayName.getText().toString();
        db.accountDao().updateAccount(currentAccount);
    }
}
