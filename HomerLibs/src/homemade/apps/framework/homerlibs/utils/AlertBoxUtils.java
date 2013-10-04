package homemade.apps.framework.homerlibs.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class AlertBoxUtils {
	static AlertDialog alert = null;

	/**
	 * Simple function utility to show an alertbox
	 * 
	 * @USE_CASE : Whenever you wish to show a quick popup..with a custom
	 *           message and a cancel button use this function
	 * 
	 * @NOTE: In order for you to be able to display the alertbox returned from
	 *        this function you will have to call show() on alertbox
	 * 
	 * @EG: Utils.getAlertDialogBox( context,
	 *      "this is a test of alertboxfuntion").show();
	 * 
	 * @param context
	 * @param msg
	 *            : msg is the message in the form of string you wish to display
	 *            inside the body of the alertbox
	 * 
	 * @return AlertDialog :
	 */

	public static AlertDialog getAlertDialogBox(Context context, String msg) {
		return getAlertDialogBox(context, msg, "Alert");
	}

	/**
	 * Simple function utility to show an alertbox
	 * 
	 * @USE_CASE : Whenever you wish to show a quick popup..with a custom
	 *           message and a cancel button use this function
	 * 
	 * @NOTE: In order for you to be able to display the alertbox returned from
	 *        this function you will have to call show() on alertbox
	 * 
	 * @EG: Utils.getAlertDialogBox( context,
	 *      "this is a test of alertboxfuntion").show();
	 * 
	 * @param context
	 * @param msg
	 *            : msg is the message in the form of string you wish to display
	 *            inside the body of the alertbox
	 * @param tittle
	 *            : tittle is the message in the form of string you wish to
	 *            display inside the Header of the alertbox
	 * @return AlertDialog :
	 */

	public static AlertDialog getAlertDialogBox(Context context, String msg,
			String tittle) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle(tittle);

		// set dialog message
		alertDialogBuilder.setMessage(msg).setCancelable(false)

		.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.dismiss();
			}
		});
		AlertDialog alert_dialog = alertDialogBuilder.create();
		return alert_dialog;
	}

	/**
	 * Display popup with a button that exits the app on clicked.
	 * 
	 * @description : it is same as getAlertBox(context , "msg") except for on
	 *              clicking of cancel button it exits the app by calling
	 *              System.exit(0)
	 * @param context
	 * @param msg
	 * @return
	 */
	public static AlertDialog getAlertDialogBoxForExit(Context context,
			String msg) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		// alertDialogBuilder.setTitle("Alert");

		// set dialog message
		alertDialogBuilder.setMessage(msg).setCancelable(false)

		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.dismiss();
				System.exit(0);
			}
		}).setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		AlertDialog alert_dialog = alertDialogBuilder.create();
		return alert_dialog;
	}

	/**
	 * Display popup with a button that exits the app on clicked.
	 * 
	 * @description : it is same as getAlertBox(context , "msg") except for on
	 *              clicking of cancel button it exits the app by calling
	 *              System.exit(0)
	 * @param context
	 * @param msg
	 * @return
	 */
	public static AlertDialog getAlertDialogBoxForFinishingActivity(
			final Context context, String msg) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Alert");

		// set dialog message
		alertDialogBuilder.setMessage(msg).setCancelable(false)

		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.dismiss();
				((Activity) (context)).finish();
			}
		}).setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		AlertDialog alert_dialog = alertDialogBuilder.create();
		return alert_dialog;
	}

	/**
	 * Simple function utility to show an alertbox
	 * 
	 * @USE_CASE : Whenever you wish to show a quick popup..with a custom
	 *           message and a cancel button use this function
	 * 
	 * @NOTE: In order for you to be able to display the alertbox returned from
	 *        this function you will have to call show() on alertbox
	 * 
	 * @EG: Utils.getAlertDialogBox( context,
	 *      "this is a test of alertboxfuntion").show();
	 * 
	 * @param context
	 * @param msg
	 *            : msg is the message in the form of string you wish to display
	 *            inside the body of the alertbox
	 * @param tittle
	 *            : tittle is the message in the form of string you wish to
	 *            display inside the Header of the alertbox
	 * @return AlertDialog :
	 */

	public static AlertDialog getAlertDialogBoxWithoutHeader(Context context,
			String msg) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set dialog message
		alertDialogBuilder.setMessage(msg).setCancelable(false)

		.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.dismiss();
			}
		});
		AlertDialog alert_dialog = alertDialogBuilder.create();
		return alert_dialog;
	}

}
