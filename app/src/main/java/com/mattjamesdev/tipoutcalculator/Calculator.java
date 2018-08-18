package com.mattjamesdev.tipoutcalculator;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    TextView[] tv_positions;
    EditText[] et_positions;

    public Calculator(){
        this(null, null);
    }

    public Calculator(TextView[] tvPositions, EditText[] etPositions){
        tv_positions = tvPositions;
        et_positions = etPositions;
    }

    public int calculateTipOutAmts(int totalTips, int count){
        int totalTipOut = 0;

        for(int i = 0; i < count; i++){
            // calculate tip out
            double tipPercent = Integer.parseInt(et_positions[i].getText().toString()) * 0.01;
            int tipOut = (int)Math.round(tipPercent*totalTips);
            totalTipOut += tipOut;

            // set TextView to tip out amount
            tv_positions[i].setText("$" + tipOut);
        }

        return totalTipOut;
    }

    public int calculateTotals(int count){
        int totalTipOut = 0;

        for(int i = 0; i < count; i++){
            totalTipOut += Integer.parseInt(tv_positions[i].getText().toString());
        }

        return totalTipOut;
    }
}
