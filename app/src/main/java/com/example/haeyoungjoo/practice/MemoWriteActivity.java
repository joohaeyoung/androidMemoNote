package com.example.haeyoungjoo.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.haeyoungjoo.practice.R.id.contextinput;

public class MemoWriteActivity extends AppCompatActivity {

    public EditText memoTitle = null;//메모 제목 객체
    public EditText memoContext = null;//메모 내용 객체


    // TextFileManager로 파일에 관한 처리
    TextFileManager mFileMgr = new TextFileManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memowrite);//activity_memowrite 레이아웃 화면에 표시.
        memoTitle = (EditText)findViewById(R.id.titleinput);//EditText id가 titleinput(제목)인 뷰 를 불러옴
        memoContext = (EditText)findViewById(contextinput);//EditText id가 contextinput(내용)인 뷰를 불러옴
    }

    //버튼 클릭시 발생하는 onclick 이벤트!
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.write://글쓰기 버튼 클릭시
            {
                String title = memoTitle .getText().toString();//EditText에 입력된 메모 제목을 String에 저장.
                mFileMgr.setMemoTitle(title);//메모제목을 mFileMgr을 객체로 이용하여 파일이름을 지정함.

                String text1 = memoContext.getText().toString();//EditText에 입력된 메모 내용을 String에 저장
                mFileMgr.save(text1);//테스트 내용을 위에서 지정한 파일이름을 통해 파일에 저장
                memoTitle .setText("");//파일에 저장후 제목을 쓰는 EditTEext에 공백으로 전환
                memoContext .setText("");//파일에 저장후 내용을 쓰는 EditTEext에 공백으로 전환

                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.see://메모 목록 보기 버튼 클릭시
            {
                Intent in = new Intent(MemoWriteActivity.this, MainActivity.class) ;//MainActivity.class 액티비티로 이동하는 인텐트 생성
                Toast.makeText(this, "리스트 보기", Toast.LENGTH_SHORT).show();
                startActivity(in);//MainActivity.class로 액티비티 전환.
                break;
            }
            case R.id.cancel://취소 버튼 클릭시
            {
                memoTitle.setText("");//메모 제목 삭제
                memoContext.setText("");//메모 내용 삭제
                Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
