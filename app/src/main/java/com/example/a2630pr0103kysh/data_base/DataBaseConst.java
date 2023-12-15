package com.example.a2630pr0103kysh.data_base;

public class DataBaseConst {
    public static final String DATA_BASE_NAME = "Notes.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String TABLE_NAME_NOTES = "Notes";
    public static final String NOTES_ID = "id";
    public static final String NOTES_TITLE = "title";
    public static final String NOTES_TEXT = "textNote";
    public static final String CREATE_TABLE_NOTES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_NOTES + " ( " +
            "" + NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTES_TITLE + " TEXT, " + NOTES_TEXT + " TEXT);";
    public static final String DELETE_TABLE_NOTES = "DROP TABLE IF EXISTS " + TABLE_NAME_NOTES;

}
