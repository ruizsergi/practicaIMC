package com.example.slafuente.imc.vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.slafuente.imc.R;

import java.io.IOException;

public class PantallaFoto extends AppCompatActivity {

    private Button botonHacerFoto;
    private ImageView marcoFoto;

    private int ancho = 0;
    private int alto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_foto);

        botonHacerFoto = (Button) findViewById(R.id.buttonFoto);
        //cojo marcofoto y le hago register para que lo asocie al contextmenu
        marcoFoto = (ImageView) findViewById(R.id.idmarcofoto);
        registerForContextMenu(marcoFoto);

        //Recupero la uri de la foto
        String uriFoto = getIntent().getStringExtra("imageUri");
        if (uriFoto != null && !("").equals(uriFoto)) {
            Uri myUri = Uri.parse(uriFoto);

            Bitmap  bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), myUri);
                //bitmap = ajustaFoto(bitmap);
                marcoFoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        botonHacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hacerFoto = new Intent(getApplicationContext(), HacerFoto.class);
                startActivity(hacerFoto);

            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.i("APP", "estoy inflando context menu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.idmakefoto:
                Intent hacerFoto = new Intent(getApplicationContext(), HacerFoto.class);
                startActivity(hacerFoto);
                return true;
            case R.id.choosefoto:
                Intent selectFoto = new Intent(getApplicationContext(), SeleccionarFotoActivity.class);
                startActivity(selectFoto);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Ajusta la foto a un determinado tamano
     * @param bm
     * @return Bitmap ajustado
     */
    public Bitmap ajustaFoto (Bitmap bm) {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.id.idmarcofoto,options);
        alto = options.outHeight;
        ancho = options.outWidth;

        int ratio;
        options.inJustDecodeBounds = false;
        if(ancho/width > alto/height ){
            ratio = ancho/width;
        }
        else{
            ratio= alto/height;
        }
        options.inSampleSize = ratio;
        bm = BitmapFactory.decodeResource(getResources(), R.id.idmarcofoto, options);
        return bm;
    }
}
