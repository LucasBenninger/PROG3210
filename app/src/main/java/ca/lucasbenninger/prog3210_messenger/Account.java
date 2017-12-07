package ca.lucasbenninger.prog3210_messenger;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by lucas on 22/11/17.
 */
@Entity
public class Account {
    public Account(){}

    @PrimaryKey
    @NonNull
    public  String username;
    public String password;
    public String displayname;
    public String bio;
    public String dob;
    public String photo;

    public Account(String username, String password, String displayname, String bio, String dob, String photo){
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.bio = bio;
        this.dob = dob;
        this.photo = photo;
    }
}
