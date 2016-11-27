package com.example.haeyoungjoo.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by hae young Joo on 2016-10-18.
 */

public class ViewActivity extends AppCompatActivity {


    String title = null; // 파일 제목을 위한 변수

    TextFileManager mFileMgr = new TextFileManager(this);// 파일처리를 위한 객체 생성.


    EditText resultTitle = null;//파일 제목을 보여줄 EditText
    EditText resultContext = null;//파일 내용을 보여줄 EditText

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);//activity_view 레이아웃을 화면에 띄움.


        resultTitle = (EditText)findViewById(R.id.resultTitle);
        resultContext = (EditText)findViewById(R.id.resulttext);

        Intent intent = getIntent();//MainActivity에서 받은 인텐트를 가져옴.
        title = intent.getExtras().getString("Title");//MainActivity에서 받은 인테트에서 데이터(제목)를 가져옴.해당 제목을 통해 파일내용을 불러올것임.
         delete.data = title;//읽어들인 파일 제목을 모든 클래스가 공유할수 있도록 전역변수에 저장.

        resultTitle.setText(title );//파일 제목을 resultTitle EditText에 입력
        resultContext.setText( mFileMgr.load(title ) );//파일 제목을을 통해 파일내용을 불러와서 resultContext EditText입력됨.

    }

    //액션바에 메뉴 생성.
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbardelete , menu);//삭제하는 매뉴를 인플레이트함.
        return super.onCreateOptionsMenu(menu);
    }

    //액션바에 있는 메뉴의 아이템 클릭시 발생하는 이벤트 처리.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_delete:

                ButtonDialogFragment myFragment = new ButtonDialogFragment();//다이얼로그 객체 생성
                myFragment.show(getFragmentManager(), "DeleteDialog" ); // show 메소드 호출을 통하여 대화상자가 화면에 표시되도록 함

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
