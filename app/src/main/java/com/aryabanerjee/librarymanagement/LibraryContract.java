package com.aryabanerjee.librarymanagement;

import android.provider.BaseColumns;

public class LibraryContract {
// To prevent someone from accidentally instantiating the contract class,
// give it an empty constructor.
private LibraryContract() {
}

/**
 * Inner class that defines constant values for the books database table.
 * Each entry in the table represents a single book.
 */
public final class FeedEntry implements BaseColumns {

    public final static String TABLE_NAME = "books";
    public final static String _ID = BaseColumns._ID;
    public final static String COL_BOOK_NAME = "Book_Name";
    public final static String COL_BOOK_ID = "Book_ID";
    public final static String COL_BOOK_PUBLICATION = "Publication";
    public final static String COL_BOOK_CATEGORY = "Category";
    public final static String COL_BOOK_AUTHOR = "Author";
    public final static String COL_BOOK_QUANTITY = "Quantity";

    /**
     * Possible values for the category of the book.
     */
    public static final String CATEGORY_UNKNOWN = "Unknown";
    public static final String CATEGORY_ENGINEERING = "Engineering";
    public static final String CATEGORY_BUSINESS = "Business";


}

/**
 * Inner class that defines constant values for the books database table.
 */
public final class FeedEntry1 implements BaseColumns
{
    public final static String TABLE_NAME_USER = "user";
    public final static String _ID = BaseColumns._ID;
    public final static String COL_USER_ID = "User_ID";
    public final static String COL_USER_NAME = "User_Name";
    public final static String COL_USER_DEPT = "Department";
    public final static String COL_USER_CONTACT_NUMBER = "Contact Number";
    public static final String USER_CHOICE_BOOK_UNKNOWN = "Unknown";

}
}
