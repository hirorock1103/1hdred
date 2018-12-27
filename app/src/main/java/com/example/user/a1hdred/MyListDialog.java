package com.example.user.a1hdred;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MyListDialog extends AppCompatDialogFragment {


    //list表示対象のidを受け取りhistoryを表示する。

    private RecyclerView recyclerView;
    private int todoInt;
    private TextView textView;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        //create view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.f_dialog_2, null);

        textView = view.findViewById(R.id.text);


        //get custom data
        todoInt = getArguments().getInt("todo_id");


            TodoManager manager = new TodoManager(getContext());
            List<TodoHistory> list = manager.getHistoryById(todoInt);

            StringBuilder sb = new StringBuilder();
            for (TodoHistory history : list){
                Log.i("INFO" , history.getId() + " / " + history.getComment());
                Log.i("INFO" , history.getId() + " / " + history.getCreatedate());

                sb.append("comment:" + (history.getComment().isEmpty() ? "なし" : history.getComment()) );
                sb.append("\n");
                sb.append("作成日:" + history.getCreatedate());
                sb.append("\n\n");
            }

        textView.setText(sb.toString());


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(view)
                .setTitle("test title")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        Dialog dialog = builder.create();

        return dialog;
    }
}
