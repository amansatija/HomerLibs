package inc.credible.homerlibs.demo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import inc.credible.homerlibs.demo.base.ActivityBase;
import inc.credible.homerlibs.session.SessionProvider;

import android.view.View;

public class ActivityHome extends ActivityBase {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home_l);
        doAfterSetContentView(savedInstanceState);




//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    @Override
    public void findViewByIds(Bundle savedInstanceState) {
        super.findViewByIds(savedInstanceState);
        mToolbar = findViewById(inc.credible.homerlibs.R.id.toolbar);
    }

    @Override
    public void setUpActionBar(Bundle savedInstanceState) {
        super.setUpActionBar(savedInstanceState);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void setUpActivity(Bundle savedInstanceState) {
        super.setUpActivity(savedInstanceState);
        setUpTextView();

    }

    private void setUpTextView() {
        AppCompatTextView mTvText = findViewById(R.id.act_home_content_l_tv_text);

        mTvText.setText(SessionProvider.getmUser(this).getmEmailId());
    }
}
