package ca.lucasbenninger.prog3210_messenger.db;

import android.app.Application;
import android.content.Context;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.entity.Account;

/**
 * Created by lucas on 01/01/18.
 */

public class DBIO{

    private  DB db;
    private Context context;

    public DBIO(Context context){
        this.context = context;
    }

    public void addAccount(String username, String password){
        db = DB.getDatabase(context);
        db.accountDao().removeAccount();
        db.accountDao().addAccount(new Account(username, password, null, null,null, null));
    }
}
