package homemade.apps.framework.homerlibs.refrence;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class FolderStructure {

	static String ParentFolderPathSuffix = "/interactingvideodemo";
	
	static String video0FolderPathSuffix = "/video0";
	static String video1FolderPathSuffix = "/video1";
	static String video2FolderPathSuffix = "/video2";
	static String video3FolderPathSuffix = "/video3";
	static String video4FolderPathSuffix = "/video4";
	static String video5FolderPathSuffix = "/video5";
	static String video6FolderPathSuffix = "/video6";
	static String video7FolderPathSuffix = "/video7";
	static String video8FolderPathSuffix = "/video8";
	static String video9FolderPathSuffix = "/video9";
	static String video10FolderPathSuffix = "/video10";
	static String video11FolderPathSuffix = "/video11";
	static String video12FolderPathSuffix = "/video12";
	static String video13FolderPathSuffix = "/video13";
	static String video14FolderPathSuffix = "/video14";
	static String video15FolderPathSuffix = "/video15";
	static String video16FolderPathSuffix = "/video16";
	static String video17FolderPathSuffix = "/video17";
	static String video18FolderPathSuffix = "/video18";
	
	
	
	public static Boolean createDirIfNotAlreadyExist(String path)
	{
		File file = new File(path);
		if(file.exists())
			return true;
		else {
			try {
				file.mkdirs();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false; 
	}
	
	public static String SdCArdpath(Context context)
	{
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	public static Boolean checkAndCreateIFNeededAllenCarrFolderStructre(Context context)
	{
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video0FolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video1FolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video2FolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video3FolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video4FolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video5FolderPathSuffix);
		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video6FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video7FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video8FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video9FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video10FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video11FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video12FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video13FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video14FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video15FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video16FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video17FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+ParentFolderPathSuffix+video18FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+video19FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+video20FolderPathSuffix);
//		createDirIfNotAlreadyExist(SdCArdpath(context)+video21FolderPathSuffix);
		
		
		return false ;
	}
	
	public static String getVideoPathForId(Context context,int circleno)
	{
		String ParentFolderpath = SdCArdpath(context)+ParentFolderPathSuffix;
		
		return ParentFolderpath+returnVideoFolderpathForcircle(circleno);
	}
	
	private static String returnVideoFolderpathForcircle(int i)
	{	if(i==0)
		return video0FolderPathSuffix;
		if(i==1)
			return video1FolderPathSuffix;
		if(i==2)
			return video2FolderPathSuffix;
		if(i==3)
			return video3FolderPathSuffix;
		if(i==3)
			return video3FolderPathSuffix;
		if(i==4)
			return video4FolderPathSuffix;
		if(i==5)
			return video5FolderPathSuffix;
		if(i==6)
			return video6FolderPathSuffix;
		if(i==7)
			return video7FolderPathSuffix;
		if(i==8)
			return video8FolderPathSuffix;
		if(i==9)
			return video9FolderPathSuffix;
		if(i==10)
			return video10FolderPathSuffix;
		if(i==11)
			return video11FolderPathSuffix;
		if(i==12)
			return video12FolderPathSuffix;
		if(i==13)
			return video13FolderPathSuffix;
		if(i==14)
			return video14FolderPathSuffix;
		if(i==15)
			return video15FolderPathSuffix;
		if(i==16)
			return video16FolderPathSuffix;
		if(i==17)
			return video17FolderPathSuffix;
		if(i==18)
			return video18FolderPathSuffix;
//		if(i==19)
//			return video19FolderPathSuffix;
//		if(i==20)
//			return video1=20FolderPathSuffix;
		
		return "";
	}
}
