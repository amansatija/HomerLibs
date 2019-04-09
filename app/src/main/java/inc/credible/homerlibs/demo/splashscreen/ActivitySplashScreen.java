package inc.credible.homerlibs.demo.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ImageView;
import android.widget.TextView;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import androidx.appcompat.widget.Toolbar;
import inc.credible.homerlibs.demo.ActivityHome;
import inc.credible.homerlibs.demo.R;
import inc.credible.homerlibs.demo.base.ActivityBase;
import inc.credible.homerlibs.demo.login.ActivityLogin;
import inc.credible.homerlibs.session.SessionProvider;


public class ActivitySplashScreen extends ActivityBase {

//    SecretTextView mCvAppName;
    TextView mCvAppName;
    ImageView mIvAppName;

    protected Class mClassLandingPage = ActivityHome.class;
    /**
     * splash screen delay Time in mills
      */
        private int mIntSplashScreenStayTime = 2000;

        private static volatile Boolean mBoolExecuteTasksInOtherThreads = true;
    private Toolbar mToolBar;
    private int mIntDelayToStay = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splashs_l);
        doAfterSetContentView(savedInstanceState);
    }



    @Override
    public void findViewByIds(Bundle savedInstanceState) {
        super.findViewByIds(savedInstanceState);

        //        mCvAppName = (SecretTextView) findViewById(R.id.splashs_l_cv_appName);
        mIvAppName = (ImageView) findViewById(R.id.act_splash_l_iv_logo);
//        mToolBar = (Toolbar)findViewById(R.id.act_splashs_l_toolbar);
    }

    @Override
    public void setUpActionBar(Bundle savedInstanceState) {
        super.setUpActionBar(savedInstanceState);
        try {
//            getSupportActionBar().hide();
//            setSupportActionBar(mToolBar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setListeners(Bundle savedInstanceState) {
        super.setListeners(savedInstanceState);
    }

    private void setUpActivity() {
        setUpAppName();
        setUpLogo();
//        fetchMetaDataFromServer();
        navigateToRequiredPageAfterDelay(mIntDelayToStay);
    }


//    protected void doOnMetaDataFetchSuccess(ModelResponseMeta mModelResponse) {
//
//        navigateToRequiredPageAfterDelay(mIntSplashScreenStayTime);
//    }
//
//
//    protected void doOnMetaDataFetchFailed(ModelResponseMeta mModelResponse) {
//
//        navigateToRequiredPageAfterDelay(mIntSplashScreenStayTime);
//    }
//
//
//    protected void doOnMetaDataFetchAuthFailed() {
//        navigateToRequiredPageAfterDelay(mIntSplashScreenStayTime);
//    }

    private void setUpLogo() {
        YoYo.with(Techniques.Wobble)
                .duration(1000)
                .playOn(mIvAppName);
    }

    private void setUpAppName() {
//        mCvAppName.show();
//        YoYo.with(Techniques.FadeIn)
//                .duration(2000)
//                .playOn(mIvAppName);
    }

    private void navigateToRequiredPageAfterDelay(int mIntDelay) {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mBoolExecuteTasksInOtherThreads) {
                        navigateToRequiredActivity();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, mIntDelay);

    }

    private void navigateToRequiredActivity() {
        try {
//            if (HomerSharedPrefrenceHelper.getPref(this)
//                    .getBoolean(HomerSharedPrefrenceHelper.mStrKeyShowIntro, true)) {
//                //navigate to getStarted onboarding page
//                navigateToActivity(this, ActivityOnBoarding.class);
//            } else
            if (SessionProvider.isUserLoggedIn(this)) {
                navigateToActivity(this,mClassLandingPage);

            } else {
////                //navigate straight to feeds page or login page as per required
                navigateToActivity(this, ActivityLogin.class);
//
            }
            this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBoolExecuteTasksInOtherThreads = true;
        setUpActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBoolExecuteTasksInOtherThreads = false;
    }


}
