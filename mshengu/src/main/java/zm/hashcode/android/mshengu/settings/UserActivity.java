package zm.hashcode.android.mshengu.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.SettingsActivity;
import zm.hashcode.android.mshengu.model.User;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.repository.Impl.DatasourceDAOImpl;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/16
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserActivity extends Activity {
    private EditText emailAddress;
    private EditText authkey;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        final DatasourceDAO dao = new DatasourceDAOImpl(this);
        Button userAddButton = (Button) findViewById(R.id.user_add_button);
        emailAddress = (EditText) findViewById(R.id.input_user_email);
        authkey = (EditText) findViewById(R.id.input_user_auth);
        userAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                User user = new User();
                user.setAuth(authkey.getText().toString());
                user.setEmail(emailAddress.getText().toString());
                dao.createUser(user);
                startActivity(new Intent(UserActivity.this, SettingsActivity.class));
            }
        });

        Button urlCancelButton = (Button) findViewById(R.id.url_cancel);

        urlCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, SettingsActivity.class));


            }
        });
    }
}