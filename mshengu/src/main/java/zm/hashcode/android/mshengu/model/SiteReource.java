package zm.hashcode.android.mshengu.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/18
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SiteReource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
