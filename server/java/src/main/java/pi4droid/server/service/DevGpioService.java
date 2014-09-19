package pi4droid.server.service;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pi4droid.server.Application;

/**
 * GPIO service
 * <p>
 * Created by jc on 28. 5. 2014.
 */
@Service
@Profile(Application.PROFILE_DEV)
public class DevGpioService implements GpioService {

    @Override
    public void setPinValue(Pin pin, PinState pinState) {

    }

    @Override
    public PinState getPinValue(Pin pin) {
        return PinState.LOW;
    }
}
