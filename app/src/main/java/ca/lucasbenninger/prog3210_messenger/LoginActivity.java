package ca.lucasbenninger.prog3210_messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.DB;
import ca.lucasbenninger.prog3210_messenger.db.entity.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private Button createAccountButton;
    private EditText username;
    private EditText password;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = DB.getDatabase(getApplicationContext());

        //Check if account present
        List<Account> accountList = db.accountDao().getAccount();
        if(accountList.size() > 0 && accountList.get(0) != null){
            //Account Present
            goToConversationList();
        }

        loginButton = (Button) findViewById(R.id.loginButton);
        createAccountButton = (Button) findViewById(R.id.createAccountButton);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
                if(logIn()){
                    goToConversationList();
                }else{
                    Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.createAccountButton:
                Intent createAccountIntent = new Intent(this, CreateAccountActivity.class);
                startActivity(createAccountIntent);
                break;
        }

    }

    private boolean logIn(){
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        List<Account> accountList = db.accountDao().getAccount();
        if(accountList.size() > 0){
            Account account = accountList.get(0);
            //Checks the saved login information for account verification
            //No server is set up to check against, and store login info.
            if(account.username.equals(usernameString) && account.password.equals(passwordString)){
                return true;
            }
        }
        return false;
    }

    private void goToConversationList(){
        Intent conversationListIntent = new Intent(this, ConversationListActivity.class);
        startActivity(conversationListIntent);
    }
}
