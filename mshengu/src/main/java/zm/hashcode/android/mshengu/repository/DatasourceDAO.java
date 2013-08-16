package zm.hashcode.android.mshengu.repository;

import zm.hashcode.android.mshengu.model.Settings;
import zm.hashcode.android.mshengu.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/16
 * Time: 7:11 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DatasourceDAO {

    public void createSettings(Settings settings);

    public void updateSettings(Settings settings);

    public Settings findSettingById(int id);

    public void deleteSettings(Settings settings);

    public Settings getSettings();


    public void createUser(User user);

    public void updateUser(User user);

    public User findUserById(int id);

    public void deleteUser(User user);

    public User getUser();


}
