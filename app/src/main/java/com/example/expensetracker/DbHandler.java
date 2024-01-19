package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private Context context;
    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_USERS = "userdetails";
    private static final String FIRST_NAME = "fname";
    private static final String LAST_NAME = "lname";
    private static final String MOB_NO = "mob";
    private static final String EMAIL = "email";
    private static final String PASS = "pass";
    private static final String M_PIN = "pin";


    static final String TABLE_NAME = "records";
    String COLUMN_TRANSACTION="type";
    String COLUMN_METHOD="method";
    String COLUMN_AMOUNT="amount";
    String COLUMN_DATE="date";
    String COLUMN_TIME="time";
    String COLUMN_NOTE="note";


    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, "
                + MOB_NO + " INTEGER, "
                + EMAIL + " TEXT PRIMARY KEY, " + PASS + " TEXT,"+ M_PIN +" TEXT)";
        db.execSQL(CREATE_TABLE);
        Log.d("DbHandler", "onCreate called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", tableName});
        int count = 0;

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return count > 0;
    }
    void insertUserDetails(String firstName, String lastName, String mobileNumber, String email, String password,String pin01) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(FIRST_NAME, firstName);
        cValues.put(LAST_NAME, lastName);
        cValues.put(MOB_NO, mobileNumber);
        cValues.put(EMAIL, email);
        cValues.put(PASS, password);
        cValues.put(M_PIN, pin01);

        db.insert(TABLE_USERS, null, cValues);
        db.close();
    }
    @SuppressLint("Range")
    public String getMPIN(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + M_PIN + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});

        String firstName = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                firstName = cursor.getString(cursor.getColumnIndex(M_PIN));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return firstName;
    }
    public boolean isUserExists(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + EMAIL + "=? AND " + PASS + "=?", new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
    public void createRecordsTable(){
        SQLiteDatabase db=getWritableDatabase();

        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_TRANSACTION + " TEXT, " +
                COLUMN_METHOD + " TEXT, " +
                COLUMN_AMOUNT + " INTEGER, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_NOTE + " TEXT)";
        db.execSQL(createTableQuery);
    }
    public void insertRecord(String transactionType, String method, int amount, String date, String time, String note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRANSACTION, transactionType);
        contentValues.put(COLUMN_METHOD, method);
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_NOTE, note);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public boolean isRecordsTableExists() {
        SQLiteDatabase db = getReadableDatabase();
        String tableName = "records";
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean tableExists = cursor.moveToFirst();
        cursor.close();
        return tableExists;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME ;

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("type",cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION)));
            user.put("method",cursor.getString(cursor.getColumnIndex(COLUMN_METHOD)));
            user.put("amount",cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)));
            user.put("time",cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            user.put("date",cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            user.put("note",cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));
            userList.add(user);
        }
        return  userList;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetUsersWithinDateRange(String fromDate, String toDate) {
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE + " BETWEEN ? AND ?";
        Cursor cursor = db.rawQuery(query, new String[]{fromDate, toDate});

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<>();
                user.put("type", cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION)));
                user.put("method", cursor.getString(cursor.getColumnIndex(COLUMN_METHOD)));
                user.put("amount", cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)));
                user.put("date", cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                user.put("time", cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                user.put("note", cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    public Cursor sendData(String date01){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE"+COLUMN_DATE+" = "+ date01;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    @SuppressLint("Range")
    public String getFirstName(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + FIRST_NAME + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});

        String firstName = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return firstName;
    }

    @SuppressLint("Range")
    public String getLastName(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + LAST_NAME + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});

        String lastName = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return lastName;
    }
    @SuppressLint("Range")
    public String getPhoneNo(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + MOB_NO + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});

        String phno = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                phno = cursor.getString(cursor.getColumnIndex(MOB_NO));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return phno;
    }
    @SuppressLint("Range")
    public String getEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + EMAIL + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});

        String e = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                 e = cursor.getString(cursor.getColumnIndex(LAST_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return e;
    }
    @SuppressLint("Range")
    public String getPass(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PASS + " FROM " + TABLE_USERS + " WHERE " + EMAIL + "=?", new String[]{email});

        String pass = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                pass = cursor.getString(cursor.getColumnIndex(PASS));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return pass;
    }
    public void update01(String curremail, String newemail, String newphoneno,String fname ,String lname) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, newemail);
        contentValues.put(MOB_NO, newphoneno);
        contentValues.put(FIRST_NAME,fname);
        contentValues.put(LAST_NAME,lname);

        int rowsAffected = db.update(TABLE_USERS, contentValues, EMAIL + "=?", new String[]{curremail});
        db.close();

        if (rowsAffected > 0) {
            Log.d("DbHandler", "User name updated successfully");
        } else {
            Log.d("DbHandler", "Failed to update user name");
        }
    }
    public void updatePass(String curremail, String newPass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PASS, newPass);

        int rowsAffected = db.update(TABLE_USERS, contentValues, EMAIL + "=?", new String[]{curremail});
        db.close();

        if (rowsAffected > 0) {
            Log.d("DbHandler", "User name updated successfully");
        } else {
            Log.d("DbHandler", "Failed to update user name");
        }


    }
}
