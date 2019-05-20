package com.example.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ssabak on 2015-10-29.
 */
public class sns_list extends Activity{
    private static final int MODIFY = Menu.FIRST;
    private static final int DELETE = Menu.FIRST + 1;
    static final int GET_STRING = 1;
    private DBAdapter mDb;
    private ListView listView;
    private ArrayList<List> mlist;
    private ArrayAdapter<List> mAdapter;
    public static String URL = "";
    private int index;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name"); // Activity2의 값을 전달받아 String 변수들에게 저장
        String buyday = intent.getStringExtra("buyday");
        String shelflife = intent.getStringExtra("shelflife");
        String party = intent.getStringExtra("party");

        ListView list = (ListView)findViewById(R.id.list); // xml의 ListView 연결
        registerForContextMenu(list); // 컨텍스트메뉴 사용

        mDb = new DBAdapter(this); //DBAdapter 객체 생성
        mlist = mDb.getAlllist(); //listview에 DB에 저장된 모든 레코드를 가져옴
        mAdapter = new ArrayAdapter<List>(this, android.R.layout.simple_list_item_1, mlist); // 텍스트만 출력하는 기본 레이아웃

        listView = (ListView)findViewById(R.id.list); // listview 연결
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 선택을 단일선택으로 지정
        listView.setAdapter(mAdapter);

        mDb.insertList(name, buyday, shelflife, party); // DB에 name, buyday, shelflife, count 입력

        refreshList(); //메소드 호출, 최신화를 해줌

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){ // 리스트뷰를 누르면
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent=new Intent(sns_list.this, sns_list_viewer.class);
                intent.putExtra("name", mlist.get(position).getName());
                intent.putExtra("buyday", mlist.get(position).getBuyday());
                intent.putExtra("shelflife", mlist.get(position).getShelflife());
                intent.putExtra("party", mlist.get(position).getParty());
                startActivity(intent);
            }
        });
    }
    public boolean onContextItemSelected(MenuItem item) { // 컨텍스트메뉴의 버튼을 무엇을 누르냐에 이벤트 정의
        switch(item.getItemId()) { // 누른곳의 Id를 가져옴
            case MODIFY : // 컨텍스트 메뉴 수정을 클릭시
                AdapterView.AdapterContextMenuInfo menuInfo;
                menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                index = menuInfo.position; // 현재 클릭한 DB의 Index값를 가져옴
                List modify = mlist.get(index); // 리스트뷰에서 누른곳의 데이터를 modify에 저장
                Intent iintent = new Intent(this, Activity4.class);
                iintent.putExtra("id", modify.getId()); // 번호의 Id를 가져와 id에 저장
                iintent.putExtra("name", modify.getName()); // 번호의 Name을 가져와 name에 저장
                iintent.putExtra("buyday", modify.getBuyday()); // 번호의 Buyday를 가져와 buyday에 저장
                iintent.putExtra("shelflife", modify.getShelflife()); // 번호의 Shelflife를 가져와 shelflife에 저장
                iintent.putExtra("party", modify.getParty()); // 번호의 Count를 가져와 count에 저장
                startActivityForResult(iintent, GET_STRING); // 양방향 액티비터 선언
                // 데이터를 Activity4에 넘겨주고 다시 Activity4에서 Activity3로 데이터를 돌려받음
                break;

            case DELETE: // 컨텍스트 메뉴 삭제를 클릭시
                AdapterView.AdapterContextMenuInfo menuInfoo;
                menuInfoo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                index = menuInfoo.position; // 현재 클릭한 DB의 Index값를 가져옴
                List delete = mlist.get(index); // 리스트뷰에서 누른곳의 데이터를 delete에 저장
                Toast.makeText(sns_list.this, delete.getName() + " 이(가) 삭제되었습니다.", Toast.LENGTH_SHORT).show(); // 토스트메세지 띄움
                mDb.deleteList(delete.getId()); // DBAdapter의 deleteList 메소드 호출
                refreshList(); // 갱신
                return false;

            default : super.onContextItemSelected(item);
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) // Acitivity4에서 돌려받은 데이터 처리
    {
        if (requestCode == GET_STRING) // Activity4에서 값이 제대로 전달되어 온 경우
        {
            if (resultCode == RESULT_OK) // InformationInput에서 호출한 경우에만 처리합니다.
            {
                Integer id = data.getIntExtra("id", 0); // 전달받은 id를 id에 저장
                String name = data.getStringExtra("name"); // 전달받은 name을 name에 저장
                String buyday = data.getStringExtra("buyday"); // 전달받은 buyday를 buyday에 저장
                String shelflife = data.getStringExtra("shelflife"); // 전달받은 shelflife를 shelflife에 저장
                String score = data.getStringExtra("count"); // 전달받은 count를 count에 저장
                mDb.updateList(id, name, buyday, shelflife, score); // DBAdapter의 updateList 메소드 호출
                refreshList(); // 갱신
            }
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // 컨텍스트 메뉴
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("선택하세요."); // 컨텍스트 메뉴 타이틀
        menu.add(0, MODIFY, Menu.NONE, "수정"); // 수정 버튼
        menu.add(0, DELETE, Menu.NONE, "삭제"); // 삭제 버튼
    }

    public boolean onCreateOptionsMenu(Menu menu) { // 옵션메뉴
        menu.add(0, 1, 0, "저장된 목록 모두 삭제"); // 저장된 목록 모두 삭제 버튼
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(sns_list.this, "목록 전체가 삭제되었습니다.", Toast.LENGTH_SHORT).show(); // 토스트메세지
        mDb.deleteAll(); // DBAdapter의 deleteAll 메소드 호출
        refreshList(); // 갱신
        return super.onOptionsItemSelected(item);
    }

    private void refreshList() {
        mlist.clear();
        mlist.addAll(mDb.getAlllist());
        mAdapter.notifyDataSetChanged();
    }
}

