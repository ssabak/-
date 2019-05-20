package com.example.refrigerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "listt.db";
    private static final int VERSION = 1;
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String BUYDAY = "buyday";
    private static final String SHELFLIFE = "shelflife";
    private static final String PARTY = "party";

    private static final String TABLE_NAME = "list";
    private static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
        ID + " integer primary key autoincrement, " +
        NAME + " text not null, " +
        BUYDAY + " text not null, " +
        SHELFLIFE + " text not null, " +
        PARTY + " text not null )";

    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE); // CREATE_TABLE
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    //creat

    public boolean insertList(String name, String buyday, String shelflife, String party){
    	ContentValues cv = new ContentValues(); // cv
    	cv.put(NAME, name);
    	cv.put(BUYDAY, buyday);
    	cv.put(SHELFLIFE, shelflife);
    	cv.put(PARTY, party);
    	return db.insert(TABLE_NAME, null, cv) != -1; //
    }
    
    // read

    public ArrayList<List> getAlllist() {
    	ArrayList<List> list = new ArrayList<List>();
    	Cursor c = db.query(TABLE_NAME, new String[] {ID, NAME, BUYDAY, SHELFLIFE, PARTY},
    			null, null, null, null, ID + " ASC"); //
    	
    	if(c.moveToFirst()) { //
    		final int indexId = c.getColumnIndex(ID);
    		final int indexName = c.getColumnIndex(NAME);
    		final int indexBuyday = c.getColumnIndex(BUYDAY);
    		final int indexShelflife = c.getColumnIndex(SHELFLIFE);
    		final int indexParty = c.getColumnIndex(PARTY);
    		
    		do { //
    			int id = c.getInt(indexId);
    			String name = c.getString(indexName);
    			String buyday = c.getString(indexBuyday);
    			String shelflife = c.getString(indexShelflife);
    			String party = c.getString(indexParty);
    			list.add(new List(id, name, buyday, shelflife, party));
    		} while(c.moveToNext()); //
    	}
    	c.close();
    	
    	return list;
    }
    
    // update

    public long updateList(int id, String name, String buyday, String shelflife, String score) {
    	ContentValues cv = new ContentValues(); // cv
    	cv.put(NAME, name);
    	cv.put(BUYDAY, buyday);
    	cv.put(SHELFLIFE, shelflife);
    	cv.put(PARTY, score);
    	String[] params = new String[] { Integer.toString(id) };
    	int result = db.update(TABLE_NAME, cv, ID + "=?", params); //
    	return result;
    }
    
    //delete
    //
    public boolean deleteList(int id) { //
    	String[] params = new String[] {Integer.toString(id) };
    	int result = db.delete(TABLE_NAME, ID + "=?", params); //
    	return result > 0;
    }
    
    //
    public boolean deleteAll() {
    	int result=db.delete(TABLE_NAME, null, null); //
    	return result > 0;
    }
}
