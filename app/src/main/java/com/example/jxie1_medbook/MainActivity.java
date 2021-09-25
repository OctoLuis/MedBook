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
/*
   Copyright 2021 Louie Xie

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/*  Class MainActivity
    @author Louie Xie
    @CCID jxie1

    MainActivity, construct the main layout of the application.
    Call to use functionalities upon on click actions.
    lick the floating action button (+) to add a new medicine.
    Edit and Delete function only visible if you make a valid selection.
        Edit: Select any medicine from the listView and click Edit on the bottom side to edit.
              Information will be provides for the medicine and you can just edit whatever you want.
        Delete: Select any medicine from the listView and click Delete to delete your selection.

    Total # of doses displayed on the bottom side of the app, and will automatically update upon
    each modification to the listView (add, edit, delete).

    References:
        Lab Materials
        Android Studio Guide
        stackoverflow
 */
public class MainActivity extends AppCompatActivity implements MedicineFragment.OnFragmentInteractionListener {

    ListView medicineList;
    static ArrayAdapter<Medicine> medicineAdapter;
    static ArrayList<Medicine> dataList = new ArrayList<>();
    static Integer selection;
    static Boolean mode = true; // true: add medicine, false: edit existing medicine


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicineList = findViewById(R.id.medicineList);

        medicineAdapter = new MedicineAdapter(this, R.layout.medicine_content, dataList);
        medicineList.setAdapter(medicineAdapter);

        final Button edit = findViewById(R.id.EditMedicine);
        final Button delete = findViewById(R.id.deleteMedicine);


        TextView totalMed = findViewById(R.id.total);
        Integer doseTotal = doseTotalCalculator.numberOfDoses(dataList);
        totalMed.setText(doseTotal.toString());

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