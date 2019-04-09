package inc.credible.homerlibs;

import android.os.Bundle;

/**
 * Created by aman on 28-05-2015.
 */
public interface HomerIDataProvider {

    public void onSavedInstance(Bundle outState);

    public void restoreFromSavedInstance(Bundle outState);

}
