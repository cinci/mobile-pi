package pi4droid.server.service;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/**
 * Interface for GPIO service
 * <p>
 * Created by jc on 18. 9. 2014.
 */
public interface GpioService {

    public void setPinValue(Pin pin, PinState pinState);

    public PinState getPinValue(Pin pin);
}
