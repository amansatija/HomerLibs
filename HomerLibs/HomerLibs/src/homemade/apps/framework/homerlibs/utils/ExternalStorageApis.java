package homemade.apps.framework.homerlibs.utils;

import android.os.Environment;
import android.os.StatFs;


/**
 * The class Geometry contains external storage functions.
 * 
 * @author Pierre Malarme
 * @version 1.O
 * 
 */
public class ExternalStorageApis {

	
	 //*********
  //Variables
  /**
   * Number of bytes in one KB = 2<sup>10</sup>
   */
  public final static long SIZE_KB = 1024L;

  /**
   * Number of bytes in one MB = 2<sup>20</sup>
   */
  public final static long SIZE_MB = SIZE_KB * SIZE_KB;

  /**
   * Number of bytes in one GB = 2<sup>30</sup>
   */
  public final static long SIZE_GB = SIZE_KB * SIZE_KB * SIZE_KB;
	
	// ---------------------------------------------------------------
	// + <static> FUNCTIONS
	// ---------------------------------------------------------------

	
	
	
	/**
	 * @return True if the external storage is available. False otherwise.
	 */
	public static boolean checkAvailable() {

		// Retrieving the external storage state
		String state = Environment.getExternalStorageState();

		// Check if available
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}

		return false;
	}

	/**
	 * @return True if the external storage is writable. False otherwise.
	 */
	public static boolean checkWritable() {

		// Retrieving the external storage state
		String state = Environment.getExternalStorageState();

		// Check if writable
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}

		return false;

	}

	
	

  //********
  // Methods

  /**
   * @return Number of bytes available on external storage
   */
  public static long getExternalAvailableSpaceInBytes() {
      long availableSpace = -1L;
      try {
          StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
          availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
      } catch (Exception e) {
          e.printStackTrace();
      }

      return availableSpace;
  }


  /**
   * @return Number of kilo bytes available on external storage
   */
  public static long getExternalAvailableSpaceInKB(){
      return getExternalAvailableSpaceInBytes()/SIZE_KB;
  }
  /**
   * @return Number of Mega bytes available on external storage
   */
  public static long getExternalAvailableSpaceInMB(){
      return getExternalAvailableSpaceInBytes()/SIZE_MB;
  }

  /**
   * @return gega bytes of bytes available on external storage
   */
  public static long getExternalAvailableSpaceInGB(){
      return getExternalAvailableSpaceInBytes()/SIZE_GB;
  }

  /**
   * @return Total number of available blocks on external storage
   */
  public static long getExternalStorageAvailableBlocks() {
      long availableBlocks = -1L;
      try {
          StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
          availableBlocks = stat.getAvailableBlocks();
      } catch (Exception e) {
          e.printStackTrace();
      }

      return availableBlocks;
  }
  
  
  public  static Boolean checkIfExternalStorageSpaceLow()
  {
  	if(getExternalAvailableSpaceInMB()<10)
  	{
  		HomerLogger.d("EXTERNALSTORAGE", "available space on sdcard size in mb = ::"+getExternalAvailableSpaceInMB());
  		return true ;
  	}
  	
  	return false;
  }
  
  public  static Boolean checkIfExternalStorageSpaceInMbIsLessThan(int value_of_camparison_in_mb)
  {
  	if(getExternalAvailableSpaceInMB()<value_of_camparison_in_mb)
  	{
  		HomerLogger.d("EXTERNALSTORAGE", "available space on sdcard size in mb = ::"+getExternalAvailableSpaceInMB());
  		return true ;
  	}
  	
  	return false;
  }
  
  
}