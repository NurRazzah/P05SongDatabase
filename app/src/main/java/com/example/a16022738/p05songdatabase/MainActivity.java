package com.example.a16022738.p05songdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    TextView tvTitle, tvSingers, tvYear, tvStars;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int selectedRadio = rgStars.getCheckedRadioButtonId();

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnShowList = (Button)findViewById(R.id.showlist);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSingers = (TextView) findViewById(R.id.tvSingers);
        tvYear = (TextView)findViewById(R.id.tvYear);
        tvStars = (TextView)findViewById(R.id.tvStar);
        etSingers = (EditText)findViewById(R.id.etSingers);
        etTitle = (EditText)findViewById(R.id.etTitle);
        etYear = (EditText)findViewById(R.id.etYear);
        rgStars =(RadioGroup)findViewById(R.id.rgStars);
        final RadioButton rbStars=(RadioButton)findViewById(selectedRadio);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String singers = etSingers.getText().toString();
                String title = etTitle.getText().toString();
                String year = etYear.getText().toString();
                int IntOfYear = Integer.valueOf(year);

                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars;

                if(rbStars.getText().toString().equals("1")){
                    stars = 1;
                }else if(rbStars.getText().toString().equals("2")){
                    stars = 2;
                }if(rbStars.getText().toString().equals("3")){
                    stars = 3;
                }if(rbStars.getText().toString().equals("4")){
                    stars = 4;
                }if(rbStars.getText().toString().equals("5")){
                    stars = 5;


                    long row_affected = dbh.insertSong(singers,title,IntOfYear, stars);
                    dbh.close();

                    if (row_affected != -1){
                        Toast.makeText(MainActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowList.class);
                startActivity(i);
            }
        });

    }
}
