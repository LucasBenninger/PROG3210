package ca.lucasbenninger.prog3210_messenger.db;

import android.app.Application;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ca.lucasbenninger.prog3210_messenger.db.entity.Account;
import ca.lucasbenninger.prog3210_messenger.db.entity.Message;

/**
 * Created by lucas on 01/01/18.
 */

public class DBIO{

    private  DB db;
    private Context context;

    public DBIO(Context context){
        this.context = context;
        db = DB.getDatabase(context);
    }

    public void addAccount(String username, String password){
        db.accountDao().removeAccount();
        db.accountDao().addAccount(new Account(username, password, null, null,null, null));
    }

    public void addMessage(String sender, String receiver, String content, String id){
        //Conversation and timestamp dummydata
        Message message = new Message(0, sender, receiver, content, 0);
        db.messageDao().addMessage(message);
    }

    public void getMessageAddMessage(JSONObject messages){
        JSONArray array = null;
        Message message;
        try {
            array = new JSONArray(messages.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                message = new Message(0, obj.get("sender").toString(), obj.get("receiver").toString(), obj.get("content").toString(), i);
                db.messageDao().addMessage(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
