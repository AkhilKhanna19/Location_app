package locationtestone.app.com.locationtestone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Akhil Khanna on 11-Nov-15.
 */
public class dataBaseHandler extends SQLiteOpenHelper {

    userDetails details = new userDetails();

    public dataBaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants._ID +
                " INTEGER PRIMARY KEY, " + Constants.COLUMN_FIRST_NAME + " TEXT, " + Constants.COLUMN_LAST_NAME +
                " TEXT, " + Constants.COLUMN_FIRST_NUMBER + " TEXT, " + Constants.COLUMN_SECOND_NUMBER + " TEXT, "
                + Constants.COLUMN_THIRD_NUMBER + " TEXT);";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
        /*db.execSQL("DROP TABLE IF EXISTS" +Constants.tableDetails.TABLE_USER);
        onCreate(db);*/

    }


    public void addUserDetails(userDetails details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.COLUMN_FIRST_NAME, details.getFirstNam());
        contentValues.put(Constants.COLUMN_LAST_NAME, details.getLastNam());
        contentValues.put(Constants.COLUMN_FIRST_NUMBER, details.getFirstNum());
        contentValues.put(Constants.COLUMN_SECOND_NUMBER, details.getSecondNum());
        contentValues.put(Constants.COLUMN_THIRD_NUMBER, details.getThirdNum());
        //Log.d("usernmae", details.firstname);


        db.insert(Constants.TABLE_NAME, null, contentValues);
        db.close();

    }

    public userDetails userInformation() {
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
                //userDetails details = new userDetails();
        if (cursor.moveToFirst()) {

            do {
                details.setFirstNam(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_FIRST_NAME)));
                details.setLastNam(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_LAST_NAME)));
                details.setFirstNum(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_FIRST_NUMBER)));
                details.setSecondNum(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_SECOND_NUMBER)));
                details.setThirdNum(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_THIRD_NUMBER)));


                //Log.d("firstname", details.firstname);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return details;
    }
}
