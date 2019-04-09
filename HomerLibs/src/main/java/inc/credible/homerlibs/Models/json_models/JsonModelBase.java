package inc.credible.homerlibs.Models.json_models;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by aman on 14-05-2015.
 */
public class JsonModelBase {

    private boolean mBoolHasBeenSet = false;

    public boolean getmBoolHasBeenSet() {
        if (mJSONObModelDetails != null) {
            mBoolHasBeenSet = true;
        }
        return mBoolHasBeenSet;
    }

    protected JSONObject mJSONObModelDetails = new JSONObject();

    protected HashMap<String, Object> mHmCachedModelDetails = new HashMap<>();


    public void setmJSONObModelDetails(JSONObject mJSONObModelDetails) {
        mBoolHasBeenSet = true;
        this.mJSONObModelDetails = mJSONObModelDetails;
    }


    public JSONObject getmJSONObModelDetails() {
        return mJSONObModelDetails;
    }

    public JsonModelBase() {

    }

    public JsonModelBase(JSONObject mJSONObArgUSerDetails) {
        setmJSONObModelDetails(mJSONObArgUSerDetails);

    }


    public String fetchParamFromJson(String mStrArgParam) {

        try {
            if (mHmCachedModelDetails.containsKey(mStrArgParam)) {
                return (String) mHmCachedModelDetails.get(mStrArgParam);
            }

            if (!mJSONObModelDetails.isNull(mStrArgParam)) {
                return mJSONObModelDetails.getString(mStrArgParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    public void setParamIntoJson(String key, String value) {

        try {
            mHmCachedModelDetails.put(key, value);
            if (mJSONObModelDetails.has(key)) {
                mJSONObModelDetails.remove(key);
            }
            mJSONObModelDetails.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void restoreFromSavedString(String mStrJsonObUserDetailsSavedString) {

        try {
            JSONObject mJsonOb = new JSONObject(mStrJsonObUserDetailsSavedString);
            setmJSONObModelDetails(mJsonOb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        super.toString();
        return mJSONObModelDetails.toString();
    }
}
