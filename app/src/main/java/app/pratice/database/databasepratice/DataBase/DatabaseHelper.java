package app.pratice.database.databasepratice.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MohanRaj_Senthilnath on 4/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private String table_Name = "Fixed_Deposit";


    public void setTableName(String table_Name) {
        this.table_Name = table_Name;
    }

    public String getTableName() {

        if(table_Name==null)
            return "Fixed_Deposit";
        else
        return this.table_Name;
    }

    public DatabaseHelper(Context context, String name) {

        super(context, name, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + this.getTableName() + "(PROFILE_NAME TEXT PRIMARY KEY,PRINCIPAL TEXT,INTEREST TEXT, FD_TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + this.getTableName());
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String PROFILE_NAME, String PRINCIPAL, String INTEREST, String FD_TYPE) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PROFILE_NAME", PROFILE_NAME);
        contentValues.put("PRINCIPAL", PRINCIPAL);
        contentValues.put("INTEREST", INTEREST);
        contentValues.put("FD_TYPE", FD_TYPE);

        if (sqLiteDatabase.insert(this.getTableName(), null, contentValues) == -1)
            return false;
        else
            return true;
    }
}
