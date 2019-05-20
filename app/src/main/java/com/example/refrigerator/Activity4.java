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
//수정페이지
public class Activity4 extends Activity {
	
	private DBAdapter mDb;

	TextView buyView;
	TextView shelflifeView;
	Button buybtn;
	Button shelflifebtn;
	Button b3;
	Button b4;
	EditText editText1;
	EditText editText2;
	TextView textView5;
	TextView textView6;
	int myear; // Buyday ���� ����
	int mmonth;
	int mday;
	int mmyear; // Shelflife ���� ����
	int mmmonth;
	int mmday;
	Integer id;
    String name; // ����� ǰ�� �̸� String ���·� ����
    String buyday;// ����� ǰ�� ���Գ�¥ String ���·� ����
    String shelflife;// ����� ǰ�� ����Ⱓ String ���·� ����
    String count; // ����� ǰ�� ���� String ���·� ����
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        
        buyView = (TextView)findViewById(R.id.textView5);
        shelflifeView = (TextView)findViewById(R.id.textView6);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        
        buybtn = (Button)findViewById(R.id.button1);
        shelflifebtn = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);	//�����ϱ� ��ư
        b4 = (Button)findViewById(R.id.button4);	//���ư��� ��ư
        
        Calendar calendar = new GregorianCalendar(); // Calendar ��ü ����
    	myear = calendar.get(Calendar.YEAR); // ���� �⵵�� �ҷ���
    	mmonth = calendar.get(Calendar.MONTH); // ���� ���� �ҷ���
    	mday = calendar.get(Calendar.DAY_OF_MONTH); // ���� �ϼ� �ҷ���
        // ����Ʈ��Ŀ�� ���� ����� myear, mmonth, mday�� ��µ�
        
        Intent iintent = getIntent(); // Activity3���� �޾ƿ� �������� ����
        id = iintent.getIntExtra("id", 0);
        name = iintent.getStringExtra("name");
        buyday = iintent.getStringExtra("buyday");
        shelflife = iintent.getStringExtra("shelflife");
        count = iintent.getStringExtra("party");
        
        // R.java�� 16������ ����Ǳ� ������ Integer�� �����Ѵ��� �ҷ��� ���� ppt�� ����
        editText2.setText(name); // name���� editText2�� Text�� ����
        buyView.setText(buyday); // buyday���� buyView�� Text�� ����
        shelflifeView.setText(shelflife); // shelflife���� shelflifeView�� Text�� ����
        editText1.setText(count); // count���� editText1�� Text�� ����
        
        buybtn.setOnClickListener(new OnClickListener() { // ��Ϲ�ư Ŭ���� ���Գ�¥ ����Ʈ��Ŀ ����
    		public void onClick(View v) {
    			new DatePickerDialog(Activity4.this, mbuySetListener, myear, mmonth, mday).show();
    			}
    	 	});
        
        shelflifebtn.setOnClickListener(new OnClickListener() { // ��Ϲ�ư Ŭ���� ������� ��¥ ����Ʈ��Ŀ ����
    		public void onClick(View v) {
    			new DatePickerDialog(Activity4.this, mshelfSetListener, myear, mmonth, mday).show();
    			}
    	 	});
              
    	b3.setOnClickListener(new OnClickListener() { // �����ϱ� ��ư�� ������
    		public void onClick(View v) {	   	
    			String name = editText2.getText().toString(); // ���� ����� ���� String���� ��ȯ�Ͽ� �� ������ ����
    			String buyday = buyView.getText().toString();
    			String shelflife = shelflifeView.getText().toString();
    			String count = editText1.getText().toString();
    			
    			if (name.equals("")) // ���� name�� ����� ���ڿ��� ���ٸ�
    		        Toast.makeText(Activity4.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
    		    else if (buyday.equals("")) // ���� buyday�� ����� ���ڿ��� ���ٸ�
    		        Toast.makeText(Activity4.this, "관심날짜를 선택하세요", Toast.LENGTH_SHORT).show();
    		    else if (shelflife.equals(""))  // ���� shelflife�� ����� ���ڿ��� ���ٸ�
    		        Toast.makeText(Activity4.this, "임기를 선택하세요", Toast.LENGTH_SHORT).show();
    		    else if (count.equals("")) // ���� count�� ����� ���ڿ��� ���ų� 0�̶��
    		        Toast.makeText(Activity4.this, "정당을 입력하세요", Toast.LENGTH_SHORT).show();
    		    else {  // �� ���ڿ��� ������ �Ǿ��ٸ�
    		    	Intent iiintent = new Intent();
    				iiintent.putExtra("id", id);
    				iiintent.putExtra("name", editText2.getText().toString());
    				iiintent.putExtra("buyday", buyView.getText().toString());
    				iiintent.putExtra("shelflife", shelflifeView.getText().toString());
    				iiintent.putExtra("count", editText1.getText().toString());
    				Toast.makeText(Activity4.this, editText2.getText().toString() + " 이(가) 리스트에 수정되었습니다.", Toast.LENGTH_SHORT).show();
    				setResult(RESULT_OK, iiintent); // Activity3�� id, name, buyday, shelflife, count�� ���� ��������
    				finish(); // ���� ��Ƽ��Ƽ ����
					}
    			}
    	 	});
    
        b4.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			setResult(RESULT_CANCELED); // ���޹��� ���� ����� ���������� ����
    			finish(); // ���� ��Ƽ��Ƽ ����
    			}
    	 	});    
        }     
        
        private DatePickerDialog.OnDateSetListener mbuySetListener = new DatePickerDialog.OnDateSetListener() { // ���Գ�¥ ����Ʈ��Ŀ ���̾�α� �޼ҵ�
        	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        		myear = year;
        		mmonth = monthOfYear;
        		mday = dayOfMonth;
        		
        		updatebuy(); 
        	}
        };
        
        private DatePickerDialog.OnDateSetListener mshelfSetListener = new DatePickerDialog.OnDateSetListener() { // ������� ��¥ ����Ʈ��Ŀ ���̾�α� �޼ҵ�
        	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        		mmyear = year;
        		mmmonth = monthOfYear;
        		mmday = dayOfMonth;
        		
        		updateshelflife(); 
        	}
        };
    	
    	private void updatebuy() { // ���Գ�¥�� �ؽ�Ʈ�� buyView�� ������
    		buyView.setText(new StringBuilder()
    				.append(myear).append("/").append(mmonth+1).append("/")
    				.append(mday).append(" ")); 
    		Toast.makeText(Activity4.this, "관심날짜: " + myear + "/" + (mmonth+1) + "/" + mday, Toast.LENGTH_SHORT).show();
    		//����Ʈ��Ŀ���� ���õ� ������ �佺Ʈ�޼����� ���
        }
    	
    	private void updateshelflife() { // ������ѳ�¥�� �ؽ�Ʈ�� shelflifeView�� ������
    		shelflifeView.setText(new StringBuilder()
    				.append(mmyear).append("/").append(mmmonth+1).append("/")
    				.append(mmday).append(" ")+"까지");
    		Toast.makeText(Activity4.this, "임기: " + myear + "/" + (mmonth+1) + "/" + mday + "까지", Toast.LENGTH_SHORT).show();

        }       
}



