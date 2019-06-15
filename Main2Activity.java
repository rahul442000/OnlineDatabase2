package com.apkglobal.onlinedatabase;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.os.Build.VERSION_CODES.O;

public class Main2Activity extends AppCompatActivity {
    EditText e11,e12,e13,e14,e15;
    Button fetch,update,delete;
    String s11,s12,s13,s14,s15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        e11=findViewById(R.id.text11);
        e12=findViewById(R.id.text22);
        e13=findViewById(R.id.text33);
        e14=findViewById(R.id.text44);
        e15=findViewById(R.id.text55);
        fetch=findViewById(R.id.fetchh);
        update=findViewById(R.id.updatee);
        delete=findViewById(R.id.deletee);

        s11=e11.getText().toString();
        s12=e12.getText().toString();
        s13=e13.getText().toString();
        s14=e14.getText().toString();
        s15=e15.getText().toString();

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s11=e11.getText().toString();
                getdata();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Main2Activity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                s11=e11.getText().toString();
                s12=e12.getText().toString();
                s13=e13.getText().toString();
                s14=e14.getText().toString();
                s15=e15.getText().toString();
                update();


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s11=e11.getText().toString();
                delete();

            }
        });
    }

    public void getdata(){
        if (s11.contentEquals("")){
            Toast.makeText(this, "Please give your ID", Toast.LENGTH_SHORT).show();
        }
        RequestQueue rq=Volley.newRequestQueue(Main2Activity.this);
        String url = Config.DATA_URL+s11;
        StringRequest sr=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showjson(response);
                Toast.makeText(Main2Activity.this, "Data Fetch", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Data Not Fetch", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(sr);
    }

    public void showjson(String response){        // JSON is a Javascript Object Notation which is start from curly braces or array.
                                   // There are two types of web services
                                   // 1: REST full Api = Representational State Transfer
                                   // 2; SOAP full API = Simple Object Access Protocol
                                   // { - JSON Object
                                   // [ - JSON Array
                                   // JSONObject jo = new JSONObject(response);
                                   // JSONArray jr = jo.getJSONArray(config.key_result);
                                   // JSONObject jt = jr.getJSONObject( O );
        String names="";
        String address="";
        String qualis="";
        String contacts="";

        try {

            JSONObject jo = new JSONObject(response);
            JSONArray jr = jo.getJSONArray(Config.KEY_RESULT);
            JSONObject jt = jr.getJSONObject(0);
            names = jt.getString(Config.KEY_NAME);
            address = jt.getString(Config.KEY_ADDRESS);
            qualis = jt.getString(Config.KEY_QUALIFICATION);
            contacts = jt.getString(Config.KEY_CONTACT);
        }catch (JSONException e) {
            e.printStackTrace();

        }
        e12.setText(names);
        e13.setText(address);
        e14.setText(qualis);
        e15.setText(contacts);


    }

    public void update() {
        RequestQueue rq=Volley.newRequestQueue(Main2Activity.this);
        String url="https://rahulsoni442000.000webhostapp.com/studentdata/update.php";
        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Main2Activity.this, "Update...", Toast.LENGTH_SHORT).show();
                e11.setText("");
                e12.setText("");
                e13.setText("");
                e14.setText("");
                e15.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Not Update...", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hm1=new HashMap<>();
                hm1.put("i",s11);
                hm1.put("n",s12);
                hm1.put("a",s13);
                hm1.put("q",s14);
                hm1.put("m",s15);
                return hm1;
            }
        };
        rq.add(sr);
    }


    public void delete(){
        if (s11.contentEquals("")){
            Toast.makeText(this, "Please give your ID", Toast.LENGTH_SHORT).show();
        }
        RequestQueue rq=Volley.newRequestQueue(Main2Activity.this);
        String url = Delete.DATA_URL+s11;
        StringRequest sr=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showjsonn(response);
                Toast.makeText(Main2Activity.this, "Data Delete", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Data Not Delete", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(sr);
    }

    public void showjsonn(String response){
        String names="";
        String address="";
        String qualis="";
        String contacts="";

        try {

            JSONObject jo = new JSONObject(response);
            JSONArray jr = jo.getJSONArray(Config.KEY_RESULT);
            JSONObject jt = jr.getJSONObject(0);
            names = jt.getString(Config.KEY_NAME);
            address = jt.getString(Config.KEY_ADDRESS);
            qualis = jt.getString(Config.KEY_QUALIFICATION);
            contacts = jt.getString(Config.KEY_CONTACT);
        }catch (JSONException e) {
            e.printStackTrace();

        }
        e12.setText(names);
        e13.setText(address);
        e14.setText(qualis);
        e15.setText(contacts);


    }
}
