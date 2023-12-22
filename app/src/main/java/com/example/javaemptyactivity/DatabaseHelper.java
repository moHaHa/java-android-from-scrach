package com.example.javaemptyactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.javaemptyactivity.models.Agent;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * Static Variables
     * For Easley Manage Agent Table
     */
    private static final String DATABASE_NAME = "reminder_app_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "agent";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_DESCRIPTION = "description";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Create Agent Table
     * 1. make query
     * 2. use execSQL method for create the table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }


    /**
     *  Insert Agent
     */
    public boolean insertAgent(Agent agent) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, agent.getName());
        values.put(COLUMN_NUMBER, agent.getNumber());
        values.put(COLUMN_DESCRIPTION, agent.getDescription());
        long newRowId = db.insert(TABLE_NAME, null, values);

        // if newRowId is not -1 so the insert done successfully , but else this mean it's failed to insert
        // so: return true => success
        //     return false => failed
        return newRowId != -1;
    }

    /**
     * Get List Of Agents
     */

    public List<Agent> getAgents() {
        List<Agent> agents = new ArrayList<>();
        SQLiteDatabase db = null;

        try {
            String selectAllQuery = "SELECT * FROM " + TABLE_NAME;

            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectAllQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    String description = cursor.getString(3);

                    // Creating new agent object and adding to list
                    agents.add(new Agent(id, name, number, description));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DATABASE_ERROR", e.toString());
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return agents;
    }
}
