package com.example.slafuente.imc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Manifest;

public class HacerFoto extends AppCompatActivity {

    private static final int CODIGO_ACTIVIDAD = 100;
    private String ruta_captura_foto;
    private static final String SUFIJO_FOTO = ".jpg";
    private static final String PREFIJO_FOTO = "TU_PIC_";
    private static final int COD_PET_PERMISOS_CAMARA = 2;
    private static final int COD_PET_PERMISOS_WREX = 3;
    private static final String MENSAJE_PERMISOS_CAMARA = "Necesito echar una foto, ¿me dejas?";
    private static final String MENSAJE_PERMISOS_WREX = "Necesito escribir en la memoria externa, ¿me dejas?";




    /**
     * Si se decide guardar la foto capturada, debo crear antes un fichero y pasar la URI (ruta) del mismo.
     * Para eso vale esta función: para crear el fichero donde será almacenado la foto y su URI
     *
     * @return La URI que identifica al fichero.
     */
    private Uri crearFicheroImagen ()
    {
        Uri uri_dest = null;
        String momento_actual = null;
        String nombre_fichero = null;
        File f = null;

        //así nos garantizamos emplear un sufijo aleatorio: el nombre del archivo de la imagen incluirá el momento exacto
        momento_actual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        nombre_fichero = PREFIJO_FOTO + momento_actual + SUFIJO_FOTO;

        ruta_captura_foto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/"+nombre_fichero;

        Log.d(getClass().getCanonicalName(), "RUTA FOTO = " + ruta_captura_foto);

        f = new File(ruta_captura_foto);

        try
        {
            if (f.createNewFile())
                Log.d(getClass().getCanonicalName(), "Fichero creado");
            else
                Log.d(getClass().getCanonicalName(), "Fichero NO creado (ya existía)");
        }
        catch (IOException e)
        {
            Log.e(getClass().getCanonicalName(), "Error creando el fichero", e);
        }

        uri_dest = Uri.fromFile(f);

        Log.d(getClass().getCanonicalName(), "URI FOTO = " + uri_dest.toString());


        return uri_dest;
    }


    private void hacerFoto ()
    {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri photoURI = crearFicheroImagen();
        //He aquí la parte opcional del código: de emplearse este parámetro,
        // la foto tomada se almacena en una localización concreta y de omitirse,
        // se almacena en una localización aleatoria
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        //el segundo parámetro es una forma de identificar la petición,
        // para poder ser recibida posteriormente, además de indicarle a Android
        // que será una Actividad HIJA
        startActivityForResult(intent, CODIGO_ACTIVIDAD);

    }

    private void pidePermiso (String permiso, int code_request, String mensaje_dialog)
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA))
        {
            Log.d(getClass().getCanonicalName(), "El permiso fue denegado");
            //podríamos mostrar un AlertDialog explicándole los motivos
        }
        //en cualquier caso, se lo pido
        ActivityCompat.requestPermissions(this, new String[]{permiso}, code_request);

    }
    private boolean tienePermisos (String permiso)
    {
        boolean bdev = false;

        bdev = (ContextCompat.checkSelfPermission(this, permiso)== PackageManager.PERMISSION_GRANTED);

        return bdev;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ((tienePermisos (android.Manifest.permission.CAMERA))&&
                (tienePermisos (android.Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            hacerFoto();
        } else if (!tienePermisos (android.Manifest.permission.CAMERA)) {
            pidePermiso (android.Manifest.permission.CAMERA, COD_PET_PERMISOS_CAMARA, MENSAJE_PERMISOS_CAMARA);
        } else {
            pidePermiso (android.Manifest.permission.WRITE_EXTERNAL_STORAGE, COD_PET_PERMISOS_WREX, MENSAJE_PERMISOS_WREX);
        }


    }

    private boolean permiso_concedido (int[] array_res) {
        return (array_res.length>0 && array_res[0] == PackageManager.PERMISSION_GRANTED);
    }

    private void salir ()  {

        Log.d(getClass().getCanonicalName(), "DENEGADO");
        Toast.makeText(this, "PERMISO ESCRITURA O CÁMARA DENEGADO ... saliendo", Toast.LENGTH_LONG);
        this.finish();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == COD_PET_PERMISOS_CAMARA) {
            if (permiso_concedido(grantResults)) {
                if (tienePermisos(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    hacerFoto();
                } else {
                    pidePermiso (android.Manifest.permission.WRITE_EXTERNAL_STORAGE, COD_PET_PERMISOS_WREX, MENSAJE_PERMISOS_WREX);
                }
            } else {
                salir();
            }
        }

        if (requestCode == COD_PET_PERMISOS_WREX) {
            if (permiso_concedido(grantResults)) {
                if (tienePermisos(android.Manifest.permission.CAMERA)) {
                    hacerFoto();
                } else {
                    pidePermiso(android.Manifest.permission.CAMERA, COD_PET_PERMISOS_WREX, MENSAJE_PERMISOS_WREX);
                }
            } else {
                salir();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(getClass().getCanonicalName(), "VUELVE DE hacer la FOTO"); //requestCode == CODIGO_ACTIVIDAD

        switch (resultCode) {
            case RESULT_OK:

                Log.d(getClass().getCanonicalName(), "La cosa fue bien Código " + resultCode);
                Bitmap bitmap = null; //la foto que se mostrará en la actividad

                if (data == null) {
                    //el fichero ha sido guarado en una ruta => se ha usado el putExtra MediaStore.EXTRA_OUTPUT
                    Log.d(getClass().getCanonicalName(), "Se empleó el parámetro MediaStore.EXTRA_OUTPUT");
                    try {
                        File imgFile = new  File(ruta_captura_foto);
                        bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    } catch (Exception e) {
                        Log.e(getClass().getCanonicalName(), "ERRORAZO recuperando la foto tomada" , e);
                    }
                }
                else {
                    //la foto ha sido capturada y devuelta en un intent =
                    // NO se ha usado el putExtra MediaStore.EXTRA_OUTPUT
                    Log.d(getClass().getCanonicalName(), "NO Se empleó el parámetro MediaStore.EXTRA_OUTPUT : se devolvió el bitmap");
                    bitmap = (Bitmap) data.getExtras().get("data");
                }

                ImageView imageView = (ImageView) findViewById(R.id.captureimg);
                imageView.setImageBitmap(bitmap);

                break;

            case RESULT_CANCELED:
                Log.d(getClass().getCanonicalName(), "La cosa se canceló " + resultCode);
                break;

            default:
                Log.d(getClass().getCanonicalName(), "FIN CON CÓDIGO " + resultCode);

        }
    }
}
