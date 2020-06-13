package inc.credible.homerlibs;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


/**
 * Created by amansatija on 5/3/16.
 */
public class HomerAcivityLifeCycleHandler implements Application.ActivityLifecycleCallbacks {
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;


    public static int getOpenedActivitesCount() {
        HomerLogger.i("getActivites count == " + started);
        return started;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        android.util.Log.w("test", "application is in foreground: " + (resumed > paused));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
        if (started == 1) {
            doOnAppOpened();
        }
    }

    private void doOnAppOpened() {

    }


    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;

        android.util.Log.w("test", "application is visible: " + (started > stopped));
        if (started <= stopped) {

            doOnAppClosed();
            started = 0;
            stopped = 0;

        }
    }

    private void doOnAppClosed() {

    }

}