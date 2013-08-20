package zm.hashcode.android.mshengu;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import zm.hashcode.android.mshengu.model.Settings;
import zm.hashcode.android.mshengu.model.User;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.repository.Impl.DatasourceDAOImpl;
import zm.hashcode.android.mshengu.settings.UrlAddActivity;
import zm.hashcode.android.mshengu.settings.UserActivity;


/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/15
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends SherlockActivity {
    private TextView serverAddress;
    private TextView userEmail;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final DatasourceDAO dao = new DatasourceDAOImpl(this);
        Button buttonSettings = (Button) findViewById(R.id.button_settings);


        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, UrlAddActivity.class));
            }
        });

        Button buttonUser = (Button) findViewById(R.id.button_user);


        buttonUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, UserActivity.class));


            }
        });

        Button buttonExitSettings = (Button) findViewById(R.id.button_exit_settings);


        buttonExitSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));


            }
        });


        serverAddress = (TextView) findViewById(R.id.server_address);
        Settings settings = dao.getSettings();
        serverAddress.setText(settings.getUrl());

        User user = dao.getUser();
        userEmail = (TextView) findViewById(R.id.user_email);
        userEmail.setText(user.getEmail());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

}