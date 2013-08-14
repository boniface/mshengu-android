package zm.hashcode.android.mshengu.test;

import android.test.ActivityInstrumentationTestCase2;
import zm.hashcode.android.mshengu.*;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public HelloAndroidActivityTest() {
        super(MainActivity.class);
    }

    public void testActivity() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
}

