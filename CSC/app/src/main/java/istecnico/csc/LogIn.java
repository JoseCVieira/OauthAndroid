package istecnico.csc;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    String str_type, str_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void onClickLogInButton(View view) throws IOException {
        final String ip_address = getTextString(R.id.serverIPInput);
        final String username = getTextString(R.id.userNameInput);
        final String userpass = getTextString(R.id.pwInput);
        final TextView output = (TextView)findViewById(R.id.resultText);
        output.setMovementMethod(new ScrollingMovementMethod());

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://"+ip_address+"/access_token";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parse_str_token(response);

                //curl -H "Authorization: Bearer <TOKEN>" http://localhost/resource.php/users
                String url ="http://"+ip_address+"/resource.php/users";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //output.setText(response);
                        String[] str_split = response.split("\"");

                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(LogIn.this, R.style.AlertDialogCustom);
                        builder.show();
                        builder.setTitle("Success!")
                                .setMessage("Wellcome " + str_split[1] + "!")
                                .setIcon(R.drawable.success)
                                .show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        String message = "";

                        if(error instanceof NetworkError)
                            message="Message: network!";
                        else if( error instanceof ServerError)
                            message="Message: server!";
                        else if( error instanceof AuthFailureError) {
                            message="Message: authentication failure!";
                        }else if( error instanceof ParseError)
                            message="Message: parse!";
                        else if( error instanceof TimeoutError)
                            message="Message: time out!";
                        else
                            message="Default message: Something went wrong.";

                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(LogIn.this, R.style.AlertDialogCustom);
                        builder.show();
                        builder.setTitle("Error - resource server :(")
                                .setMessage(message)
                                .setIcon(R.drawable.error)
                                .show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String,String>();
                        return params;

                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> headers=new HashMap<String,String>();
                        headers.put("Authorization",str_type + " " + str_token);
                        return headers;
                    }
                };
                queue.add(stringRequest);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                String message = "";

                if(error instanceof NetworkError)
                    message="Message: network!";
                else if( error instanceof ServerError)
                    message="Message: server!";
                else if( error instanceof AuthFailureError)
                    message="Message: authentication failure!";
                else if( error instanceof ParseError)
                    message="Message: parse!";
                else if( error instanceof TimeoutError)
                    message="Message: time out!";
                else
                    message="Default message: Something went wrong.";

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(LogIn.this, R.style.AlertDialogCustom);
                builder.show();
                builder.setTitle("Error authentication server :(")
                        .setMessage(message)
                        .setIcon(R.drawable.error)
                        .show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("grant_type","password");
                params.put("client_id","projetocsc");
                params.put("client_secret", "abc123");
                params.put("username",username);     //alex
                params.put("password",userpass);     //whisky
                params.put("scope","basic email");
                return params;

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<String,String>();
                headers.put("Accept","1.0");
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    private String getTextString(int id) {
        EditText targetText = (EditText) findViewById(id);
        return targetText.getText().toString();
    }

    public void parse_str_token(String str){
        String[] str_split = str.split("\"");
        str_type = str_split[3];
        str_token = str_split[9];
    }
}