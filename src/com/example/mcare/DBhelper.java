package com.example.mcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {
static int schema_version=1;
static String DATABASE_NAME = "careDATABASE";
	public DBhelper(Context context) {
		super(context,DATABASE_NAME, null, schema_version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL("CREATE TABLE registration (id integer primary key autoincrement,name text,sname text,gender text,DOB text,age text)");
		arg0.execSQL("CREATE TABLE advice(id INTEGER PRIMARY KEY AUTOINCREMENT , symptoms TEXT,other TEXT)");
		arg0.execSQL("CREATE TABLE appointment(id INTEGER PRIMARY KEY AUTOINCREMENT, appointDate TEXT,appointTime TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS registration");
		
		onCreate(arg0);
	}

	public boolean insertData(String name,String sname, String gender, String dateinput,String age) {
		SQLiteDatabase db =this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("sname", sname);
		contentValues.put("gender", gender);
		contentValues.put("DOB", dateinput);
		contentValues.put("age", age);
		long result = db.insert("registration", null, contentValues);
		if(result== -1){
			return false;
		}else{
			return true;
		}
		
		
		// TODO Auto-generated method stub
		
	}

	public Cursor selectAll(String id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db =this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM registration WHERE id=?", new String[]{id});
		
		return cursor;
	}

	public Cursor getKeyData() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("SELECT * FROM registration ", null);
		return res;
	}
	
	public Cursor getKeyData(String entredNumber) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("SELECT * FROM registration ", null);
		return res;
	}

	public boolean updateDetails(String id,String name,String sname, String date, String gender,String age) {
		SQLiteDatabase db =this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", id);
		contentValues.put("name", name);
		contentValues.put("sname", sname);
		contentValues.put("gender", gender);
		contentValues.put("DOB", date);
		contentValues.put("age", age);
		db.update("registration", contentValues, "id = ?", new String[]{id});
		return true;
		// TODO Auto-generated method stub
		
	}

	public boolean insertAdvice(String symptoms, String other) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cvs = new ContentValues();
		cvs.put("symptoms", symptoms);
		cvs.put("other", other);
		long result = db.insert("advice", null, cvs);
		if(result == -1){
			return false;
		}else{
			return true;
		}
	}

	public boolean confirmAppointment(String date, String time) {
		// TODO Auto-generated method stub
		SQLiteDatabase db =this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("appointDate", date);
		contentValues.put("appointTime", time);
		long result = db.insert("appointment", null, contentValues);
		if(result ==-1){
			return false;
		}else
		return true;
	}

}
