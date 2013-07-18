package homemade.apps.framework.homerlibs.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author Amn
 * 
 */
public class NetworkApis {

	private String debugtag = "NetworkApis";

	// //////////////////////////////////////////////
	// Static functions
	// ///////////////////////////////////////////

	/**
	 * @deprecated
	 * 
	 *             function was built to get status of internet how ever it does
	 *             return null some devices and hence we have deprecated this
	 *             function . we highly recommend do not use this function
	 * @param context
	 * @return
	 */
	public static boolean isInternetAvailable1thisretunsnullintablets(
			Context context) {
		ConnectivityManager connec = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// ARE WE CONNECTED TO THE NET
		try {
			if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
					|| connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING
					|| connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING
					|| connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
				// MESSAGE TO SCREEN FOR TESTING (IF REQ)
				// Toast.makeText(this, connectionType + ” connected”,
				// Toast.LENGTH_SHORT).show();
				return true;
			} else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
					|| connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
				// System.out.println(“Not Connected”);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @deprecated
	 * 
	 * 
	 *             this function was giving false negative ...!!1 it says net
	 *             not on even when net was on ...!!!
	 * @param context
	 * @return
	 */
	public static boolean isInternetAvailable(Context context) {
		boolean isInternetAvailable = false;

		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();

			if (networkInfo != null && (networkInfo.isConnected())) {
				isInternetAvailable = true;
			}
		} catch (Exception exception) {
			// Do Nothing
		}
		return isInternetAvailable;

	}

	public static boolean isInterAvail(Context context)

	{

		Boolean result = false;
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnected()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			urlc.setConnectTimeout(3000);
			urlc.connect();
			if (urlc.getResponseCode() == 200) {
				result = true;
			} else {
				result = false;
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean isOnline() {
		try {
			Utils.executeWorkaroundToBypassAndPerformNetworkOnMainThread();
			InetAddress.getByName("google.com").isReachable(1);

			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
