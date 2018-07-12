package com.aryabanerjee.librarymanagement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry1;
import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class User_IssueActivity extends AppCompatActivity {
/**
 * EditText field to enter the user's name
 */
private EditText mUserNameEditText;
/**
 * EditText field to enter the user's ID
 */
private EditText mUserIDEditText;
/**
 * EditText field to enter the user's department
 */
private EditText mUserDepartmentEditText;
/**
 * Dropdown spinner to enter the user's book of choice
 */
private Spinner mUserBookChoiceSpinner;
/**
 * EditText field to enter the user's contact number
 */
private EditText mUserContactNumber;

private String mUserChoiceBook = FeedEntry1.USER_CHOICE_BOOK_UNKNOWN;



@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user__issue);

    mUserNameEditText = (EditText) findViewById(R.id.edit_user_name);
    mUserIDEditText = (EditText) findViewById(R.id.edit_user_id);
    mUserDepartmentEditText = (EditText) findViewById(R.id.edit_user_department);
    mUserContactNumber = (EditText) findViewById(R.id.edit_user_contact_number);
    mUserBookChoiceSpinner = (Spinner) findViewById(R.id.spinner_user_book_choice);

    loadSpinnerData();
}

private void loadSpinnerData()
{
    //database handler
    LibraryDBHelper db = new LibraryDBHelper(getApplicationContext());

    //Spinner Drop down elements
    List<String> books = db.getAllBooks();

    //Creating adapter for spinner
    ArrayAdapter<String> User_choice_book_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, books);

    //Drop down layout style - drop down list
    User_choice_book_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

    //attaching data adapter to spinner
    mUserBookChoiceSpinner.setAdapter(User_choice_book_adapter);

    mUserBookChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //On selecting a spinner item
            String book = parent.getItemAtPosition(position).toString();

            //Showing selected spinner item
            Toast.makeText(parent.getContext(), "You selected: " + book, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            mUserChoiceBook = FeedEntry1.USER_CHOICE_BOOK_UNKNOWN;
        }
    });
}

/**
 * Get user input from editor and save new pet into database.
 */
private void insertBook() {
    // Read from input fields
    // Use trim to eliminate leading or trailing white space
    String UsernameString = mUserNameEditText.getText().toString().trim();
    String UseridString = mUserIDEditText.getText().toString().trim();
    String UserdepartmentString = mUserDepartmentEditText.getText().toString().trim();
    String UsercontactnumberString = mUserContactNumber.getText().toString().trim();
    //String BookquantityString = mBookQuantityEditText.getText().toString().trim();
    int contact_number = Integer.parseInt(UsercontactnumberString);

    //Create a database helper
    LibraryDBHelper mDBHelper = new LibraryDBHelper(this);

    //Gets the database in write mode
    SQLiteDatabase db = mDBHelper.getWritableDatabase();

    // Create a ContentValues object where column names are the keys,
    // and pet attributes from the editor are the values.
    ContentValues values = new ContentValues();
    values.put(FeedEntry1.COL_USER_NAME, UsernameString);
    values.put(FeedEntry1.COL_USER_ID, UseridString);
    values.put(FeedEntry1.COL_USER_DEPT, UserdepartmentString);
    values.put(FeedEntry1.COL_USER_CONTACT_NUMBER, UsercontactnumberString);
    //values.put(LibraryContract.FeedEntry.COL_BOOK_AUTHOR, BookauthorString);
    //values.put(LibraryContract.FeedEntry.COL_BOOK_QUANTITY, quantity);

    // Insert a new row for pet in the database, returning the ID of that new row.
    long newRowId = db.insert(FeedEntry1.TABLE_NAME_USER, null, values);

    // Show a toast message depending on whether or not the insertion was successful
    if (newRowId == -1) {
        // If the row ID is -1, then there was an error with insertion.
        Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
    } else {
        // Otherwise, the insertion was successful and we can display a toast with the row ID.
        Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
    }
    db.close();
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu options from the res/menu/menu_editor.xml file.
    // This adds menu items to the app bar.
    getMenuInflater().inflate(R.menu.menu_user_issue, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item)
{
    // User clicked on a menu option in the app bar overflow menu
    switch (item.getItemId()) {
        // Respond to a click on the "Save" menu option
        case R.id.action_save:
            // Save pet to database
            insertBook();
            // Exit activity
            finish();
            return true;
        // Respond to a click on the "Delete" menu option
        case R.id.action_delete:
            // Do nothing for now
            return true;
        // Respond to a click on the "Up" arrow button in the app bar
        case android.R.id.home:
            // Navigate back to parent activity (CatalogActivity)
            NavUtils.navigateUpFromSameTask(this);
            return true;
    }
    return super.onOptionsItemSelected(item);

}
}
