package rp.edu.sg.c346.id20021576.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    TextView tvId;
    EditText etTitle, etSinger, etYear;
    RadioGroup rgStar;
    RadioButton rbStar1, rbStar2, rbStar3, rbStar4, rbStar5;
    Button btnUpd, btnDel, btnCancel;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvId = findViewById(R.id.tvId);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStar);
        rbStar1 = findViewById(R.id.rbStar1);
        rbStar2 = findViewById(R.id.rbStar2);
        rbStar3 = findViewById(R.id.rbStar3);
        rbStar4 = findViewById(R.id.rbStar4);
        rbStar5 = findViewById(R.id.rbStar5);
        btnUpd = findViewById(R.id.btnUpd);
        btnDel = findViewById(R.id.btnDel);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");
        tvId.setText("ID: " + data.get_id());
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));
        etSinger.setText(data.getSingers());

        if ((data.getStar()) == "*") {
            rbStar1.setChecked(true);
        } else if (data.getStar().equals("**")) {
            rbStar2.setChecked(true);
        } else if (data.getStar().equals("***")) {
            rbStar3.setChecked(true);
        } else if (data.getStar().equals("****")) {
            rbStar4.setChecked(true);
        } else
            if (data.getStar().equals("*****")) {
            rbStar5.setChecked(true);
        }

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
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
                data.setStars(dataStars);
                dbh.updateSong(data);
                dbh.close();
                Toast.makeText(ThirdActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.get_id());
                Toast.makeText(ThirdActivity.this, "Delete successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ThirdActivity.this, ListActivity.class);
                startActivity(intent2);
            }
        });
    }

}