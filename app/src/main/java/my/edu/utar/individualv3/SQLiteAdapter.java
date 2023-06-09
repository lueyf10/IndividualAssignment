package my.edu.utar.individualv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAdapter {
    public static final String MYDATABASE_NAME = "MY_DATABASE";
    public static final String MYDATABASE_TABLE = "MY_TABLE";
    public static final int MYDATABASE_VERSION = 1;
    public static final String KEY_CONTENT = "Username";
    public static final String KEY_CONTENT2 = "Level";
    public static final String KEY_CONTENT3 = "Score";

    private static final String SCRIPT_CREATE_DATABASE = "create table "
            + MYDATABASE_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CONTENT + " text not null, "
            + KEY_CONTENT2 + " int, "
            + KEY_CONTENT3 + " int);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    public SQLiteAdapter(Context c) {
        context = c;
    }
    public SQLiteAdapter openToRead() throws
            android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }
    public SQLiteAdapter openToWrite() throws
            android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        sqLiteHelper.close();
    }

    public long insert(String content, int content2, int content3 ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CONTENT, content);
        contentValues.put(KEY_CONTENT2, content2);
        contentValues.put(KEY_CONTENT3, content3);
        return sqLiteDatabase.insert(MYDATABASE_TABLE, null,
                contentValues);
    }

    public int deleteAll() {
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }
    public String queueAll() {
        String[] columns = new String[] { KEY_CONTENT, KEY_CONTENT2, KEY_CONTENT3 };
        String orderBy = KEY_CONTENT3 + " DESC, " + KEY_CONTENT2 + " DESC";
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, null,
                null, null, null, orderBy,"25");
        String result = "";
        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT);
        int index_CONTENT2 = cursor.getColumnIndex(KEY_CONTENT2);
        int index_CONTENT3 = cursor.getColumnIndex(KEY_CONTENT3);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + cursor.getString(index_CONTENT) + "\t\t\t"
                    + cursor.getString(index_CONTENT2) + "\t\t\t" + cursor.getString(index_CONTENT3)
                    + "\n";
        }
        return result;
    }


    public class SQLiteHelper extends SQLiteOpenHelper {
        public SQLiteHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}