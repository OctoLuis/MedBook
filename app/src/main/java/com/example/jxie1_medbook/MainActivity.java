package com.example.jxie1_medbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView medicineList;
    static ArrayAdapter<Medicine> medicineAdapter;
    static ArrayList<Medicine> dataList = new ArrayList<>();
    static Integer selection;

    Medicine med_1 = new Medicine(new Date(), "Weed", 5, "mg", 3);
    Medicine med_2 = new Medicine(new Date(), "Meat", 200, "mg", 4);
    Medicine med_3 = new Medicine(new Date(), "Lemon", 10, "mg", 2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicineList = findViewById(R.id.medicineList);

        dataList.add(med_1);
        dataList.add(med_2);
        dataList.add(med_3);

        medicineAdapter = new MyAdapter(this, R.layout.medicine_content, dataList);
        medicineList.setAdapter(medicineAdapter);

        medicineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selection = i;
                medicineList.setSelector(R.color.grey_2);
            }
        });

    }

    public void addNewMedicine(View view) {
        Intent intent = new Intent(this, AddMedicine.class);
        startActivity(intent);
    }

    public void deleteMedicine(View view) {
        if (selection != null) {
            dataList.remove(selection.intValue());
            selection = null;
            medicineAdapter.notifyDataSetChanged();
            medicineList.setSelector(R.color.white);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}