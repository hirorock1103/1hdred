package com.example.user.a1hdred;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyDialog extends AppCompatDialogFragment {

    private EditText edit_number;
    private EditText edit_memo;
    private Button button;
    private TodoManager todoManager;
    private Todo todo;


    private AlertDialog dialog;

    public Dialog onCreateDialog(Bundle savedInstanceState){

        final String todo_id = getArguments().getString("todo_id");
        final String position = getArguments().getString("position");

        todoManager = new TodoManager(getActivity());
        todo = todoManager.getTodoById(Integer.parseInt(todo_id));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.f_dialog_1, null);

        TextView titleText = new TextView(getActivity());

        titleText.setText(todo.getTitle() + "の本日の回数をセット");
        titleText.setTextSize(18);
        titleText.setPadding(50,50,10,10);
        titleText.setTextColor(getResources().getColor(R.color.colorAccent));

        builder.setView(view)
                .setCustomTitle(titleText)
                .setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        //View
        edit_number = view.findViewById(R.id.edit_number);
        edit_memo = view.findViewById(R.id.edit_memo);
        button = view.findViewById(R.id.bt_dialog_reg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TodoHistory history = new TodoHistory();
                history.setTodo_id(Integer.parseInt(todo_id));
                history.setComment(edit_memo.getText().toString());

                todoManager.addHistory(history);
                todoManager.addCount(Integer.parseInt(todo_id),Integer.parseInt(edit_number.getText().toString()));

                Log.i("INFO", "update Complete!" );
                //after insert, button is disabled
                button.setEnabled(false);

                //呼び出し元に処理が終了したことを通知する
                Intent result = new Intent();
                result.putExtra("position", position);

                if(getTargetFragment() != null){

                    //FROM Fragment
                    Log.i("INFO", "from fragmnt");
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, result);

                    //show message
                    Toast.makeText(getContext(),"登録しました！",Toast.LENGTH_SHORT).show();

                    //dialog close
                    dialog.dismiss();

                }else{
                    Log.i("INFO", "not from fragmnt");
                }

            }
        });

        dialog = builder.create();
        return dialog;

    }


}
