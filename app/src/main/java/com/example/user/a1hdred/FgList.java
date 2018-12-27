package com.example.user.a1hdred;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class FgList extends Fragment {

    private List<Todo> list;

    private RecyclerView recyclerView;
    private MyAdapter _adapter;
    private Button bt_back;

    NoticeEventFromListFragmentListener listener;

    public static final int DIALOG_FRAGMENT = 1;

    public FgList(){

    }

    public void setList(List<Todo> list){
        this.list = list;
    }

    public interface NoticeEventFromListFragmentListener{
        public void noticeFromListFragment(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (NoticeEventFromListFragmentListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.f_list, container, false);

//        bt_back = view.findViewById(R.id.bt_back2);
//        bt_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.noticeFromListFragment(R.id.bt_back2);
//            }
//        });

        recyclerView = view.findViewById(R.id.list_titles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _adapter = new MyAdapter(list);
        recyclerView.setAdapter(_adapter);

        return view;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView freespace;
        public TextView count;
        public TextView endCount;
        public ImageButton bt_countup;
        public ImageButton bt_countdown;
        public ImageButton bt_dialog;
        public ImageButton bt_memo_list;
        PieView pieView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            freespace = itemView.findViewById(R.id.freespace);
            count = itemView.findViewById(R.id.count);
            endCount = itemView.findViewById(R.id.text2);
            bt_countup = itemView.findViewById(R.id.bt_countup);
            bt_countdown = itemView.findViewById(R.id.bt_countdown);
            bt_dialog = itemView.findViewById(R.id.bt_dialog);
            bt_memo_list = itemView.findViewById(R.id.bt_memo_list);
            pieView = itemView.findViewById(R.id.pieView);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private List<Todo> list;
        MyAdapter(List<Todo> list){
            this.list = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.f_list_card, viewGroup,false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        public void setList(List<Todo> list){
            this.list = list;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            int c = list.get(position).getCount();
            int d = list.get(position).getEndCont();

            //set data to View
            holder.title.setText(list.get(position).getTitle());
            holder.freespace.setText(list.get(position).getFreespace());
            holder.count.setText(String.valueOf(c));
            holder.endCount.setText(String.valueOf(d));


            //set pieView
            float percent = (float)c / (float)d;
            holder.pieView.setPercentageTextSize((float)10);
            holder.pieView.setPercentage(percent * 100);
            PieAngleAnimation animation = new PieAngleAnimation(holder.pieView);
            animation.setDuration(1000);
            holder.pieView.startAnimation(animation);

            //TodoObject
            final Todo todoData = list.get(position);

            //position
            final int myPosition = position;

            final int id = list.get(position).getId();
            holder.bt_countup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TodoManager manager = new TodoManager(getContext());
                    manager.countUp(id);

                    Todo todo = manager.getTodoById(id);

                        int c = todo.getCount();
                        int d = todo.getEndCont();

                    //update view
                    holder.count.setText(String.valueOf(c));
                    holder.endCount.setText(String.valueOf(d));
                    float percent_1 = (float)c / (float)d;
                    holder.pieView.setPercentageTextSize((float)10);
                    holder.pieView.setPercentage(percent_1*100);
                    PieAngleAnimation animation = new PieAngleAnimation(holder.pieView);
                    animation.setDuration(1000);
                    holder.pieView.startAnimation(animation);

                }
            });

            holder.bt_memo_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openListDialog(todoData.getId());
                }
            });

            holder.bt_countdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TodoManager manager = new TodoManager(getContext());
                    manager.countDown(id);

                    Todo todo = manager.getTodoById(id);

                        int c = todo.getCount();
                        int d = todo.getEndCont();

                    //update view
                    holder.count.setText(String.valueOf(c));
                    holder.endCount.setText(String.valueOf(d));
                    float percent_1 = (float)c / (float)d;
                    holder.pieView.setPercentageTextSize((float)10);
                    holder.pieView.setPercentage(percent_1*100);
                    PieAngleAnimation animation = new PieAngleAnimation(holder.pieView);
                    animation.setDuration(1000);
                    holder.pieView.startAnimation(animation);
                }
            });

            holder.bt_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openDialog(todoData.getId(), myPosition);
                }
            });

        }

        public void openDialog(int todo_id, int position){

            MyDialog dialog = new MyDialog();
            Bundle bundle = new Bundle();
            bundle.putString("todo_id", String.valueOf(todo_id));
            bundle.putString("position", String.valueOf(position));
            dialog.setArguments(bundle);
            dialog.setTargetFragment(FgList.this, 37);
            dialog.show(getActivity().getSupportFragmentManager(),"example dialog");

        }

        public void openListDialog(int todo_id){
            MyListDialog dialog = new MyListDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("todo_id", todo_id);
            dialog.setArguments(bundle);
            dialog.show(getActivity().getSupportFragmentManager(), "list dialog");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 37){

            Log.i("INFO", "requestCode:" + requestCode);
            if(resultCode == Activity.RESULT_OK){

                //get data from dialog
                Bundle bundle = data.getExtras();
                String position = bundle.getString("position");

                //View update
                TodoManager manager = new TodoManager(getContext());
                List<Todo> list = manager.getList();
                _adapter.setList(list);
                _adapter.notifyItemChanged(Integer.parseInt(position));

            }

        }

    }
}
