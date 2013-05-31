package homemade.apps.framework.homerlibs.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.content.CursorLoader;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.EditText;
import android.widget.TextView;

public class Utils {

	static AlertDialog alert = null;

	private static String debugtag = "HomerLibsUtils";

	/**
	 * This function converts pixel value to dip value .
	 * 
	 * USE CASES : when ever setting value of height or width of any view from
	 * code you must always set the dip value
	 * 
	 * @param context
	 * @param f
	 * @return
	 */
	public static int getDipValuefor(Context context, float f) {

		// The gesture threshold expressed in dip
		final float GESTURE_THRESHOLD_DIP = f;

		// Convert the dips to pixels
		final float scale = context.getResources().getDisplayMetrics().density;
		int int_dip_value = (int) (GESTURE_THRESHOLD_DIP * scale + 0.05f);

		return int_dip_value;
	}

	public static Boolean textHasBeenEnteredInTextBox(EditText et) {
		Boolean result = false;

		if (et.getText().toString().trim().length() > 0)
			result = true;
		else
			result = false;

		return result;

	}

	public static void applyFontToTextView(Context context, TextView tv,
			String fontassetspath) {
		try {
			Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
					fontassetspath);
			tv.setTypeface(typeFace);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// mTVTittleTxt.getBackground().setAlpha(255);
	}

	public static String EllipseString(String value, int length) {
		StringBuilder buf = new StringBuilder(value);
		if (buf.length() > length) {
			// if (buf.length()==length_before_newline){
			// buf.append("\n");
			// }
			buf.trimToSize();
			buf.setLength(length);
			buf.append("...");
		}

		return buf.toString();
	}

	public static void executeWorkaroundToBypassAndPerformNetworkOnMainThread() {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}

	/**
	 * @NOTE: THIS FUNCTION DOES NOT WORK PROPERLY AS OF NOW NEED TO WORK ON IT
	 *        ;It has been copid from c360 base customer activity where it was
	 *        working properly
	 * @param context
	 * @param uri
	 * @param medistore_mediatype_media_data
	 * @return
	 */
	public String getFileNameFromUri(Context context, Uri uri,
			String medistore_mediatype_media_data) {
		String fileName = "unknown";// default fileName
		Uri filePathUri = uri;
		if (uri.getScheme().toString().compareTo("content") == 0) {
			Cursor cursor = context.getContentResolver().query(uri, null, null,
					null, null);
			if (cursor.moveToFirst()) {
				int column_index = cursor
						.getColumnIndexOrThrow(medistore_mediatype_media_data);// Instead
																				// of
																				// "MediaStore.Images.Media.DATA"
																				// can
																				// be
																				// used
																				// "_data"
				filePathUri = Uri.parse(cursor.getString(column_index));
				fileName = filePathUri.getLastPathSegment().toString();

			}
		} else if (uri.getScheme().compareTo("file") == 0) {
			fileName = filePathUri.getLastPathSegment().toString();
		} else {
			fileName = fileName + "_"
					+ filePathUri.getLastPathSegment().toString();
		}
		return fileName;
	}

	/**
	 * @NOTE: THIS FUNCTION DOES NOT WORK PROPERLY AS OF NOW NEED TO WORK ON IT
	 *        ;It has been copid from c360 base customer activity where it was
	 *        working properly
	 * @param contentUri
	 * @param medistore_mediatype_media_data
	 * @return
	 */
	public String getRealPathFromURI(Uri contentUri,
			String medistore_mediatype_media_data) {
		String[] proj = { medistore_mediatype_media_data };
		CursorLoader loader = new CursorLoader(null);
		Cursor cursor = loader.loadInBackground();
		int column_index = cursor
				.getColumnIndexOrThrow(medistore_mediatype_media_data);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	/**
	 * Returns true if the device has a screen larger than 6 inch
	 * 
	 * @param activity
	 * @return
	 */
	public static boolean isTablet(Activity activity) {

		Display display = activity.getWindowManager().getDefaultDisplay();

		DisplayMetrics displayMetrics = new DisplayMetrics();

		display.getMetrics(displayMetrics);

		int width = displayMetrics.widthPixels / displayMetrics.densityDpi;

		int height = displayMetrics.heightPixels / displayMetrics.densityDpi;

		double screenDiagonal = Math.sqrt(width * width + height * height);

		HomerLogger.v("you device screen diagonal is :", "" + screenDiagonal);

		return (screenDiagonal >= 6.5);

	}

	public static void LogTheConfigBeignUsedToLoadResources(Context context) {
		// Figure out what kind of display we have
		int screenLayout = context.getResources().getConfiguration().screenLayout;

		if ((screenLayout & Configuration.SCREENLAYOUT_SIZE_SMALL) == Configuration.SCREENLAYOUT_SIZE_SMALL)
			HomerLogger.d(debugtag, "Screen size is Small");
		else if ((screenLayout & Configuration.SCREENLAYOUT_SIZE_NORMAL) == Configuration.SCREENLAYOUT_SIZE_NORMAL)
			HomerLogger.d(debugtag, "Screen size is Normal");
		else if ((screenLayout & Configuration.SCREENLAYOUT_SIZE_LARGE) == Configuration.SCREENLAYOUT_SIZE_LARGE)
			HomerLogger.d(debugtag, "Screen size is Large");

		if ((screenLayout & Configuration.SCREENLAYOUT_LONG_YES) == Configuration.SCREENLAYOUT_LONG_YES)
			HomerLogger.d(debugtag, "Screen size is Long");

		
//		if ((screenLayout & Configuration.) == Configuration.SCREENLAYOUT_SIZE_SMALL)
//			HomerLogger.d(debugtag, "Screen size is Small");
		
		// Get the metrics
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		int heightPixels = metrics.heightPixels;
		int widthPixels = metrics.widthPixels;
		int densityDpi = metrics.densityDpi;
		float density = metrics.density;
		float scaledDensity = metrics.scaledDensity;
		float xdpi = metrics.xdpi;
		float ydpi = metrics.ydpi;

		HomerLogger.d(debugtag, "Screen W x H pixels: " + widthPixels + " x "
				+ heightPixels);
		HomerLogger.d(debugtag, "Screen X x Y dpi: " + xdpi + " x " + ydpi);
		HomerLogger.d(debugtag, "density = " + density + "  scaledDensity = "
				+ scaledDensity + "  densityDpi = " + densityDpi);
	}

}
