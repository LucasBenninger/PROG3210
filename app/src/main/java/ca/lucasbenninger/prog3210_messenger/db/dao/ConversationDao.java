package ca.lucasbenninger.prog3210_messenger.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.entity.Conversation;

/**
 * Created by lucas on 11/12/17.
 */

@Dao
public interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createConversation(Conversation conversation);

    @Query("select * from conversation")
    public List<Conversation> getConversations();

    @Query("Select * from conversation where conversationId = :conversationId")
    public List<Conversation> getConversation(int conversationId);

    @Query("delete from conversation where conversationId = :conversationId")
    void deleteConversation(int conversationId);
}
