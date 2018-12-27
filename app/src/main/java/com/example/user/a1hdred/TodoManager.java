package com.example.user.a1hdred;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoManager extends MyDbHelper {
    public TodoManager(Context context) {
        super(context);
    }

    /*
    全てのデータを取得する
     */
    public List<Todo> getList(){

        List<Todo> list = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_TODO + " ORDER BY id DESC ";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){

            Todo todo = new Todo();
            todo.setId(c.getInt(c.getColumnIndex(TODO_COLUMN_ID)));
            todo.setTitle(c.getString(c.getColumnIndex(TODO_COLUMN_TITLE)));
            todo.setFreespace(c.getString(c.getColumnIndex(TODO_COLUMN_FREESPACE)));
            todo.setCount(c.getInt(c.getColumnIndex(TODO_COLUMN_COUNT)));
            todo.setEndCont(c.getInt(c.getColumnIndex(TODO_COLUMN_ENDCOUNT)));
            todo.setCategory(c.getInt(c.getColumnIndex(TODO_COLUMN_CATEGORY)));
            list.add(todo);

            c.moveToNext();
        }

        return list;
    }

    /**
     * get by id
     */
    public Todo getTodoById(int id){

        Todo todo = new Todo();

        String query = "SELECT * FROM " + TABLE_TODO + " WHERE " + TODO_COLUMN_ID + " = " + id;

        Log.i("INFO",query);

        SQLiteDatabase db = getWritableDatabase();

        Cursor c =db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()){

            todo.setId(c.getInt(c.getColumnIndex(TODO_COLUMN_ID)));
            todo.setTitle(c.getString(c.getColumnIndex(TODO_COLUMN_TITLE)));
            todo.setFreespace(c.getString(c.getColumnIndex(TODO_COLUMN_FREESPACE)));
            todo.setCount(c.getInt(c.getColumnIndex(TODO_COLUMN_COUNT)));
            todo.setEndCont(c.getInt(c.getColumnIndex(TODO_COLUMN_ENDCOUNT)));
            todo.setCategory(c.getInt(c.getColumnIndex(TODO_COLUMN_CATEGORY)));
            c.moveToNext();

        }

        return todo;

    }

    /**
     * add
     */
    public long addTodo(Todo todo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TODO_COLUMN_TITLE, todo.getTitle());
        values.put(TODO_COLUMN_FREESPACE, todo.getFreespace());
        values.put(TODO_COLUMN_ENDCOUNT, todo.getEndCont());
        long id = db.insert(TABLE_TODO, null, values);
        return id;
    }

    /**
     * up count
     */
    public void countUp(int todo_id){

        String query = "UPDATE " + TABLE_TODO + " SET " + TODO_COLUMN_COUNT + " = " + " ("+TODO_COLUMN_COUNT+"+1)  WHERE " + TODO_COLUMN_ID + " = " + todo_id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }

    /**
     * up donw
     */
    public void countDown(int todo_id){

        String query = "UPDATE " + TABLE_TODO + " SET " + TODO_COLUMN_COUNT + " = " + " ("+TODO_COLUMN_COUNT+"-1)  WHERE " + TODO_COLUMN_ID + " = " + todo_id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }

    /**
     * add history
     */
    public long addHistory(TodoHistory history){

        ContentValues values = new ContentValues();
        values.put(HISTORY_COLUMN_TODO_ID, history.getTodo_id());
        values.put(HISTORY_COLUMN_COMMENT, history.getComment());
        values.put(HISTORY_COLUMN_CREATEDATE, Common.formatDate(new Date(),Common.DB_DATE_FORMAT ));

        SQLiteDatabase db = getWritableDatabase();
        long insertId = db.insert(TABLE_TODO_HISTORY, null, values);

        return insertId;

    }

    /**
     * addCount
     */
    public void addCount(int todo_id, int count){

        String query = "UPDATE " + TABLE_TODO + " SET " + TODO_COLUMN_COUNT + " = " + " ("+TODO_COLUMN_COUNT+"+" +count+ ")  WHERE " + TODO_COLUMN_ID + " = " + todo_id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }

    /**
     * get history by id
     */
    public List<TodoHistory> getHistoryById(int todoId){

        List<TodoHistory> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_TODO_HISTORY + " WHERE " + HISTORY_COLUMN_TODO_ID + " = " + todoId + " ORDER BY " + HISTORY_COLUMN_TODO_ID + " DESC ";

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){

            TodoHistory history = new TodoHistory();

            history.setId(c.getInt(c.getColumnIndex(HISTORY_COLUMN_ID)));
            history.setTodo_id(c.getInt(c.getColumnIndex(HISTORY_COLUMN_TODO_ID)));
            history.setComment(c.getString(c.getColumnIndex(HISTORY_COLUMN_COMMENT)));
            history.setCreatedate(c.getString(c.getColumnIndex(HISTORY_COLUMN_CREATEDATE)));

            list.add(history);

            c.moveToNext();

        }


        return list;


    }

}
