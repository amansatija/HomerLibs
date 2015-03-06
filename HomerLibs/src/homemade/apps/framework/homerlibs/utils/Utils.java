package homemade.apps.framework.homerlibs.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.content.CursorLoader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class Utils {

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

	public static float convertPxsToItsRespectiveDpValue(Context context, int px) {

		// Converting pixels to dips
		int pixels = px;
		float scale = context.getResources().getDisplayMetrics().density;
		float dips = pixels / scale;
		return dips;

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
	 *            for images the srting should be= MediaStore.Images.Media.DATA
	 * @return
	 */
	public static String getFileNameFromUri(Context context, Uri uri,
			String medistore_mediatype_media_data) {

		String[] proj = { medistore_mediatype_media_data };
		String fileName = "unknown";// default fileName
		Uri filePathUri = uri;
		if (uri.getScheme().toString().compareTo("content") == 0) {
			CursorLoader loader = new CursorLoader(context, uri, proj, null,
					null, null);
			Cursor cursor = loader.loadInBackground();
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
	 *            for images the srting should be= MediaStore.Images.Media.DATA
	 * @return
	 */
	public static String getRealPathFromURI(Context mContext, Uri contentUri,
			String medistore_mediatype_media_data) {
		String[] proj = { medistore_mediatype_media_data };
		CursorLoader loader = new CursorLoader(mContext, contentUri, proj,
				null, null, null);
		Cursor cursor = loader.loadInBackground();
		int column_index = cursor
				.getColumnIndexOrThrow(medistore_mediatype_media_data);
		cursor.moveToFirst();

		HomerLogger.d("Path from uri is " + cursor.getString(column_index));
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
			HomerLogger.d("Screen size is Small");
		else if ((screenLayout & Configuration.SCREENLAYOUT_SIZE_NORMAL) == Configuration.SCREENLAYOUT_SIZE_NORMAL)
			HomerLogger.d("Screen size is Normal");
		else if ((screenLayout & Configuration.SCREENLAYOUT_SIZE_LARGE) == Configuration.SCREENLAYOUT_SIZE_LARGE)
			HomerLogger.d("Screen size is Large");

		if ((screenLayout & Configuration.SCREENLAYOUT_LONG_YES) == Configuration.SCREENLAYOUT_LONG_YES)
			HomerLogger.d("Screen size is Long");

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
		HomerLogger.d("Screen W x H pixels: " + widthPixels + " x "
				+ heightPixels);
		HomerLogger.d("Screen X x Y dpi: " + xdpi + " x " + ydpi);
		HomerLogger.d("density = " + density + "  scaledDensity = "
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

	/**
	 * Performs an HTTP Post request to the specified url with the specified
	 * parameters.
	 * 
	 * @param url
	 *            The web address to post the request to
	 * @param _paramss
	 *            The parameters to send via the request
	 * @return The response of the request
	 * @throws Exception
	 */
	public static String executeHttpPostWithMultiPartEntity(String url,
			List<NameValuePair> arrlis_params,
			String str_path_of_file_to_be_uploaded,
			String str_name_of_file_to_be_uploaded, String str_img_key)
			throws Exception {
		HttpURLConnection connection = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		BufferedReader in = null;

		String pathToOurFile = str_path_of_file_to_be_uploaded;// Environment.getExternalStorageDirectory()+"/recording.wav";//"/data/file_to_send.mp3";

		HttpPost httppost = new HttpPost(url);

		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);

		HttpClient httpClient = new DefaultHttpClient();

		int bytesRead = 0, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File file = new File(pathToOurFile);
		Log.d("CUSTOMHTTPCLIENT", "..pathtofile" + pathToOurFile);
		if (file.exists()) {
			FileInputStream fileInputStream = new FileInputStream(file);

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			// add file to multipartentity
			ByteArrayBody bab = new ByteArrayBody(buffer,
					str_name_of_file_to_be_uploaded);
			entity.addPart(str_img_key, bab);
			// }
		}
		// retrive auth and other parameters from arrlist_params and add them to
		// the multipartentity
		for (int i = 0; i < arrlis_params.size(); i++) {
			NameValuePair nvp = arrlis_params.get(i);
			StringBody sb_temp_param_value = new StringBody(nvp.getValue());
			entity.addPart(nvp.getName(), sb_temp_param_value);
		}

		httppost.setEntity(entity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));

		StringBuffer sb = new StringBuffer("");
		String line = "";
		String NL = System.getProperty("line.separator");
		while ((line = in.readLine()) != null) {
			sb.append(line + NL);
		}
		in.close();

		String result = sb.toString();
		return result;

	}

	public static String getStringFromInputStream(InputStream is) {
		Reader reader = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(reader);

		StringBuffer sb = new StringBuffer("");
		String line = "";
		String NL = System.getProperty("line.separator");
		try {
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String result = sb.toString();

		HomerLogger.i("String From Iput Stream is ::===" + result);
		return result;
	}

	public String convertInputStreamToString(InputStream in) {

		InputStreamReader is = new InputStreamReader(in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(is);
		String read = null;
		try {
			read = br.readLine();

			while (read != null) {
				// System.out.println(read);
				sb.append(read);
				read = br.readLine();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();

	}

	public static void hideSoftKeyboard(Activity activity) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) activity
					.getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(activity
					.getCurrentFocus().getWindowToken(), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Boolean checkIfDatesAreOnSameDay(Date date1, Date date2) {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(date1).equals(fmt.format(date2));

	}

	/**
	 * 
	 * @param url
	 * @param nameValuePairs
	 * 
	 *            // Add your data // List<NameValuePair> nameValuePairs = new
	 *            // ArrayList<NameValuePair>(2); // nameValuePairs.add(new
	 *            BasicNameValuePair("id", "12345")); // nameValuePairs.add(new
	 *            BasicNameValuePair("stringdata", // "AndDev is Cool!"));
	 * @return
	 */
	public static String postData(String url, List<NameValuePair> nameValuePairs) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		// HttpPost httppost = new
		// HttpPost("http://www.yoursite.com/script.php");
		HttpPost httppost = new HttpPost(url);
		HomerLogger.d("url = " + url);
		String mStrResponse = "init";
		try {

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse = httpclient.execute(httppost);

			// So we can't initialize InputStream although it is not an
			// interface
			InputStream inputStream = httpResponse.getEntity().getContent();

			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);

			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			StringBuilder stringBuilder = new StringBuilder();

			String bufferedStrChunk = null;

			while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
				stringBuilder.append(bufferedStrChunk);
			}
			mStrResponse = stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mStrResponse;
	}

	/**
	 * 
	 * @param url
	 * @param nameValuePairs
	 * 
	 *            // Add your data // List<NameValuePair> nameValuePairs = new
	 *            // ArrayList<NameValuePair>(2); // nameValuePairs.add(new
	 *            BasicNameValuePair("id", "12345")); // nameValuePairs.add(new
	 *            BasicNameValuePair("stringdata", // "AndDev is Cool!"));
	 * @return
	 */
	public static String executeHttpGet(String url,
			List<NameValuePair> nameValuePairs) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		// HttpPost httppost = new
		// HttpPost("http://www.yoursite.com/script.php");

		String mStrResponse = "init";

		try {
			String properUrl=url;
			if (nameValuePairs.size()>0) {
				properUrl = url + "?"
						+ URLEncodedUtils.format(nameValuePairs, "UTF-8");
				
			}
			HttpResponse httpResponse = httpclient.execute(new HttpGet(properUrl));
			HomerLogger.d("url = " + properUrl);
			// So we can't initialize InputStream although it is not an
			// interface
			InputStream inputStream = httpResponse.getEntity().getContent();

			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream,HTTP.UTF_8);

			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			StringBuilder stringBuilder = new StringBuilder();

			String bufferedStrChunk = null;

			while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
				stringBuilder.append(bufferedStrChunk);
			}
			mStrResponse = stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mStrResponse;
	}
}
