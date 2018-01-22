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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClickRegister_r(View view) {
        String ip_address = getTextString(R.id.serverIPInput_r);
        final String username = getTextString(R.id.userNameInput_r);
        final String userpass = getTextString(R.id.pwInput_r);
        final TextView output = (TextView)findViewById(R.id.resultText_r);
        output.setMovementMethod(new ScrollingMovementMethod());

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://"+ip_address+"/register.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //output.setText(parse_str_register(response));
                parse_str_register(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                if(error instanceof NetworkError)
                    output.setText("error: network!");
                else if( error instanceof ServerError)
                    output.setText("error: server!");
                else if( error instanceof AuthFailureError)
                    output.setText("error: auth failure!");
                else if( error instanceof ParseError)
                    output.setText("error: parse!");
                else if( error instanceof TimeoutError)
                    output.setText("error: time out!");
                else
                    output.setText("Default: that didn't work!");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("requester_name",username);
                params.put("requester_pass",userpass);
                return params;

            }
        };
        queue.add(stringRequest);
    }

    private String getTextString(int id) {
        EditText targetText = (EditText) findViewById(id);
        return targetText.getText().toString();
    }

    public void parse_str_register(String str){
        if(str.contains("1")) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(Register.this, R.style.AlertDialogCustom);
            builder.show();
            builder.setTitle("Success!")
                    .setMessage("Successfully registered!")
                    .setIcon(R.drawable.success)
                    .show();
        }else{
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(Register.this, R.style.AlertDialogCustom);
            builder.show();
            builder.setTitle("Error!")
                    .setMessage("User name already in use!")
                    .setIcon(R.drawable.error)
                    .show();
        }
    }
}
