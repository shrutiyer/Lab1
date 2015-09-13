package com.example.siyer.grocerylist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> toDoList = new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View addListnr = findViewById(R.id.addbtn);
        final EditText addedGrocery = (EditText) findViewById(R.id.groceryItem);
        addListnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList.add(0,addedGrocery.getText().toString());
                itemsAdapter.notifyDataSetChanged();
                addedGrocery.setText("");
            }
        });
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog.Builder alertEdit = new AlertDialog.Builder(this);
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoList);
        ListView listView = (ListView) findViewById(R.id.groceryList);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                System.out.println(position);
                System.out.println(id);
                //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                alertDialogBuilder.setMessage("Do you want to edit or delete the item?");

                alertDialogBuilder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MainActivity.this, "You clicked edit button", Toast.LENGTH_LONG).show();

                        alertEdit.setMessage("Enter the item");
                        final EditText editedItem = new EditText(MainActivity.this);

                        editedItem.setInputType(InputType.TYPE_CLASS_TEXT);
                        alertEdit.setView(editedItem);

                        alertEdit.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                toDoList.set(position,editedItem.getText().toString());
                                System.out.println(editedItem.getText().toString());
                                itemsAdapter.notifyDataSetChanged();
                            }
                        });

                        alertEdit.setNegativeButton("Cancel", null);
                        alertEdit.show();
                    }
                });

                alertDialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        toDoList.remove(position);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item has been deleted", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
