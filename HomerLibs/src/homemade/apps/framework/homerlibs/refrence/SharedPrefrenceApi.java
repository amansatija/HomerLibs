package homemade.apps.framework.homerlibs.refrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefrenceApi {

	private static SharedPreferences mPrefs;
	
	public SharedPrefrenceApi(Context context)
	{
	
//	mPrefs = context.getSharedPreferences(context.getPackageName()+"prefrences", Context.MODE_PRIVATE);
	 mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public SharedPreferences getPrefrences() {
		return mPrefs;
	}

	public void setPrefrences(SharedPreferences mPrefs) {
		this.mPrefs = mPrefs;
	}

	
//	public void addSharedPrefEntryForVideoListItem(VideoListItemData data) {
//		addSharedPrefEntryForVideoListItem(data,false);
//	}
//	
//	public void addSharedPrefEntryForVideoListItem(VideoListItemData data,Boolean Value) {
//		mPrefs.edit().putBoolean(data.getEpisodeNoForCircleText(), Value).commit();
//	}
//	public void addSharedPrefEntryForVideoListItem(String key,Boolean Value) {
//		mPrefs.edit().putBoolean(key, Value).commit();
//	}
//	
//	public Boolean getSharedPrefEntryForVideoListItem(VideoListItemData data) {
//		return mPrefs.getBoolean(data.getEpisodeNoForCircleText(), false);
//	}
//	
//	public void addSharedPrefEntryForCurrVideoCircleNoKey(VideoListItemData data) {
//		mPrefs.edit().putString(AppBaseAbstract.getCurrPlayingVideoCircleNoKeyForBooleanValue(),data.getEpisodeNoForCircleText()).commit();
//	}
//	
//	public String getSharedPrefEntryForCurrVideoCircleNoKey() {
//		return mPrefs.getString(AppBaseAbstract.getCurrPlayingVideoCircleNoKeyForBooleanValue(), "-1");
//	}
//	
	
}
