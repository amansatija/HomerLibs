package inc.credible.homerlibs;

import android.content.Context;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by admin on 20-Feb-15.
 */
public class FragmentHomer extends Fragment implements View.OnClickListener {
    public View mVRootView;

    protected ProgressBar mPbHLLoaderProgess;
    protected TextView mTvHLErrMsg;

    public interface OnFragmentUiLoadedListener {
        public void doOnFragmentUiLoaded(FragmentHomer fragment);
    }


    private OnFragmentUiLoadedListener mFragmentUiLoadedListener = new OnFragmentUiLoadedListener() {
        @Override
        public void doOnFragmentUiLoaded(FragmentHomer fragment) {

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
        mPbHLLoaderProgess = (ProgressBar) v.findViewById(R.id.hl_load_n_error_pb);
        mTvHLErrMsg = (TextView) v.findViewById(R.id.hl_load_n_error_tv_msg);
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
            mPbHLLoaderProgess.setVisibility(View.VISIBLE);
            mTvHLErrMsg.setVisibility(View.GONE);
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
            mPbHLLoaderProgess.setVisibility(View.GONE);
            if (mStrMsg != null) {
                mTvHLErrMsg.setText(mStrMsg);
            }
            mTvHLErrMsg.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doOnDataFound() {
        try {
            HomerLogger.d(" data  found  ");
            mPbHLLoaderProgess.setVisibility(View.GONE);
            mTvHLErrMsg.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
