package com.example.android.ppfinterestcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String currentBalanceAmount = Constant.ZERO;
    String aprCurrBalAmount = Constant.ZERO;
    String mayCurrBalAmount = Constant.ZERO;
    String junCurrBalAmount = Constant.ZERO;
    String julCurrBalAmount = Constant.ZERO;
    String augCurrBalAmount = Constant.ZERO;
    String sepCurrBalAmount = Constant.ZERO;
    String octCurrBalAmount = Constant.ZERO;
    String novCurrBalAmount = Constant.ZERO;
    String decCurrBalAmount = Constant.ZERO;
    String janCurrBalAmount = Constant.ZERO;
    String febCurrBalAmount = Constant.ZERO;
    String marCurrBalAmount = Constant.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set interest rate
        setTextView(R.id.interest_rate, Constant.INTEREST_RATE);
        // Set radio button default state - before 5th
        setRadioButtonDefaultDate();
        // Set current balance
        setCurrentBalance();
        // Set Current Balance for Each Month (Previous Months balance + Deposit Amount)
        setTotalCurrentBalanceEachMonth();
    }

    /**
     * Set Text in TextView
     *
     * @param id   Resource Id
     * @param text Text to set in TextView
     */
    private void setTextView(int id, String text) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(text);
    }

    /**
     * Initialize Radio Button Default Date - Before 5th
     */
    private void setRadioButtonDefaultDate() {
        setRadioButtonChecked(R.id.apr_radio_button_before, true);
        setRadioButtonChecked(R.id.may_radio_button_before, true);
        setRadioButtonChecked(R.id.jun_radio_button_before, true);
        setRadioButtonChecked(R.id.jul_radio_button_before, true);
        setRadioButtonChecked(R.id.aug_radio_button_before, true);
        setRadioButtonChecked(R.id.sep_radio_button_before, true);
        setRadioButtonChecked(R.id.oct_radio_button_before, true);
        setRadioButtonChecked(R.id.nov_radio_button_before, true);
        setRadioButtonChecked(R.id.dec_radio_button_before, true);
        setRadioButtonChecked(R.id.jan_radio_button_before, true);
        setRadioButtonChecked(R.id.feb_radio_button_before, true);
        setRadioButtonChecked(R.id.mar_radio_button_before, true);
    }

    /**
     * Set RadioButton State - Checked/UnChecked (true/false)
     *
     * @param id        Resource Id
     * @param isChecked Checked/UnChecked (true/false)
     */
    private void setRadioButtonChecked(int id, boolean isChecked) {
        RadioButton radioButton = (RadioButton) findViewById(id);
        radioButton.setChecked(isChecked);
    }


    /**
     * Set Current Balance Amount
     */
    private void setCurrentBalance() {
        final EditText currentBalanceText = (EditText) findViewById(R.id.current_balance);
        currentBalanceText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // set current balance
                    setCurrentBalanceAmount(currentBalanceText.getText().toString());
                    // set current balance to each month
                    setCurrentBalanceAmountEachMonth();
                }
            }
        });
    }

    /**
     * Set Current Balance Amount
     */
    private void setCurrentBalanceAmount(String currentBalanceText) {
        currentBalanceAmount = currentBalanceText.isEmpty() ? Constant.ZERO : currentBalanceText;
    }

    /**
     * Set Current Balance Amount To Each Month
     */
    private void setCurrentBalanceAmountEachMonth() {
        setTextView(R.id.apr_current_balance, currentBalanceAmount);
        setTextView(R.id.may_current_balance, currentBalanceAmount);
        setTextView(R.id.jun_current_balance, currentBalanceAmount);
        setTextView(R.id.jul_current_balance, currentBalanceAmount);
        setTextView(R.id.aug_current_balance, currentBalanceAmount);
        setTextView(R.id.sep_current_balance, currentBalanceAmount);
        setTextView(R.id.oct_current_balance, currentBalanceAmount);
        setTextView(R.id.nov_current_balance, currentBalanceAmount);
        setTextView(R.id.dec_current_balance, currentBalanceAmount);
        setTextView(R.id.jan_current_balance, currentBalanceAmount);
        setTextView(R.id.feb_current_balance, currentBalanceAmount);
        setTextView(R.id.mar_current_balance, currentBalanceAmount);
    }

    /**
     * Set Current Balance Each Month on Focus Change (Previous Months balance + Deposit Amount).
     */
    private void setTotalCurrentBalanceEachMonth() {
        setOnFocusChangeForDepositAmount(R.id.apr_dep, Constant.APR);
        setOnFocusChangeForDepositAmount(R.id.may_dep, Constant.MAY);
        setOnFocusChangeForDepositAmount(R.id.jun_dep, Constant.JUN);
    }

    /**
     * Set OnFocusChange For Deposit Amount
     */
    private void setOnFocusChangeForDepositAmount(final int id, final String month) {
        final EditText text = (EditText) findViewById(id);
        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    setTotalCurrentBalance(text.getText().toString(), month);
            }
        });
    }

    private void setTotalCurrentBalance(String depositAmount, String month) {
        depositAmount = depositAmount.isEmpty() ? Constant.ZERO : depositAmount;
        switch (month) {

            case Constant.APR:
                int totalAprCurrAmount = Integer.parseInt(depositAmount) +
                        Integer.parseInt(currentBalanceAmount);
                aprCurrBalAmount = String.valueOf(totalAprCurrAmount);
                setTextView(R.id.apr_current_balance, aprCurrBalAmount);
                break;

            case Constant.MAY:
                int totalMayCurrAmount = Integer.parseInt(depositAmount) +
                        Integer.parseInt(aprCurrBalAmount);
                mayCurrBalAmount = String.valueOf(totalMayCurrAmount);
                setTextView(R.id.may_current_balance, mayCurrBalAmount);
                break;

            case Constant.JUN:
                int totalJunCurrAmount = Integer.parseInt(depositAmount) +
                        Integer.parseInt(mayCurrBalAmount);
                junCurrBalAmount = String.valueOf(totalJunCurrAmount);
                setTextView(R.id.jun_current_balance, junCurrBalAmount);
                break;
        }
    }
}
