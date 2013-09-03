package homemade.apps.framework.homerlibs.refrence;
//package homemade.apps.framework.homerlibs.utils;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.MalformedURLException;
//
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.res.Resources.NotFoundException;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.util.Log;
//import android.widget.Toast;
//
//public class SocialApi {
//
//	
//
//	private Context mContext;
//	
//	private static Activity mActivity;
//	
//	
//	private String debugtag = "SocialApi";
//	
//	private static Facebook mFacebook;
//	public static Facebook getmFacebook() {
//		return mFacebook;
//	}
//	
//	private static String mStrFbObjectIDToPostTo="398449453565868_437718639638949";
//	public static void setmFacebook(Facebook mFacebook_) {
//		mFacebook = mFacebook_;
//	}
//	
//	
//	private SharedPreferences sp;
//
//	
//	
//	private String[] mPermission  = {"publish_stream","manage_pages"};
//	public String[] getPermission() {
//		return mPermission;
//	}
//	public void setPermission(String[] mPermission) {
//		this.mPermission = mPermission;
//	}
//	
//	
//	public static enum FbRequestActions
//	{
//		LIKE,COMMENT,POST
//	}
//	
//	
//	public SocialApi(Context activity) {
//		mActivity = (Activity)activity;
//		mContext = activity;
//
//		executeWorkaroundToBypassAndPerformNetworkOnMainThread();
//		if (mActivity != null) {
//			String APP_ID = mActivity.getString(R.string.APP_ID);
//			if (mFacebook==null) {
//				mFacebook = new Facebook(APP_ID);
//			}
//			fetchObjectIdToPostTo();
//			restoreSessionAndAcessTokenIfAlreadyPresent();
//			
//		} else {
//			Log.d(debugtag,
//					"mActivity is null ..... activity passed to the social api in the constructor is null");
//		}
//	}
//
//	private void fetchObjectIdToPostTo() {
//		try {
//			mActivity.getResources().getString(R.string.socialposttoObject);
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	public void restoreSessionAndAcessTokenIfAlreadyPresent() {
//		sp = mActivity.getPreferences(Context.MODE_PRIVATE);
//		String access_token = sp.getString("access token", null);
//		long expires = sp.getLong("access expires", 0);
//
//		if (access_token != null) {
//			mFacebook.setAccessToken(access_token);
//		}
//		if (expires != 0) {
//			mFacebook.setAccessExpires(expires);
//		}
//	}
//
//	public void executeWorkaroundToBypassAndPerformNetworkOnMainThread() {
//		if (android.os.Build.VERSION.SDK_INT > 9) {
//			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//					.permitAll().build();
//			StrictMode.setThreadPolicy(policy);
//		}
//	}
//	
//	
//	private void fbAuthorize(Activity activity ,String [] permisions ,DialogListener FacebookAuthorisationHandler) {
//		if (mFacebook!=null) {
//			mFacebook.authorize(activity, permisions,
//					Facebook.FORCE_DIALOG_AUTH, FacebookAuthorisationHandler);
//		}
//		else
//		{
//			Log.d(debugtag, "inside fbAuthorize :: mFacebook ==nulll ..!! ");
//		}
//	}
//
//	private void fbImageSubmit() {
//		
//				Bundle b = new Bundle();
//				b.putString("picture", "your image url");
//				b.putString("caption", "your title");
//				b.putString("description", "test");
//				b.putString("name",
//						"Hi Friends, I am using the your app name app for Android!");
//				b.putString("link", "https://market.android.com/details?id="
//						+ mActivity.getApplication().getPackageName().toString());
//				try {
//					String strRet = "";
//					strRet = mFacebook.request("/me/feed", b, "POST");
//					JSONObject json;
//					try {
//						json = Util.parseJson(strRet);
//						if (!json.isNull("id")) {
//							Log.i("Facebook", "Image link submitted.");
//						} else {
//							Log.e("Facebook", "Error: " + strRet);
//						}
//					} catch (FacebookError e) {
//						Log.e("Facebook", "Error: " + e.getMessage());
//					}
//				} catch (Exception e) {
//					Log.e("Facebook", "Error: " + e.getMessage());
//				}
//			
//		
//		
//	}
//	
//	public void fbRequest(FbRequestActions action,Runnable run,DialogListener listener)
//	{
//		if (mFacebook!=null) {
//			if(mFacebook.isSessionValid())
//			{
//				
//			}
//			else
//			{
//				Log.d(debugtag, "inside fbRequest:: Session is invalid ,,!! opening authorising dialoge box ,,,!  ");
//				fbAuthorize(mActivity, getPermission(), listener);
//			}
//		}else
//		{
//			Log.d(debugtag, "inside fbRequest::  mFacebook is null call constructor before calling this function !!1");
//		}
//	}
//	
//	public  static void fbLike()
//	{
//		Bundle params = new Bundle();
//		Bundle parameters = new Bundle();
//		parameters.putString("message", "truely shameful");
//	
//		try {
//
//			mFacebook.request(mStrFbObjectIDToPostTo + "/likes", params,
//					"POST");
//			
//			Toast.makeText(mActivity,
//					"Post has been liked on Allencarrs facebook page",
//					Toast.LENGTH_SHORT).show();
////			mFacebook.request("185547141525640_4618344066103" + "/comments",
////					parameters, "POST");
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static void fbComment(String comment)
//	{
//		Bundle params = new Bundle();
//		Bundle parameters = new Bundle();
//		parameters.putString("message", comment);
//	
//		try {
//
////			mFacebook.request("185547141525640_4618344066103" + "/likes", params,
////					"POST");
//			mFacebook.request(mStrFbObjectIDToPostTo + "/comments",
//					parameters, "POST");
//			
//			Toast.makeText(mActivity,
//					"following comment has been posted to the post on Allencars facebookpage",
//					Toast.LENGTH_SHORT).show();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
