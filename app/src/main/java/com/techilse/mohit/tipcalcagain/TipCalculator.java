package com.techilse.mohit.tipcalcagain;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class TipCalculator extends Activity {


    double bill;
    double tip;
    double finalBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        EditText billET = (EditText) findViewById(R.id.billET);
        final EditText tipET = (EditText) findViewById(R.id.tipET);
        final EditText finaBillET = (EditText) findViewById(R.id.finalbillET);

        final TextView textView = (TextView) findViewById(R.id.textView2);


        final SeekBar seekBarTip = (SeekBar) findViewById(R.id.tipSeekBar);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                finalBill = 0.0;
                try {

                    tip = Double.valueOf(i);
                } catch (NumberFormatException e) {
                    //finaBillET.setText("Bill should be Numeric");
                    tip = 15;
                }
                tipET.setText(String.valueOf(i));
                finalBill = bill + (tip * bill) / 100;
                finaBillET.setText(String.valueOf(finalBill));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };




        seekBarTip.setOnSeekBarChangeListener(seekBarChangeListener);





        try {
            bill = Double.parseDouble(billET.getText().toString());
            tip = Double.parseDouble(tipET.getText().toString());
        } catch (NumberFormatException e) {
            //billET.setText("0.0");
            //tipET.setText("15");
            bill = 0.0;
            tip = 15;
            finaBillET.setText("Bill and Tip should be Numeric");
        }


        TextWatcher billWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                finalBill = 0.0;

                try {
                    bill = Double.parseDouble(charSequence.toString());
                } catch (NumberFormatException e) {
                    //finaBillET.setText("Bill should be Numeric");
                    bill = 0.0;
                }
                finalBill = bill + (tip * bill) / 100;
                finaBillET.setText(String.valueOf(finalBill));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        TextWatcher tipWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                finalBill = 0.0;
                try {
                    tip = Double.parseDouble(charSequence.toString());
                    if(tip>100){
                        Toast.makeText(getApplicationContext(),"Tip cannot be more than 100%",Toast.LENGTH_SHORT).show();
                        tip = 100;
                        tipET.setText("100");
                    }

                    seekBarTip.setProgress((int)tip);
                } catch (NumberFormatException e) {
                    //finaBillET.setText("Bill should be Numeric");
                    tip = 15;
                }
                finalBill = bill + (tip * bill) / 100;
                finaBillET.setText(String.valueOf(finalBill));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };


        billET.addTextChangedListener(billWatcher);
        tipET.addTextChangedListener(tipWatcher);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
