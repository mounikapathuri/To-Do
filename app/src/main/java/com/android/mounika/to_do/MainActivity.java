package com.android.mounika.to_do;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.android.mounika.to_do.R.layout.dialog;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database=null;
    DataBase mydb=new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list1);
        String[] data = {"one", "two", "three", "four", "five", "six", "seven", "eight"};
        MyAdapter adapter = new MyAdapter(data);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.item1:
                View menuitem = findViewById(R.id.item1);
                menuitem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                        final View mView = getLayoutInflater().inflate(dialog, null);
                        Button button= (Button) mView.findViewById(R.id.button3);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final EditText mEnter = (EditText) mView.findViewById(R.id.editText);
                                final EditText mEntersomething = (EditText) mView.findViewById(R.id.editText2);
                                DatePicker mDate = (DatePicker) mView.findViewById(R.id.datePicker2);
                                String title=mEnter.getText().toString();
                                String description=mEntersomething.getText().toString();
                                String day= String.valueOf(mDate.getDayOfMonth());
                                String month= String.valueOf(mDate.getMonth());
                                String year= String.valueOf(mDate.getYear());
                                StringBuilder sb=new StringBuilder();
                                sb.append(day).append("/").append(month).append("/").append(year);
                                String date=sb.toString();

                                if(mEnter.length()!=0&&mEntersomething.length()!=0){
                                    ContentValues map=new ContentValues();
                                    map.put("KEY_TITLE",title);
                                    map.put("KEY_DESCRIPTION",description);
                                    map.put("KEY_DATE",date);
                                    database.insert("Mydatatable",null,map);
                                }
                                String col[]={"KEY_TITLE","KEY_DESCRIPTION","KEY_DATE"};
                                Cursor cursor = database.query("Mydatatable",col,null,null,null,null,null);
                                if(cursor!=null && cursor.getCount() >0){
                                    cursor.moveToFirst();

                                    do{
                                        String mtitle=cursor.getString(0);
                                        String mdescription=cursor.getString(1);
                                        String mdate=cursor.getString(2);

                                        Log.i("Detail","Title="+mtitle+"Description= "+mdescription+" Date="+mdate);
                                    }
                                    while(cursor.moveToNext());
                                }else{
                                    Toast.makeText(MainActivity.this,"no database",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                        mBuilder.setView(mView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                    }
                });
                break;
        }
        return true;
    }

    class MyAdapter extends BaseAdapter{
        String[] data=null;
        MyAdapter(String data[]){
            this.data=data;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=LayoutInflater.from(MainActivity.this);

            View view=inflater.inflate(R.layout.list,null);
            return view;
        }
    }
}
