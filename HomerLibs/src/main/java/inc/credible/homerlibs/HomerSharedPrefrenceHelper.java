package inc.credible.homerlibs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Set;

public class HomerSharedPrefrenceHelper {
    public static final String mStrPrefName = "PrefsContractorTracking";

    public static final String mStrKeyShowIntro = "mStrKeyShowIntro";

    public static final String mStrKeyLoggedIn = "mStrKeyLoggedIn";


    public static final String mStrKeyStrUserData = "mStrKeyStrUserData";


    public static final String mStrKeyBoolFlagOneSignalUrIdSentToServer =  "mStrKeyBoolOneSignalUrIdSentToServer";

    public static final String mStrKeyStrOneSignalUrIdSentToServer =  "mStrKeyStrOneSignalUrIdSentToServer";

    public static final String mStrKeyArrListTags
            =  "mStrKeyArrListTags";


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Data provider keys  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    private static HashMap<String, SharedPreferences> mPrefMap = new HashMap<String, SharedPreferences>();

    public static SharedPreferences getPref(Context context, String PrefName) {
        SharedPreferences mPref;
        if (PrefName == null)
            PrefName = mStrPrefName;
        if (mPrefMap != null) {
            mPref = mPrefMap.get(PrefName);
            if (mPref == null) {
                mPref = context.getSharedPreferences(PrefName,
                        Context.MODE_PRIVATE);
                mPrefMap.put(PrefName, mPref);
            }
        } else {

            HomerLogger.e("ERROR WHILE FETCHING SHAREDPREF MAP IS NULL");
        }
        return mPrefMap.get(PrefName);
    }

    public static SharedPreferences getPref(Context context) {
        return HomerSharedPrefrenceHelper.getPref(context, null);
    }

    public static boolean saveString(Context mCtxArgContext, String mStrArgKey, String mStrArgVal) {
        boolean returnVar = false;
        try {
            returnVar = saveString(mCtxArgContext, mStrPrefName, mStrArgKey, mStrArgVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveString(Context mCtxArgContext, String mStrArgPrefName, String mStrArgKey, String mStrArgVal) {
        boolean returnVar = false;
        try {
            returnVar = HomerSharedPrefrenceHelper.getPref(mCtxArgContext, mStrArgPrefName).edit().putString(mStrArgKey, mStrArgVal).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }


    public static boolean saveBoolean(Context mCtxArgContext, String mStrArgKey, Boolean mBoolArgVal) {
        boolean returnVar = false;
        try {
            returnVar = saveBoolean(mCtxArgContext, mStrPrefName, mStrArgKey, mBoolArgVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveBoolean(Context mCtxArgContext, String mStrArgPrefName, String mStrArgKey, Boolean mBoolArgVal) {
        boolean returnVar = false;
        try {
            returnVar = HomerSharedPrefrenceHelper.getPref(mCtxArgContext, mStrArgPrefName).edit().putBoolean(mStrArgKey, mBoolArgVal).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveInt(Context mCtxArgContext, String mStrArgKey, int mIntArgVal) {
        boolean returnVar = false;
        try {
            returnVar = saveInt(mCtxArgContext, mStrPrefName, mStrArgKey, mIntArgVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveInt(Context mCtxArgContext, String mStrArgPrefName, String mStrArgKey, int mIntArgVal) {
        boolean returnVar = false;
        try {
            returnVar = HomerSharedPrefrenceHelper.getPref(mCtxArgContext, mStrArgPrefName).edit().putInt(mStrArgKey, mIntArgVal).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveLong(Context mCtxArgContext, String mStrArgKey, long mLongArgVal) {
        boolean returnVar = false;
        try {
            returnVar = saveLong(mCtxArgContext, mStrPrefName, mStrArgKey, mLongArgVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveLong(Context mCtxArgContext, String mStrArgPrefName, String mStrArgKey, long mLongArgVal) {
        boolean returnVar = false;
        try {
            returnVar = HomerSharedPrefrenceHelper.getPref(mCtxArgContext, mStrArgPrefName).edit().putLong(mStrArgKey, mLongArgVal).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveStringSet(Context mCtxArgContext, String mStrArgKey, Set<String> mSetStrArgVal) {
        boolean returnVar = false;
        try {
            returnVar = saveStringSet(mCtxArgContext, mStrPrefName, mStrArgKey, mSetStrArgVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    public static boolean saveStringSet(Context mCtxArgContext, String mStrArgPrefName, String mStrArgKey, Set<String> mSetStrArgVal) {
        boolean returnVar = false;
        try {
            returnVar = HomerSharedPrefrenceHelper.getPref(mCtxArgContext, mStrArgPrefName).edit().putStringSet(mStrArgKey, mSetStrArgVal).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVar;
    }


    public static void removeKey(Context context, String mStrPrefKeyToBeRemoved) {

        removeKey(context,mStrPrefName, mStrPrefKeyToBeRemoved);
    }

    public static void removeKey(Context context, String mStrPrefName, String mStrPrefKeyToBeRemoved) {


        try {
            HomerSharedPrefrenceHelper.getPref(context, mStrPrefName).edit().remove(mStrPrefKeyToBeRemoved).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}