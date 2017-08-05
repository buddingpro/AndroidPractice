package app.pratice.database.databasepratice;

import android.content.Context;
import android.content.DialogInterface;
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

    Button save, delete;

    String ProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DailogCreator();

            }
        });

    }

    private void initializeComponents() {


        editTextPrincipal = (EditText) findViewById(R.id.textView_PrincipalValue);
        editTextInterest = (EditText) findViewById(R.id.textView_InterestValue);
        editTextFDtype = (EditText) findViewById(R.id.textViewFDTypeValue);

        save = (Button) findViewById(R.id.saveButton);
        delete = (Button) findViewById(R.id.deleteButton);

        //new change
        //new change 2
        //new change 3

        for(int i = 0; i<10;i++){

        }
    }


    private void saveDetails(String tableName) {

        DatabaseHelper database = new DatabaseHelper(this, "practice.db");
        database.setTableName(tableName);
        boolean result = database.insertData(tableName, editTextPrincipal.getText().toString(), editTextInterest.getText().toString(), editTextFDtype.getText().toString());

        Context ctx = this; // for Activity, or Service. Otherwise simply get the context.
        String dbname = "practice.db";
        File dbpath = ctx.getDatabasePath(dbname);

        if(true) {
            Toast.makeText(this, "inserted " + dbpath, Toast.LENGTH_SHORT).show();
            Log.d("LOCATION",dbpath.toString());
        }
    }


    private void DailogCreator() {

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

                        saveDetails(ProfileName);
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

