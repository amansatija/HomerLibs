package homemade.apps.framework.homerlibs.activities;

import java.util.ArrayList;

import homemade.apps.homerlibs.R;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * This is an attempt to generalise Base Activities
 * 
 * 
 */
public class FragmentActivityHomerBase extends FragmentActivity {

	private static Boolean mBoolOnPauseCalledByUs = false;

	public static Boolean getOnPauseCalledByUs() {
		return mBoolOnPauseCalledByUs;
	}

	public static void setOnPauseCalledByUs(Boolean mBoolOnPauseCalledByUs) {
		FragmentActivityHomerBase.mBoolOnPauseCalledByUs = mBoolOnPauseCalledByUs;
	}

	public void replaceContainerWithFrag(int intResIdOfContainerToReplace,
			Fragment frag, Boolean FlagAddToBackStack, String tag) {
		try {
			FragmentTransaction t = this.getSupportFragmentManager()
					.beginTransaction();
			t.replace(intResIdOfContainerToReplace, frag);
			if (FlagAddToBackStack != null) {
				if (FlagAddToBackStack)
					t.addToBackStack(tag);
			}
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void replaceContainerWithFrag(int intResIdOfContainerToReplace,
			Fragment frag, Boolean FlagAddToBackStack) {
		try {
			replaceContainerWithFrag(intResIdOfContainerToReplace, frag,
					FlagAddToBackStack, "tag");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		setOnPauseCalledByUs(false);
	}

	public void navigateToActivity(Class ActivityToNavigateTo) {

		navigateToActivity(ActivityToNavigateTo, new ArrayList<Integer>());
	}

	public void navigateToActivity(Class ActivityToNavigateTo,
			ArrayList<Integer> mArrflag) {

		Intent intent = new Intent(this, ActivityToNavigateTo);
		overridePendingTransition(R.anim.hl_anim_slide_from_right_to_left,
				R.anim.hl_anim_slide_from_top_to_bottom);
		try {
			for (int i = 0; i < mArrflag.size(); i++) {
				intent.addFlags(mArrflag.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startActivity(intent);
		setOnPauseCalledByUs(true);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		setOnPauseCalledByUs(true);
		super.startActivityForResult(intent, requestCode);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		setOnPauseCalledByUs(false);

	}

	public void changeActivityAfterDelay(int delay,
			final Class ActivityToNavigateTo) {
		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen
		 * after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				navigateToActivity(ActivityToNavigateTo);

				finish();
			}
		}, delay * 1000);
	}
}
