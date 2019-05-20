package com.example.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Note_Write extends Activity{
	private static final String DEBUG_TAG = "withpd";
	private EditText title;
	private EditText content;
	private EditText pw;
	private String open;
	public Note_Write(){
		Log.i(DEBUG_TAG,"Note_Write 생성자 시작");
		open = "공개";
//		query = new Query(this); 
//		title.setText("");
//		content.setText("");
//		pw.setText(""); 
	}
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_write);
        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        pw = (EditText)findViewById(R.id.pw);
		pw.setEnabled(false);
		pw.setClickable(false);
		pw.setText("");
        Log.i(DEBUG_TAG,"Note_Write onCreate 끝 인텐트 받음");
	 }
	 public void mOnClick(View v){
		 Log.i(DEBUG_TAG,"Note_Write mOnClick 시작");
		 switch(v.getId()){
		 case R.id.radiobtn1 :
			 Log.i(DEBUG_TAG,"공개");
			 pw.setEnabled(false);
			 pw.setClickable(false);
			 pw.setText("");
			 open = "공개";
			 break;
		 case R.id.radiobtn2 : 
			 Log.i(DEBUG_TAG,"비공개");
 			 pw.setEnabled(true);
 			 pw.setClickable(true);
 			 open = "비공개";
			 break;
		 case R.id.back :
			 finish();
			 break;
		 case R.id.save :
			 Log.i(DEBUG_TAG,"mOnClick 의 R.id.save 로 들어왔다.");
			 if(title.getText().toString().equals("")||content.getText().toString().equals("")){
				 Toast.makeText(this, "제목과 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
				 return;
			 }else{
				 Log.i(DEBUG_TAG,"mOnClick 의 R.id.save의 else 문 시작");
				 Intent intent = new Intent(this,Note_Save.class);
				 intent.putExtra("title", title.getText().toString());
				 intent.putExtra("con", content.getText().toString());
				 intent.putExtra("pw",pw.getText().toString());
				 intent.putExtra("open",open);
				 intent.putExtra("check", "save");
				 Log.i(DEBUG_TAG,"open 값  : "+ open);
				 Log.i(DEBUG_TAG,"mOnClick 의 R.id.save의 else 문 끝");
				 if(open.equals("비공개") && pw.getText().toString().equals("")){
					 Log.i(DEBUG_TAG,""+open);
					 Toast.makeText(this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
					 return; 
				 }
//
//				 
				 Log.i(DEBUG_TAG,"Note_List로 넘어가기 위해 Activity 실행!");
				 startActivity(intent);
				finish();
			 }
			 Toast.makeText(Note_Write.this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
			 finish();
			 break;
		 }
	 }
}