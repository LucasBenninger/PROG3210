package ca.lucasbenninger.prog3210_messenger.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.lucasbenninger.prog3210_messenger.db.entity.Conversation;
import ca.lucasbenninger.prog3210_messenger.db.dao.ConversationDao;
import ca.lucasbenninger.prog3210_messenger.db.entity.Message;
import ca.lucasbenninger.prog3210_messenger.db.dao.MessageDao;
import ca.lucasbenninger.prog3210_messenger.db.dao.ContactDao;
import ca.lucasbenninger.prog3210_messenger.db.entity.Account;
import ca.lucasbenninger.prog3210_messenger.db.dao.AccountDao;
import ca.lucasbenninger.prog3210_messenger.db.entity.Contact;

/**
 * Created by lucas on 22/11/17.
 */

@Database(entities = {Account.class, Message.class, Contact.class, Conversation.class}, version = 1)
public abstract class DB extends RoomDatabase {

    private static DB INSTANCE;

    public abstract AccountDao accountDao();
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
    public abstract ConversationDao conversationDao();

    public static DB getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, DB.class, "prog3210_messenger").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}