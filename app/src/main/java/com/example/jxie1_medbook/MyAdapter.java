package com.example.jxie1_medbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Medicine> {
    public MyAdapter(Context context,int position, ArrayList<Medicine> medicines) {
        super(context, position, medicines);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Medicine medicine = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.medicine_content,
                    parent, false);
        }

        TextView medicineName = (TextView) convertView.findViewById(R.id.medicineName);
        TextView dose = (TextView) convertView.findViewById(R.id.dose);
        TextView unit = (TextView) convertView.findViewById(R.id.unit);
        TextView freq = (TextView) convertView.findViewById(R.id.freq);

        medicineName.setText(medicine.getName());
        dose.setText(Integer.toString(medicine.getDoseAmount()));
        unit.setText(medicine.getDoseUnit());
        freq.setText(Integer.toString(medicine.getDailyFrequency()));

        return convertView;
    }
}







