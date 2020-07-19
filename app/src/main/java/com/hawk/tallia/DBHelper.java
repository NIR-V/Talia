/**
 * @author Abir Haque
 * @version 1.0
 * @since 2020-07-12
 */
package com.hawk.tallia;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "journal.db";

    public static final String TABLE_NAME = "entries";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String RATING = "rating";
    public static final String ENTRY = "entry";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id integer primary key, date text, rating text, entry text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertEntry (String date, String rating, String entry ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("rating", rating);
        contentValues.put("entry", entry);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " +  TABLE_NAME + " where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();
        int numRows = 0;
        while(res.isAfterLast() == false){
            numRows++;
            res.moveToNext();
        }
        db.close();
        return numRows;
    }

    public boolean updateEntry (Integer id, String date, String rating, String entry ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("rating", rating);
        contentValues.put("entry", entry);
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public void deleteEntry (Integer id, String date, String rating, String entry) {
        int numRows = Integer.valueOf(numberOfRows());
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if (numRows==id)
        {
            db.delete(TABLE_NAME, "id = " + id, null);
        }
        else
        {
            int i = 0;
            while (res.isAfterLast() == false) {
                res.moveToPosition(id + i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", id + i);
                contentValues.put("date", res.getString(res.getColumnIndex(DATE)));
                contentValues.put("rating", res.getString(res.getColumnIndex(RATING)));
                contentValues.put("entry", res.getString(res.getColumnIndex(ENTRY)));
                db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id + i)});
                res.moveToNext();
                i++;
            }
            int index = i + id;
            db.delete(TABLE_NAME, "id = " + index, null);
            db.close();
        }
    }

    public ArrayList<String> getAllDates()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(DATE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllRating()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(RATING)));
            res.moveToNext();
        }
        return array_list;
    }
}