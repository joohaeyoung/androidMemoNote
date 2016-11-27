package com.example.haeyoungjoo.practice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.io.File;

/**
 * Created by hae young Joo on 2016-10-31.
 */

public class ButtonDialogFragment extends DialogFragment {

    EditText resultContext= null;
    EditText resultTitle= null;


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(  getActivity() );
        builder.setTitle("종료 확인 대화 상자")
                .setMessage("메시지를 삭제하시겠습니까?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            resultContext= (EditText)getActivity().findViewById(R.id.resultTitle);//ViewActivity의 EditeText resultContext를 이용함
                            resultTitle=(EditText)getActivity().findViewById(R.id.resulttext);//ViewActivity의 EditText resultTitle을 이용함

                            resultTitle.setText(" ");//공백문자로 만듬
                            resultContext.setText(" ");//공백문자로 만듬

                        File file = new File("/data/data/com.example.haeyoungjoo.practice/files", delete.data );//파일경로와, 파일제목을 이용하여 파일 객체 생성
                        file.delete();//해당 파일 삭제.

                       Intent i = new Intent( getActivity(), MainActivity.class );
                        startActivity(i);//MainActivity로 화면전환
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}