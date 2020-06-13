package inc.credible.homerlibs;

import android.content.Context;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * Created by admin on 20-Feb-15.
 */
public class HomerFragment extends Fragment implements View.OnClickListener {
    public View mVRootView;

    protected ProgressBar mPbLoaderProgess;
    protected TextView mTvErrMsg;

    public interface OnFragmentUiLoadedListener {
        public void doOnFragmentUiLoaded(HomerFragment fragment);
    }


    private OnFragmentUiLoadedListener mFragmentUiLoadedListener = new OnFragmentUiLoadedListener() {
        @Override
        public void doOnFragmentUiLoaded(HomerFragment fragment) {

        }
    };

    public OnFragmentUiLoadedListener getmFragmentUiLoadedListener() {
        return mFragmentUiLoadedListener;
    }

    public void setmFragmentUiLoadedListener(OnFragmentUiLoadedListener mFragmentUiLoadedListener) {
        this.mFragmentUiLoadedListener = mFragmentUiLoadedListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentUiLoadedListener) {
            this.mFragmentUiLoadedListener = (OnFragmentUiLoadedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentUiLoadedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mFragmentUiLoadedListener =null;
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * call this function after onCreateView from your child fragment
     * so that there is a general guidelines as to what to do when.
     * this function calls
     * <p/>
     * findViewByIds(v);
     * setListeners(v);
     * mVRootView = v;
     * setUpFrag(v);
     * <p/>
     * in the given order you can do the appropriate functions by overridding these mini functions
     * this is a an attempt tp generalise code placement so as to minimise crashes
     * and to bring order to choas ..
     * Peace v :) ..
     * Because everybody like clean code ,,,
     *
     * @param v: View of the frag ..
     */
    public void doAfterOnCreateView(View v) {

        findViewByIds(v);
        setListeners(v);
        mVRootView = v;

        setUpFrag(v);

    }

    public void findViewByIds(View v) {
        mPbLoaderProgess = (ProgressBar) v.findViewById(R.id.hl_load_n_error_pb);
        mTvErrMsg = (TextView) v.findViewById(R.id.hl_load_n_error_tv_msg);
    }

    public void setListeners(View v) {

    }

    public void setUpFrag(View v) {

        getmFragmentUiLoadedListener().doOnFragmentUiLoaded(this);
    }

    public void stopAllWork(){

    }

    public void showLoader(){
        try {
            HomerLogger.d("show loader");
            mPbLoaderProgess.setVisibility(View.VISIBLE);
            mTvErrMsg.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doOnNoDataAvail(int mResIdOfStrMsg) {
        try {
            doOnDataNotFound(getString(mResIdOfStrMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doOnNoDataAvail(String mStrMsg) {
        try {
            HomerLogger.d(" data  not avail");
            doOnDataNotFound(mStrMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doOnDataNotFound() {
        doOnDataNotFound(null);
    }

    protected void doOnDataNotFound(String mStrMsg) {
        try {
            HomerLogger.d(" data not found  ");
            mPbLoaderProgess.setVisibility(View.GONE);
            if (mStrMsg != null) {
                mTvErrMsg.setText(mStrMsg);
            }
            mTvErrMsg.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doOnDataFound() {
        try {
            HomerLogger.d(" data  found  ");
            mPbLoaderProgess.setVisibility(View.GONE);
            mTvErrMsg.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
