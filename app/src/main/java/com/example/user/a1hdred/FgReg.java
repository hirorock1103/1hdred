package com.example.user.a1hdred;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FgReg extends Fragment {

    private InputMethodManager inputMethodManager;
    private ConstraintLayout layout;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private Button button;
    private Button bt_back;

    NoticeEventFromRegFragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_register, container, false);

        editText = view.findViewById(R.id.input1);
        editText2 = view.findViewById(R.id.input2);
        editText3 = view.findViewById(R.id.input3);
        layout = view.findViewById(R.id.frame);
        inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        button = view.findViewById(R.id.bt_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String error = "";

                if(editText.getText().toString().isEmpty() == false){

                }else{
                    error += "タイトルは必須です。\n";
                }

                if(editText3.getText().toString().isEmpty() == false){

                }else{
                    error += "ゴールカウントは必須です。\n";
                }

                if(error == ""){

                    TodoManager manager = new TodoManager(getContext());
                    Todo todo = new Todo();
                    todo.setTitle(editText.getText().toString());
                    todo.setFreespace(editText2.getText().toString());
                    todo.setEndCont(Integer.parseInt(editText3.getText().toString()));
                    long insertid = manager.addTodo(todo);
                    if(insertid > 0){
                        Toast.makeText(getContext(), "add complete" , Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(getContext(), "必須項目が入力されていません。" , Toast.LENGTH_SHORT).show();

                }


            }
        });

//        bt_back = view.findViewById(R.id.bt_back);
//        bt_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("INFO", "bt_back");
//                listener.noticeFromRegFragment(R.id.bt_back);
//            }
//        });


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                inputMethodManager.hideSoftInputFromWindow(layout.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                layout.requestFocus();
                return false;
            }
        });

        CardView cardView = view.findViewById(R.id.cardview);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof NoticeEventFromRegFragmentListener)){

            throw new UnsupportedOperationException("Listener is not implementation");

        }else{

            listener = (NoticeEventFromRegFragmentListener) context;

        }


    }

    public interface NoticeEventFromRegFragmentListener{
        public void noticeFromRegFragment(int id);
    }


}

