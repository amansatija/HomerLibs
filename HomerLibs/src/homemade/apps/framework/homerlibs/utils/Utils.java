package homemade.apps.framework.homerlibs.utils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

	private static HashMap<String, Typeface> mHmCacheTypeFaces = new HashMap<String, Typeface>();

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

	public static int convertDoubleToInteger(Double d) {
		return (int) Math.round(d);
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
			if (tv != null) {

				Typeface typeFace = null;
				if (!mHmCacheTypeFaces.containsKey(mHmCacheTypeFaces)) {
					typeFace = Typeface.createFromAsset(context.getAssets(),
							fontassetspath);
					mHmCacheTypeFaces.put(fontassetspath, typeFace);
				} else {
					typeFace = mHmCacheTypeFaces.get(mHmCacheTypeFaces);
				}
				tv.setTypeface(typeFace);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// mTVTittleTxt.getBackground().setAlpha(255);
	}

	public static void applyShadowLayerToTextView(Context context, TextView tv,
			int resIdOfColor, float radius, int dx, int dy) {
		tv.setShadowLayer((float) radius, dx, dy, context.getResources()
				.getColor(resIdOfColor));
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

		// if ((screenLayout & Configuration.) ==
		// Configuration.SCREENLAYOUT_SIZE_SMALL)
		// HomerLogger.d(debugtag, "Screen size is Small");

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

		String folder = "";
		String falsepositive = " (note this is based on density and can be false positive)";
		if (density >= 1 && density < 1.5)
			folder = "(mdpi)" + falsepositive;
		if (density < 1)
			folder = "(ldpi)" + falsepositive;
		if (density >= 1.5 && density < 2)
			folder = "(hdpi)" + falsepositive;
		if (density >= 2)
			folder = "(xhdpi)" + falsepositive;
		HomerLogger.d("The folder being used here is ,,,~~!! ===" + folder);
		HomerLogger.d(debugtag, "Screen W x H pixels: " + widthPixels + " x "
				+ heightPixels);
		HomerLogger.d(debugtag, "Screen X x Y dpi: " + xdpi + " x " + ydpi);
		HomerLogger.d(debugtag, "density = " + density + "  scaledDensity = "
				+ scaledDensity + "  densityDpi = " + densityDpi);

	}

	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {

		}
	}

	public static void handleShareClickedEvent(Activity activity, String sub,
			String content) {
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

		// Add data to the intent, the receiving app will decide what to do with
		// it.
		intent.putExtra(Intent.EXTRA_SUBJECT, sub);
		intent.putExtra(Intent.EXTRA_TEXT, content);
		// ... and starting it with a chooser:

		activity.startActivity(Intent.createChooser(intent,
				"How do you want to share?"));
	}

	public static void clearApplicationData(Context context) {
		try {
			File cache = context.getCacheDir();
			File appDir = new File(cache.getParent());
			if (appDir.exists()) {
				String[] children = appDir.list();
				for (String s : children) {
					if (!s.equals("lib")) {
						deleteDir(new File(appDir, s));
						HomerLogger.d("**************** File /data/data/"
								+ context.getPackageName() + "/" + s
								+ " DELETED *******************");
					}
				}
			}
		} catch (Exception e) {
			HomerLogger.e("error deleting data of Applicaton "
					+ context.getPackageName());
			e.printStackTrace();
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}
}
