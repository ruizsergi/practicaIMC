package com.example.slafuente.imc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by slafuente on 22/01/2017.
 */

public class BaseDatosRegistro extends SQLiteOpenHelper{

    //Query para crear la tabla usuario
    private static final String sqlCreacionTablaUsuarios = "" +
            "CREATE TABLE usuario(email TEXT PRIMARY KEY, password TEXT)";

    /**
     * Constructor de BaseDatosRegistro
     * @param context el contexto
     * @param nombre nombre de la bbdd
     * @param cursorFactory
     * @param version version de la bbdd
     */
    public BaseDatosRegistro(Context context, String nombre, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context,nombre,cursorFactory,version);
    }
    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTablaUsuarios);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //extraer datos antgua version (selects)
        //crear nueva version   (create)
        //copiar datos antiguos a los nuevos  (inserts)
        //everything by  sql queries
    }

    /**
     * Cerramos la base de datos
     * @param db base de datos
     */
    private void cerrarBaseDatos(SQLiteDatabase db) {
        db.close();
    }

    /**
     * Insertamos un usuario en bbdd
     * @param usuario
     */
    public void insertarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO usuario (email, password) VALUES ('"+ usuario.getEmail() + "'"
                + ", '" + usuario.getPassword() + "')");
        cerrarBaseDatos(db);
    }

    /**
     * Buscamos  y retornamos el usuario en la bbdd
     * @param email
     * @return Usuario encontrado
     */
    public Usuario buscarUsuario(String email, String password) {
        Usuario usuario = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String consulta = "SELECT email,password FROM usuario WHERE email ='" + email +"' AND " +
                "password ='"+ password +"'";
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.d(getClass().getCanonicalName(), "email es "  + cursor.getString(0));
            Log.d(getClass().getCanonicalName(), "password es " + cursor.getString(1));
            usuario = new Usuario(cursor.getString(0), cursor.getString(1));
        }
        //Cerramos cursor y bbdd
        cursor.close();
        cerrarBaseDatos(db);
        return usuario;
    }

    /**
     * Comprobamos que exista el usuario en bbdd mirando si existe el email
     * @param email
     * @return boolean con usuario esta o no en bbdd
     */
    public boolean existeUsuario(String email) {
        boolean existe = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String consulta = "SELECT email FROM usuario WHERE email ='" + email +"'";
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor != null && cursor.getCount() > 0) {
            existe = true;
        }
        //Cerramos cursor y bbdd
        cursor.close();
        cerrarBaseDatos(db);
        return existe;
    }
}
