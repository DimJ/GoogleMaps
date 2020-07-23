package com.example.googlemaps.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Map.db";

    public final String attributesTableName = "attribute";
    public final String attributesColumnId = "id";
    public final String attributesColumnName = "name";

    public final String employeeTableName = "employee";
    public final String employeeColumnId = "id";
    public final String employeeColumnName = "name";
    public final String employeeColumnDateOfBirth = "dateOfBirth";
    public final String employeeColumnHasCar = "hasCar";
    public final String employeeColumnAddress = "address";
    public final String employeeColumnLat = "latitude";
    public final String employeeColumnLon = "longitude";

    public final String employeeAttributeTableName = "employeeAttribute";
    public final String employeeAttributeColumnEmployeeId = "employeeId";
    public final String employeeAttributeColumnAttributeId = "attributeId";

    public DatabaseHelper(Context context){
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table attribute (id integer primary key, name text )");
        sqLiteDatabase.execSQL("create table employee (id integer primary key, name text, dateOfBirth text, hasCar integer, address text, latitude real, longitude real )");
        sqLiteDatabase.execSQL("create table employeeAttribute (employeeId integer, attributeId integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS attribute");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS emplolyee");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS employeeAttribute");
        onCreate(sqLiteDatabase);
    }

    public int numberOfAttributes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from attribute ", null );
        int code = 0;
        if(res.moveToLast())
            code = res.getInt( res.getColumnIndex(attributesColumnId) );

        return code;
    }

    public Attribute getAttribute(int id) {
        Attribute attribute = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+attributesTableName+" where id="+id+"", null );
        if(res.moveToFirst())
        {
            int code = res.getInt( res.getColumnIndex(attributesColumnId) );
            String name = res.getString( res.getColumnIndex(attributesColumnName) );
            attribute = new Attribute(code, name);
        }
        return attribute;
    }

    public ArrayList<Attribute> getAllAttributes()
    {
        ArrayList<Attribute> allAttributes = new ArrayList<Attribute>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from attribute ", null );

        if(res.moveToFirst())
        {
            do{
                int code = res.getInt( res.getColumnIndex(attributesColumnId) );
                String name = res.getString( res.getColumnIndex(attributesColumnName) );
                allAttributes.add(new Attribute(code, name));
            }while(res.moveToNext());
        }

        return allAttributes;
    }

    public void insertAttribute( int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(attributesColumnId, id);
        contentValues.put(attributesColumnName, name);
        db.insert("attribute", null, contentValues);
    }

    public void updateAttribute (int id, String name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(attributesColumnName, name);
        db.update(attributesTableName, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
    }

    public void deleteAttribute (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(attributesTableName, "id = ? ", new String[] { Integer.toString(id) });
    }

}
