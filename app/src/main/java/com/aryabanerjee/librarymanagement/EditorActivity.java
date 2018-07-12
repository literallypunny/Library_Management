package com.aryabanerjee.librarymanagement;

import android.content.ContentValues;
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

import com.aryabanerjee.librarymanagement.LibraryContract.FeedEntry;

import static com.aryabanerjee.librarymanagement.LibraryContract.*;

public class EditorActivity extends AppCompatActivity {
/**
 * EditText field to enter the book's name
 */
private EditText mBookNameEditText;
/**
 * EditText field to enter the book's id
 */
private EditText mBookIDEditText;
/**
 * EditText field to enter the book's publication
 */
private EditText mBookPublicationEditText;
/**
 * Dropdown spinner to enter the book's category
 */
private Spinner mBookCategorySpinner;
/**
 * EditText field to enter the book's author
 */
private EditText mBookAuthorEditText;
/**
 * EditText field to enter the book's quantity
 */
private EditText mBookQuantityEditText;

/**
 * Gender of the pet. The possible valid values are in the PetContract.java file:
 * {@link FeedEntry#CATEGORY_UNKNOWN}, {@link FeedEntry#CATEGORY_ENGINEERING}, or
 * {@link FeedEntry#CATEGORY_BUSINESS}.
 */
private String mCategory = FeedEntry.CATEGORY_UNKNOWN;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editor);

    // Find all relevant views that we will need to read user input from
    mBookNameEditText = (EditText) findViewById(R.id.edit_book_name);
    mBookIDEditText = (EditText) findViewById(R.id.edit_book_id);
    mBookPublicationEditText = (EditText) findViewById(R.id.edit_book_publication);
    mBookCategorySpinner = (Spinner) findViewById(R.id.spinner_book_category);
    mBookAuthorEditText = (EditText) findViewById(R.id.edit_book_author);
    mBookQuantityEditText = (EditText) findViewById(R.id.edit_book_quantity);

    setupSpinner();
}

/**
 * Setup the dropdown spinner that allows the user to select the gender of the pet.
 */

private void setupSpinner() {
    // Create adapter for spinner. The list options are from the String array it will use
    // the spinner will use the default layout
    ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.array_category_options, android.R.layout.simple_spinner_item);

    // Specify dropdown layout style - simple list view with 1 item per line
    genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

    // Apply the adapter to the spinner
    mBookCategorySpinner.setAdapter(genderSpinnerAdapter);

    // Set the integer mSelected to the constant values
    mBookCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.category_engineering))) {
                    mCategory = FeedEntry.CATEGORY_ENGINEERING;
                } else if (selection.equals(getString(R.string.category_business))) {
                    mCategory = FeedEntry.CATEGORY_BUSINESS;
                } else {
                    mCategory = FeedEntry.CATEGORY_UNKNOWN;
                }
            }
        }

        // Because AdapterView is an abstract class, onNothingSelected must be defined
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            mCategory = FeedEntry.CATEGORY_UNKNOWN;
        }
    });
}

/**
 * Get user input from editor and save new book into database.
 */
private void insertBook() {
    // Read from input fields
    // Use trim to eliminate leading or trailing white space
    String BooknameString = mBookNameEditText.getText().toString().trim();
    String BookidString = mBookIDEditText.getText().toString().trim();
    String BookpublicationString = mBookPublicationEditText.getText().toString().trim();
    String BookauthorString = mBookAuthorEditText.getText().toString().trim();
    String BookquantityString = mBookQuantityEditText.getText().toString().trim();
    int quantity = Integer.parseInt(BookquantityString);

    //Create a database helper
    LibraryDBHelper mDBHelper = new LibraryDBHelper(this);

    //Gets the database in write mode
    SQLiteDatabase db = mDBHelper.getWritableDatabase();

    // Create a ContentValues object where column names are the keys,
    // and pet attributes from the editor are the values.
    ContentValues values = new ContentValues();
    values.put(FeedEntry.COL_BOOK_NAME, BooknameString);
    values.put(FeedEntry.COL_BOOK_ID, BookidString);
    values.put(FeedEntry.COL_BOOK_PUBLICATION, BookpublicationString);
    values.put(FeedEntry.COL_BOOK_CATEGORY, mCategory);
    values.put(FeedEntry.COL_BOOK_AUTHOR, BookauthorString);
    values.put(FeedEntry.COL_BOOK_QUANTITY, quantity);

    // Insert a new row for pet in the database, returning the ID of that new row.
    long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);

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
    getMenuInflater().inflate(R.menu.menu_editor, menu);
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
