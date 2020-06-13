package inc.credible.homerlibs.session;

/**
 * Created by aman on 06-05-2015.
 */

import android.content.Context;

import inc.credible.homerlibs.HomerLogger;
import inc.credible.homerlibs.HomerSharedPrefrenceHelper;
import inc.credible.homerlibs.json_models.ModelUser;


/**
 * this class holds all the data for the current session  and it is a singelton class
 */
public class SessionProvider {


    private static ModelUser mMUser;

    public static ModelUser getmUser(Context context) {
        if (mMUser == null && context != null) {
            HomerLogger.i("inside getmUser, mMUser==null");
            String mStrUserData = HomerSharedPrefrenceHelper.getPref(context).
                    getString(HomerSharedPrefrenceHelper.mStrKeyStrUserData, "");
            mMUser = new ModelUser();
            mMUser.restoreFromSavedString(mStrUserData);
        }
        return mMUser;
    }

    public static void setmUser(Context context, ModelUser mModelUser) {

        HomerSharedPrefrenceHelper.saveString(context, HomerSharedPrefrenceHelper.mStrKeyStrUserData, mModelUser.toString());
        mMUser = mModelUser;
    }

    public static void editUser(Context context , String mStrKey , String mValue){
        mMUser.setParamIntoJson(mStrKey,mValue);
        HomerSharedPrefrenceHelper.saveString(context, HomerSharedPrefrenceHelper.mStrKeyStrUserData, mMUser.toString());
    }
    public static void logUserIn(Context context, ModelUser mModelArgUser) {
        HomerSharedPrefrenceHelper.getPref(context).edit()
                .putBoolean(HomerSharedPrefrenceHelper.mStrKeyLoggedIn, true).commit();

        setmUser(context, mModelArgUser);
    }

    public static void logUserOut(Context context) {
        setmUser(context, new ModelUser());

        HomerSharedPrefrenceHelper.removeKey(context,HomerSharedPrefrenceHelper.mStrKeyLoggedIn);

        HomerSharedPrefrenceHelper.removeKey(context,HomerSharedPrefrenceHelper.mStrKeyStrUserData);

    }

    public static boolean isUserLoggedIn(Context context){
        return HomerSharedPrefrenceHelper.getPref(context)
                .getBoolean(HomerSharedPrefrenceHelper.mStrKeyLoggedIn, false);
    }
}