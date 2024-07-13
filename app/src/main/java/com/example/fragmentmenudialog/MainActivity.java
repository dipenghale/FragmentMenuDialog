package com.example.fragmentmenudialog;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Optionally, you can add a title and a navigation icon
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My App Bar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_icon); // Replace with your own icon
        }

        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);

        button1.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentcontainer, FirstFragment.class, null)
                    .commit();

        });


        button2.setOnClickListener(v -> {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentcontainer, SecondFragment.class, null)
                    .commit();

        });

        button3.setOnClickListener(v -> {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentcontainer, ThirdFragment.class, null)
                    .commit();

        });


        //custom dialog
        findViewById(R.id.customDialog).setOnClickListener(v-> {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_dialog, viewGroup, false);
                builder.setView(dialogView);
                //AlertDialog alertDialog = builder.create();
                builder.show();




        });


        //date picker and time picker dialog
       Button btnDatePicker=findViewById(R.id.btn_date);
       Button btnTimePicker=findViewById(R.id.btn_time);
       EditText txtDate=findViewById(R.id.in_date);
       EditText txtTime=findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(v-> {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int yy, int mm, int dd) {
                                txtDate.setText(dd + "-" + (mm + 1) + "-" + yy);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

        });


        btnTimePicker.setOnClickListener(v-> {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }

                        }, mHour, mMinute, false);
                timePickerDialog.show();

        });

        Button btn = findViewById(R.id.btnShow);
        Button btnpop = findViewById(R.id.btnShowPop);
        registerForContextMenu(btn);


        Button closeButton = findViewById(R.id.closebtn);
        builder = new AlertDialog.Builder(this);
        closeButton.setOnClickListener(v-> {


                //Uncomment the below code to Set the message and title from the strings.xml file
                //builder.setMessage(R.string.dialog_message);
                builder.setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to close this application ?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //Toast.makeText(getApplicationContext(),"you choose yes action for alertbox", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you clicked no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                //AlertDialog alert = builder.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                builder.show();

        });

        btnpop.setOnClickListener(v-> {

                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                //popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) MainActivity.this);
                popup.inflate(R.menu.popup_menu);
            popup.show();//showing popup menu

            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(MainActivity.this, "Clicked "+item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
                /*
                switch (item.getItemId()) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Clicked Search", Toast.LENGTH_SHORT).show();
                        return true;
                    case 1:
                        Toast.makeText(MainActivity.this, "Clicked Upload" , Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }*/
            });




        });

    //}

    }
    //options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
        /*
        switch(item.getItemId()) {
            case 0:
                // do your code
                return true;
            case 1:
                // do your code
                return true;
            case 2:
                // do your code
                return true;
            case 3:
                // do your code
                return true;
            case 4:
                // do your code
                return true;
            case 5:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/

        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.setHeaderTitle("Context Menu");
        //menu.add(0, v.getId(), 0, "Upload");
        //menu.add(0, v.getId(), 0, "Search");
        //menu.add(0, v.getId(), 0, "Share");
        //menu.add(0, v.getId(), 0, "Bookmark");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        menu.setHeaderTitle("Select Context Menu");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }


}