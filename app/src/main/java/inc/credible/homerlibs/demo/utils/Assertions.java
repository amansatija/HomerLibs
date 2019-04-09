package inc.credible.homerlibs.demo.utils;

import android.app.Activity;
import android.widget.EditText;

import java.net.URL;

import inc.credible.homerlibs.HomerLogger;
import inc.credible.homerlibs.formvalidation.HomerFormValidator;


/**
 * Created by aman on 06-05-2015.
 */
public class Assertions {

    public static boolean isAVaildEmailAddress(Activity activity, EditText mEtEmail) {

        if (!HomerFormValidator.isEmailAddress(mEtEmail, false, true)) {

            final String EMAIL_MSG = "Please Enter a valid Email Address \n ";

//            AlertBoxUtils.showCoolAlertView(activity, "Oops", EMAIL_MSG,
//                    RelativeLayoutCoolCartoonAvatarCustomAlertView.Orientation.HORIZONTAL);
            //via snackbar
//            AlertBoxUtils.showSnackBar(activity, EMAIL_MSG);

            return false;
        }
        mEtEmail.setError(null);
        return true;
    }


    public static boolean isAVaildPassword(Activity activity, EditText mEtPassword) {
        if (!HomerFormValidator.isAValidPassword(mEtPassword, true, true)) {

            String mStrError = "\"Your password needs to be of atleast 8 charecters ,\nIt should not contain any spaces in between , " +
                    "\nIt should contain atleast one lower case letter ,\n atleast one uppercase letter , \n atleast one number and \n atleast one symbol \n eg: Abc@1234";
            //via cool alert view
//            AlertBoxUtils.showCoolAlertView(activity, "Oops", HomerFormValidator.PASSWORD_MSG,
//                    RelativeLayoutCoolCartoonAvatarCustomAlertView.Orientation.VERTICLE);
            //via snackbar
//            AlertBoxUtils.showSnackBar(activity, HomerFormValidator.PASSWORD_RULES_MSG);
            return false;
        }
        mEtPassword.setError(null);
        return true;
    }


    public static boolean checkIfBothPasswordsAreSame(Activity activity, EditText mEtPassword1, EditText mEtPassword2) {
        if (!mEtPassword1.getText().toString().equals(mEtPassword2.getText().toString())) {

            final String mStrErrorMEssage = "The two passowrds you entered do not match," +
                    " make sure you enter the same password in both the feilds ";


            final String mStrErrorMEssage2 = "Passwords Do Not Match";
            //via cool alert view
//            AlertBoxUtils.showCoolAlertView(activity, "Oops", mStrErrorMEssage,
//                    RelativeLayoutCoolCartoonAvatarCustomAlertView.Orientation.VERTICLE);

            //via snackbar
//            AlertBoxUtils.showSnackBar(activity, mStrErrorMEssage);

            mEtPassword1.setError(mStrErrorMEssage2);
            mEtPassword2.setError(mStrErrorMEssage2);

            return false;
        }

        mEtPassword1.setError(null);
        mEtPassword2.setError(null);
        return true;
    }

    public static boolean checkIfTexthasBeenEntered(Activity activity, EditText mEt) {
        if (!HomerFormValidator.hasText(mEt, true)) {

//            final String mStrErrorMEssage = "Please make sure you fill in all the reuired feilds ";
//
//            AlertBoxUtils.showCoolAlertView(activity, "Oops", mStrErrorMEssage,
//                    RelativeLayoutCoolCartoonAvatarCustomAlertView.Orientation.VERTICLE);

//            AlertBoxUtils.showSnackBar(activity, mStrErrorMEssage);

            return false;
        }
        mEt.setError(null);
        return true;
    }

//    public static boolean checkIfServerSentNoError(Activity activity, HashMap<String, Object> mResponse) {
//        return checkIfServerSentNoError(activity, mResponse, true);
//    }
//
//    public static boolean checkIfServerSentNoError(Activity activity, HashMap<String, Object> mResponse, boolean showErrDialog) {
//        LinkedTreeMap<String, Object> mTMapResponseStatus = null;
//
//        mTMapResponseStatus = (LinkedTreeMap<String, Object>) mResponse.get("response_status");
//
//        String mStrStatus = (String) mTMapResponseStatus.get("status");
//        if (mStrStatus.equals("success")) {
//
//
//            return true;
//        } else {
////            ActivityBase mActivity = (ActivityBase) activity;
////            if (showErrDialog) {
////                ArrayList<String> mArrMessages = (ArrayList<String>) mTMapResponseStatus.get("message");
////                String mStrMessages = (String) mArrMessages.get(0);
////                AlertBoxUtils.showCustomAlertPopup(mActivity.getSupportFragmentManager(), "OOPS",
////                        mStrMessages,
////                        "!");
////            }
//            return false;
//        }
//
//    }

    public static boolean isAValidUrl(String mStrArgUrl) {

        try {
            URL url = new URL(mStrArgUrl);
            return true;
        } catch (Exception e) {
            // it wasn't a URL
            HomerLogger.i("String ::" + mStrArgUrl + " is not a valid url");
            return false;
        }
    }

    public static boolean isAValidPhoneNumber(EditText mEtMobileNo) {

        if (HomerFormValidator.isPhoneNumber(mEtMobileNo, true, true)) return true;
        return false;
    }
}