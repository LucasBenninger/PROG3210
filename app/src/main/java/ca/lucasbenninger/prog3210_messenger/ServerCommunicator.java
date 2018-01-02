package ca.lucasbenninger.prog3210_messenger;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import ca.lucasbenninger.prog3210_messenger.db.DBIO;

/**
 * Created by lucas on 30/12/17.
 */

public class ServerCommunicator {

    String url = "http://159.203.56.202:8000";
    FirebaseId firebaseId = new FirebaseId();
    RequestQueue requestQueue;
    Context context;
    DBIO dbio;

    ServerCommunicator(Context context) {
        this.context = context;
        dbio = new DBIO(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    public void createAccount(final String username, final String password) {
        url = url + "/account/create";
        JSONObject params = new JSONObject();
        try {
            params.put("username", username);
            params.put("password", password);
            params.put("firebase", firebaseId.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String paramsString = params.toString();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (response.has("_id")) {
                            //Account details returned, therefore account created.
                            System.out.println("Account Created Succesfully");
                            dbio.addAccount(username, password);
                        } else if (response.has("error")) {
                            System.out.println("Error Creating Account - server known error");
                            System.out.println(response);
                        } else {
                            System.out.println("Error Creating Account");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    return paramsString.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    VolleyLog.e(e.toString());
                    return null;
                }
            }
        };

        requestQueue.add(req);
    }

    public void sendMessage(final String sender, final String receiver, final String content) {
        url = url + "/message/send";
        JSONObject params = new JSONObject();
        try {
            params.put("sender", sender);
            params.put("receiver", receiver);
            params.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String paramsString = params.toString();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (response.has("_id")) {
                            //Account details returned, therefore account created.
                            System.out.println("Account Created Succesfully");
                            try {
                                dbio.addMessage(sender, receiver, content, response.get("_id").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (response.has("error")) {
                            System.out.println("Error Creating Account - server known error");
                            System.out.println(response);
                        } else {
                            System.out.println("Error Creating Account");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    return paramsString.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    VolleyLog.e(e.toString());
                    return null;
                }
            }
        };

        requestQueue.add(req);
    }

    public void getMessages(String username) {
        url = url + "/message/get/" + username;
        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dbio.getMessageAddMessage(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(req);

    }
}
