package com.example.user.a1hdred;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FgButtons extends Fragment {

    Button button1;
    Button button2;
    Button button3;

    private NoticeEventFromButtonFragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.f_buttons, container, false);

        button1 = view.findViewById(R.id.bt_1);
        button2 = view.findViewById(R.id.bt_2);
        button3 = view.findViewById(R.id.bt_3);
        setButtons();
        return view;

    }

    public interface NoticeEventFromButtonFragmentListener{
        public void noticeFromButtonFragment(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (NoticeEventFromButtonFragmentListener) context;
    }

    public void setButtons(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.noticeFromButtonFragment(R.id.bt_1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.noticeFromButtonFragment(R.id.bt_2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.noticeFromButtonFragment(R.id.bt_3);
            }
        });
    }
}
