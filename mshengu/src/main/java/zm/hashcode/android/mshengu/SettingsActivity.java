package zm.hashcode.android.mshengu;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;


/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/15
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends SherlockActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}