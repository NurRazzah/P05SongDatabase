package com.example.a16022738.p05songdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifySong extends AppCompatActivity {

    Button btnUpdate, btnDel, btnCancel;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Song song;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_song);

        int selectedRadio = rgStars.getCheckedRadioButtonId();

        btnUpdate = (Button) findViewById(R.id.btnInsert);
        btnDel = (Button) findViewById(R.id.showlist);
        btnCancel = (Button) findViewById(R.id.cancel);
        tv = (TextView)findViewById(R.id.tvall);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etYear = (EditText) findViewById(R.id.etYear);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);
        final RadioButton rbStars = (RadioButton) findViewById(selectedRadio);
        RadioButton rb = (RadioButton)findViewById(selectedRadio);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("data");


        etSingers.setText(String.valueOf(song.getSingers()));
        etYear.setText(String.valueOf(song.getYear()));
        etTitle.setText(String.valueOf(song.getTitle()));

        tv.setText("Id: " + String.valueOf(song.get_id()));

        int modifystar = song.getStars();

        if (modifystar == 1){
            RadioButton rb1 = (RadioButton)findViewById(R.id.rb1);
            rb1.setChecked(true);
        }
        else if (modifystar ==2){
            RadioButton rb2 = (RadioButton)findViewById(R.id.rb2);
            rb2.setChecked(true);
        }
        else if (modifystar ==3){
            RadioButton rb3 = (RadioButton)findViewById(R.id.rb3);
            rb3.setChecked(true);
        }
        else if (modifystar ==4){
            RadioButton rb4 = (RadioButton)findViewById(R.id.rb4);
            rb4.setChecked(true);
        }
        else{
            RadioButton rb5 = (RadioButton)findViewById(R.id.rb5);
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifySong.this);
                song.setTitle(etTitle.getText().toString());
                song.setSingers(etSingers.getText().toString());
                song.setYear(Integer.parseInt(etYear.getText().toString()));



                String Strigtifyrb = rbStars.getText().toString();
                int stars;

                if (Strigtifyrb.equals('1')){
                   stars = 1;
                }
                else if (Strigtifyrb.equals('2')){
                    stars = 2;
                }
                else if (Strigtifyrb.equals('3')){
                    stars = 3;
                }
                else if (Strigtifyrb.equals('4')){
                    stars = 4;
                }
                else{
                    stars = 5;
                }
                song.setStars(stars);
                dbh.updateSong(song);
                dbh.close();
                Toast.makeText(ModifySong.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ModifySong.this,
                        ShowList.class);
                startActivity(i);
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifySong.this);
                dbh.deleteSong(song.get_id());
                dbh.close();
                Toast.makeText(ModifySong.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ModifySong.this,
                        ShowList.class);
                startActivity(i);
                finish();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModifySong.this, MainActivity.class);
                i.putExtra("type","delete");

                setResult(RESULT_OK, i);
                finish();

            }
        });
        }
    }
