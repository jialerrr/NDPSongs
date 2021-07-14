package rp.edu.sg.c346.id20021576.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rgStar;
    Button btnInsert, btnShow;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    Song years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStar);
        btnInsert = findViewById(R.id.btnUpd);
        btnShow = findViewById(R.id.btnDel);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataTitle = etTitle.getText().toString();
                String dataSinger = etSinger.getText().toString();
                int dataYear = Integer.parseInt(etYear.getText().toString());
                int checkButton = rgStar.getCheckedRadioButtonId();
                String dataStars = "";
                if (checkButton == R.id.rbStar1) {
                    dataStars = "*";
                } else if (checkButton == R.id.rbStar2) {
                    dataStars = "**";
                } else if (checkButton == R.id.rbStar3) {
                    dataStars = "***";
                } else if (checkButton == R.id.rbStar4) {
                    dataStars = "****";
                } else if (checkButton == R.id.rbStar5) {
                    dataStars = "*****";
                }
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(dataTitle, dataSinger, dataYear, dataStars);

                    if(inserted_id < 0) {
                        Toast.makeText(MainActivity.this, "Insert unsuccessful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                    }

                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                rgStar.clearCheck();

                }

        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

    }
}