package com.example.googlemaps.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class EmployeeDatabaseHelper extends DatabaseHelper {

    public EmployeeDatabaseHelper(Context context){
        super(context);
    }

    public int numberOfEmployees(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+employeeTableName, null );
        int code = 0;
        if(res.moveToLast())
            code = res.getInt( res.getColumnIndex(employeeColumnId) );

        return code;
    }

    public Employee getEmployee(int id) {
        Employee employee = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+employeeTableName+" where id="+id+"", null );
        if(res.moveToFirst())
        {
            int code = res.getInt( res.getColumnIndex(employeeColumnId) );
            String name = res.getString( res.getColumnIndex(employeeColumnName) );
            String dateOfBirth = res.getString( res.getColumnIndex(employeeColumnDateOfBirth) );
            int hasCar = res.getInt( res.getColumnIndex(employeeColumnHasCar) );
            String address = res.getString( res.getColumnIndex(employeeColumnAddress) );
            double latitude = res.getDouble( res.getColumnIndex(employeeColumnLat));
            double longitude = res.getDouble( res.getColumnIndex(employeeColumnLon));

            employee = new Employee(code, name, dateOfBirth, hasCar, address, latitude, longitude);
        }
        return employee;
    }

    public ArrayList<Employee> getAllEmployees()
    {
        ArrayList<Employee> allEmployees = new ArrayList<Employee>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+employeeTableName, null );

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

    public void insertEmployee( int id, String name, String dateOfBirth, int hasCar, String address, double latitude, double longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(employeeColumnId, id);
        contentValues.put(employeeColumnName, name);
        contentValues.put(employeeColumnDateOfBirth, dateOfBirth);
        contentValues.put(employeeColumnHasCar, hasCar);
        contentValues.put(employeeColumnAddress, address);
        contentValues.put(employeeColumnLat, latitude);
        contentValues.put(employeeColumnLon, longitude);
        db.insert(employeeTableName, null, contentValues);
    }

    public void updateEmployee (int id, String name, String dateOfBirth, int hasCar, String address, double latitude, double longitude ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(employeeColumnName, name);
        contentValues.put(employeeColumnDateOfBirth, dateOfBirth);
        contentValues.put(employeeColumnHasCar, hasCar);
        contentValues.put(employeeColumnAddress, address);
        contentValues.put(employeeColumnLat, latitude);
        contentValues.put(employeeColumnLon, longitude);
        db.update(employeeTableName, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
    }

    public void deleteEmployee (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(employeeTableName, "id = ? ", new String[] { Integer.toString(id) });
    }

}
