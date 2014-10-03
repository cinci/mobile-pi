package pi4droid.client.app.activity.gpio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import pi4droid.client.app.R;

/**
 * Main activity
 * <p/>
 * Created by jc on 25. 6. 2014.
 */
public class GpioActivity extends Activity {

    private GpioPresenter gpioPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpioPresenter = new GpioPresenter(this);
        gpioPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gpioPresenter.initPinStates();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
