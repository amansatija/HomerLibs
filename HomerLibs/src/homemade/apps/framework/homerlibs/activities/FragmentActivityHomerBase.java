package homemade.apps.framework.homerlibs.activities;

import homemade.apps.homerlibs.R;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentActivityHomerBase extends FragmentActivity {

	public void replaceContainerWithFrag(int intResIdOfContainerToReplace,
			Fragment frag, Boolean FlagAddToBackStack) {
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		t.replace(intResIdOfContainerToReplace, frag);
		if (FlagAddToBackStack)
			t.addToBackStack("tag");
		t.commit();

	}

	public void navigateToActivity(Class ActivityToNavigateTo) {

		Intent intent = new Intent(this, ActivityToNavigateTo);
		overridePendingTransition(R.anim.hl_anim_slide_from_right_to_left,
				R.anim.hl_anim_slide_from_top_to_bottom);
		startActivity(intent);

	}

}
