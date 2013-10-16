package homemade.apps.framework.homerlibs.utils.download;

public class DownloadResponse {

	private String debugtag = "DownloadResponse";

	private String mStrResponse = "";

	public String getResponse() {
		return mStrResponse;
	}

	public void setResponse(String mStrResponse) {
		this.mStrResponse = mStrResponse;
	}

	public DownloadResponse(String response, String video_path) {
		this.mStrResponse = response;

	}

}