package com.example.jxie1_medbook;

import java.util.ArrayList;

public class doseTotalCalculator {
    public static Integer numberOfDoses(ArrayList<Medicine> arrayList) {
        Integer total = 0;
        for (int i = 0; i< arrayList.size(); i++) {
            total += arrayList.get(i).getDailyFrequency();
        }
        return total;
    }
}
