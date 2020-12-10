package com.example.cloneprjocetpts_kavita12rpl11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

public class EditSepeda extends AppCompatActivity {
    EditText texmerk;
    EditText txtkode;
    EditText txtwarna;
    EditText txtharga;
    EditText txtjenis;
    Button btnedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sepeda);

        texmerk = findViewById(R.id.merk);
        txtkode = findViewById(R.id.kode);
        txtwarna = findViewById(R.id.warna);
        txtharga = findViewById(R.id.hargasewa);
        txtjenis = findViewById(R.id.jenis);
        btnedit = findViewById(R.id.btn_editspd);

        Bundle extras = getIntent().getExtras();
        final String Id = extras.getString("id");
        final String merk = extras.getString("merk");
        final String kodesepeda = extras.getString("kodesepeda");
        final String warna = extras.getString("warna");
        final String hargasewa = extras.getString("hargasewa");
        final String jenis = extras.getString("jenis");

        texmerk.setText(merk);
        txtkode.setText(kodesepeda);
        txtwarna.setText(warna);
        txtharga.setText(hargasewa);
        txtjenis.setText(jenis);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> body = new HashMap<>();
                body.put("id", Id);
                body.put("merk",texmerk.getText().toString());
                body.put("kodesepeda",txtkode.getText().toString());
                body.put("warna",txtwarna.getText().toString());
                body.put("hargasewa",txtharga.getText().toString());
                body.put("jenis",txtjenis.getText().toString());

                AndroidNetworking.post(BaseURL.url+"updateSepeda.php")
                        .addBodyParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .setOkHttpClient(((initial) getApplication()).getOkHttpClient())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("TAG", "respon: "+response);
                                String message = response.optString("message");

                                Toast.makeText(EditSepeda.this, message, Toast.LENGTH_LONG).show();
                                Log.d("AS", "onResponse: "+message);
                                if (message.equalsIgnoreCase("success")) {
                                    Toast.makeText(EditSepeda.this,"Update Sepeda berhasil",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(EditSepeda.this,"Update Sepeda gagal",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("A", "onError: " + anError.getErrorBody());
                                Log.d("A", "onError: " + anError.getLocalizedMessage());
                                Log.d("A", "onError: " + anError.getErrorDetail());
                                Log.d("A", "onError: " + anError.getResponse());
                                Log.d("A", "onError: " + anError.getErrorCode());

                            }
                        });

            }
        });
    }
}