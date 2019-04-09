package inc.credible.homerlibs.formvalidation;

import android.widget.EditText;

import java.util.regex.Pattern;

public class HomerFormValidator {
    // Regular Expression
    // you can change the expression based on your need
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_REGEX = "\\d{3}\\d{7}";

    /**
     * below is the explaination of the reg ex
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";


    // Error Messages
    public static final String REQUIRED_MSG = "required";
    public static final String INVALID_EMAIL_MSG = "Invalid Email \n ";

    public static final String INVALID_PASSWORD_MSG = "Invalid Password \n ";
    public static final String PASSWORD_RULES_MSG = "password must contain \natleast 8 charecters \nno spaces \none lower case letter \n" +
            "one uppper case letter \none special charecter \nand one number \neg: Abc@1234";
    public static final String PHONE_MSG = "Invalid Phome No";
    public static final String USERNAME_INVALID_MSG = "Invalid User Name";

    /**
     * call this method when you need to check email validation
     *
     * @param editText
     * @param required
     * @return
     */
    public static boolean isEmailAddress(EditText editText, boolean required, boolean setError) {
        return isValid(editText, EMAIL_REGEX, INVALID_EMAIL_MSG, required, setError);
    }
    /**
     * call this method when you need to check email validation
     *
     * @param mStrToBeChecked
     * @param required
     * @return
     */
    public static boolean isEmailAddress(String mStrToBeChecked, boolean required) {
        return isValid(mStrToBeChecked, EMAIL_REGEX,required);
    }

    /**
     * call this method when you need to check password validation for the foll criterias
     * # a digit must occur at least once
     * # a lower case letter must occur at least once
     * # an upper case letter must occur at least once
     * # a special character must occur at least once
     * # no whitespace allowed in the entire string
     * # anything, at least eight places though
     * <p/>
     * <p></p>
     *
     * @param editText
     * @param required
     * @return
     */
    public static boolean isAValidPassword(EditText editText, boolean required, boolean setError) {
        return isValid(editText, PASSWORD_REGEX, PASSWORD_RULES_MSG, required, setError);
    }

    /**
     * call this method when you need to check phone number validation
     *
     * @param editText
     * @param required
     * @return
     */
    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required, boolean setError) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required, setError);
    }

    /**
     * Validates the credit card number using the Luhn algorithm
     *
     * @param editText the credit card number
     * @return
     */
    public static boolean isCreditCardNumber(EditText editText) {

        String cardNumber = "";
        cardNumber = editText.getText().toString().trim();

        int sum = 0, digit, addend = 0;
        boolean doubled = false;
        try {
            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                digit = Integer.parseInt(cardNumber.substring(i, i + 1));
                if (doubled) {
                    addend = digit * 2;
                    if (addend > 9) {
                        addend -= 9;
                    }
                } else {
                    addend = digit;
                }
                sum += addend;
                doubled = !doubled;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (sum % 10) == 0;
    }

    /**
     * return true if the input field is valid, based on the parameter passed
     *
     * @param editText
     * @param regex
     * @param errMsg
     * @param required
     * @return
     */
    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex,
                                  String errMsg, boolean required, boolean setError) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText, setError))
            return false;

        // pattern doesn't match so returning false
        // note :required is false in the below line because we have already checked that ..!!
        if (!isValid(text,regex,false)) {
            if (setError) {
                editText.setError(errMsg);
            }
            return false;
        }

        return true;
    }

    /**
     * return true if the input field is valid, based on the parameter passed
     *
     * @param mStrTobeChecked
     * @param regex
     * @param required
     * @return
     */
    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(String mStrTobeChecked, String regex, boolean required) {

        // text required and editText is blank, so return false
        if (required && !hasText(mStrTobeChecked))
            return false;

        // pattern doesn't match so returning false
        if (!Pattern.matches(regex, mStrTobeChecked)) {
            return false;
        }

        return true;
    }

    /**
     * // check the input field has any text or not // return true if it
     * contains text otherwise false
     *
     * @param editText
     * @return
     */

    public static boolean hasText(EditText editText, boolean setError) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (!hasText(text)) {
            if (setError) {
                editText.setError(REQUIRED_MSG);
            }
            return false;
        }

        return true;
    }

    /**
     * // check the input field has any text or not // return true if it
     * contains text otherwise false
     *
     * @param mStrToBeChecked
     * @return
     */

    public static boolean hasText(String mStrToBeChecked) {

        // length 0 means there is no text
        if (mStrToBeChecked==null||mStrToBeChecked.length() == 0) {
            return false;
        }

        return true;
    }


    public static boolean hasAtleastSoManyCharecters(EditText editText,
                                                     int mIntMinNoOfCharcEditTextShouldContain) {

        String text = editText.getText().toString().trim();
        if (text.length() >= mIntMinNoOfCharcEditTextShouldContain)
            return true;

        return false;
    }

    public static boolean hasAtTheMaxSoManyCharecters(EditText editText,
                                                      int mIntMaxNoOfCharcEditTextShouldContain) {

        String text = editText.getText().toString().trim();
        if (text.length() <= mIntMaxNoOfCharcEditTextShouldContain)
            return true;

        return false;
    }

    public static boolean hasExactlySoManyCharecters(EditText editText,
                                                     int mIntNoOfCharcEditTextShouldContain) {

        String text = editText.getText().toString().trim();
        if (text.length() == mIntNoOfCharcEditTextShouldContain)
            return true;

        return false;
    }
}
