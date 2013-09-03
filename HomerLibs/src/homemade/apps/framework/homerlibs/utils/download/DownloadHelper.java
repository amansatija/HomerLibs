package homemade.apps.framework.homerlibs.utils.download;

import homemade.apps.framework.homerlibs.utils.HomerLogger;

import java.io.Serializable;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

public class DownloadHelper implements Serializable {

	private String debugtag = "DownloadHelper";

	private DownloadManager mDownloadManager;

	private BroadcastReceiver mBroadcastRecieverOnDownloadComplete;

	private Uri URI;

	private long mDownloadRefrence;

	public DownloadHelper(Context context_) {
		getDownLoadManagerFromSystem(context_);

	}

	public void getDownLoadManagerFromSystem(Context context_) {
		if (context_ != null) {
			mDownloadManager = (DownloadManager) context_
					.getSystemService(Context.DOWNLOAD_SERVICE);
		} else {
			HomerLogger
					.d(debugtag,
							"inside getDownLoadManagerFromSystem:: context is null either the application has resumed and you haven reset the context or the constructor has not been called.....please  call the contructor to set the context before using the class..!!");
		}
	}

	private DownloadResponse download(Context context,
			String url_to_download_from_server) {
		if (context != null) {

			if (mDownloadManager != null) {
				setURL(url_to_download_from_server);

				DownloadManager.Request request = new DownloadManager.Request(
						URI);

				// Restrict the types of networks over which this download may
				// proceed.
				request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
						| DownloadManager.Request.NETWORK_MOBILE);
				// Set whether this download may proceed over a roaming
				// connection.
				request.setAllowedOverRoaming(false);
				// Set the title of this download, to be displayed in
				// notifications
				// (if enabled).
				request.setTitle("My Data Download");
				// Set a description of this download, to be displayed in
				// notifications (if enabled)
				request.setDescription("Android Data download using DownloadManager.");
				// Set the local destination for the downloaded file to a path
				// within the application's external files directory
				request.setDestinationInExternalFilesDir(context,
						Environment.DIRECTORY_DOWNLOADS, "/video.m4v");

				// Enqueue a new download and same the referenceId
				mDownloadRefrence = mDownloadManager.enqueue(request);
			} else {
				HomerLogger
						.d(debugtag,
								"inside Download:: mDownloadManager is null either the application has resumed and you haven reset the context or the constructor has not been called.....please  call the contructor to set the context before using the class..chek the code in constructor for further details !!");
			}

		} else {
			HomerLogger
					.d(debugtag,
							"inside Download:: context is null either the application has resumed and you haven reset the context or the constructor has not been called.....please  call the contructor to set the context before using the class..!!");
		}

		return new DownloadResponse("", "");
	}

	public void setURL(String strValue) {
		if (strValue != null)

		{

			try {
				URI = Uri.parse(strValue);
			} catch (Exception e) {

				e.printStackTrace();
				HomerLogger
						.d(debugtag,
								"inside setURL:: failed to parse the string to a uri succesfully ...!! failed to set uri ,,!1 check the uri u r trying to set ");
			}
		} else {
			HomerLogger
					.d(debugtag,
							"inside setURL::  url that you have passed  is Null please check the url u r trying to set..");
		}
	}

	public Uri getURL() {
		return URI;
	}
}
