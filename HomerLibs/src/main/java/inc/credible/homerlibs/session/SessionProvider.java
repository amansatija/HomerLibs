package inc.credible.homerlibs.session;

/**
 * Created by aman on 06-05-2015.
 */

import android.content.Context;

import inc.credible.homerlibs.HomerLogger;
import inc.credible.homerlibs.HomerPrefHelper;
import inc.credible.homerlibs.json_models.ModelUser;


/**
 * this class holds all the data for the current session  and it is a singelton class
 */
public class SessionProvider {


    private static ModelUser mMUser;

    public static ModelUser getmUser(Context context) {
        if (mMUser == null && context != null) {
            HomerLogger.i("inside getmUser, mMUser==null");
            String mStrUserData = HomerPrefHelper.getPref(context).
                    getString(HomerPrefHelper.mStrKeyStrUserData, "");
            mMUser = new ModelUser();
            mMUser.restoreFromSavedString(mStrUserData);
        }
        return mMUser;
    }

    public static void setmUser(Context context, ModelUser mModelUser) {

        HomerPrefHelper.saveString(context, HomerPrefHelper.mStrKeyStrUserData, mModelUser.toString());
        mMUser = mModelUser;
    }

    public static void editUser(Context context , String mStrKey , String mValue){
        mMUser.setParamIntoJson(mStrKey,mValue);
        HomerPrefHelper.saveString(context, HomerPrefHelper.mStrKeyStrUserData, mMUser.toString());
    }
    public static void logUserIn(Context context, ModelUser mModelArgUser) {
        HomerPrefHelper.getPref(context).edit()
                .putBoolean(HomerPrefHelper.mStrKeyLoggedIn, true).commit();

        setmUser(context, mModelArgUser);
    }

    public static void logUserOut(Context context) {
        setmUser(context, new ModelUser());

        HomerPrefHelper.removeKey(context, HomerPrefHelper.mStrKeyLoggedIn);

        HomerPrefHelper.removeKey(context, HomerPrefHelper.mStrKeyStrUserData);

    }

    public static boolean isUserLoggedIn(Context context){
        return HomerPrefHelper.getPref(context)
                .getBoolean(HomerPrefHelper.mStrKeyLoggedIn, false);
    }
}