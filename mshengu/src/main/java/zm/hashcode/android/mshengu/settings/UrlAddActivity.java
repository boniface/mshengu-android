package zm.hashcode.android.mshengu.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.SettingsActivity;
import zm.hashcode.android.mshengu.model.Settings;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.repository.Impl.DatasourceDAOImpl;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/16
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class UrlAddActivity extends Activity {
    private EditText serverAddress;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_activity);
        final DatasourceDAO dao = new DatasourceDAOImpl(this);
        serverAddress = (EditText) findViewById(R.id.input_site_address_url);
        Button urlAddButton = (Button) findViewById(R.id.url_add_button);
        urlAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final Settings settings = new Settings();
                settings.setUrl(serverAddress.getText().toString());
                dao.createSettings(settings);
                startActivity(new Intent(UrlAddActivity.this, SettingsActivity.class));
            }
        });

        Button urlCancelButton = (Button) findViewById(R.id.url_cancel);

        urlCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(UrlAddActivity.this, SettingsActivity.class));


            }
        });
    }
}