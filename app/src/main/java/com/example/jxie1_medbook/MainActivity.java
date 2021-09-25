package com.example.jxie1_medbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MedicineFragment.OnFragmentInteractionListener{

    ListView medicineList;
    static ArrayAdapter<Medicine> medicineAdapter;
    static ArrayList<Medicine> dataList = new ArrayList<>();
    static Integer selection;
    static Boolean mode = true; // true: add, false: edit


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicineList = findViewById(R.id.medicineList);

        medicineAdapter = new MyAdapter(this, R.layout.medicine_content, dataList);
        medicineList.setAdapter(medicineAdapter);

        final Button edit = findViewById(R.id.EditMedicine);
        final Button delete = findViewById(R.id.deleteMedicine);


        TextView totalMed = findViewById(R.id.total);
        totalMed.setText(((Integer) dataList.size()).toString());

        medicineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selection = i;
                medicineList.setSelector(R.color.grey_3);
                edit.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
            }
        });

        final FloatingActionButton add = findViewById(R.id.floatingActionButton_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = true;
                new MedicineFragment().show(getSupportFragmentManager(), "Add");
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = false;
                if (selection != null) {
                    MedicineFragment editMedicine =
                            MedicineFragment.newInstance(dataList.get(selection));
                    editMedicine.show(getSupportFragmentManager(), "Edit");
                }
            }
        });

    }

    public void deleteMedicine(View view) {
        if (selection != null) {
            dataList.remove(selection.intValue());
            selection = null;
            medicineAdapter.notifyDataSetChanged();
            medicineList.setSelector(R.color.white);
            Button edit = findViewById(R.id.EditMedicine);
            Button delete = findViewById(R.id.deleteMedicine);
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);

            TextView totalMed = findViewById(R.id.total);
            Integer doseTotal = doseTotalCalculator.numberOfDoses(dataList);
            totalMed.setText(doseTotal.toString());

        }
    }

    @Override
    public void onEditConfirmPressed(Medicine medicine) {
        if (selection != null) {
            dataList.get(selection).setName(medicine.getName());
            dataList.get(selection).setDailyFrequency(medicine.getDailyFrequency());
            dataList.get(selection).setDoseAmount(medicine.getDoseAmount());
            dataList.get(selection).setDoseUnit(medicine.getDoseUnit());
            dataList.get(selection).setDateStart(medicine.getDateStart());
            selection = null;

            medicineAdapter.notifyDataSetChanged();
            medicineList.setSelector(R.color.white);

            Button edit = findViewById(R.id.EditMedicine);
            Button delete = findViewById(R.id.deleteMedicine);
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);

            TextView totalMed = findViewById(R.id.total);
            Integer doseTotal = doseTotalCalculator.numberOfDoses(dataList);
            totalMed.setText(doseTotal.toString());
        }
    }

    @Override
    public void onAddConfirmPressed(Medicine medicine) {
        medicineAdapter.add(medicine);

        selection = null;
        medicineList.setSelector(R.color.white);

        Button edit = findViewById(R.id.EditMedicine);
        Button delete = findViewById(R.id.deleteMedicine);
        edit.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);

        TextView totalMed = findViewById(R.id.total);
        Integer doseTotal = doseTotalCalculator.numberOfDoses(dataList);
        totalMed.setText(doseTotal.toString());
    }
}