package ca.lucasbenninger.prog3210_messenger.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.entity.Message;

/**
 * Created by lucas on 04/12/17.
 */

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMessage(Message message);

    @Query("select * from message")
    public List<Message> getMessages();

    @Query("select * from message where sender = :username")
    public List<Message> getMessagesFrom(String username);

    @Update
    void updateMessage(Message message);

    @Query("delete from message where sender = :username")
    void deleteMessagesFrom(String username);

    @Query("delete from message")
    void removeMessages();
}
