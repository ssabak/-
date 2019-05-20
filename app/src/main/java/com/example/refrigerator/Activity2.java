package com.example.refrigerator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
// 등록 페이지
public class Activity2 extends Activity {
	
	TextView buyView; // 구입날짜 표시할 TextView
	TextView shelflifeView; // 유통기한 날짜 표시할 TextView
	Button buybtn; // 구입날짜 지정 버튼
	Button shelflifebtn; // 유통기한 날짜 지정 버튼
	Button b3; // 등록 버튼
	Button b4; // 취소 버튼
	EditText editText1; // 개수
	EditText editText2; // 물품 이름
	TextView textView5; // 구입날짜
	TextView textView6; // 유통기한
	int myear; // Buyday 저장 변수
	int mmonth;
	int mday;
	int mmyear; // Shelflife 저장 변수
	int mmmonth;
	int mmday;
    String name; // 저장될 품목 이름 String 형태로 저장
    String buyday;// 저장될 품목 구입날짜 String 형태로 저장
    String shelflife;// 저장될 품목 유통기간 String 형태로 저장
    String party; // 저장될 품목 개수 String 형태로 저장
	
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);	 	
    	setContentView(R.layout.reg);
    	
    	buyView = (TextView)findViewById(R.id.textView5); // xml의 textView와 editText 연결
    	shelflifeView = (TextView)findViewById(R.id.textView6);
    	editText1 = (EditText)findViewById(R.id.editText1);
    	editText2 = (EditText)findViewById(R.id.editText2);
    
    	buybtn = (Button)findViewById(R.id.button1); // xml의 버튼 연결
    	shelflifebtn = (Button)findViewById(R.id.button2);
    	b3 = (Button)findViewById(R.id.button3);
    	b4 = (Button)findViewById(R.id.button4);
    
    	Calendar calendar = new GregorianCalendar(); // Calendar 객체 선언
    	myear = calendar.get(Calendar.YEAR); // 현재 년도를 불러옴
    	mmonth = calendar.get(Calendar.MONTH); // 현재 월을 불러옴
    	mday = calendar.get(Calendar.DAY_OF_MONTH); // 현재 일수 불러옴
    
    buybtn.setOnClickListener(new OnClickListener() { // 버튼 클릭시 구입날짜 설정 데이트픽커 보임
		public void onClick(View v) {
			new DatePickerDialog(Activity2.this, mbuySetListener, myear, mmonth, mday).show();
			}
	 	});
    
    shelflifebtn.setOnClickListener(new OnClickListener() { // 버튼 클릭시 유통기한 날짜 설정 데이트픽커 보임
		public void onClick(View v) {
			new DatePickerDialog(Activity2.this, mshelfSetListener, myear, mmonth, mday).show();
			}
	 	});
          
	b3.setOnClickListener(new OnClickListener() { // 등록하기 버튼을 누를시
		public void onClick(View v) {	
			String name = editText2.getText().toString(); // 물품 이름이 name에 저장됨
			String buyday = buyView.getText().toString(); // 구입날짜가 buyday에 저장됨
			String shelflife = shelflifeView.getText().toString(); // 유통기한 날짜가 shelflife에 저장됨
			String party = editText1.getText().toString(); // 개수가 count에 저장됨
			
		    if (name.equals("")) // 만약 name에 저장된 문자열이 없다면
		        Toast.makeText(Activity2.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
		    else if (buyday.equals("__________")) // 만약 buyday에 저장된 문자열이 buyView에 저장된 문자열이라면
		        Toast.makeText(Activity2.this, "관심날짜를 선택하세요", Toast.LENGTH_SHORT).show();
		    else if (shelflife.equals("_______________")) // 만약 shelflife에 저장된 문자열이 shelflifeView에 저장된 문자열이라면
		        Toast.makeText(Activity2.this, "임기를 선택하세요", Toast.LENGTH_SHORT).show();
		    else if (party.equals("") || party.equals("0")) // 만약 count에 저장된 문자열이 없거나 0이라면
		        Toast.makeText(Activity2.this, "정당을 입력하세요", Toast.LENGTH_SHORT).show();
		    else { // 다 문자열이 기입이 되었다면
				Intent intent = new Intent(Activity2.this, Activity3.class);
				intent.putExtra("name", editText2.getText().toString());
				intent.putExtra("buyday", buyView.getText().toString());
				intent.putExtra("shelflife", shelflifeView.getText().toString());
				intent.putExtra("party", editText1.getText().toString());
		    	startActivity(intent); // Activity3로 name, buyday, shelflife, count의 값을 보냄
		    	Toast.makeText(Activity2.this, editText2.getText().toString() + " 이(가) 리스트에 추가되었습니다.", Toast.LENGTH_SHORT).show(); // 토스트메세지를 띄움
				}
			 }
	 	});
    
    b4.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			finish(); // 메인으로 돌아가기 버튼 누를시 실행중인 액티비티창이 종료됨
			}
	 	});    
    }
    
    
    private DatePickerDialog.OnDateSetListener mbuySetListener = new DatePickerDialog.OnDateSetListener() { // 구입날짜 데이트픽커 다이얼로그 메소드
    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    		myear = year;
    		mmonth = monthOfYear;
    		mday = dayOfMonth;
    		
    		updatebuy(); 
    	}
    };
    
    private DatePickerDialog.OnDateSetListener mshelfSetListener = new DatePickerDialog.OnDateSetListener() { // 유통기한 날짜 데이트픽커 다이얼로그 메소드
    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    		mmyear = year;
    		mmmonth = monthOfYear;
    		mmday = dayOfMonth;
    		
    		updateshelflife(); 
    	}
    };
	
	private void updatebuy() { // 구입날짜를 텍스트뷰 buyView에 세팅함
		buyView.setText(new StringBuilder()
				.append(myear).append("/").append(mmonth+1).append("/")
				.append(mday).append(" ")); // buyView에 myear/mmonth/mday 저장함
		Toast.makeText(Activity2.this, "관심날짜: " + myear + "/" + (mmonth+1) + "/" + mday, Toast.LENGTH_SHORT).show();
		//데이트픽커에서 세팅된 구입일 토스트메세지로 출력
    }
	
	private void updateshelflife() { // 유통기한날짜를 텍스트뷰 shelflifeView에 세팅함
		shelflifeView.setText(new StringBuilder()
				.append(mmyear).append("/").append(mmmonth+1).append("/")
				.append(mmday).append(" ")+"까지");  // shelflifeView에  myear/mmonth/mday 저장함
		Toast.makeText(Activity2.this, "임기: " + myear + "/" + (mmonth+1) + "/" + mday + "까지", Toast.LENGTH_SHORT).show(); // 2013/11/13까지
		//데이트픽커에서 세팅된 유통기한 토스트메세지로 출력
    }
}
