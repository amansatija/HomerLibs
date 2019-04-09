package inc.credible.homerlibs.Models.json_models;

import org.json.JSONObject;

/**
 * Created by aman on 06-05-2015.
 */
public class ModelUser extends JsonModelBase {

    public static final String mStrKeyId = "id";
    public static final String mStrKeyEmailId = "email_address";
    public static final String mStrKeyFirstName = "first_name";
    public static final String mStrKeylastName = "last_name";
    public static final String mStrKeyUserName = "user_name";
    public static final String mStrKeyGender = "gender";
    public static final String mStrKeyToken = "token";

    public static final String mStrValueGenderMale = "Male";
    public static final String mStrValueGenderFemale = "Female";
    public static final String mStrKeyMobile = "mobile";

    /**
     * {
     * "id": "31",
     * "email_address": "satija.aman@gmail.com",
     * "password": null,
     * "salt": null,
     * "first_name": "aman",
     * "last_name": null,
     * "profile_pic": null,
     * "current_location": null,
     * "dob": null,
     * "gender": null,
     * "role": "normal",
     * "login_via": "AW",
     * "status": "active",
     * "email_verify": "false",
     * "register_date": "2015-05-02 15:27:53",
     * "last_login_date": "2015-05-02 15:27:53",
     * "mobile":"1234567890"
     * }
     */
    public ModelUser() {

    }

    public ModelUser(String mStrJSONObArgUSerDetails) {
        restoreFromSavedString(mStrJSONObArgUSerDetails);
    }

    public ModelUser(JSONObject mJSONObArgUSerDetails) {
        setmJSONObModelDetails(mJSONObArgUSerDetails);
    }

    public String getmUserId() {
        return fetchParamFromJson(mStrKeyId);
    }

    public void setmUserId(String userId) {
        setParamIntoJson(mStrKeyId, userId);
    }

    public String getmEmailId() {
        return fetchParamFromJson(mStrKeyEmailId);
    }


    public String getmFirstName() {
        return fetchParamFromJson(mStrKeyFirstName);
    }


    public void setmFirstName(String mFirstName) {
        setParamIntoJson(mStrKeyFirstName, mFirstName);
    }

    public String getmLastName() {
        return fetchParamFromJson(mStrKeylastName);
    }


    public void setmLastName(String mStrLastName) {
        setParamIntoJson(mStrKeylastName, mStrLastName);
    }

    public String getmUserName() {
        return fetchParamFromJson(mStrKeyUserName);
    }

    public String getGender() {
        return fetchParamFromJson(mStrKeyGender);
    }

    public void setmGender(String gender) {
        setParamIntoJson(mStrKeyGender, gender.toLowerCase());
    }

    public void setmEmailId(String email) {
        setParamIntoJson(mStrKeyEmailId, email);
    }

    public String getmMobile() {
        return fetchParamFromJson(mStrKeyMobile);
    }

    public void setmMobile(String mobile) {
        setParamIntoJson(mStrKeyMobile, mobile);
    }

    public String getmToken() {
        return fetchParamFromJson(mStrKeyToken);
    }

    public void setmToken(String token) {
        setParamIntoJson(mStrKeyToken, token);
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

        return super.toString();
    }


}
