package com.mattjamesdev.tipoutcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText et_total_tips;
    private TextView tv_date;
    private Calculator calculator;

    private TextView tv_busser_tip_amt;
    private TextView tv_hostess_tip_amt;
    private TextView tv_runner_tip_amt;
    private TextView tv_bar_tip_amt;
    private TextView tv_kitchen_tip_amt;
    private EditText et_busser_tip_perc;
    private EditText et_hostess_tip_perc;
    private EditText et_runner_tip_perc;
    private EditText et_bar_tip_perc;
    private EditText et_kitchen_tip_perc;

    TextView[] tv_positions;
    EditText[] et_positions;

    private TextView tv_total_tip_out_amt;
    private TextView tv_total_take_home_amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set date
        tv_date = (TextView) findViewById(R.id.tv_date);
        String date = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        tv_date.setText(date);

        // initialize total tip EditText
        et_total_tips = (EditText) findViewById(R.id.et_total_tips);
        et_total_tips.addTextChangedListener(tipsListener);

        // initialize and collect data associated views
        tv_busser_tip_amt = (TextView) findViewById(R.id.tv_busser_tip_amt);
        tv_hostess_tip_amt = (TextView) findViewById(R.id.tv_hostess_tip_amt);
        tv_runner_tip_amt = (TextView) findViewById(R.id.tv_runner_tip_amt);
        tv_bar_tip_amt = (TextView) findViewById(R.id.tv_bar_tip_amt);
        tv_kitchen_tip_amt = (TextView) findViewById(R.id.tv_kitchen_tip_amt);
        tv_positions = new TextView[]{tv_busser_tip_amt, tv_hostess_tip_amt, tv_runner_tip_amt, tv_bar_tip_amt, tv_kitchen_tip_amt};

        et_busser_tip_perc = (EditText) findViewById(R.id.et_busser_tip_perc);
        et_hostess_tip_perc = (EditText) findViewById(R.id.et_hostess_tip_perc);
        et_runner_tip_perc = (EditText) findViewById(R.id.et_runner_tip_perc);
        et_bar_tip_perc = (EditText) findViewById(R.id.et_bar_tip_perc);
        et_kitchen_tip_perc = (EditText) findViewById(R.id.et_kitchen_tip_perc);
        et_positions = new EditText[]{et_busser_tip_perc, et_hostess_tip_perc,et_runner_tip_perc, et_bar_tip_perc, et_kitchen_tip_perc};

        tv_total_tip_out_amt = (TextView) findViewById(R.id.tv_total_tip_out_amt);
        tv_total_take_home_amt = (TextView) findViewById(R.id.tv_total_take_home_amt);

        // create Calculator
        calculator = new Calculator(tv_positions, et_positions);
    }

    private final TextWatcher tipsListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {

                int count = tv_positions.length;

                int totalTips = Integer.parseInt(et_total_tips.getText().toString());
                int totalTipOut = calculator.calculateTipOutAmts(totalTips, count);
                int takeHomeAmt = totalTips - totalTipOut;

                tv_total_tip_out_amt.setText("$" + totalTipOut);
                tv_total_take_home_amt.setText("$" + takeHomeAmt);

            } catch (Exception e) {

            }
        }
    };
}
