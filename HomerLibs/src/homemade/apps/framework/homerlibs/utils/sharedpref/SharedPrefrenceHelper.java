package homemade.apps.framework.homerlibs.utils.sharedpref;

import homemade.apps.framework.homerlibs.utils.HomerLogger;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceHelper {
	public static final String mStrPrefName = "SharedPrefDefaultHomer";

	private static HashMap<String, SharedPreferences> mPrefMap = new HashMap<String, SharedPreferences>();

	public static SharedPreferences getPref(Context context, String PrefName) {
		SharedPreferences mPref = null;
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
}