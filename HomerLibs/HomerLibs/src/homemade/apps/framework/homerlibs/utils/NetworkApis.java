package homemade.apps.framework.homerlibs.utils;

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
	
	////////////////////////////////////////////////
	//Static functions
	/////////////////////////////////////////////
	
		
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
	 * 
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

}
