package com.example.android.ppfinterestcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String currentBalanceAmount = Constant.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set interest rate
        initInterestRate();
        // set current balance
        initCurrentBalance();
    }

    private void initInterestRate() {
        TextView interestRate = (TextView) findViewById(R.id.interest_rate);
        interestRate.setText(Constant.INTEREST_RATE);
    }

    private void setCurrentBalanceAmount() {
        EditText currentBalance = (EditText) findViewById(R.id.current_balance);
        currentBalanceAmount = currentBalance.getText().toString();
    }

    private void initCurrentBalance() {
        EditText currentBalance = (EditText) findViewById(R.id.current_balance);
        currentBalance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // set current balance
                    setCurrentBalanceAmount();

                    // set current balance to each month
                    setAprCurBalance(currentBalanceAmount);
                    setMayCurBalance(currentBalanceAmount);
                    setJunCurBalance(currentBalanceAmount);
                    setJulCurBalance(currentBalanceAmount);
                    setAugCurBalance(currentBalanceAmount);
                    setSepCurBalance(currentBalanceAmount);
                    setOctCurBalance(currentBalanceAmount);
                    setNovCurBalance(currentBalanceAmount);
                    setDecCurBalance(currentBalanceAmount);
                    setJanCurBalance(currentBalanceAmount);
                    setFebCurBalance(currentBalanceAmount);
                    setMarCurBalance(currentBalanceAmount);
                }
            }
        });
    }

    private void setAprCurBalance(String currentBalanceAmount) {
        TextView aprCurBalance = (TextView) findViewById(R.id.apr_current_balance);
        aprCurBalance.setText(currentBalanceAmount);
    }

    private void setMayCurBalance(String currentBalanceAmount) {
        TextView mayCurBalance = (TextView) findViewById(R.id.may_current_balance);
        mayCurBalance.setText(currentBalanceAmount);
    }

    private void setJunCurBalance(String currentBalanceAmount) {
        TextView junCurBalance = (TextView) findViewById(R.id.jun_current_balance);
        junCurBalance.setText(currentBalanceAmount);
    }

    private void setJulCurBalance(String currentBalanceAmount) {
        TextView julCurBalance = (TextView) findViewById(R.id.jul_current_balance);
        julCurBalance.setText(currentBalanceAmount);
    }

    private void setAugCurBalance(String currentBalanceAmount) {
        TextView augCurBalance = (TextView) findViewById(R.id.aug_current_balance);
        augCurBalance.setText(currentBalanceAmount);
    }

    private void setSepCurBalance(String currentBalanceAmount) {
        TextView sepCurBalance = (TextView) findViewById(R.id.sep_current_balance);
        sepCurBalance.setText(currentBalanceAmount);
    }

    private void setOctCurBalance(String currentBalanceAmount) {
        TextView octCurBalance = (TextView) findViewById(R.id.oct_current_balance);
        octCurBalance.setText(currentBalanceAmount);
    }

    private void setNovCurBalance(String currentBalanceAmount) {
        TextView novCurBalance = (TextView) findViewById(R.id.nov_current_balance);
        novCurBalance.setText(currentBalanceAmount);
    }

    private void setDecCurBalance(String currentBalanceAmount) {
        TextView decCurBalance = (TextView) findViewById(R.id.dec_current_balance);
        decCurBalance.setText(currentBalanceAmount);
    }

    private void setJanCurBalance(String currentBalanceAmount) {
        TextView janCurBalance = (TextView) findViewById(R.id.jan_current_balance);
        janCurBalance.setText(currentBalanceAmount);
    }

    private void setFebCurBalance(String currentBalanceAmount) {
        TextView febCurBalance = (TextView) findViewById(R.id.feb_current_balance);
        febCurBalance.setText(currentBalanceAmount);
    }

    private void setMarCurBalance(String currentBalanceAmount) {
        TextView marCurBalance = (TextView) findViewById(R.id.mar_current_balance);
        marCurBalance.setText(currentBalanceAmount);
    }
}
