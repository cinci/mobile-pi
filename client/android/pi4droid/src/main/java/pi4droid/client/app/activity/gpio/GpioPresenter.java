package pi4droid.client.app.activity.gpio;

import android.widget.Switch;
import android.widget.Toast;
import pi4droid.client.app.AsyncConstants;
import pi4droid.client.app.PinConstants;
import pi4droid.client.app.R;
import pi4droid.client.app.RequestConstants;
import pi4droid.client.app.async.AsyncCallback;
import pi4droid.client.app.async.GpioRequestAsyncTask;
import pi4droid.client.app.listener.SwitchChangeListener;

import java.util.Map;

/**
 * GPIO presenter
 * <p/>
 * Created by jc on 23. 6. 2014.
 */
public class GpioPresenter implements AsyncCallback {

    private static final String BASE_URL = "http://192.168.0.112:8080";

    private GpioActivity gpioActivity;
    private Switch[] pinSwitches = new Switch[8];

    public GpioPresenter(GpioActivity gpioActivity) {
        this.gpioActivity = gpioActivity;
    }

    public void onCreate() {
        pinSwitches[0] = ((Switch) gpioActivity.findViewById(R.id.switch0));
        pinSwitches[1] = ((Switch) gpioActivity.findViewById(R.id.switch1));
        pinSwitches[2] = ((Switch) gpioActivity.findViewById(R.id.switch2));
        pinSwitches[3] = ((Switch) gpioActivity.findViewById(R.id.switch3));
        pinSwitches[4] = ((Switch) gpioActivity.findViewById(R.id.switch4));
        pinSwitches[5] = ((Switch) gpioActivity.findViewById(R.id.switch5));
        pinSwitches[6] = ((Switch) gpioActivity.findViewById(R.id.switch6));
        pinSwitches[7] = ((Switch) gpioActivity.findViewById(R.id.switch7));

        for (int i = 0; i < pinSwitches.length; i++) {
            Switch pinSwitch = pinSwitches[i];
            pinSwitch.setOnCheckedChangeListener(new SwitchChangeListener(i, this));
        }
    }

    public void initPinStates() {
        for (int i = 0; i < PinConstants.PINS; i++) {
            GpioRequestAsyncTask task = new GpioRequestAsyncTask(this);
            task.setUrl(BASE_URL + String.format(RequestConstants.GET_PIN_VALUE, i));
            task.setPinNumber(i);
            task.execute();
        }
    }

    public void setPinState(Integer pinNumber, Boolean value) {
        GpioRequestAsyncTask task = new GpioRequestAsyncTask(this);
        task.setUrl(BASE_URL + String.format(RequestConstants.SET_PIN_VALUE, pinNumber, value ? 1 : 0));
        task.setPinNumber(pinNumber);
        task.execute();
    }

    @Override
    public void setAsyncResult(Map<String, String> result) {
        int pinNumber = Integer.parseInt(result.get(AsyncConstants.PIN_NUMBER));
        String response = result.get(AsyncConstants.RESPONSE);

        if (response == null) {
            Toast.makeText(gpioActivity, "Unable to get PIN " + pinNumber + " state", Toast.LENGTH_SHORT).show();

        }
        else {
            pinSwitches[pinNumber].setChecked(response.equals(PinConstants.STATE_HIGH));
        }
    }
}
