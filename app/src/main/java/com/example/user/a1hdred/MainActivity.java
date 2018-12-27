package com.example.user.a1hdred;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FgButtons.NoticeEventFromButtonFragmentListener, FgReg.NoticeEventFromRegFragmentListener,FgList.NoticeEventFromListFragmentListener {

    FragmentManager manager;

    private Button bt_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_back = findViewById(R.id.bt_back);
        setListener();

        manager = getSupportFragmentManager();

        List<Fragment> list = manager.getFragments();

        if( list.size() == 0 ){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.layout, new FgButtons());
            transaction.commit();

        }
    }

    @Override
    public void noticeFromButtonFragment(int id) {

        switch (id){
            case R.id.bt_1:
                Log.i("INFO", "bt_1");

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                List<Fragment> list = manager.getFragments();
                for (Fragment fragment : list){
                    transaction.remove(fragment);
                }
                transaction.add(R.id.layout, new FgReg());
                transaction.commit();
                break;
            case R.id.bt_2:

                //データをセットしてfragmentに渡す
                TodoManager todoManager = new TodoManager(this);
                List<Todo> slist = todoManager.getList();

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                List<Fragment> list2 = manager.getFragments();
                for (Fragment fragment : list2){
                    transaction2.remove(fragment);
                }

                Fragment fg_list = new FgList();
                ((FgList) fg_list).setList(slist);
                transaction2.add(R.id.layout,fg_list );
                transaction2.commit();

                break;
            case R.id.bt_3:
                Log.i("INFO", "bt_3");
                break;
            default:
                Log.i("INFO", "default");
                break;

        }

    }

    public void setListener(){

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Fragmentを破棄してbackする
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                List<Fragment> list = manager.getFragments();
                for (Fragment fragment : list){
                    transaction.remove(fragment);
                }
                transaction.add(R.id.layout, new FgButtons());
                transaction.commit();

            }
        });




    }

    @Override
    public void noticeFromRegFragment(int id) {

//        if(id == R.id.bt_back){
//
//            //Fragmentを破棄してbackする
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            List<Fragment> list = manager.getFragments();
//            for (Fragment fragment : list){
//                transaction.remove(fragment);
//            }
//            transaction.add(R.id.layout, new FgButtons());
//            transaction.commit();
//
//        }

    }

    @Override
    public void noticeFromListFragment(int id) {


//        if(id == R.id.bt_back2){
//
//            //Fragmentを破棄してbackする
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            List<Fragment> list = manager.getFragments();
//            for (Fragment fragment : list){
//                transaction.remove(fragment);
//            }
//            transaction.add(R.id.layout, new FgButtons());
//            transaction.commit();
//
//        }

    }
}
