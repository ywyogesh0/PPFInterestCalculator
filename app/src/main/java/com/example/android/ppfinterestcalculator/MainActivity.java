package com.example.android.ppfinterestcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set interest rate
        setInterestTextView(Constant.INTEREST_RATE_TEXTVIEW, Constant.INTEREST_RATE_VALUE);
        // Set radio button default state - before 5th
        setRadioButtonDefaultDate(1, 12);
        // Set OnFocusChangeListener for EditText View
        setOnFocusChangeListener(0, 12);
        // Set Calculate Button ClickListner
        setCalculateButtonClickListener();
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
     * Set RadioButton State - Checked/UnChecked (true/false)
     *
     * @param id        Resource Id
     * @param isChecked Checked/UnChecked (true/false)
     */
    private void setRadioButtonChecked(int id, boolean isChecked) {
        if (id != -1) {
            RadioButton radioButton = (RadioButton) findViewById(id);
            radioButton.setChecked(isChecked);
        }
    }

    /**
     * Set OnFocusChangeListener For EditText View.
     */
    private void setOnFocusChangeListener(int startMonthId, int endMonthId) {
        for (int i = startMonthId; i <= endMonthId; i++) {
            setOnFocusChange(getResIdForDepositAmountEditTextView(i), i);
        }
    }

    /**
     * Set OnFocusChange For EditText
     */
    private void setOnFocusChange(final int id, final int month) {
        final EditText text = (EditText) findViewById(id);
        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setTextViewAmount(month);
                    setTotalInterestEarned();
                    setTotalClosingAmount();
                }
            }
        });
    }

    /**
     * Set TextView Amount For Each Month
     *
     * @param month month CONSTANT
     */
    private void setTextViewAmount(int month) {
        switch (month) {

            case Constant.ZERO:
                setCurrentBalanceTextView(Constant.APR, getCurrBalanceAmountApril());
                setInterestTextView(Constant.APR, getInterestAmountMonthly(getCurrBalanceAmountApril()));

                for (int i = Constant.MAY; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.APR:
                setCurrentBalanceTextView(Constant.APR, getCurrBalanceAmountApril());
                setInterestTextView(Constant.APR, getInterestAmountMonthly(getCurrBalanceAmountApril()));

                for (int i = Constant.MAY; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.MAY:
                for (int i = Constant.MAY; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.JUN:
                for (int i = Constant.JUN; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.JUL:
                for (int i = Constant.JUL; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.AUG:
                for (int i = Constant.AUG; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.SEP:
                for (int i = Constant.SEP; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.OCT:
                for (int i = Constant.OCT; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.NOV:
                for (int i = Constant.NOV; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.DEC:
                for (int i = Constant.DEC; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.JAN:
                for (int i = Constant.JAN; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.FEB:
                for (int i = Constant.FEB; i <= Constant.MAR; i++) {
                    setCurrentBalanceTextView(i, getCurrBalanceAmountMayToMar(i));
                    setInterestTextView(i, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i)));
                }
                break;

            case Constant.MAR:
                setCurrentBalanceTextView(Constant.MAR, getCurrBalanceAmountMayToMar(Constant.MAR));
                setInterestTextView(Constant.MAR, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(Constant.MAR)));
                break;

            default:
                break;
        }
    }

    private void setCalculateButtonClickListener() {
        Button calcButton = (Button) findViewById(R.id.calc_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentBalanceTextView(Constant.MAR, getCurrBalanceAmountMayToMar(Constant.MAR));
                setInterestTextView(Constant.MAR, getInterestAmountMonthly(getCurrBalanceAmountMayToMar(Constant.MAR)));
                setTotalInterestEarned();
                setTotalClosingAmount();
            }
        });
    }

    /**
     * Return current balance from may-mar.
     *
     * @param monthId constant month id
     * @return current balance from may-mar
     */
    private double getCurrBalanceAmountMayToMar(int monthId) {
        return getCurrentBalanceTextViewAmount(monthId - 1) +
                getDepositAmountEditTextViewAmount(monthId);
    }

    /**
     * Return current balance of april.
     *
     * @return current balance of april
     */
    private double getCurrBalanceAmountApril() {
        return getDepositAmountEditTextViewAmount(Constant.APR - 1) +
                getDepositAmountEditTextViewAmount(Constant.APR);
    }

    /**
     * Return Resource Id For Deposit Amount EditText View
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForDepositAmountEditTextView(int monthId) {
        switch (monthId) {
            case Constant.ZERO:
                return R.id.current_balance;
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
     * Return Resource Id for Current Balance TextView
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForCurrentBalanceTextView(int monthId) {
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
     * Return Resource Id for Interest TextView
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForInterestTextView(int monthId) {
        switch (monthId) {
            case Constant.APR:
                return R.id.apr_int;
            case Constant.MAY:
                return R.id.may_int;
            case Constant.JUN:
                return R.id.jun_int;
            case Constant.JUL:
                return R.id.jul_int;
            case Constant.AUG:
                return R.id.aug_int;
            case Constant.SEP:
                return R.id.sep_int;
            case Constant.OCT:
                return R.id.oct_int;
            case Constant.NOV:
                return R.id.nov_int;
            case Constant.DEC:
                return R.id.dec_int;
            case Constant.JAN:
                return R.id.jan_int;
            case Constant.FEB:
                return R.id.feb_int;
            case Constant.MAR:
                return R.id.mar_int;
            case Constant.INTEREST_RATE_TEXTVIEW:
                return R.id.interest_rate;
            case Constant.TOTAL_INTEREST_TEXTVIEW:
                return R.id.total_interest;
            default:
                return -1;
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
     * Return Deposit Amount EditTextView Amount
     *
     * @param monthId constant month id
     * @return Deposit Amount EditTextView Amount
     */
    private double getDepositAmountEditTextViewAmount(int monthId) {
        TextView text = (TextView) findViewById(getResIdForDepositAmountEditTextView(monthId));
        return text.getText().toString().isEmpty() ?
                Constant.ZERO : Double.parseDouble(text.getText().toString());
    }

    /**
     * Return Current Balance TextView Amount
     *
     * @param monthId constant month id
     * @return Current Balance textView Amount
     */
    private double getCurrentBalanceTextViewAmount(int monthId) {
        TextView text = (TextView) findViewById(getResIdForCurrentBalanceTextView(monthId));
        return text.getText().toString().isEmpty() ?
                Constant.ZERO : Double.parseDouble(text.getText().toString());
    }

    /**
     * Return Interest TextView Amount
     *
     * @param monthId constant month id
     * @return Interest textView Amount
     */
    private double getInterestTextViewAmount(int monthId) {
        TextView text = (TextView) findViewById(getResIdForInterestTextView(monthId));
        return text.getText().toString().isEmpty() ?
                Constant.ZERO : Double.parseDouble(text.getText().toString());
    }

    /**
     * Return monthly interest
     *
     * @param currentBalance current balance amount
     * @return monthly interest
     */
    private double getInterestAmountMonthly(double currentBalance) {
        return (currentBalance * Constant.INTEREST_RATE_VALUE * 1) / (12 * 100);
    }

    /**
     * Set Current Balance Text in TextView
     *
     * @param monthId constant month id
     * @param number  number to set in TextView
     */
    private void setCurrentBalanceTextView(int monthId, double number) {
        TextView textView = (TextView) findViewById(getResIdForCurrentBalanceTextView(monthId));
        textView.setText(String.valueOf(number));
    }

    /**
     * Set Interest Text in TextView
     *
     * @param monthId constant month id
     * @param number  number to set in TextView
     */
    private void setInterestTextView(int monthId, double number) {
        TextView textView = (TextView) findViewById(getResIdForInterestTextView(monthId));
        textView.setText(String.valueOf(number));
    }

    /**
     * Set Total Interest Earned.
     */
    private void setTotalInterestEarned() {
        double totalInterest = 0;
        for (int i = Constant.APR; i <= Constant.MAR; i++) {
            totalInterest = totalInterest + getInterestTextViewAmount(i);
        }
        setInterestTextView(Constant.TOTAL_INTEREST_TEXTVIEW, totalInterest);
    }

    /**
     * Set Total Closing Amount
     */
    private void setTotalClosingAmount() {
        TextView totalAmountTextView = (TextView) findViewById(R.id.total_amount);
        totalAmountTextView.setText(String.valueOf(getCurrentBalanceTextViewAmount(Constant.MAR) +
                getInterestTextViewAmount(Constant.TOTAL_INTEREST_TEXTVIEW)));
    }
}