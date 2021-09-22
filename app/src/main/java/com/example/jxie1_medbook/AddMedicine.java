package com.example.jxie1_medbook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMedicine extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);
    }

    public void addMedicine(View view) throws Exception {

        EditText new_medicine_name = (EditText) findViewById(R.id.new_medicine_name);
        EditText new_medicine_year = (EditText) findViewById(R.id.new_medicine_year);
        EditText new_medicine_day = (EditText) findViewById(R.id.new_medicine_day);
        EditText new_medicine_month = (EditText) findViewById(R.id.new_medicine_month);
        EditText new_medicine_dose = (EditText) findViewById(R.id.new_medicine_dose);
        EditText new_medicine_freq = (EditText) findViewById(R.id.new_medicine_freq);
        RadioGroup new_medicine_unit = (RadioGroup) findViewById(R.id.unit_selection);
        int selectedUnit= new_medicine_unit.getCheckedRadioButtonId();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd");

        String name = new_medicine_name.getText().toString();
        String unit = ((RadioButton) findViewById(selectedUnit)).getText().toString();
        Integer dose = Integer.parseInt(new_medicine_dose.getText().toString());
        String day = new_medicine_day.getText().toString();
        String month = new_medicine_month.getText().toString();
        String year = new_medicine_year.getText().toString();
        String inputDateString = year+"-"+month+"-"+day;
        Integer freq = Integer.parseInt(new_medicine_freq.getText().toString());
        Date date = dateFormat.parse("2000-01-01");
        date = dateFormat.parse(inputDateString);

        MainActivity.dataList.add(new Medicine(date, name, dose, unit, freq));
        MainActivity.medicineAdapter.notifyDataSetChanged();
        finish();
    }
}
