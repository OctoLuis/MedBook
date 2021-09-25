package com.example.jxie1_medbook;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;

/*  Class AddMedicineFragment
    @Author Louie Xie
 */
public class MedicineFragment extends DialogFragment {
    private EditText medicineName;
    private EditText medicineDose;
    private RadioGroup medicineUnit;
    private RadioButton mg;
    private RadioButton mcg;
    private RadioButton drop;
    private EditText medicineFreq;
    private EditText medicineDay;
    private EditText medicineMonth;
    private EditText medicineYear;

    @SuppressLint("SimpleDateFormat") static SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd");


    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener {
        void onAddConfirmPressed(Medicine medicine);
        void onEditConfirmPressed(Medicine medicine);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must be implemented.");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.new_medicine, null);
        medicineName = view.findViewById(R.id.new_name);
        medicineDose = view.findViewById(R.id.new_dose);
        medicineFreq = view.findViewById(R.id.new_freq);
        medicineUnit = view.findViewById(R.id.new_unit);
        mg = view.findViewById(R.id.unit_mg_1);
        mcg = view.findViewById(R.id.unit_mcg_1);
        drop = view.findViewById(R.id.unit_drop_1);
        medicineDay = view.findViewById(R.id.new_day);
        medicineMonth = view.findViewById(R.id.new_month);
        medicineYear = view.findViewById(R.id.new_year);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if (getArguments() != null) {
            String date = getArguments().getString("date");
            String unit = getArguments().getString("unit");

            switch (unit) {
                case "mcg":
                    mg.setChecked(false);
                    mcg.setChecked(true);
                    drop.setChecked(false);
                    break;
                case "drop":
                    mg.setChecked(false);
                    mcg.setChecked(false);
                    drop.setChecked(true);
                    break;
                case "mg":
                default:
                    // Generally, this is not used, just in case.
                    mg.setChecked(true);
                    mcg.setChecked(false);
                    drop.setChecked(false);
                    break;
            }

            medicineName.setText(getArguments().getString("name"));
            medicineDose.setText(getArguments().getString("dose"));
            medicineFreq.setText(getArguments().getString("frequency"));

            medicineYear.setText(date.substring(0,4));
            medicineMonth.setText(date.substring(5,7));
            medicineDay.setText(date.substring(8,10));
        }

        if (!MainActivity.mode && MainActivity.selection != null) {

            return builder
                    .setView(view)
                    .setTitle("Edit Medicine")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name = medicineName.getText().toString();
                            int unitID = medicineUnit.getCheckedRadioButtonId();
                            String unit = "mg"; // default
                            if (unitID == mg.getId()) {
                                unit = "mg";
                            } else if (unitID == mcg.getId()){
                                unit = "mcg";
                            } else if (unitID == drop.getId()) {
                                unit = "drop";
                            }

                            String day = medicineDay.getText().toString();
                            String month = medicineMonth.getText().toString();
                            String year = medicineYear.getText().toString();
                            String doseStr = medicineDose.getText().toString();
                            String freqStr = medicineFreq.getText().toString();

                            String inputDateString = year+"-"+month+"-"+day;

                            if (TextUtils.isEmpty(day) || TextUtils.isEmpty(month) ||
                                    TextUtils.isEmpty(year) || TextUtils.isEmpty(name) ||
                                    TextUtils.isEmpty(doseStr) || TextUtils.isEmpty(freqStr)) {
                                Toast.makeText(view.getContext(), "Please Enter All Required Information!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Float dose = Float.parseFloat(doseStr);
                            Integer freq = Integer.parseInt(freqStr);

                            Date date = new Date();
                            try {
                                date = dateFormat.parse(inputDateString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Medicine newMed = new Medicine(date, name, dose, unit, freq);
                            listener.onEditConfirmPressed(newMed);
                        }
                    }).create();


        } else {

            return builder
                    .setView(view)
                    .setTitle("Add Medicine")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name = medicineName.getText().toString();
                            int unitID = medicineUnit.getCheckedRadioButtonId();
                            String unit = "N/A";
                            if (unitID == mg.getId()) {
                                unit = "mg";
                            } else if (unitID == mcg.getId()){
                                unit = "mcg";
                            } else if (unitID == drop.getId()) {
                                unit = "drop";
                            }

                            String day = medicineDay.getText().toString();
                            String month = medicineMonth.getText().toString();
                            String year = medicineYear.getText().toString();
                            String doseStr = medicineDose.getText().toString();
                            String freqStr = medicineFreq.getText().toString();
                            String inputDateString = year+"-"+month+"-"+day;

                            if (TextUtils.isEmpty(day) || TextUtils.isEmpty(month) ||
                                    TextUtils.isEmpty(year) || TextUtils.isEmpty(name) ||
                                    TextUtils.isEmpty(doseStr) || TextUtils.isEmpty(freqStr)) {
                                Toast.makeText(view.getContext(), "Please Enter All Required Information!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Float dose = Float.parseFloat(doseStr);
                            Integer freq = Integer.parseInt(freqStr);

                            Date date = new Date();
                            try {
                                date = dateFormat.parse(inputDateString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Medicine newMed = new Medicine(date, name, dose, unit, freq);
                            listener.onAddConfirmPressed(newMed);
                        }
                    }).create();

        }

    }

    public static MedicineFragment newInstance(Medicine medicine) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", medicine.getName());
        bundle.putSerializable("unit", medicine.getDoseUnit());
        bundle.putSerializable("dose", medicine.getDoseAmount().toString());
        bundle.putSerializable("frequency", medicine.getDailyFrequency().toString());
        bundle.putSerializable("date", (dateFormat.format(medicine.getDateStart())));

        MedicineFragment fragment = new MedicineFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}

