package com.example.cloneprjocetpts_kavita12rpl11;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Registrasi extends AppCompatActivity {

    EditText txtemail;
    EditText txtnamalengkap;
    EditText txtpassword;
    EditText txtnohp;
    EditText txtnoktp;
    EditText txtalamat;
    Button btnregister;
    TextView tvlogin;

    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        txtemail = (EditText)findViewById(R.id.email);
        txtnamalengkap = (EditText)findViewById(R.id.nama);
        txtpassword = (EditText)findViewById(R.id.password);
        txtnohp = (EditText)findViewById(R.id.nohp);
        txtalamat = (EditText)findViewById(R.id.alamat);
        txtnoktp = (EditText)findViewById(R.id.noktp);
        btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String nama = txtnamalengkap.getText().toString();
                String nohp = txtnohp.getText().toString();
                String alamat = txtalamat.getText().toString();
                String noktp = txtnoktp.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(Registrasi.this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (password.isEmpty()){
                    Toast.makeText(Registrasi.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (nama.isEmpty()){
                    Toast.makeText(Registrasi.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (nohp.isEmpty()){
                    Toast.makeText(Registrasi.this, "Nohp tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (alamat.isEmpty()){
                    Toast.makeText(Registrasi.this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (noktp.isEmpty()){
                    Toast.makeText(Registrasi.this, "Noktp tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                AndroidNetworking.post(BaseURL.url+"registrasi.php")
                        .addBodyParameter("noktp", txtnoktp.getText().toString())
                        .addBodyParameter("email", txtemail.getText().toString())
                        .addBodyParameter("password", txtpassword.getText().toString())
                        .addBodyParameter("nama", txtnamalengkap.getText().toString())
                        .addBodyParameter("nohp", txtnoktp.getText().toString())
                        .addBodyParameter("alamat", txtalamat.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean respon = hasil.getBoolean("respon");
                                    Log.d("STATUS", "onResponse: " + hasil);
                                    if (respon) {
                                        sharedPreferences.edit().putString("logged","customer").apply();
                                        Intent intent = new Intent(Registrasi.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
//                                        progressDialog.dismiss();
                                    } else {
                                        Toast.makeText(Registrasi.this, "gagal", Toast.LENGTH_SHORT).show();
//                                        progressDialog.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                                Log.d("RBA", "onError: " + error.getErrorBody());
                                Log.d("RBA", "onError: " + error.getLocalizedMessage());
                                Log.d("RBA", "onError: " + error.getErrorDetail());
                                Log.d("RBA", "onError: " + error.getResponse());
                                Log.d("RBA", "onError: " + error.getErrorCode());
                            }
                        });
            }
        });
        tvlogin = (TextView) findViewById(R.id.tvlogin);
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registrasi.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
        }
