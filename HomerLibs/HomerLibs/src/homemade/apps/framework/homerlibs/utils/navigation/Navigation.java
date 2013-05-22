package homemade.apps.framework.homerlibs.utils.navigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class Navigation {


	public static void pubReplaceContainerWithFrag(Context context,int intResIdOfContainerToReplace,
			Fragment frag, Boolean FlagAddToBackStack) {
		FragmentTransaction t = null;
		try {
			t = ((FragmentActivity) context).getSupportFragmentManager()
					.beginTransaction();
			t.replace(intResIdOfContainerToReplace, frag);
			t.commit();
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
	}
	
}
