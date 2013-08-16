package zm.hashcode.android.mshengu.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import zm.hashcode.android.mshengu.model.Settings;
import zm.hashcode.android.mshengu.model.User;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/16
 * Time: 7:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class DatasourceDAOImpl implements DatasourceDAO {

    private SQLiteDatabase database;
    private DBAdapter dbHelper;

    public DatasourceDAOImpl(Context context) {
        dbHelper = new DBAdapter(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    @Override
    public void createSettings(Settings settings) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_URL, settings.getUrl()); // Settings URL
        // Inserting Row
        database.insert(DBAdapter.TABLE_SETTINGS, null, values);
        close(); // Closing database connection
    }

    @Override
    public void updateSettings(Settings settings) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_URL, settings.getUrl());
        // updating row
        database.update(DBAdapter.TABLE_SETTINGS, values, DBAdapter.COLUMN_ID + " = ?",
                new String[]{String.valueOf(settings.getId())});
        close();
    }

    @Override
    public Settings findSettingById(int id) {
        open();
        Cursor cursor = database.query(DBAdapter.TABLE_SETTINGS, new String[]{DBAdapter.COLUMN_ID,
                DBAdapter.COLUMN_URL}, DBAdapter.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Settings settings = new Settings();
        settings.setId(cursor.getInt(0));
        settings.setUrl(cursor.getString(1));
        close();
        return settings;
    }

    @Override
    public void deleteSettings(Settings settings) {
        open();
        database.delete(DBAdapter.TABLE_SETTINGS, DBAdapter.COLUMN_ID + " = ?", new String[]{String.valueOf(settings.getId())});

        close();
    }

    @Override
    public Settings getSettings() {
        String selectQuery = "SELECT  * FROM " + DBAdapter.TABLE_SETTINGS;
        final Settings settings = new Settings();
        open();
        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                settings.setId(cursor.getInt(0));
                settings.setUrl(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        close();
        return settings;
    }

    @Override

    public void createUser(User user) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_EMAIL, user.getEmail()); // Settings URL
        values.put(DBAdapter.COLUMN_AUTH, user.getAuth()); // Settings URL
        // Inserting Row
        database.insert(DBAdapter.TABLE_MSHENGU_USER, null, values);
        close(); // Closing database connection
    }

    @Override
    public void updateUser(User user) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_EMAIL, user.getEmail()); // Settings URL
        values.put(DBAdapter.COLUMN_AUTH, user.getAuth()); // Settings URL
        // updating row
        database.update(DBAdapter.TABLE_MSHENGU_USER, values, DBAdapter.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        close();
    }

    @Override
    public User findUserById(int id) {
        open();
        Cursor cursor = database.query(DBAdapter.TABLE_MSHENGU_USER, new String[]{DBAdapter.COLUMN_ID,
                DBAdapter.COLUMN_EMAIL, DBAdapter.COLUMN_AUTH}, DBAdapter.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setEmail(cursor.getString(1));
        user.setAuth(cursor.getString(2));
        close();
        return user;
    }

    @Override
    public void deleteUser(User user) {
        open();
        database.delete(DBAdapter.TABLE_MSHENGU_USER, DBAdapter.COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});

        close();
    }

    @Override
    public User getUser() {
        String selectQuery = "SELECT  * FROM " + DBAdapter.TABLE_MSHENGU_USER;
        final User user = new User();
        open();
        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
                user.setAuth(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        close();
        return user;
    }
}
