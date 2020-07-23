package com.example.googlemaps.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ConnectionsDatabaseHelper extends EmployeeDatabaseHelper{

    public ConnectionsDatabaseHelper(Context context) { super(context); }

    public boolean hasForConnection( int employeeId, int attributeId ){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+employeeAttributeTableName+" where "+employeeAttributeColumnEmployeeId+"="+employeeId+" and "+employeeAttributeColumnAttributeId+"="+attributeId;
        Log.i("ConnectionsDatabaseHelp", query);
        Cursor res =  db.rawQuery( query, null );
        if(res.moveToFirst())
            return true;
        else
            return false;
    }

    public void insertConnection( int employeeId, int attributeId ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(employeeAttributeColumnEmployeeId, employeeId);
        contentValues.put(employeeAttributeColumnAttributeId, attributeId);
        db.insert(employeeAttributeTableName, null, contentValues);
    }

    public void deleteConnection( int employeeId, int attributeId ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(employeeAttributeTableName, employeeAttributeColumnEmployeeId+" = ? and "+employeeAttributeColumnAttributeId+" = ?", new String[] { Integer.toString(employeeId), Integer.toString(attributeId) });
    }

    public void deleteConnectionBasedOnAttribute( int attributeId ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(employeeAttributeTableName, employeeAttributeColumnAttributeId+" = ?", new String[] { Integer.toString(attributeId) });
    }

    public void deleteConnectionBasedOnEmployee( int employeeId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(employeeAttributeTableName, employeeAttributeColumnEmployeeId+" = ? ", new String[] { Integer.toString(employeeId) });
    }

    public ArrayList<Employee> getEmployeesBasedOnAttributeId( int attributeId )
    {
        ArrayList<Employee> allEmployees = new ArrayList<Employee>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+employeeTableName+", "+employeeAttributeTableName+" where "
                +employeeColumnId+"="+employeeAttributeColumnEmployeeId
                +" and "+employeeAttributeColumnAttributeId+"="+attributeId;
        Cursor res =  db.rawQuery(query , null );

        if(res.moveToFirst())
        {
            do{
                int code = res.getInt( res.getColumnIndex(employeeColumnId) );
                String name = res.getString( res.getColumnIndex(employeeColumnName) );
                String dateOfBirth = res.getString( res.getColumnIndex(employeeColumnDateOfBirth) );
                int hasCar = res.getInt( res.getColumnIndex(employeeColumnHasCar) );
                String address = res.getString( res.getColumnIndex(employeeColumnAddress) );
                double latitude = res.getDouble( res.getColumnIndex(employeeColumnLat));
                double longitude = res.getDouble( res.getColumnIndex(employeeColumnLon));
                allEmployees.add(new Employee(code, name, dateOfBirth, hasCar, address, latitude, longitude));
            }while(res.moveToNext());
        }

        return allEmployees;
    }

}
