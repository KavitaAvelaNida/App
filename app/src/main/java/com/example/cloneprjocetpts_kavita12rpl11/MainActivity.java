package com.example.cloneprjocetpts_kavita12rpl11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText txtusername , txtpassword;
    Button btnlogin;
    TextView tvregister;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        txtusername = findViewById(R.id.edtusername);
        txtpassword = findViewById(R.id.edtpassword);
        btnlogin = findViewById(R.id.btnlogin);
        tvregister = findViewById(R.id.txtregister);


        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        sharedPreferences.edit().putString("logged", sharedPreferences.getString("logged", "missing")).apply();

        String admin = sharedPreferences.getString("logged", "missing");
        String customer = sharedPreferences.getString("logged", "missing");

        if(customer.equals("2")){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        }else if (admin.equals("1")){
            Intent intent = new Intent(MainActivity.this, ListData.class);
            startActivity(intent);
            finish();
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtusername.getText().toString();
                String password = txtpassword.getText().toString();
                progressDialog.setTitle("Logging In...");
                progressDialog.show();
                AndroidNetworking.post(BaseURL.url + "login.php")
                        .addBodyParameter("email", username )
                        .addBodyParameter("password", password)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("hasil", "onResponse: ");
                                try {
                                    JSONObject PAYLOAD = response.getJSONObject("PAYLOAD");
                                    boolean sukses = PAYLOAD.getBoolean("respon");
                                    String roleuser = PAYLOAD.getString("roleuser");
                                    Log.d("PAYLOAD", "onResponse: " + PAYLOAD);

                                    if (sukses && roleuser.equals("1")) {
                                        sharedPreferences.edit().putString("logged","admin").apply();
                                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                        startActivity(intent);
                                        finish();
                                        progressDialog.dismiss();
                                    } else if (sukses && roleuser.equals("2")){
                                        sharedPreferences.edit().putString("logged","customer").apply();
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                        progressDialog.dismiss();
                                    } else {
                                        Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                progressDialog.dismiss();
                                Log.d("TAG", "onError: " + anError.getErrorDetail());
                                Log.d("TAG", "onError: " + anError.getErrorBody());
                                Log.d("TAG", "onError: " + anError.getErrorCode());
                                Log.d("TAG", "onError: " + anError.getResponse());
                            }
                        });

            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registrasi.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);                    }
                }).create().show();
    }
}
