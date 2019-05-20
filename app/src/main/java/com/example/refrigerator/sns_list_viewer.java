package com.example.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ssabak on 2015-10-29.
 */
public class sns_list_viewer extends Activity {
    Intent intent;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    public static String URL = "";

    private static final String DEBUG_TAG = "withpd";

    private Button facebook;
    private Button twiter;
    private Button blog;
    private Button back;

    public sns_list_viewer(){
        Log.i(DEBUG_TAG, "NoteMain 생성자 시작");
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_sub);

        textView1=(TextView) findViewById(R.id.textView1);
        textView2=(TextView) findViewById(R.id.textView2);
        textView3=(TextView) findViewById(R.id.textView3);
        textView4=(TextView) findViewById(R.id.textView4);

        intent=getIntent();
        textView1.setText(intent.getStringExtra("name").toString());
        textView2.setText(intent.getStringExtra("buyday").toString());
        textView3.setText(intent.getStringExtra("shelflife").toString());
        textView4.setText(intent.getStringExtra("party").toString());

        facebook = (Button)findViewById(R.id.button1);
        twiter = (Button)findViewById(R.id.button2);
        blog = (Button)findViewById(R.id.button3);
        back = (Button)findViewById(R.id.button4);

        facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "https://www.facebook.com/" + intent.getStringExtra("name").toString(); // 눌린곳의 getName 메소드를 호출해서 문자열이랑 합쳐 URL에 저장
                Uri uri = Uri.parse(URL); // Uri로 변환해서 변수 uri에 저장
                Intent intent = new Intent(Intent.ACTION_VIEW, uri); // 암시적인 인텐트로 ACTION_VIEW 설정
                startActivity(intent); // ACTION_VIEW 실행
            }
        });

        twiter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "https://www.twitter.com/" + intent.getStringExtra("name").toString(); // 눌린곳의 getName 메소드를 호출해서 문자열이랑 합쳐 URL에 저장
                Uri uri = Uri.parse(URL); // Uri로 변환해서 변수 uri에 저장
                Intent intent = new Intent(Intent.ACTION_VIEW, uri); // 암시적인 인텐트로 ACTION_VIEW 설정
                startActivity(intent); // ACTION_VIEW 실행

            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "https://blog.naver.com/" + intent.getStringExtra("name").toString(); // 눌린곳의 getName 메소드를 호출해서 문자열이랑 합쳐 URL에 저장
                Uri uri = Uri.parse(URL); // Uri로 변환해서 변수 uri에 저장
                Intent intent = new Intent(Intent.ACTION_VIEW, uri); // 암시적인 인텐트로 ACTION_VIEW 설정
                startActivity(intent); // ACTION_VIEW 실행


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


    }
}
