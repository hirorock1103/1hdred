package com.example.user.a1hdred;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final int DBVERSION = 7;
    private static final String DBNAME = "Hundres.db";

    protected static final String TABLE_TODO = "Todo";
    protected static final String TODO_COLUMN_ID = "id";
    protected static final String TODO_COLUMN_TITLE = "title";
    protected static final String TODO_COLUMN_FREESPACE = "freespace";
    protected static final String TODO_COLUMN_COUNT = "count";
    protected static final String TODO_COLUMN_ENDCOUNT = "end_count";
    protected static final String TODO_COLUMN_CATEGORY = "category";

    protected static final String TABLE_TODO_HISTORY = "TodoHistory";
    protected static final String HISTORY_COLUMN_ID = "id";
    protected static final String HISTORY_COLUMN_TODO_ID = "todo_id";
    protected static final String HISTORY_COLUMN_COMMENT = "comment";
    protected static final String HISTORY_COLUMN_CREATEDATE = "createdate";


    public MyDbHelper( Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("INFO","onCreate");

        String query ="CREATE TABLE IF NOT EXISTS " + TABLE_TODO + "(" +
                TODO_COLUMN_ID + " integer primary key autoincrement ," +
                TODO_COLUMN_TITLE + " text," +
                TODO_COLUMN_FREESPACE + " text," +
                TODO_COLUMN_COUNT + " integer default 0," +
                TODO_COLUMN_ENDCOUNT + " integer default 0," +
                TODO_COLUMN_CATEGORY + " integer" +
                ")";

        db.execSQL(query);

        query ="CREATE TABLE IF NOT EXISTS " + TABLE_TODO_HISTORY + "(" +
                HISTORY_COLUMN_ID + " integer primary key autoincrement ," +
                HISTORY_COLUMN_TODO_ID + " integer," +
                HISTORY_COLUMN_COMMENT + " text," +
                HISTORY_COLUMN_CREATEDATE + " text" +
                ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

        String query = " DROP TABLE IF EXISTS " + TABLE_TODO;
        db.execSQL(query);
        query = " DROP TABLE IF EXISTS " + TABLE_TODO_HISTORY;
        db.execSQL(query);

        onCreate(db);

    }
}
