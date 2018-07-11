package com.aryabanerjee.librarymanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry;

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

    //Execute the SQL statement
    db.execSQL(SQL_CREATE_BOOKS_TABLE);
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
{
    //if (oldVersion < 2)
      //  db.execSQL("ALTER TABLE "+ TABLE_NAME +" ADD "+ COL_BOOK_NAME +" TEXT NOT NULL");
}
}

