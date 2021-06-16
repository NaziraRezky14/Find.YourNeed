package com.example.findyourneed;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Objects;

public class detail extends AppCompatActivity {

    JSONObject obj;
    JSONArray data;
    TextView nama, tag, alamat, ig, TVText1, TVText2, TVText3;
    ImageView IVProduk1, IVProduk2, IVProduk3;
    String[] produkPic = new String[3];
    String[] produkText = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_detail);

        nama = (TextView) findViewById(R.id.namatoko);
        tag = (TextView) findViewById(R.id.tag);
        alamat = (TextView) findViewById(R.id.alamat);
        ig = (TextView) findViewById(R.id.ig);
        TVText1 = (TextView) findViewById(R.id.TVText1);
        TVText2 = (TextView) findViewById(R.id.TVText2);
        TVText3 = (TextView) findViewById(R.id.TVText3);

        IVProduk1 = (ImageView) findViewById(R.id.IVProduk1);
        IVProduk2 = (ImageView) findViewById(R.id.IVProduk2);
        IVProduk3 = (ImageView) findViewById(R.id.IVProduk3);

        Intent intent = getIntent();
        String idDetail = intent.getStringExtra("id");

        try {
            this.obj = new JSONObject(loadJSONFromAsset());
            this.data = obj.getJSONArray("data");

            nama.setText(getNama(data, idDetail));
            tag.setText(getTag(data, idDetail));
            alamat.setText(getAlamat(data, idDetail));
            ig.setText("Instagram : " + getIG(data, idDetail));

            getProdukPic(data, idDetail);
            getProdukText(data, idDetail);

            String img1 = this.produkPic[0];
            String img2 = this.produkPic[1];
            String img3 = this.produkPic[2];

            int imageResource1 = getResources().getIdentifier(img1, null, getPackageName());
            @SuppressLint("UseCompatLoadingForDrawables") Drawable res1 = getResources().getDrawable(imageResource1);
            IVProduk1.setImageDrawable(res1);

            int imageResource2 = getResources().getIdentifier(img2, null, getPackageName());
            @SuppressLint("UseCompatLoadingForDrawables") Drawable res2 = getResources().getDrawable(imageResource2);
            IVProduk2.setImageDrawable(res2);

            int imageResource3 = getResources().getIdentifier(img3, null, getPackageName());
            @SuppressLint("UseCompatLoadingForDrawables") Drawable res3 = getResources().getDrawable(imageResource3);
            IVProduk3.setImageDrawable(res3);

            TVText1.setText(this.produkText[0]);
            TVText2.setText(this.produkText[1]);
            TVText3.setText(this.produkText[2]);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("FindYourNeed.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String getNama(JSONArray jsonArray, String inputId) throws JSONException {
        String nama = "";

        for(int i=0; i<jsonArray.length(); i++) {
            String nama_json = jsonArray.getJSONObject(i).getString("id");
            if(inputId.equals(nama_json)) {
                nama = jsonArray.getJSONObject(i).getString("toko");
                break;
            }
        }

        return nama;
    }

    public String getTag(JSONArray jsonArray, String inputId) throws JSONException {
        String data = "";

        for(int i=0; i<jsonArray.length(); i++) {
            String nama_json = jsonArray.getJSONObject(i).getString("id");
            if(inputId.equals(nama_json)) {
                data = jsonArray.getJSONObject(i).getString("tag");
                break;
            }
        }

        return data;
    }

    public String getAlamat(JSONArray jsonArray, String inputId) throws JSONException {
        String data = "";

        for(int i=0; i<jsonArray.length(); i++) {
            String nama_json = jsonArray.getJSONObject(i).getString("id");
            if(inputId.equals(nama_json)) {
                data = jsonArray.getJSONObject(i).getString("alamat");
                break;
            }
        }

        return data;
    }

    public String getIG(JSONArray jsonArray, String inputId) throws JSONException {
        String data = "";

        for(int i=0; i<jsonArray.length(); i++) {
            String nama_json = jsonArray.getJSONObject(i).getString("id");
            if(inputId.equals(nama_json)) {
                data = jsonArray.getJSONObject(i).getString("ig");
                break;
            }
        }

        return data;
    }

    //Nested JSONArray
    public void getProdukPic(JSONArray jsonArray, String inputId) throws JSONException {
        for(int i=0; i<jsonArray.length(); i++) {
            String nama_json = jsonArray.getJSONObject(i).getString("id");
            if(inputId.equals(nama_json)) {
                JSONArray nested = jsonArray.getJSONObject(i).getJSONArray("produk");
                for(int j=0; j<nested.length(); j++) {
                    this.produkPic[j] = nested.getJSONObject(j).getString("foto");
                }
                break;
            }
        }

    }

    //Nested JSONArray
    public void getProdukText(JSONArray jsonArray, String inputId) throws JSONException {
        for(int i=0; i<jsonArray.length(); i++) {
            String nama_json = jsonArray.getJSONObject(i).getString("id");
            if(inputId.equals(nama_json)) {
                JSONArray nested = jsonArray.getJSONObject(i).getJSONArray("produk");
                for(int j=0; j<nested.length(); j++) {
                    this.produkText[j] = nested.getJSONObject(j).getString("text");
                }
                break;
            }
        }

    }
}