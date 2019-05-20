package com.example.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ssabak on 2015-10-29.
 */
public class memo_list extends Activity{
    Intent intent;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    private static final String DEBUG_TAG = "withpd";
    private Button write;
    private Button memo_list;
    private Button noti;
    private Button back;

    public memo_list(){
        Log.i(DEBUG_TAG, "NoteMain 생성자 시작");
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_sub);

        textView1=(TextView) findViewById(R.id.textView1);
        textView2=(TextView) findViewById(R.id.textView2);
        textView3=(TextView) findViewById(R.id.textView3);
        textView4=(TextView) findViewById(R.id.textView4);

        intent=getIntent();
        textView1.setText(intent.getStringExtra("name").toString());
        textView2.setText(intent.getStringExtra("buyday").toString());
        textView3.setText(intent.getStringExtra("shelflife").toString());
        textView4.setText(intent.getStringExtra("party").toString());

        write = (Button)findViewById(R.id.button1);
        memo_list = (Button)findViewById(R.id.button2);
        noti = (Button)findViewById(R.id.button3);
        back = (Button)findViewById(R.id.button4);

        write.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(DEBUG_TAG,"Note_Write intent : Note_Write로 go!");
                Intent intent = new Intent(memo_list.this, Note_Write.class);
                startActivity(intent);
            }
        });

        memo_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(memo_list.this, Note_Save.class);
                intent.putExtra("check", "list");
                startActivity(intent);

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


    }


}
