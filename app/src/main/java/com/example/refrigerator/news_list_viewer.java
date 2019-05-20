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
public class news_list_viewer extends Activity{
    Intent intent;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    public static String URL = "";

    private static final String DEBUG_TAG = "withpd";
    private Button naver;
    private Button nate;
    private Button daum;
    private Button back;

    public news_list_viewer(){
        Log.i(DEBUG_TAG, "NoteMain 생성자 시작");
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_sub);

        textView1=(TextView) findViewById(R.id.textView1);
        textView2=(TextView) findViewById(R.id.textView2);
        textView3=(TextView) findViewById(R.id.textView3);
        textView4=(TextView) findViewById(R.id.textView4);

        intent=getIntent();
        textView1.setText(intent.getStringExtra("name").toString());
        textView2.setText(intent.getStringExtra("buyday").toString());
        textView3.setText(intent.getStringExtra("shelflife").toString());
        textView4.setText(intent.getStringExtra("party").toString());


        naver = (Button)findViewById(R.id.button1);
        nate = (Button)findViewById(R.id.button2);
        daum= (Button)findViewById(R.id.button3);
        back = (Button)findViewById(R.id.button4);

        naver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "http://news.search.naver.com/search.naver?where=news&sm=tab_jum&ie=utf8&query=" + intent.getStringExtra("name").toString(); // 눌린곳의 getName 메소드를 호출해서 문자열이랑 합쳐 URL에 저장
                Uri uri = Uri.parse(URL); // Uri로 변환해서 변수 uri에 저장
                Intent intent = new Intent(Intent.ACTION_VIEW, uri); // 암시적인 인텐트로 ACTION_VIEW 설정
                startActivity(intent); // ACTION_VIEW 실행

            }
        });

        nate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "http://news.nate.com/search?q=" + intent.getStringExtra("name").toString(); // 눌린곳의 getName 메소드를 호출해서 문자열이랑 합쳐 URL에 저장
                Uri uri = Uri.parse(URL); // Uri로 변환해서 변수 uri에 저장
                Intent intent = new Intent(Intent.ACTION_VIEW, uri); // 암시적인 인텐트로 ACTION_VIEW 설정
                startActivity(intent); // ACTION_VIEW 실행

            }
        });

        daum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "http://search.daum.net/search?q=" + intent.getStringExtra("name").toString(); // 눌린곳의 getName 메소드를 호출해서 문자열이랑 합쳐 URL에 저장
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
