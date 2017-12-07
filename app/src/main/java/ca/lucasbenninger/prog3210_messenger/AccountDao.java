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
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAccount(Account user);

    @Query("select * from account")
    public List<Account> getAccount();

    @Update
    void updateAccount(Account account);

    @Query("delete from account")
    void removeAccount();

}
