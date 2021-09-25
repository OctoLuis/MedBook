package com.example.jxie1_medbook;

import java.util.ArrayList;

/*  Class: doesTotalCalculator
    @author: Louie Xie
    To calculate the total number of doses need to take daily.
    total: total of frequencies of each medicine.
 */
public class doseTotalCalculator {
    public static Integer numberOfDoses(ArrayList<Medicine> arrayList) {
        Integer total = 0;
        for (int i = 0; i< arrayList.size(); i++) {
            total += arrayList.get(i).getDailyFrequency();
        }
        return total;
    }
}
