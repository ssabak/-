package com.example.refrigerator;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

//import android.view.ContextMenu;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ContextMenu.ContextMenuInfo;
//import android.widget.AdapterView;
//import android.widget.AdapterView.AdapterContextMenuInfo;
//import android.widget.AdapterView;

public class Note_Save extends Activity {
	private static final String DEBUG_TAG = "withpd";
	WordDBHelper helper;
	Intent intent;
	String mTitle;
	String mContent;
	String mOpen; 
	String mPw;
    Cursor cursor;
    ContentValues row;
    SQLiteDatabase db;
    Button goMain; 
    Boolean check_open;
    Dialog dialog;

    
    public void onCreate(Bundle savedInstanceState) {
    	Log.i(DEBUG_TAG,"Note_List onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_save);
        helper = new WordDBHelper(this);
        db = helper.getWritableDatabase();
        row = new ContentValues();
        String bool;

        
        intent = getIntent();
        if(intent.getExtras().getString("check").equals("save")){
	      	mTitle = intent.getExtras().getString("title");
			mContent = intent.getExtras().getString("con");
			mPw = intent.getExtras().getString("pw");
			mOpen = intent.getExtras().getString("open");
	        
			row.put("title",mTitle);
			row.put("con", mContent);
			row.put("pw", mPw); 
			row.put("open", mOpen);
			db.insert("memo", null, row); 
        }
        if(intent.getExtras().getString("check").equals("alarm")){
        	Log.i(DEBUG_TAG,"예약있을 때 : "+ intent.getExtras().getString("check"));
        	row.put("alarm","예약");
        	cursor = db.rawQuery("SELECT * FROM memo", null); 
        	startManagingCursor(cursor);  
        	
        	SimpleCursorAdapter Adapter = null; 
            Adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{"title","alarm"},new int[]{android.R.id.text1,android.R.id.text2});
            ListView list = (ListView)findViewById(R.id.list);
            goMain = (Button)findViewById(R.id.gomain);
            list.setAdapter(Adapter);
        }else{
	    	cursor = db.rawQuery("SELECT * FROM memo", null); 
	    	startManagingCursor(cursor);
	
	        SimpleCursorAdapter Adapter = null;
	        Adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{"title","open"},new int[]{android.R.id.text1,android.R.id.text2});
	        ListView list = (ListView)findViewById(R.id.list);
	        goMain = (Button)findViewById(R.id.gomain);
	        list.setAdapter(Adapter);
	        list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
				      cursor.moveToPosition(position);
				      String check = cursor.getString(4);
				      Log.i(DEBUG_TAG,"check : " +check);
					if(check.equals("공개")){
						check_open = true;
					}else
						check_open = false;
					Log.i(DEBUG_TAG,"check_open : " +check_open); 
					if(check_open.equals(true)){
						Intent intent = new Intent(getApplicationContext(),conView.class);
						intent.putExtra("position",position);
						startActivity(intent);
					}else{
						cursor.moveToPosition(position);
						Intent intent = new Intent(getApplicationContext(),Password.class);
						intent.putExtra("pass", cursor.getString(3));
						intent.putExtra("position", position);
						Log.i(DEBUG_TAG,"비공개 글 암호풀기 Password.java.");
						Log.i(DEBUG_TAG,"pass : " +cursor.getString(3));
						Log.i(DEBUG_TAG,"position : " +position);
						startActivity(intent);
					}
				}
			});
	        
        }
        goMain.setOnClickListener(new OnClickListener() { 
        	public void onClick(View v) {
        		//Intent intent = new Intent(Note_Save.this, Activity5.class);
				//startActivity(intent);
				finish();
        	}
        }); 
    }
/*
	public boolean onCreateOptionsMenu(Menu menu) { // 옵션메뉴
		menu.add(0, 1, 0, "저장된 목록 모두 삭제"); // 저장된 목록 모두 삭제 버튼
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		db = helper.getWritableDatabase();
//    			cursor.moveToPosition(index);
		Log.i(DEBUG_TAG, "delete 의 index값 : " + index);
//    			db.execSQL("DELETE FROM memo WHERE _id="+0+";");
		db.execSQL("DELETE FROM memo ");
		helper.close();
		Toast.makeText(Note_Save.this, "메모가 모두 삭제되었습니다.", Toast.LENGTH_SHORT).show();
		finish();// 토스트메세지

		return super.onOptionsItemSelected(item);
	}
	*/
    
}