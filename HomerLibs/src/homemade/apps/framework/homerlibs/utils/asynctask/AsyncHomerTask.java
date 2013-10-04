package homemade.apps.framework.homerlibs.utils.asynctask;

import android.os.AsyncTask;

public class AsyncHomerTask extends AsyncTask<Void, Void, Void> {
	// /////////////////////////////////////////////////////////////////
	// ////////////Variable Declarations/////////////////////////////
	// //////////////////////////////////////////////////////////////
	public static enum TaskState {
		Starting, Running, Completed, NONE
	}

	private TaskState mCurrStateOfTheTask = TaskState.NONE;

	public TaskState getCurrStateOfTheTask() {
		return mCurrStateOfTheTask;
	}

	public void setCurrStateOfTheTask(TaskState mCurrStateOfTheTask) {
		this.mCurrStateOfTheTask = mCurrStateOfTheTask;
	}

	// /////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////
	// ////////Class Begins/////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////

	@Override
	protected void onPreExecute() {
		setCurrStateOfTheTask(TaskState.Starting);
	}

	@Override
	protected Void doInBackground(Void... params) {
		setCurrStateOfTheTask(TaskState.Starting);
		return null;
	}

	@Override
	protected void onPostExecute(Void notUsed) {
		setCurrStateOfTheTask(TaskState.Starting);
	}

}
