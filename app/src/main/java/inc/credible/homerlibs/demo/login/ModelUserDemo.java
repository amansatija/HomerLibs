package inc.credible.homerlibs.demo.login;

import inc.credible.homerlibs.Models.json_models.ModelUser;

public class ModelUserDemo extends ModelUser {

    public static final String mStrKeyEmailId = "email_address_demo";

    public String getmEmailId() {
        return fetchParamFromJson(mStrKeyEmailId);
    }

    public void setmEmailId(String mEmailId) {
        setParamIntoJson(mStrKeyEmailId, mEmailId);
    }
}
