package app.pratice.database.databasepratice.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;
/**
 * Created by MohanRaj_Senthilnath on 4/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fd_db";
    //public static final String TABLE_NAME = "fd_info";
    public static final String TABLE_NAME = "fd_info_two";
    public static final String PRINCIPAL_VALUE = "principal";
    public static final String INTEREST_VALUE = "interest";


    public static String CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+"("+PRINCIPAL_VALUE+" TEXT,"+INTEREST_VALUE+" TEXT);";

    public static final String DB_TAG = "Database Operations";

    public SQLiteDatabase SQLdb;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(DB_TAG,"database Created ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(DB_TAG,"Table Creating "+CREATE_QUERY);
        db.execSQL(CREATE_QUERY);
        Log.i(DB_TAG,"Table Created "+CREATE_QUERY);
    }

    public void InsertData(DatabaseHelper dbh , String principal_value , String interest_Value){

        this.SQLdb = dbh.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(PRINCIPAL_VALUE,principal_value);
        contentvalues.put(INTEREST_VALUE,interest_Value);
        long response = SQLdb.insert(TABLE_NAME,null,contentvalues);

        Log.i(DB_TAG, "InsertData: One Row inserted " +response);


    }

    public Cursor getData(DatabaseHelper dbh){

        this.SQLdb = dbh.getReadableDatabase();
        String[] columns = {PRINCIPAL_VALUE ,INTEREST_VALUE};
        Cursor cursor = SQLdb.query(TABLE_NAME,columns,null,null,null,null,null);
        return cursor;

    }


    public void closeDatabase(){

        this.SQLdb.close();
    }


    public Cursor getRow(DatabaseHelper dbh, String principal){

        this.SQLdb = dbh.getReadableDatabase();
        String selection  = PRINCIPAL_VALUE + " LIKE ?";
        String[] columns = {INTEREST_VALUE};
        String[] args = {principal};
        Log.i(DB_TAG, "Get Data " +principal);
        Cursor cursor = SQLdb.query(TABLE_NAME,columns,selection,args,null,null,null);
        return cursor;
    }

    public void delete(DatabaseHelper dbh, String principal_value , String interest_Value){
        this.SQLdb = dbh.getWritableDatabase();
        String selection  = PRINCIPAL_VALUE + " LIKE ? AND "+INTEREST_VALUE+" LIKE ?";
        String[] args = {principal_value,interest_Value};
        SQLdb.delete(TABLE_NAME,selection,args);
    }


    public void update(DatabaseHelper dbh, String principal_value , String interest_Value , String updated_principal_value){
        this.SQLdb = dbh.getWritableDatabase();
        String selection  = PRINCIPAL_VALUE + " LIKE ? AND "+INTEREST_VALUE+" LIKE ?";
        String[] args = {principal_value,interest_Value};
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(PRINCIPAL_VALUE,updated_principal_value);
        SQLdb.update(TABLE_NAME,contentvalues,selection,args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
