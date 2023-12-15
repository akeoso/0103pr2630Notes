package com.example.a2630pr0103kysh.data_base;

import static com.example.a2630pr0103kysh.data_base.DataBaseConst.NOTES_ID;
import static com.example.a2630pr0103kysh.data_base.DataBaseConst.NOTES_TEXT;
import static com.example.a2630pr0103kysh.data_base.DataBaseConst.NOTES_TITLE;
import static com.example.a2630pr0103kysh.data_base.DataBaseConst.TABLE_NAME_NOTES;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a2630pr0103kysh.data.Notes;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    public DataBaseManager(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }
    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }
    @SuppressLint("Range")
    public List<Notes> getNotes(){
        List<Notes> notesList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_NOTES, null);
        while (cursor.moveToNext()){
            Notes note = new Notes();
            note.setId(cursor.getInt(cursor.getColumnIndex(NOTES_ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(NOTES_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(NOTES_TEXT)));
            notesList.add(note);
        }
        cursor.close();
        return notesList;
    }
    @SuppressLint("Range")
    public Notes getNote(int noteId){
        Notes note = new Notes();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_NOTES + " WHERE " + NOTES_ID + " = " + "\"" + noteId + "\"", null);
        if (cursor.moveToFirst()){
            note.setId(cursor.getInt(cursor.getColumnIndex(NOTES_ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(NOTES_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(NOTES_TEXT)));
        }
        cursor.close();
        return note;
    }
    public void addNote (Notes note){
        ContentValues cv = new ContentValues();
        cv.put(NOTES_TITLE, note.getTitle());
        cv.put(NOTES_TEXT, note.getText());
        db.insert(TABLE_NAME_NOTES, null, cv);
    }

    public void editNote (Notes note){
        ContentValues cv = new ContentValues();
        cv.put(NOTES_TITLE, note.getTitle());
        cv.put(NOTES_TEXT, note.getText());
        db.update(TABLE_NAME_NOTES, cv, NOTES_ID + " = " + note.getId(), null);
    }

    public void delNote(Notes notes){
        db.delete(TABLE_NAME_NOTES, NOTES_ID + " = " + notes.getId(), null);
    }
}
