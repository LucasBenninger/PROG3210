package ca.lucasbenninger.prog3210_messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class createAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private EditText passwordRepeat;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        passwordRepeat = (EditText) findViewById(R.id.passwordRepeat);
        createAccountButton = (Button) findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(this);


    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.createAccountButton:
                if(validateAccount()){
                    Intent createAccountIntent = new Intent(this, ProfileEditorActivity.class);
                    startActivity(createAccountIntent);
                }
                break;
        }
    }

    private boolean validateAccount(){
        boolean valid = true;
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        String passwordRepeatString = passwordRepeat.getText().toString();

        if(usernameString.equals("")){
            Toast.makeText(this, "Username required!", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(passwordString.equals("")){
            Toast.makeText(this, "Password required!", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(!passwordString.equals(passwordRepeatString)){
            Toast.makeText(this, "Passwords Don't Match!", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
}
