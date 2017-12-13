package ca.lucasbenninger.prog3210_messenger.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import java.sql.Blob;

/**
 * Created by lucas on 26/11/17.
 */

@Entity
public class Contact {

    @PrimaryKey
    @NonNull
    public String userName;
    public String displayName;
    public String bio;
    public String dob;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] photo;

    public Contact(String userName, String displayName, String bio, String dob, byte[] photo){
        this.userName = userName;
        this.displayName = displayName;
        this.bio = bio;
        this.dob = dob;
        this.photo = photo;
    }
}
