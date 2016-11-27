package com.example.haeyoungjoo.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView m_ListView;//리스트뷰! (어뎁터 뷰)
    private ArrayAdapter<String> m_Adapter; //배열에서 값을 가져오는 어뎁터

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] values = fileList() ; //어플리케이션이 현재 저장한 파일 리스트 반환.

        // 어느날 갑자기 생성하지도 않는 instance-run 이라는 파일이 자동으로 존재해서 fileList()로 파일을 불러오면
        // 배열의 첫번쨰 원소로 존재하여 instance-run 파일까지 리스트로 불러와진다.
        // 그리하여 배열을 왼쪽으로 한칸씩 이동하여서 instance-run 파일은 메모 목록에 나오지 않도록 하였다.
        if(values.length == 1 )
            values[0] = " ";
        else{
            int i ;
            for(i = 0 ; i < values.length-1 ; i++ ){
                values[i]=values[i+1];
            }
            values[i]=" ";
        }

        m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);//어뎁터 생성.

        // Xml에서 추가한 ListView의 객체
        m_ListView = (ListView) findViewById(R.id.list);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트를 처리할 리스너 설정
        m_ListView.setOnItemClickListener(onClickListItem);


    }

    // 아이템 터치 이벤트 리스너 구현
    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            String data =  m_Adapter.getItem(position);//리스트의 해당 위치에 있는 아이템을 가져와서 String data에 저장.
            Intent intent = new Intent(MainActivity.this, ViewActivity.class);//ViewActivity.class로 넘겨주는 인텐트 생성
            intent.putExtra("Title",data);//인텐트를 사용하여 다른 액티비티에 넘겨줄때 값까지 같이 넘겨줌.(제목을 넘겨줌)
            startActivity(intent);// ViewActivity.class 로 액티비티 전환!
        }
    };

    //액션바에 글 작성하는 옵션 메뉴.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbarwrite, menu);//인플레이트 시킴!
        //  인플레이트란? : xml 상에 정의된 뷰들을 객체로 만드는 과정을 인플레이트라고 한다
        return super.onCreateOptionsMenu(menu);

    }

    //액션바에 있는 메뉴 아이템을 클릭했을때 발생하는 이벤트.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_memo://해당 아이템 아이디가 action.memo라면
                Intent intent = new Intent(MainActivity.this, MemoWriteActivity.class);//MemoWriteActivity.class액티비티로 넘어가는 인텐트 생성
                startActivity(intent);//MemoWriteActivity.class 액티비티로 이동!
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
