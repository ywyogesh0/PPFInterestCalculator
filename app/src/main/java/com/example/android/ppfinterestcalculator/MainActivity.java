package com.example.android.ppfinterestcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentBalanceAmount = Constant.ZERO;
    int aprCurrBalAmount = Constant.ZERO;
    int mayCurrBalAmount = Constant.ZERO;
    int junCurrBalAmount = Constant.ZERO;
    int julCurrBalAmount = Constant.ZERO;
    int augCurrBalAmount = Constant.ZERO;
    int sepCurrBalAmount = Constant.ZERO;
    int octCurrBalAmount = Constant.ZERO;
    int novCurrBalAmount = Constant.ZERO;
    int decCurrBalAmount = Constant.ZERO;
    int janCurrBalAmount = Constant.ZERO;
    int febCurrBalAmount = Constant.ZERO;
    int marCurrBalAmount = Constant.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set interest rate
        setTextView(R.id.interest_rate, Constant.INTEREST_RATE);
        // Set radio button default state - before 5th
        setRadioButtonDefaultDate(1, 12);
        // Set current balance
        setCurrentBalance();
        // Set OnFocusChangeListener For Deposit Amount
        setOnFocusChangeListenerForDepositAmount(1, 12);
    }

    /**
     * Set Text in TextView
     *
     * @param id     Resource Id
     * @param number number to set in TextView
     */
    private void setTextView(int id, double number) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(String.valueOf(number));
    }

    /**
     * Initialize Radio Button Default Date - Before 5th
     */
    private void setRadioButtonDefaultDate(int startMonthId, int endMonthId) {
        for (int i = startMonthId; i <= endMonthId; i++) {
            setRadioButtonChecked(getResIdForRadioButtonBefore(i), true);
        }
    }

    /**
     * Return Resource Id for Radio Button before
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForRadioButtonBefore(int monthId) {
        switch (monthId) {
            case Constant.APR:
                return R.id.apr_radio_button_before;
            case Constant.MAY:
                return R.id.may_radio_button_before;
            case Constant.JUN:
                return R.id.jun_radio_button_before;
            case Constant.JUL:
                return R.id.jul_radio_button_before;
            case Constant.AUG:
                return R.id.aug_radio_button_before;
            case Constant.SEP:
                return R.id.sep_radio_button_before;
            case Constant.OCT:
                return R.id.oct_radio_button_before;
            case Constant.NOV:
                return R.id.nov_radio_button_before;
            case Constant.DEC:
                return R.id.dec_radio_button_before;
            case Constant.JAN:
                return R.id.jan_radio_button_before;
            case Constant.FEB:
                return R.id.feb_radio_button_before;
            case Constant.MAR:
                return R.id.mar_radio_button_before;
            default:
                return -1;
        }
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
                    setCurrentBalanceAmountEachMonth(1, 12);
                }
            }
        });
    }

    /**
     * Set Current Balance Amount
     */
    private void setCurrentBalanceAmount(String currentBalanceText) {
        currentBalanceAmount = currentBalanceText.isEmpty() ?
                Constant.ZERO : Integer.parseInt(currentBalanceText);
    }

    /**
     * Set Current Balance Amount To Each Month
     */
    private void setCurrentBalanceAmountEachMonth(int startMonthId, int endMonthId) {
        for (int i = startMonthId; i <= endMonthId; i++) {
            setTextView(getResIdForCurrentBalance(i), currentBalanceAmount);
        }
    }

    /**
     * Return Resource Id for Current Balance
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForCurrentBalance(int monthId) {
        switch (monthId) {
            case Constant.APR:
                return R.id.apr_current_balance;
            case Constant.MAY:
                return R.id.may_current_balance;
            case Constant.JUN:
                return R.id.jun_current_balance;
            case Constant.JUL:
                return R.id.jul_current_balance;
            case Constant.AUG:
                return R.id.aug_current_balance;
            case Constant.SEP:
                return R.id.sep_current_balance;
            case Constant.OCT:
                return R.id.oct_current_balance;
            case Constant.NOV:
                return R.id.nov_current_balance;
            case Constant.DEC:
                return R.id.dec_current_balance;
            case Constant.JAN:
                return R.id.jan_current_balance;
            case Constant.FEB:
                return R.id.feb_current_balance;
            case Constant.MAR:
                return R.id.mar_current_balance;
            default:
                return -1;
        }
    }

    /**
     * Set OnFocusChange For Deposit Amount.
     */
    private void setOnFocusChangeListenerForDepositAmount(int startMonthId, int endMonthId) {
        for (int i = startMonthId; i <= endMonthId; i++) {
            setOnFocusChangeForDepositAmount(getResIdForDepositAmount(i), i);
        }
    }

    /**
     * Return Resource Id For Deposit Amount
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForDepositAmount(int monthId) {
        switch (monthId) {
            case Constant.APR:
                return R.id.apr_dep;
            case Constant.MAY:
                return R.id.may_dep;
            case Constant.JUN:
                return R.id.jun_dep;
            case Constant.JUL:
                return R.id.jul_dep;
            case Constant.AUG:
                return R.id.aug_dep;
            case Constant.SEP:
                return R.id.sep_dep;
            case Constant.OCT:
                return R.id.oct_dep;
            case Constant.NOV:
                return R.id.nov_dep;
            case Constant.DEC:
                return R.id.dec_dep;
            case Constant.JAN:
                return R.id.jan_dep;
            case Constant.FEB:
                return R.id.feb_dep;
            case Constant.MAR:
                return R.id.mar_dep;
            default:
                return -1;
        }
    }

    /**
     * Set OnFocusChange For Deposit Amount
     */
    private void setOnFocusChangeForDepositAmount(final int id, final int month) {
        final EditText text = (EditText) findViewById(id);
        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    setTotalCurrentBalanceAmount(text.getText().toString(), month);
            }
        });
    }

    /**
     * Set Total Current Balance Amount for Each Month
     *
     * @param depositAmount amount deposited
     * @param month         month CONSTANT
     */
    private void setTotalCurrentBalanceAmount(String depositAmount, int month) {
        int depositAmountValue = 0;
        depositAmountValue = depositAmount.isEmpty() ?
                Constant.ZERO : Integer.parseInt(depositAmount);
        switch (month) {

            case Constant.APR:
                setTotalCurrentBalance(depositAmountValue, 1);
                break;

            case Constant.MAY:
                setTotalCurrentBalance(depositAmountValue, 2);
                break;

            case Constant.JUN:
                setTotalCurrentBalance(depositAmountValue, 3);
                break;

            case Constant.JUL:
                setTotalCurrentBalance(depositAmountValue, 4);
                break;

            case Constant.AUG:
                setTotalCurrentBalance(depositAmountValue, 5);
                break;

            case Constant.SEP:
                setTotalCurrentBalance(depositAmountValue, 6);
                break;

            case Constant.OCT:
                setTotalCurrentBalance(depositAmountValue, 7);
                break;

            case Constant.NOV:
                setTotalCurrentBalance(depositAmountValue, 8);
                break;

            case Constant.DEC:
                setTotalCurrentBalance(depositAmountValue, 9);
                break;

            case Constant.JAN:
                setTotalCurrentBalance(depositAmountValue, 10);
                break;

            case Constant.FEB:
                setTotalCurrentBalance(depositAmountValue, 11);
                break;

            case Constant.MAR:
                setTotalCurrentBalance(depositAmountValue, 12);
                break;
            default:
                break;
        }
    }

    /**
     * Set Total Current Balance Amount For Each Month
     *
     * @param depositAmountValue deposit amount value
     * @param monthId            constant month id
     */
    private void setTotalCurrentBalance(int depositAmountValue, int monthId) {
        if (monthId > 12)
            return;
        int totalCurrAmount = depositAmountValue + getBalanceAmountForMonth(monthId - 1);
        setMonthlyBalanceAmount(totalCurrAmount, monthId);
        setTextView(getResIdForCurrentBalance(monthId), getBalanceAmountForMonth(monthId));
        // Remaining Months Current Balance Update (Recursive)
        setTotalCurrentBalance(depositAmountValue, monthId + 1);
    }

    /**
     * Return Balance Amount For Month
     *
     * @param monthId constant month id
     * @return Balance Amount For Month
     */
    private int getBalanceAmountForMonth(int monthId) {
        switch (monthId) {
            case Constant.APR:
                return aprCurrBalAmount;
            case Constant.MAY:
                return mayCurrBalAmount;
            case Constant.JUN:
                return janCurrBalAmount;
            case Constant.JUL:
                return julCurrBalAmount;
            case Constant.AUG:
                return augCurrBalAmount;
            case Constant.SEP:
                return sepCurrBalAmount;
            case Constant.OCT:
                return octCurrBalAmount;
            case Constant.NOV:
                return novCurrBalAmount;
            case Constant.DEC:
                return decCurrBalAmount;
            case Constant.JAN:
                return janCurrBalAmount;
            case Constant.FEB:
                return febCurrBalAmount;
            case Constant.MAR:
                return marCurrBalAmount;
            case Constant.ZERO:
                return currentBalanceAmount;
            default:
                return -1;
        }
    }

    /**
     * Set Total Monthly Balance Amount (previous month + deposit).
     *
     * @param totalBalanceAmount total balance amount (previous month + deposit)
     * @param monthId            constant month id
     */
    private void setMonthlyBalanceAmount(int totalBalanceAmount, int monthId) {
        switch (monthId) {
            case Constant.APR:
                aprCurrBalAmount = totalBalanceAmount;
            case Constant.MAY:
                marCurrBalAmount = totalBalanceAmount;
            case Constant.JUN:
                junCurrBalAmount = totalBalanceAmount;
            case Constant.JUL:
                julCurrBalAmount = totalBalanceAmount;
            case Constant.AUG:
                augCurrBalAmount = totalBalanceAmount;
            case Constant.SEP:
                sepCurrBalAmount = totalBalanceAmount;
            case Constant.OCT:
                octCurrBalAmount = totalBalanceAmount;
            case Constant.NOV:
                novCurrBalAmount = totalBalanceAmount;
            case Constant.DEC:
                decCurrBalAmount = totalBalanceAmount;
            case Constant.JAN:
                janCurrBalAmount = totalBalanceAmount;
            case Constant.FEB:
                febCurrBalAmount = totalBalanceAmount;
            case Constant.MAR:
                marCurrBalAmount = totalBalanceAmount;
            default:
                break;
        }
    }
}
