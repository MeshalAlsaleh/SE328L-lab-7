package com.example.se328llab7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;



    EditText employeeID,employeeSalary,employeeName,employeeID2,newSalary;

    LinearLayout updatingLayout;

    int x=1;// to make my UPDATE layout visble/unvisible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttnAdd=(Button)findViewById(R.id.bttnAdd);
        Button bttnUpdate=(Button)findViewById(R.id.bttnUpdate);
        Button bttnDelete=(Button)findViewById(R.id.bttnDelete);
        Button bttnView=(Button)findViewById(R.id.bttnView);
        Button bttnSubmitUpdatedSalray=(Button)findViewById(R.id.bttnSubmitUpdatedQuantity);
        Button bttnViewDataOnList=(Button)findViewById(R.id.bttnViewDataOnList);


        myDB=new DatabaseHelper(this);




        employeeSalary=(EditText)findViewById(R.id.employeeSalary);
        employeeName=(EditText)findViewById(R.id.employeeName);
        employeeID2=(EditText)findViewById(R.id.employeeID2);
        employeeID=(EditText)findViewById(R.id.employeeID);

        newSalary=(EditText)findViewById(R.id.productQuantity2);


        updatingLayout=(LinearLayout)findViewById(R.id.layout);// the UPDATE layout

        bttnViewDataOnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,list.class));
            }
        });



        bttnAdd.setOnClickListener(new View.OnClickListener() {
            //adds a record to the table
            @Override
            public void onClick(View v) {

                if(myDB.addData(Integer.parseInt(employeeID.getText().toString()),employeeName.getText().toString(),Integer.parseInt(employeeSalary.getText().toString()))==false)
                    Toast.makeText(MainActivity.this,"Data was not entered into the table \nPlease check your input!",Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(MainActivity.this,"Data was successfully entered into the table",Toast.LENGTH_LONG).show();



            }
        });

        bttnUpdate.setOnClickListener(new View.OnClickListener() {
            //updates the SALARY of a record specified by the id
            // press the update button to make the layout visible, press it again to make it invisble
            @Override
            public void onClick(View v) {
                x++;

                if(x%2==0){
                    updatingLayout.setVisibility(View.VISIBLE);

                    bttnSubmitUpdatedSalray.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDB.update(Integer.parseInt(employeeID2.getText().toString()),Integer.parseInt(newSalary.getText().toString()));

                        }
                    });

                }
                else
                    updatingLayout.setVisibility(View.INVISIBLE);







            }
        });

        bttnDelete.setOnClickListener(new View.OnClickListener() {
            //deletes a row specified  by the employee_NAME
            //then display a toast with the count of rows deleted
            //if no rows are found, display a toast saying that nothing was deleted
            @Override
            public void onClick(View v) {

                int result=myDB.dltRow(employeeName.getText().toString());

                if(result>=1)
                    Toast.makeText(MainActivity.this,+result+"Row(s) were susscessfully deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"No rows were deleted \nPlease try again",Toast.LENGTH_LONG).show();


            }
        });


        //bttnCount

        bttnView.setOnClickListener(new View.OnClickListener() {
            // if nothing is entered in the employee NAME it will view all enteries in the table
            // if a string was written in the employee NAME it will show the record
            //an error will appear if no results were found

            Cursor cur;
            StringBuffer buffer=new StringBuffer();

            @Override
            public void onClick(View v) {
                if (employeeName.getText().toString().equals("")) {
                    cur = myDB.getListContents();
                } else if (!(employeeName.getText().toString().equals(""))) {
                    cur = myDB.getSpecificResult(employeeName.getText().toString());
                }

                if (cur.getCount()==0)
                    Toast.makeText(MainActivity.this, "No results found !", Toast.LENGTH_LONG).show();
                else {

                    while (cur.moveToNext()) {
                        for (int i = 0; i < cur.getColumnCount(); i++) {
                            buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());

                }
            }
        });



    }

    protected Cursor getDataList(){

        return myDB.getListContents();

    }
}