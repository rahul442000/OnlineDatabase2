package com.apkglobal.onlinedatabase;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2,e3,e4,e5;
    String s1,s2,s3,s4,s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.submit);
        b2=findViewById(R.id.show);
        e1=findViewById(R.id.userid);
        e2=findViewById(R.id.name);
        e3=findViewById(R.id.address);
        e4=findViewById(R.id.qual);
        e5=findViewById(R.id.contact);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Submit successfully", Toast.LENGTH_SHORT).show();
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                s5=e5.getText().toString();
                register();

            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

    }



    public void register() {
        RequestQueue rq=Volley.newRequestQueue(MainActivity.this);
        String url="https://rahulsoni442000.000webhostapp.com/studentdata/register.php";
        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Register...", Toast.LENGTH_SHORT).show();
                e1.setText("ID");
                e2.setText("Name");
                e3.setText("Address");
                e4.setText("Qualification");
                e5.setText("Contact");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Not Register...", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hm1=new HashMap<>();
                hm1.put("i",s1);
                hm1.put("n",s2);
                hm1.put("a",s3);
                hm1.put("q",s4);
                hm1.put("m",s5);
                return hm1;
            }
        };
        rq.add(sr);
    }
}