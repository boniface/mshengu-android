package zm.hashcode.android.mshengu.model;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/16
 * Time: 7:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int id;
    private String email;
    private String auth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
