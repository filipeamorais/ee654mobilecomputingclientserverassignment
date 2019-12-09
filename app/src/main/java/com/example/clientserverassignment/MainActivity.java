package com.example.clientserverassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serverclientassignment.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    protected com.example.serverclientassignment.IMyAidlInterface service;
    ServiceConnection mServiceConn;
    TextView textViewResult;
    EditText editTextValue;
    int whichAttribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining variables and views
        final CharSequence[] fieldOptions = new CharSequence[] {"All", "Title", "Author", "Publisher", "Year"};
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        editTextValue = (EditText)findViewById(R.id.editTextValue);

        //loading the images
        ImageView viewDatabaseImageView = this.findViewById(R.id.viewDatabaseImageView);
        viewDatabaseImageView.setImageResource(R.drawable.database_view);
        ImageView editDatabaseImageView = this.findViewById(R.id.editDatabaseImageView);
        editDatabaseImageView.setImageResource(R.drawable.database_edit);
        ImageView deleteDatabaseImageView = this.findViewById(R.id.deleteDatabaseImageView);
        deleteDatabaseImageView.setImageResource(R.drawable.database_delete);
        //creating the dialogs with the options
        viewDatabaseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialogbuilder =
                        new AlertDialog.Builder(MainActivity.this);
                alertdialogbuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                //code for the database operation
                                try {
                                    String argument = editTextValue.getText().toString();
                                    if (editTextValue.getText().toString().trim().length() == 0) {argument="0";}
                                    String str = service.clickedShow(whichAttribute, argument);
                                    textViewResult.setText(str);
                                } catch (RemoteException e) {e.printStackTrace();}
                            }
                        });
                alertdialogbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // User cancelled the dialog
                            }
                        });

                alertdialogbuilder.setSingleChoiceItems(fieldOptions, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(getBaseContext(), fieldOptions[which]+ " selected", Toast.LENGTH_SHORT).show();
                                whichAttribute = which;
                            }
                        });

                AlertDialog dialog = alertdialogbuilder.create();
                dialog.show();
            }


        });

        deleteDatabaseImageView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
               AlertDialog.Builder alertdialogbuilder =
                       new AlertDialog.Builder(MainActivity.this);
               alertdialogbuilder.setPositiveButton("OK",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog,
                                               int id) {
                               //code for the database operation
                               try {
                                   String argument = editTextValue.getText().toString();
                                   if (editTextValue.getText().toString().trim().length() == 0) {argument="0";}
                                   String str = service.clickedDelete(whichAttribute, argument);
                                   textViewResult.setText(str);
                               } catch (RemoteException e) {e.printStackTrace();}
                           }
                       });
               alertdialogbuilder.setNegativeButton("Cancel",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog,
                                               int id) {
                               // User cancelled the dialog
                           }
                       });

               alertdialogbuilder.setSingleChoiceItems(fieldOptions, 0,
                       new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog,
                                               int which) {
                               Toast.makeText(getBaseContext(), fieldOptions[which]+ " selected", Toast.LENGTH_SHORT).show();
                               whichAttribute = which;
                           }
                       });

               AlertDialog dialog = alertdialogbuilder.create();
               dialog.show();
                                                       }


                                                   });
        //initiating connection to the server
        initConnection();
    }

    void initConnection(){
        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name){
                service = null;
                Toast.makeText(getApplicationContext(),
                        "Disconnected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onServiceConnected(ComponentName name, IBinder iservice){
                service = IMyAidlInterface.Stub.asInterface(iservice);
                Toast.makeText(getApplicationContext(), "Connected",  Toast.LENGTH_SHORT).show();
            }
        };
        if(service == null) {
            Intent it = new Intent();
            it.setClassName("com.example.serverclientassignment",
                    "com.example.serverclientassignment.ComputeService");
            bindService(it, mServiceConn, Service.BIND_AUTO_CREATE);
        }
    } // end of initConnection()

    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConn);
    }
}
