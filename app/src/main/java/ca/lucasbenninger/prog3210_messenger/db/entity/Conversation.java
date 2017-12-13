package ca.lucasbenninger.prog3210_messenger.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lucas on 11/12/17.
 */

@Entity
public class Conversation {
    @PrimaryKey(autoGenerate = true)
    public int conversationId;
    public String contact;

    public Conversation(String contact){

    }
}
