package ca.lucasbenninger.prog3210_messenger;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by lucas on 04/12/17.
 */

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addContact(Contact contact);

    @Query("select * from contact")
    public List<Contact> getContacts();

    @Query("select * from contact where username = :username")
    public List<Contact> getContact(String username);

    @Update
    void updateContact(Contact contact);

    @Query("delete from contact where username = :username")
    void deleteContact(String username);

    @Query("delete from contact")
    void removeContacts();
}
