package com.example.tyler.a11004851_pset4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    Context context;
    ArrayList<todo> contactList;
    todo todo1;
    private ListView listView;
    private EditText todoList;
    private TextView textView;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        helper = new DBHelper(this);

    }

    public void submitTodo(View view) {

//        textView =  (TextView) findViewById(R.id.number);
//        textView.setText(string);
        todoList =  (EditText) findViewById(R.id.todo);
        String string = todoList.getText().toString();
        todo1 = new todo(string, 0);
        helper.create(todo1);
        contactList = helper.read();
        makeTrackAdapter();
    }

//    private class deleteToDo implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(final AdapterView<?> parent,final View view,final int position,final long id){
//            ArrayAdapter arrayAdapter = new ArrayAdapter<todo>
//                    (this, android.R.layout.simple_list_item_1, android.R.id.text1, contactList);
//            todo todo = arrayAdapter.getItem(position);
//
//
//        }
//    }

    public void makeTrackAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<todo>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, contactList);
        listView = (ListView) findViewById(R.id.toDoList);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        todo1.setChecked(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        todo1.setChecked(2);
                        v.performClick();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        todo1.setChecked(3);
                        break;
                }
                return false;
            }
        });
        Log.d("hoi", "status = " + todo1.getChecked());
        assert listView != null;
        listView.setAdapter(arrayAdapter);
    }

    public void changeChecked(View view) {
        todo1.setChecked(1);
    }
}



