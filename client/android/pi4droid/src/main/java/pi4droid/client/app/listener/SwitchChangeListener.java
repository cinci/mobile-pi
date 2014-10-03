package pi4droid.client.app.listener;

import android.widget.CompoundButton;
import pi4droid.client.app.activity.gpio.GpioPresenter;

/**
 * Switch change listener
 * <p/>
 * Created by jc on 25. 6. 2014.
 */
public class SwitchChangeListener implements CompoundButton.OnCheckedChangeListener {
    private Integer pinNumber;
    private GpioPresenter gpioPresenter;

    public SwitchChangeListener(Integer pinNumber, GpioPresenter gpioPresenter) {
        this.pinNumber = pinNumber;
        this.gpioPresenter = gpioPresenter;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        gpioPresenter.setPinState(pinNumber, isChecked);
    }
}
