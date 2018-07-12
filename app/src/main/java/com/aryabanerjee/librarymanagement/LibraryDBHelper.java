package com.aryabanerjee.librarymanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry;
import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry1;

import java.util.ArrayList;
import java.util.List;

import static com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry.COL_BOOK_NAME;
import static com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry.TABLE_NAME;

public class LibraryDBHelper extends SQLiteOpenHelper
{
    public static final String LOG_TAG = LibraryDBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "library.db";

    private static final int DATABASE_VERSION = 1;
    //private static final int oldVersion = 1;

    public LibraryDBHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

/**
 * This is called when the database is created for the first time.
 */
@Override
public void onCreate(SQLiteDatabase db)
{
    //Create a string  that contains the SQL statement to create the books table
    String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + FeedEntry.TABLE_NAME + " ("
            + FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FeedEntry.COL_BOOK_NAME + " TEXT NOT NULL, "
            + FeedEntry.COL_BOOK_ID + " INTEGER NOT NULL, "
            + FeedEntry.COL_BOOK_PUBLICATION + " TEXT, "
            + FeedEntry.COL_BOOK_CATEGORY + " TEXT NOT NULL, "
            + FeedEntry.COL_BOOK_AUTHOR + " TEXT NOT NULL, "
            + FeedEntry.COL_BOOK_QUANTITY + " INTEGER NOT NULL DEFAULT 0);";


    //Create a string  that contains the SQL statement to create the users table
    String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + FeedEntry1.TABLE_NAME_USER + " ("
            + FeedEntry1._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FeedEntry1.COL_USER_NAME + " TEXT NOT NULL, "
            + FeedEntry1.COL_USER_ID + " INTEGER NOT NULL, "
            + FeedEntry1.COL_USER_DEPT + " TEXT NOT NULL, "
            + FeedEntry1.COL_USER_CONTACT_NUMBER + "INTEGER NOT NULL DEFAULT 0);";

    //Execute the SQL statement
    db.execSQL(SQL_CREATE_BOOKS_TABLE);
    db.execSQL(SQL_CREATE_USERS_TABLE);
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
{
    //if (oldVersion < 2)
      //  db.execSQL("ALTER TABLE "+ TABLE_NAME +" ADD "+ COL_BOOK_NAME +" TEXT NOT NULL");
}
public List<String> getAllBooks()
{
    List<String> books = new ArrayList<String>();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    String[] projection = {
            LibraryContract.FeedEntry.COL_BOOK_NAME};

    //LibraryDBHelper mDBHelper = new LibraryDBHelper(this);
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(
            LibraryContract.FeedEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null);

    if (cursor.moveToFirst()) {
        do {
            books.add(cursor.getString(1));
        } while (cursor.moveToNext());
    }

    //closing connection
    cursor.close();
    db.close();

    return books;
}
}

