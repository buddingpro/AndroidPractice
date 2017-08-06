package app.pratice.database.databasepratice;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import app.pratice.database.databasepratice.DataBase.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    EditText editTextPrincipal, editTextInterest, editTextFDtype;

    Button save, delete, retrieve , update;

    String ProfileName;

    public static final String MainActivity_TAG = "View Operations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        init_Listeners();
        Log.i(MainActivity_TAG, "onCreate: View Created");
    }

    private void initializeComponents() {

        editTextPrincipal = (EditText) findViewById(R.id.textView_PrincipalValue);
        editTextInterest = (EditText) findViewById(R.id.textView_InterestValue);
        editTextFDtype = (EditText) findViewById(R.id.textViewFDTypeValue);

        save = (Button) findViewById(R.id.saveButton);
        delete = (Button) findViewById(R.id.deleteButton);
        retrieve = (Button) findViewById(R.id.ButtonRetrieve);
        update = (Button) findViewById(R.id.UpdateButton);
    }


    private void init_Listeners(){

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveDetails();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                deleteEntry();

            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getDetails();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

    }

    private void saveDetails() {

        DatabaseHelper database = new DatabaseHelper(this);
        database.InsertData(database,editTextPrincipal.getText().toString(),editTextInterest.getText().toString());
        Toast.makeText(getBaseContext(),"Data inserted",Toast.LENGTH_SHORT).show();
        database.closeDatabase();
    }

    private void getDetails(){
        DatabaseHelper database = new DatabaseHelper(this);
        Cursor cursor =  database.getData(database);
        cursor.moveToFirst();
        do{
            Log.i(MainActivity_TAG ,cursor.getString(0) +" & "+cursor.getString(1));
        }
        while ( cursor.moveToNext());

        Log.i(MainActivity_TAG ,"Details received");
        database.closeDatabase();
    }


    private void deleteEntry() {

        String inputData = editTextFDtype.getText().toString();
        DatabaseHelper database = new DatabaseHelper(this);
        Cursor cursor = database.getRow(database, inputData);
        cursor.moveToFirst();
        do {

            Log.i(MainActivity_TAG ,cursor.getString(0));
            database.delete(database,inputData,cursor.getString(0));
        }
        while(cursor.moveToNext());

    }

    private void updateInfo(){
        String inputData = editTextFDtype.getText().toString();
        DatabaseHelper database = new DatabaseHelper(this);
        database.update(database,editTextPrincipal.getText().toString(),editTextInterest.getText().toString() ,inputData);
    }

    private void DialogCreator() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Save Profile");
        alertDialogBuilder.setMessage("Enter Profile Name");

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialogBuilder.setView(input);


        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ProfileName = input.getText().toString();

                        saveDetails();
                    }
                });

        alertDialogBuilder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

