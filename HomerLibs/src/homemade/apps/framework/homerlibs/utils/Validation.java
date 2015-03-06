package homemade.apps.framework.homerlibs.utils;

import java.util.regex.Pattern;

import android.widget.EditText;

public class Validation {
	// Regular Expression
	// you can change the expression based on your need
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String PHONE_REGEX = "\\d{3}-\\d{7}";

	// Error Messages
	public static final String REQUIRED_MSG = "required";
	public static final String EMAIL_MSG = "invalid email";
	public static final String PHONE_MSG = "###-#######";

	/**
	 * call this method when you need to check email validation
	 * 
	 * @param editText
	 * @param required
	 * @return
	 */
	public static boolean isEmailAddress(EditText editText, boolean required) {
		return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
	}

	/**
	 * call this method when you need to check phone number validation
	 * 
	 * @param editText
	 * @param required
	 * @return
	 */
	// call this method when you need to check phone number validation
	public static boolean isPhoneNumber(EditText editText, boolean required) {
		return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
	}

	/**
	 * Validates the credit card number using the Luhn algorithm
	 * 
	 * @param cardNumber
	 *            the credit card number
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
			String errMsg, boolean required) {

		String text = editText.getText().toString().trim();
		// clearing the error, if it was previously set by some other values
		editText.setError(null);

		// text required and editText is blank, so return false
		if (required && !hasText(editText))
			return false;

		// pattern doesn't match so returning false
		if (required && !Pattern.matches(regex, text)) {
			editText.setError(errMsg);
			return false;
		}
		;

		return true;
	}

	/**
	 * // check the input field has any text or not // return true if it
	 * contains text otherwise false
	 * 
	 * @param editText
	 * @return
	 */

	public static boolean hasText(EditText editText) {

		String text = editText.getText().toString().trim();
		editText.setError(null);

		// length 0 means there is no text
		if (text.length() == 0) {
			editText.setError(REQUIRED_MSG);
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
