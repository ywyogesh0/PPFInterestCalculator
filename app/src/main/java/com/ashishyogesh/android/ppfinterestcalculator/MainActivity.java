package com.ashishyogesh.android.ppfinterestcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set interest rate
        setInterestTextView(Constant.INTEREST_RATE_TEXT_VIEW, Constant.INTEREST_RATE_VALUE);
        // Set radio button default state - before 5th
        setRadioButtonDefaultDate(Constant.APR, Constant.MAR);
        // Set OnFocusChangeListener for EditText View
        setOnFocusChangeListener(Constant.ZERO, Constant.MAR);
        // Set Calculate Button ClickListener
        setCalculateButtonClickListener();
        // Set OnCheckedChangeListener for RadioButton Before 5
        setOnCheckedChangeListenerBefore5(Constant.APR, Constant.MAR);
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
            setOnFocusChange(getResIdForDepositAmountEditTextView(i));
        }
    }

    /**
     * Set OnFocusChange For EditText
     */
    private void setOnFocusChange(final int id) {
        final Map<Boolean, EditText> validateMap = new HashMap<>();
        final EditText text = (EditText) findViewById(id);
        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validateCurrBalText(validateMap))
                        setAmountExceedErrorMessage(validateMap.get(Boolean.FALSE), "7500000");
                    else if (!validateDepAmount(validateMap))
                        setAmountExceedErrorMessage(validateMap.get(Boolean.FALSE), "150000");
                    else {
                        setTextViewAmount();
                        setTotalInterestEarned();
                        setTotalClosingAmount();
                    }
                }
            }
        });
    }

    private boolean validateCurrBalText(Map<Boolean, EditText> validateMap) {
        EditText currentBalanceText = (EditText) findViewById(getResIdForDepositAmountEditTextView(Constant.ZERO));
        double currentBalanceAmount = currentBalanceText.getText().toString().isEmpty() ?
                Constant.ZERO : Double.parseDouble(currentBalanceText.getText().toString());
        if (currentBalanceAmount > 7500000) {
            validateMap.put(Boolean.FALSE, currentBalanceText);
            return false;
        }
        return true;
    }

    private boolean validateDepAmount(Map<Boolean, EditText> validateMap) {
        double totalDepositAmount = 0;
        for (int i = Constant.APR; i <= Constant.MAR; i++) {
            EditText text = (EditText) findViewById(getResIdForDepositAmountEditTextView(i));
            totalDepositAmount = totalDepositAmount + (text.getText().toString().isEmpty() ?
                    Constant.ZERO : Double.parseDouble(text.getText().toString()));
            if (totalDepositAmount > 150000) {
                validateMap.put(Boolean.FALSE, text);
                return false;
            }
        }
        return true;
    }

    private void setAmountExceedErrorMessage(EditText editText, String maxAmount) {
        editText.setError("Input Error : Maximum Amount Allowed is " + maxAmount);
        Toast.makeText(this, "Input Error : Maximum Amount Allowed is " + maxAmount,
                Toast.LENGTH_LONG).show();
    }

    /**
     * Set TextView Amount For Each Month
     */

    private void setTextViewAmount() {
        setCurrentBalanceTextView(Constant.APR, getCurrBalanceAmountApril(true));
        setInterestTextView(Constant.APR,
                getInterestAmountMonthly(getCurrBalanceAmountApril(isBefore5Selected(Constant.APR))));
        for (int i = Constant.MAY; i <= Constant.MAR; i++) {
            setCurrentBalanceTextView(i,
                    getCurrBalanceAmountMayToMar(i, true));
            setInterestTextView(i,
                    getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i, isBefore5Selected(i))));
        }
    }

    private void setCalculateButtonClickListener() {
        final Map<Boolean, EditText> validateMap = new HashMap<>();
        Button calcButton = (Button) findViewById(R.id.calc_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateCurrBalText(validateMap))
                    setAmountExceedErrorMessage(validateMap.get(Boolean.FALSE), "7500000");
                else if (!validateDepAmount(validateMap))
                    setAmountExceedErrorMessage(validateMap.get(Boolean.FALSE), "150000");
                else {
                    setTextViewAmount();
                    setTotalInterestEarned();
                    setTotalClosingAmount();
                }
            }
        });
    }

    /**
     * Return current balance from may-mar.
     *
     * @param monthId           constant month id
     * @param isBefore5Selected radio button selected for before 5
     * @return current balance from may-mar
     */
    private double getCurrBalanceAmountMayToMar(int monthId, boolean isBefore5Selected) {
        return isBefore5Selected ? getCurrentBalanceTextViewAmount(monthId - 1) +
                getDepositAmountEditTextViewAmount(monthId) :
                getCurrentBalanceTextViewAmount(monthId - 1);
    }

    /**
     * Return current balance of april.
     *
     * @param isBefore5Selected radio button selected for before 5
     * @return current balance of april
     */
    private double getCurrBalanceAmountApril(boolean isBefore5Selected) {

        return isBefore5Selected ? getDepositAmountEditTextViewAmount(Constant.APR - 1) +
                getDepositAmountEditTextViewAmount(Constant.APR) :
                getDepositAmountEditTextViewAmount(Constant.APR - 1);
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
            case Constant.INTEREST_RATE_TEXT_VIEW:
                return R.id.interest_rate;
            case Constant.TOTAL_INTEREST_TEXT_VIEW:
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
     * Return Resource Id for Radio Group
     *
     * @param monthId constant month id
     * @return Resource Id
     */
    private int getResIdForRadioGroup(int monthId) {
        switch (monthId) {
            case Constant.APR:
                return R.id.apr_radio_group;
            case Constant.MAY:
                return R.id.may_radio_group;
            case Constant.JUN:
                return R.id.jun_radio_group;
            case Constant.JUL:
                return R.id.jul_radio_group;
            case Constant.AUG:
                return R.id.aug_radio_group;
            case Constant.SEP:
                return R.id.sep_radio_group;
            case Constant.OCT:
                return R.id.oct_radio_group;
            case Constant.NOV:
                return R.id.nov_radio_group;
            case Constant.DEC:
                return R.id.dec_radio_group;
            case Constant.JAN:
                return R.id.jan_radio_group;
            case Constant.FEB:
                return R.id.feb_radio_group;
            case Constant.MAR:
                return R.id.mar_radio_group;
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
    private double getDepositAmountEditTextViewAmount(int monthId) throws NumberFormatException {
        EditText text = (EditText) findViewById(getResIdForDepositAmountEditTextView(monthId));
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
        return currentBalance * Constant.INTEREST_RATE_VALUE / 1200;
    }

    /**
     * Return round of double value up-to 2 places of decimal.
     *
     * @param value value going to round of
     * @return round of value
     */
    private double getRoundOfValue(double value) {
        return (double) Math.round(value * 100) / 100;
    }

    /**
     * Set Current Balance Text in TextView
     *
     * @param monthId constant month id
     * @param number  number to set in TextView
     */
    private void setCurrentBalanceTextView(int monthId, double number) {
        TextView textView = (TextView) findViewById(getResIdForCurrentBalanceTextView(monthId));
        textView.setText(String.valueOf(getRoundOfValue(number)));
    }

    /**
     * Set Interest Text in TextView
     *
     * @param monthId constant month id
     * @param number  number to set in TextView
     */
    private void setInterestTextView(int monthId, double number) {
        TextView textView = (TextView) findViewById(getResIdForInterestTextView(monthId));
        textView.setText(String.valueOf(getRoundOfValue(number)));
    }

    /**
     * Set Total Interest Earned.
     */
    private void setTotalInterestEarned() {
        double totalInterest = 0;
        for (int i = Constant.APR; i <= Constant.MAR; i++) {
            totalInterest = totalInterest + getInterestTextViewAmount(i);
        }
        setInterestTextView(Constant.TOTAL_INTEREST_TEXT_VIEW, totalInterest);
    }

    /**
     * Set Total Closing Amount
     */
    private void setTotalClosingAmount() {
        TextView totalAmountTextView = (TextView) findViewById(R.id.total_amount);
        totalAmountTextView.setText(String.valueOf(getCurrentBalanceTextViewAmount(Constant.MAR) +
                getInterestTextViewAmount(Constant.TOTAL_INTEREST_TEXT_VIEW)));
    }

    /**
     * True/False  - Before 5th Selected/After 5th Selected
     *
     * @param monthId constant month id
     * @return True/False  - Before 5th Selected/After 5th Selected
     */
    private boolean isBefore5Selected(int monthId) {
        RadioGroup radioGroup = (RadioGroup) findViewById(getResIdForRadioGroup(monthId));
        return getResIdForRadioButtonBefore(monthId) == radioGroup.getCheckedRadioButtonId();
    }

    /**
     * Set OnCheckedChange Listener for RadioButton Before 5
     */
    private void setOnCheckedChangeListenerBefore5(int startMonthId, int endMonthId) {
        for (int i = startMonthId; i <= endMonthId; i++) {
            callOnCheckedChangeListener(i);
        }
    }

    private void callOnCheckedChangeListener(int monthId) {
        RadioButton radioButtonBefore5 = (RadioButton) findViewById(getResIdForRadioButtonBefore(monthId));
        radioButtonBefore5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setInterestTextView(Constant.APR,
                            getInterestAmountMonthly(getCurrBalanceAmountApril(true)));
                    for (int i = Constant.MAY; i <= Constant.MAR; i++) {
                        setInterestTextView(i,
                                getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i, true)));
                    }
                } else {
                    setInterestTextView(Constant.APR,
                            getInterestAmountMonthly(getCurrBalanceAmountApril(false)));
                    for (int i = Constant.MAY; i <= Constant.MAR; i++) {
                        setInterestTextView(i,
                                getInterestAmountMonthly(getCurrBalanceAmountMayToMar(i, false)));
                    }
                }
                setTotalInterestEarned();
                setTotalClosingAmount();
            }
        });
    }
}