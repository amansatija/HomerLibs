package inc.credible.homerlibs.demo.base;

import android.view.Menu;

import inc.credible.homerlibs.ActivityHomer;
import inc.credible.homerlibs.demo.R;

public class ActivityBase extends ActivityHomer {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hl_act_nav_menu_of, menu); // hl_nav_menu_of.xml + act_nav_menu_of.xml
        return true;
    }
}
