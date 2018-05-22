package com.example.a16022738.p05songdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


    public class ShowList extends AppCompatActivity {
        DBHelper db;
        ArrayList<Song> al;
        ArrayAdapter aa;
        ListView lv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.showlist);

            lv = (ListView) findViewById(R.id.lv);

            db = new DBHelper(this);
            db.getWritableDatabase();

            al = db.getAllSongs();
            db.close();

            aa = new ArrayAdapter<String>(ShowList.this,
                    android.R.layout.simple_list_item_1, al);
            
            lv.setAdapter(aa);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int
                        position, long identity) {
                    Intent i = new Intent(ShowList.this,
                            ModifySong.class);

                    Song data = al.get(position);

                    String title = data.getTitle();
                    String singer = data.getSingers();
                    int year = data.getYear();
                    int star = data.getStars();

                    Song selected = new Song(title, singer, year, star);
                    i.putExtra("data", selected);
                    startActivityForResult(i, 9);
                }
            });

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode,
                                        Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == 9){
                lv.performClick();
            }
        }
    }
