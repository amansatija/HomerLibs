package homemade.apps.framework.homerlibs.activities;

import homemade.apps.homerlibs.R;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * This is an attempt to generalise Base Activities
 * 
 * @author Comp1
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
			Fragment frag, Boolean FlagAddToBackStack) {
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		t.replace(intResIdOfContainerToReplace, frag);
		if (FlagAddToBackStack)
			t.addToBackStack("tag");
		t.commit();

	}

	@Override
	protected void onResume() {
		super.onResume();
		setOnPauseCalledByUs(false);
	}

	public void navigateToActivity(Class ActivityToNavigateTo) {

		Intent intent = new Intent(this, ActivityToNavigateTo);
		overridePendingTransition(R.anim.hl_anim_slide_from_right_to_left,
				R.anim.hl_anim_slide_from_top_to_bottom);
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

}
