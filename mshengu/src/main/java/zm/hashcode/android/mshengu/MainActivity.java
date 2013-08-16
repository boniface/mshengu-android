package zm.hashcode.android.mshengu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import zm.hashcode.android.mshengu.listeners.TabListener;
import zm.hashcode.android.mshengu.view.DeploymentFragment;
import zm.hashcode.android.mshengu.view.MapFragment;
import zm.hashcode.android.mshengu.view.ServiceFragment;

public class MainActivity extends SherlockFragmentActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addTabs();
        if (savedInstanceState != null) {
            // restore selected tab index from previous 'session'
            int index = savedInstanceState.getInt("selected_tab_index", 0);
            getSupportActionBar().setSelectedNavigationItem(index);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int index = getSupportActionBar().getSelectedNavigationIndex();
        outState.putInt("selected_tab_index", index);
    }


    private void addTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);

        String currentTitle = getResources().getString(R.string.service);
        ActionBar.Tab currentTab = actionBar.newTab();
        currentTab.setText(currentTitle);
        currentTab.setTabListener(new TabListener(this, currentTitle, ServiceFragment.class));
        actionBar.addTab(currentTab);

        String locationsTitle = getResources().getString(R.string.deployment);
        ActionBar.Tab locationsTab = actionBar.newTab();
        locationsTab.setText(locationsTitle);
        locationsTab.setTabListener(new TabListener(this, locationsTitle, DeploymentFragment.class));
        actionBar.addTab(locationsTab);


        String mapTitle = getResources().getString(R.string.map);
        ActionBar.Tab mapTab = actionBar.newTab();
        mapTab.setText(mapTitle);
        mapTab.setTabListener(new TabListener(this, mapTitle, MapFragment.class));
        actionBar.addTab(mapTab);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            EditText unitId = (EditText) findViewById(R.id.unitID);
            unitId.setText(scanResult.getContents());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("item !!!!!!!!!!!!ID : ", "onOptionsItemSelected Item ID" + id);
        startActivity(new Intent(this, SettingsActivity.class));

        return true;
    }


}

