package com.example.se328llab7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class list extends ListActivity {


    TextView txtView;

    List<String> args = new ArrayList<String>();
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myDB = new DatabaseHelper(this);
        TextView txtView = (TextView) findViewById(R.id.textView222);


        Cursor cur = myDB.getListContents();
        StringBuffer buffer = new StringBuffer();


        while (cur.moveToNext()) { //populating the buffer
            for (int i = 0; i < cur.getColumnCount(); i++) {
                buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
            }
            buffer.append("\n");
        }


        args.clear();

        int y = 0;
        int z = 0;

        for (int i = 0; i < buffer.length(); i++) {

            if (buffer.charAt(i) == '\n') {//checking where the \n is located
                z = z + 1;
            }

            try {
                if (z % 4 == 0 && z > 0) { //here its 4 since we will have \n repeated four times per record
                    args.add(buffer.substring(y, i - 1));
                    z = 0;
                    y = i + 1;
                }
            } catch (Exception e) {
                Toast.makeText(list.this, "Error in z method", Toast.LENGTH_LONG).show();
            }

        }


        setListAdapter(new ArrayAdapter<String>(list.this, R.layout.activity_list, R.id.textView222, args));


    }
}