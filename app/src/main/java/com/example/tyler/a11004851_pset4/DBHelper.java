package com.example.tyler.a11004851_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by tyler on 25-9-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContactDB.db";
    private static final String KEY_ID = "id";
    private static final String KEY_TODO = "todo";
    private static final String KEY_CHECKED = "checked";
    private static final String TABLE = "contactTable";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TODO
                + " TEXT NOT NULL," + KEY_CHECKED + " TEXT NOT NULL)";

        db.execSQL(CREATE_DB);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(todo contact) {
        SQLiteDatabase db = getWritableDatabase();
//        onUpgrade(db, 1, 1);
        ContentValues values = new ContentValues();
        values.put(KEY_TODO, contact.getTodo());
        values.put(KEY_CHECKED, contact.getChecked());
        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<todo> read() {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<todo> contacts = new ArrayList<>();

        String query = "SELECT " + KEY_ID + ", " + KEY_TODO + ", " + KEY_CHECKED + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex(KEY_TODO));
                int number = cursor.getInt(cursor.getColumnIndex(KEY_CHECKED));
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));

                todo contact = new todo(name, number, id);
                contacts.add(contact);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
     }

     public int update(todo contact) {
         SQLiteDatabase db = getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(KEY_TODO, contact.getTodo());
         values.put(KEY_CHECKED, contact.getChecked());

         return db.update(TABLE, values, KEY_ID + " = ? ", new String[] {String.valueOf(contact.getID())});
     }

     public void delete(todo contact) {
         SQLiteDatabase db = getWritableDatabase();
         db.delete(TABLE, "" + KEY_ID + " = ? ", new String[] {String.valueOf(contact.getID())});
         db.close();
     }

}
