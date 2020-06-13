package inc.credible.homerlibs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import inc.credible.homerlibs.session.SessionProvider;


/**
 * Created by amansatija on 5/3/16.
 */
public class HomerActivity extends AppCompatActivity implements View.OnClickListener{
    protected ProgressBar mPbLoaderProgess;
    protected TextView mTvErrMsg;
    protected ImageView mIvErrImg;
    protected LinearLayout mLlErrWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * call this function after setContentView(R.layout.yourActivityLayout) from your child activity
     * so that there is a general guidelines as to what to do when.
     * this function calls
     * <p/>
     * setUpActionBar();
     * findViewByIds();
     * setListeners();
     * setUpActivity();
     * <p/>
     * in the given order you can do the appropriate functions by ovverridding these mini functions
     * this is a an attempt tp generalise code placement so as to minimise crashes
     * and to bring order to choas ..
     * Peace v :) ..
     * Because everybody like clean code ,,,
     * @param savedInstanceState
     */
    public void doAfterSetContentView(Bundle savedInstanceState) {
        findViewByIds(savedInstanceState);
        setUpActionBar(savedInstanceState);
        setListeners(savedInstanceState);
        setUpActivity(savedInstanceState);
    }

    public void setUpActionBar(Bundle savedInstanceState) {
    }

    public void findViewByIds(Bundle savedInstanceState) {
        mPbLoaderProgess = (ProgressBar)findViewById(R.id.hl_load_n_error_pb);
        mTvErrMsg= (TextView) findViewById(R.id.hl_load_n_error_tv_msg);
        mLlErrWrapper = (LinearLayout)findViewById(R.id.hl_load_n_error_ll_centerWrapper);
        mIvErrImg= (ImageView) findViewById(R.id.hl_load_n_error_iv_img);
    }

    public void setListeners(Bundle savedInstanceState) {

    }

    public void setUpActivity(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.anim_activity_open_previous, R.anim.anim_activity_close_next);
    }


    /**
     * Always Call this function whenever you wish to start a new activity
     * this way you have a handle on when onPause is called by us and when it has been called by the system
     * <p/>
     * Also this consists of overriding activity pending transition so
     * activity transition animation is maintained via this function
     *
     * @param context
     * @param pageToNavigateTo
     */

    public void navigateToActivity(Context context, Class pageToNavigateTo) {
        try {
            navigateToActivity(context, pageToNavigateTo, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Always Call this function whenever you wish to start a new activity
     * this way you have a handle on when onPause is called by us and when it has been called by the system
     * <p/>
     * Also this consists of overriding activity pending transition so
     * activity transition animation is maintained via this function
     *
     * @param context
     * @param pageToNavigateTo
     */

    public void navigateToActivity(Context context, Class pageToNavigateTo, Integer IntentFlag) {
        try {
            Intent intent = new Intent(context, pageToNavigateTo);
            if (IntentFlag != null) {
                intent.setFlags(IntentFlag);
            }
//            overridePendingTransition(R.anim.anim_activity_open_next, R.anim.anim_activity_close_previous);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Always Call this function whenever you wish to start a new activity
     * this way you have a handle on when onPause is called by us and when it has been called by the system
     * <p/>
     * Also this consists of overriding activity pending transition so
     * activity transition animation is maintained via this function
     *
     * @param context
     * @param intent
     */

    public void navigateToActivity(Context context, Intent intent) {
        try {

//            overridePendingTransition(R.anim.anim_activity_open_next, R.anim.anim_activity_close_previous);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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




    public void doOnDataNotFound(){
        doOnDataNotFound(null);
    }
    private void doOnNoDataAvail(){
        doOnDataNotFound(getString(R.string.hl_generic_err_msg));
    }

    public void doOnDataNotFound(String mStrMsg) {
        try {
            HomerLogger.d("history data not found  ");
            mPbLoaderProgess.setVisibility(View.GONE);
            if(mStrMsg!=null){
                mTvErrMsg.setText(mStrMsg);
            }
            showError();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOnDataFound() {
        try {
            HomerLogger.d("history data not found  ");
            mPbLoaderProgess.setVisibility(View.GONE);
            hideError();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showLoader(){
        try {
            HomerLogger.d("show loader   ");
            mPbLoaderProgess.setVisibility(View.VISIBLE);
            hideError();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideLoader(){
        HomerLogger.d("show loader   ");
        try {
            mPbLoaderProgess.setVisibility(View.GONE);
            hideError();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showError(){
        showError(true);
    }
    public void showError(Boolean mArgBoolShowErrImage){
        try {
            mTvErrMsg.setVisibility(View.VISIBLE);
            mLlErrWrapper.setVisibility(View.VISIBLE);
            if(mArgBoolShowErrImage){
                mIvErrImg.setVisibility(View.VISIBLE);
            }else{
                mIvErrImg.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hideError(){
        hideError(true);
    }

    public void hideError(Boolean mArgBoolHideErrImage){
        try {
            mTvErrMsg.setVisibility(View.GONE);
            mLlErrWrapper.setVisibility(View.GONE);
            if(mArgBoolHideErrImage){
                mIvErrImg.setVisibility(View.GONE);
            }else{
                mIvErrImg.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


//        if (id == R.id.action_settings) {
//            return true;
//        }
         else if (id == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
    protected void logout() {
        SessionProvider.logUserOut(this);
        finish();
    }


    public void doOnOfRefreshClicked(MenuItem mMenuItem){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate menu from xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hl_act_nav_menu_of, menu);
        return true;
    }

}
