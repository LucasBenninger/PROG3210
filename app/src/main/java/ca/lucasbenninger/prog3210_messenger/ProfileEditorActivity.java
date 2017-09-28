package ca.lucasbenninger.prog3210_messenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileEditorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView profileImage;
    private EditText dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        profileImage = (ImageView) findViewById(R.id.profileImage);
        dob = (EditText) findViewById(R.id.dobEditText);

        profileImage.setOnClickListener(this);
        dob.setOnClickListener(this);
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
}
