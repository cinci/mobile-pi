package pi4droid.client.app;

import android.os.AsyncTask;
import android.widget.Switch;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * GPIO presenter
 * <p/>
 * Created by jc on 23. 6. 2014.
 */
public class GpioPresenter {

    private static final int PINS = 8;

    private static final String BASE_URL = "http://192.168.0.112:8080";
    private static final String GET_PIN = "/get/pin/%d";
    private static final String SET_PIN = "/set/pin/%d/value/%d";

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
        for (int i = 0; i < PINS; i++) {
            new RestRequestAsyncTask(String.format(GET_PIN, i), i).execute();
        }
    }

    public void setPinState(Integer pinNumber, Boolean value) {
        new RestRequestAsyncTask(String.format(SET_PIN, pinNumber, value ? 1 : 0), pinNumber).execute();
    }

    private class RestRequestAsyncTask extends AsyncTask<Void, Void, String> {
        private Integer pinNumber;
        private String method;

        private RestRequestAsyncTask(String method, Integer pinNumber) {
            this.method = method;
            this.pinNumber = pinNumber;
        }

        @Override
        protected String doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            return restTemplate.getForObject(BASE_URL + method, String.class);
        }

        @Override
        protected void onPostExecute(String result) {
            pinSwitches[pinNumber].setChecked(result.equals("HIGH"));
        }
    }
}
