package com.example.refrigerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
//메인 페이지
public class Activity1 extends Activity {
	
	private DBAdapter mDb;
    private ArrayList<List> mlist;
	private ListView listView;
	private ArrayAdapter<List> mAdapter;



	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.main);

		Intent intent = getIntent();
		String name = intent.getStringExtra("name"); // Activity2의 값을 전달받아 String 변수들에게 저장
		String buyday = intent.getStringExtra("buyday");
		String shelflife = intent.getStringExtra("shelflife");
		String party = intent.getStringExtra("party");


		mDb = new DBAdapter(this); //DBAdapter 객체 생성
		mlist = mDb.getAlllist(); //listview에 DB에 저장된 모든 레코드를 가져옴
		mAdapter = new ArrayAdapter<List>(this, android.R.layout.simple_list_item_1, mlist); // 텍스트만 출력하는 기본 레이아웃

		listView = (ListView)findViewById(R.id.listView); // listview 연결
		//listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 선택을 단일선택으로 지정
		listView.setAdapter(mAdapter);

		mDb.insertList(name, buyday, shelflife, party); // DB에 name, buyday, shelflife, count 입력

		refreshList(); //메소드 호출, 최신화를 해줌






		final AlertDialog.Builder builder = new AlertDialog.Builder(this); // AlertDialog â ����
    	builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
    	@Override
    	public void onClick(DialogInterface dialog, int which) {
    	Activity1.this.finish();
    	}
    	}).setNegativeButton("취소", new DialogInterface.OnClickListener() {
    	@Override
    	public void onClick(DialogInterface dialog, int which) {
    	dialog.cancel();
    	}
    	}).setMessage("어플리케이션을 종료하시겠습니까?").setTitle("어플리케이션 종료");
        
    Button b1 = (Button)findViewById(R.id.button1);
    Button b2 = (Button)findViewById(R.id.button2);
    Button b3 = (Button)findViewById(R.id.button3);
	Button b6 = (Button)findViewById(R.id.button6);
	Button b7 = (Button)findViewById(R.id.button7);

	Button b4 = (Button)findViewById(R.id.button4); //sns
	Button b5 = (Button)findViewById(R.id.button5); //news
	
	b1.setOnClickListener(new OnClickListener() { // ��ǰ��� ��ư�� ������ Activity2�� ��ȯ
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity2.class);
				startActivity(intent);
			}
		});  
	
	b2.setOnClickListener(new OnClickListener() { // ��ǰ��� ��ư�� ������ Activity3�� ��ȯ
		public void onClick(View v) {
			Intent intent = new Intent(Activity1.this, Activity3_1.class);
			startActivity(intent);
		}
	});  	
	
	b3.setOnClickListener(new OnClickListener() { // ���� ��ư�� ������ AlertDialogâ ����
		public void onClick(View v) {
			builder.show();
		}
	});

	b6.setOnClickListener(new OnClickListener() { // ��ǰ��� ��ư�� ������ Activity3�� ��ȯ
			public void onClick(View v) {
			Intent intent = new Intent(Activity1.this, question.class);
			startActivity(intent);
		}
		});

	b7.setOnClickListener(new OnClickListener() { // ��ǰ��� ��ư�� ������ Activity3�� ��ȯ
			public void onClick(View v) {
			Intent intent = new Intent(Activity1.this, exclamation.class);
			startActivity(intent);
		}
		});


		b4.setOnClickListener(new OnClickListener() { // ��ǰ��� ��ư�� ������ Activity3�� ��ȯ
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, sns_list.class);
				startActivity(intent);
			}
		});

		b5.setOnClickListener(new OnClickListener() { // ��ǰ��� ��ư�� ������ Activity3�� ��ȯ
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, news_list.class);
				startActivity(intent);
			}
		});
	}

	private void refreshList() {
		mlist.clear();
		mlist.addAll(mDb.getAlllist());
		mAdapter.notifyDataSetChanged();
	}
    
}




