package homemade.apps.framework.homerlibs.utils;

import android.util.Log;

public class HomerLogger {

	private static boolean mKeepLogging = true;

	private static boolean mKeepLoggingE = true;
	private static boolean mKeepLoggingI = true;
	private static boolean mKeepLoggingV = true;
	private static boolean mKeepLoggingW = true;
	private static boolean mKeepLoggingD = true;

	private boolean mkeepLoggingAtObjectLevel = true;

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////
	// //////features to be added later on :: a boolean to stop all logs from
	// homer libs;
	// //////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public HomerLogger() {

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////
	// //
	// // Getters and setters
	// //
	// //////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean keepLogging() {
		return mKeepLogging;
	}

	public static void setKeepLogging(boolean mKeepLogging) {
		HomerLogger.mKeepLogging = mKeepLogging;
	}

	public static boolean keepLoggingE() {
		return mKeepLoggingE;
	}

	public static void setKeepLoggingE(boolean mKeepLoggingE) {
		HomerLogger.mKeepLoggingE = mKeepLoggingE;
	}

	public static boolean keepLoggingI() {
		return mKeepLoggingI;
	}

	public static void setKeepLoggingI(boolean mKeepLoggingI) {
		HomerLogger.mKeepLoggingI = mKeepLoggingI;
	}

	public static boolean keepLoggingV() {
		return mKeepLoggingV;
	}

	public static void setKeepLoggingV(boolean mKeepLoggingV) {
		HomerLogger.mKeepLoggingV = mKeepLoggingV;
	}

	public static boolean keepLoggingW() {
		return mKeepLoggingW;
	}

	public static void setKeepLoggingW(boolean mKeepLoggingW) {
		HomerLogger.mKeepLoggingW = mKeepLoggingW;
	}

	public static boolean keepLoggingD() {
		return mKeepLoggingD;
	}

	public static void setKeepLoggingD(boolean mKeepLoggingD) {
		HomerLogger.mKeepLoggingD = mKeepLoggingD;
	}

	public boolean keepLoggingAtObjectLevel() {
		return mkeepLoggingAtObjectLevel;
	}

	public void setkeepLoggingAtObjectLevel(boolean mkeepLoggingAtObjectLevel) {
		this.mkeepLoggingAtObjectLevel = mkeepLoggingAtObjectLevel;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////
	// //
	// // Logging functions............At global level ...!!!
	// //
	// //////////////////////////////////////////////////////////////////////////////////////////////////

	public static void e(String debugtab, String msg) {
		if (keepLogging()) {
			if (keepLoggingE()) {
				HomerLogger.e(debugtab, msg, null);
			}
		}
	}

	public static void e(String debugtab, String msg, Exception e) {
		if (keepLogging()) {
			if (keepLoggingE()) {
				if (e == null) {
					Log.e(debugtab, msg);
				} else {
					Log.e(debugtab, msg, e);
				}
			}
		}
	}

	public static void i(String debugtab, String msg) {
		if (keepLogging()) {
			if (keepLoggingI()) {
				HomerLogger.i(debugtab, msg, null);
			}
		}
	}

	public static void i(String debugtab, String msg, Exception e) {
		if (keepLogging()) {
			if (keepLoggingI()) {
				if (e == null) {
					Log.i(debugtab, msg);
				} else {
					Log.i(debugtab, msg, e);
				}
			}
		}
	}

	public static void v(String debugtab, String msg) {
		if (keepLogging()) {
			if (keepLoggingV()) {
				HomerLogger.v(debugtab, msg, null);
			}
		}
	}

	public static void v(String debugtab, String msg, Exception e) {
		if (keepLogging()) {
			if (keepLoggingV()) {
				if (e == null) {
					Log.v(debugtab, msg);
				} else {
					Log.v(debugtab, msg, e);
				}
			}
		}
	}

	public static void w(String debugtab, String msg) {
		if (keepLogging()) {
			if (keepLoggingW()) {
				HomerLogger.w(debugtab, msg, null);
			}
		}
	}

	public static void w(String debugtab, String msg, Exception e) {
		if (keepLogging()) {
			if (keepLoggingW()) {
				if (e == null) {
					Log.w(debugtab, msg);
				} else {
					Log.w(debugtab, msg, e);
				}
			}
		}
	}

	public static void d(String debugtab, String msg) {
		if (keepLogging()) {
			if (keepLoggingD()) {
				HomerLogger.e(debugtab, msg, null);
			}
		}
	}

	public static void d(String debugtab, String msg, Exception e) {
		if (keepLogging()) {
			if (keepLoggingD()) {
				if (e == null) {
					Log.d(debugtab, msg);
				} else {
					Log.d(debugtab, msg, e);
				}
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////
	// //
	// // Logging functions............At Object level ...!!!
	// //
	// //////////////////////////////////////////////////////////////////////////////////////////////////

	public void eAtObjectLevel(String debugtab, String msg) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingE()) {
					HomerLogger.e(debugtab, msg, null);
				}
			}
		}

	}

	public void eAtObjectLevel(String debugtab, String msg, Exception e) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingE()) {
					if (e == null) {
						Log.e(debugtab, msg);
					} else {
						Log.e(debugtab, msg, e);
					}
				}
			}
		}

	}

	public void iAtObjectLevel(String debugtab, String msg) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingI()) {
					HomerLogger.i(debugtab, msg, null);
				}
			}
		}

	}

	public void iAtObjectLevel(String debugtab, String msg, Exception e) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingI()) {
					if (e == null) {
						Log.i(debugtab, msg);
					} else {
						Log.i(debugtab, msg, e);
					}
				}
			}
		}

	}

	public void vAtObjectLevel(String debugtab, String msg) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingV()) {
					HomerLogger.v(debugtab, msg, null);
				}
			}
		}

	}

	public void vAtObjectLevel(String debugtab, String msg, Exception e) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingV()) {
					if (e == null) {
						Log.v(debugtab, msg);
					} else {
						Log.v(debugtab, msg, e);
					}
				}
			}
		}

	}

	public void wAtObjectLevel(String debugtab, String msg) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingW()) {
					HomerLogger.w(debugtab, msg, null);
				}
			}
		}

	}

	public void wAtObjectLevel(String debugtab, String msg, Exception e) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingW()) {
					if (e == null) {
						Log.w(debugtab, msg);
					} else {
						Log.w(debugtab, msg, e);
					}
				}
			}
		}

	}

	public void dAtObjectLevel(String debugtab, String msg) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingD()) {
					HomerLogger.e(debugtab, msg, null);
				}
			}
		}

	}

	public void dAtObjectLevel(String debugtab, String msg, Exception e) {
		if (keepLoggingAtObjectLevel()) {
			if (keepLogging()) {
				if (keepLoggingD()) {
					if (e == null) {
						Log.d(debugtab, msg);
					} else {
						Log.d(debugtab, msg, e);
					}
				}
			}
		}

	}
	// ////////////////////////////////////////////////////////////////////////////////////////////////////
	// //
	// //a function for logging that onli hommer libs uses ,,,!! so that we can
	// stop all logs from homer libs by just one boolean
	// //
	// //////////////////////////////////////////////////////////////////////////////////////////////////
}
