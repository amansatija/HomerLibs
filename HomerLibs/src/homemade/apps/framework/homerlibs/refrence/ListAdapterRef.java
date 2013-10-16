package homemade.apps.framework.homerlibs.refrence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapterRef extends ArrayAdapter<String> {

	public ListAdapterRef(Context context) {
		super(context, 0);

	}

	@Override
	public int getCount() {
		return super.getCount();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {

		}

		return convertView;
	}

}