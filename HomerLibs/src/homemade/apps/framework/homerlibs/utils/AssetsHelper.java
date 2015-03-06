package homemade.apps.framework.homerlibs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Debug.MemoryInfo;

public class AssetsHelper {

	private static HashMap<String, Bitmap> mHmCacheAssestsBitmaps = new HashMap<String, Bitmap>();

	public static Bitmap fetchBitmapFromAssets(Context context,
			String pathToFileInAssetsFolder, int width, int height) {
		if (mHmCacheAssestsBitmaps != null) {
			if (mHmCacheAssestsBitmaps.containsKey(pathToFileInAssetsFolder)) {
				return mHmCacheAssestsBitmaps.get(pathToFileInAssetsFolder);
			} else {
				Bitmap bitmap = null;
				try {

					HomerLogger
							.d("path to assests that we are tryign to open is ::"
									+ pathToFileInAssetsFolder);
					InputStream is = context.getAssets().open(
							pathToFileInAssetsFolder);
					// bitmap = decodeSampledBitmapFromResourceMemOpt(is, width,
					// height);

					bitmap = BitmapFactory.decodeStream(is);
					mHmCacheAssestsBitmaps
							.put(pathToFileInAssetsFolder, bitmap);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return bitmap;
			}
		} else {
			return null;
		}
	}

	public static Bitmap decodeSampledBitmapFromResourceMemOpt(
			InputStream inputStream, int reqWidth, int reqHeight) {

		byte[] byteArr = new byte[0];
		byte[] buffer = new byte[1024];
		int len;
		int count = 0;

		try {
			while ((len = inputStream.read(buffer)) > -1) {
				if (len != 0) {
					if (count + len > byteArr.length) {
						byte[] newbuf = new byte[(count + len) * 2];
						System.arraycopy(byteArr, 0, newbuf, 0, count);
						byteArr = newbuf;
					}

					System.arraycopy(buffer, 0, byteArr, count, len);
					count += len;
				}
			}

			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(byteArr, 0, count, options);

			options.inSampleSize = calculateInSampleSize(options, reqWidth,
					reqHeight);
			options.inPurgeable = true;
			options.inInputShareable = true;
			options.inJustDecodeBounds = false;
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;

			int[] pids = { android.os.Process.myPid() };
			// MemoryInfo myMemInfo = mAM.getProcessMemoryInfo(pids)[0];
			// HomerLogger.e( "dalvikPss (decoding) = " + myMemInfo.dalvikPss);

			return BitmapFactory.decodeByteArray(byteArr, 0, count, options);

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}
}
