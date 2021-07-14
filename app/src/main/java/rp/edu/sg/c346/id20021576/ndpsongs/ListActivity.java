package rp.edu.sg.c346.id20021576.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

public class ListActivity extends AppCompatActivity {

    Button btnFilter;
    ListView lv;
    Spinner spnYear;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    ArrayList<String> spnAl;
    ArrayAdapter<String> spnAa;
    Song year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnFilter = findViewById(R.id.btnFilter);
        spnYear = findViewById(R.id.spnYear);
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(ListActivity.this);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
        String [] array = getResources().getStringArray(R.array.spinnerYear);
        spnAl = new ArrayList<String>();
        spnAa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spnAl);
        spnYear.setAdapter(spnAa);
        spnAa.add("Filter Years");
        for (int i = 0; i < al.size(); i++) {
            spnAa.add(String.valueOf(al.get(i).getYear()));
        }

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                ArrayList<Song> al2;
                al2 = new ArrayList<Song>();
                al2.addAll(dbh.getAllSongs("*****"));

                ArrayAdapter aa2 = new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_1, al2);
                lv.setAdapter(aa2);
                aa2.notifyDataSetChanged();
                }
            });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(ListActivity.this, ThirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ArrayList<Song> alFilt;
                    alFilt = new ArrayList<Song>();
                    int yearFind = 0;

                    if(spnYear.getSelectedItemPosition() == 0) {
                        lv.setAdapter(aa);
                    } else {
                        yearFind = Integer.parseInt(spnYear.getSelectedItem().toString());
                        for (int i = 0; i < al.size(); i++) {
                            if (al.get(i).getYear() == yearFind) {
                                alFilt.add(al.get(i));
                            }
                        }
                        ArrayAdapter aaFilt = new ArrayAdapter<Song>(ListActivity.this, android.R.layout.simple_list_item_1, alFilt);
                        lv.setAdapter(aaFilt);


                        aaFilt.notifyDataSetChanged();
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        }

}
