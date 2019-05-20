package com.example.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends Activity {
	private static final String DEBUG_TAG = "withpd";
	EditText inpass;
	Button ok;
	String password;
	int position;
	Intent intent;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.password);
	    inpass = (EditText)findViewById(R.id.inpass);
	    ok = (Button)findViewById(R.id.ok);
	}
	public void mOnClick(View v){
		intent = getIntent();
	    password = intent.getStringExtra("pass");
	    position = intent.getIntExtra("position", 0);
	    Log.i(DEBUG_TAG,"password.java로 넘어왔다.");
	    Log.i(DEBUG_TAG,"password : "+password);
	    Log.i(DEBUG_TAG,"position : "+position);
	    Log.i(DEBUG_TAG,"확인 : "+inpass.getText().toString());
	    if(inpass.getText().toString().equals(password)){
	    	Log.i(DEBUG_TAG,"if로 들어온다.");
			intent = new Intent(getApplicationContext(),conView.class);
			intent.putExtra("position",position);
			startActivity(intent);
			finish();
	    }else if(inpass.getText().toString().equals("")){
	    	Log.i(DEBUG_TAG,"else로 들어온다.");
	    	Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
	    	return;
	    }else{
	    	Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
	    	inpass.setText("");
	    }

	}

}
