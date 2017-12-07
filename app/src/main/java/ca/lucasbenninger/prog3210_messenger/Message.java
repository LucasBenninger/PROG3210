package ca.lucasbenninger.prog3210_messenger;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Created by lucas on 26/11/17.
 */

@Entity(primaryKeys = {"sender", "receiver", "timestamp"})
public class Message {

    @NonNull
    public String sender;
    @NonNull
    public String receiver;
    public String content;
    @NonNull
    public int timestamp;

    public Message(String sender, String receiver, String content, int timestamp){
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
    }



}
