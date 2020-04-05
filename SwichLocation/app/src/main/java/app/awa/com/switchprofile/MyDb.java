package app.awa.com.switchprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malli on 8/1/2016.
 */
public class MyDb extends SQLiteOpenHelper {
    //create the bd name
    public static final String DB_NAME="EmpInfo";
    public static final String TABLE_NAME="emp";//table name
    //table field
    public static final String NAME="lati";
    public static final String PHONE="longi";
    public static final String ID="id";

//constructor
    public MyDb(Context context) {
        super(context, DB_NAME, null, 1);//1=da version
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
   sqLiteDatabase.execSQL("create table emp"+"(id integer primary key,lati text,longi text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
    //insert the values
    public void insertAllData(app.awa.com.switchprofile.EmpInfo empInfo){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,"1");
        values.put(NAME,empInfo.getLati());
        values.put(PHONE,empInfo.getLongi());

        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
    }
    //get the data
    public List<app.awa.com.switchprofile.EmpInfo> getAllData(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        List<app.awa.com.switchprofile.EmpInfo> list=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from emp",null);
        if (cursor.moveToFirst()){
            do {
                app.awa.com.switchprofile.EmpInfo empInfo=new app.awa.com.switchprofile.EmpInfo();//create the constructor from EmpInfo
                empInfo.setLati(cursor.getString(1));
                empInfo.setLongi(cursor.getString(2));

                //add the values from list
                list.add(empInfo);

            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        cursor.close();
        return list;
    }
}
