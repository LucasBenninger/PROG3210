package ca.lucasbenninger.prog3210_messenger;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by lucas on 22/11/17.
 */

@Database(entities = {Account.class, Message.class, Contact.class}, version = 1)
public abstract class DB extends RoomDatabase {

    private static DB INSTANCE;

    public abstract AccountDao accountDao();
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();

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