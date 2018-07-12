package com.aryabanerjee.librarymanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry;
import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry1;

public class CatalogActivity extends AppCompatActivity
{
    private LibraryDBHelper mDBHelper;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_catalog);

    //Setup FAB to open EditorActivity
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
            startActivity(intent);
        }
    });

    mDBHelper = new LibraryDBHelper(this);
}

@Override
protected void onStart()
{
    super.onStart();
    displayDatabaseInfo();
}

private void displayDatabaseInfo()
{
    //Create and/or open a database to read from it
    SQLiteDatabase db = mDBHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    String[] projection = {
            FeedEntry._ID,
            FeedEntry.COL_BOOK_NAME,
            FeedEntry.COL_BOOK_ID,
            FeedEntry.COL_BOOK_PUBLICATION,
            FeedEntry.COL_BOOK_CATEGORY,
            FeedEntry.COL_BOOK_AUTHOR,
            FeedEntry.COL_BOOK_QUANTITY};

    String[] projection_user = {
            FeedEntry1._ID,
            FeedEntry1.COL_USER_NAME,
            FeedEntry1.COL_USER_ID,
            FeedEntry1.COL_USER_DEPT,
            FeedEntry1.COL_USER_CONTACT_NUMBER};

    //Perform a query on the books table
    Cursor cursor = db.query(
            FeedEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null);

    TextView displayView = (TextView) findViewById(R.id.text_view_book);

    try {
        // Create a header in the Text View that looks like this:
        //
        // The book table contains <number of rows in Cursor> pets.
        // _id - Name -Book ID  - Category - Quantity
        //
        // In the while loop below, iterate through the rows of the cursor and display
        // the information from each column in this order.
        displayView.setText("The book table contains " + cursor.getCount() + " books.\n\n");
        displayView.append(FeedEntry._ID + " - " +
                FeedEntry.COL_BOOK_NAME + " - " +
                FeedEntry.COL_BOOK_ID + " - " +
                FeedEntry.COL_BOOK_PUBLICATION + " - " +
                FeedEntry.COL_BOOK_CATEGORY + " - " +
                FeedEntry.COL_BOOK_AUTHOR + " - " +
                FeedEntry.COL_BOOK_QUANTITY + "\n");

        // Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(FeedEntry._ID);
        int booknameColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_NAME);
        int bookidColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_ID);
        int publicationColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_PUBLICATION);
        int categoryColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_CATEGORY);
        int authorColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_AUTHOR);
        int quantityColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_QUANTITY);

        // Iterate through all the returned rows in the cursor
        while (cursor.moveToNext()) {
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            int currentID = cursor.getInt(idColumnIndex);
            String currentBookName = cursor.getString(booknameColumnIndex);
            String currentBookID = cursor.getString(bookidColumnIndex);
            String currentPublication = cursor.getString(publicationColumnIndex);
            String currentAuthor = cursor.getString(authorColumnIndex);
            int currentCategory = cursor.getInt(categoryColumnIndex);
            int currentQuantity = cursor.getInt(quantityColumnIndex);
            // Display the values from each column of the current row in the cursor in the TextView
            displayView.append(("\n" + currentID + " - " +
                    currentBookName + " - " +
                    currentBookID + " - " +
                    currentPublication + " - " +
                    currentCategory + " - " +
                    currentAuthor + " - " +
                    currentQuantity));
        }

        //Perform a query on the books table
        Cursor cursor_user = db.query(
                FeedEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        // Create a header in the Text View that looks like this:
        //
        // The user table contains <number of rows in Cursor> pets.
        // _id - name - breed - gender - weight
        //
        // In the while loop below, iterate through the rows of the cursor and display
        // the information from each column in this order.
        displayView.setText("The user table contains " + cursor_user.getCount() + " books.\n\n");
        displayView.append(FeedEntry1._ID + " - " +
                FeedEntry1.COL_USER_NAME + " - " +
                FeedEntry1.COL_USER_ID + " - " +
                FeedEntry1.COL_USER_DEPT + " - " +
                FeedEntry1.COL_USER_CONTACT_NUMBER + "\n");

        // Figure out the index of each column
        int id_user_tableColumnIndex = cursor.getColumnIndex(FeedEntry1._ID);
        int usernameColumnIndex = cursor.getColumnIndex(FeedEntry1.COL_USER_NAME);
        int useridColumnIndex = cursor.getColumnIndex(FeedEntry1.COL_USER_ID);
        int userdepartmentColumnIndex = cursor.getColumnIndex(FeedEntry1.COL_USER_DEPT);
        int usercontactnumberColumnIndex = cursor.getColumnIndex(FeedEntry1.COL_USER_CONTACT_NUMBER);
        //int authorColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_AUTHOR);
        //int quantityColumnIndex = cursor.getColumnIndex(FeedEntry.COL_BOOK_QUANTITY);

        // Iterate through all the returned rows in the cursor
        while (cursor_user.moveToNext()) {
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            int currentuser_table_ID = cursor_user.getInt(id_user_tableColumnIndex);
            String currentUserName = cursor_user.getString(usernameColumnIndex);
            String currentUserID = cursor_user.getString(useridColumnIndex);
            String currentUserDepartment = cursor_user.getString(userdepartmentColumnIndex);
            int currentusercontactnumber = cursor_user.getInt(usercontactnumberColumnIndex);
            //int currentCategory = cursor.getInt(categoryColumnIndex);
            //int currentQuantity = cursor.getInt(quantityColumnIndex);
            // Display the values from each column of the current row in the cursor in the TextView
            displayView.append(("\n" + currentuser_table_ID + " - " +
                    currentUserName + " - " +
                    currentUserID + " - " +
                    currentUserDepartment + " - " +
                    currentusercontactnumber));
        }
    }

finally {
        cursor.close();
    }
    }

    private void insertBook()
    {
        //Gets the database in write mode
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COL_BOOK_NAME, "Networking Essentials");
        values.put(FeedEntry.COL_BOOK_ID, "333");
        values.put(FeedEntry.COL_BOOK_PUBLICATION, "ABC Publication");
        values.put(FeedEntry.COL_BOOK_CATEGORY, FeedEntry.CATEGORY_ENGINEERING);
        values.put(FeedEntry.COL_BOOK_AUTHOR, "Arya");
        values.put(FeedEntry.COL_BOOK_QUANTITY, 10);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_catalog, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
        // Respond to a click on the "Insert dummy data" menu option
        case R.id.action_insert_dummy_data:
            insertBook();
            displayDatabaseInfo();
            return true;
        case R.id.action_delete_all_entries:
            return true;
    }

    return super.onOptionsItemSelected(item);
}
}
