package pi4droid.server.service;

import com.pi4j.io.gpio.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pi4droid.server.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * GPIO service
 * <p>
 * Created by jc on 28. 5. 2014.
 */
@Service
@Profile(Application.PROFILE_PI)
public class PiGpioService implements GpioService {

    private static final Map<Pin, GpioPinDigitalOutput> outputPinCache = new HashMap<>();

    private static final GpioController GPIO_CONTROLLER = GpioFactory.getInstance();

    @Override
    public void setPinValue(Pin pin, PinState pinState) {
        GpioPinDigitalOutput outputPin = getOutputPin(pin);
        outputPin.setState(pinState);
    }

    @Override
    public PinState getPinValue(Pin pin) {
        GpioPinDigitalOutput outputPin = getOutputPin(pin);
        return outputPin.getState();
    }

    private GpioPinDigitalOutput getOutputPin(Pin pin) {
        if (outputPinCache.containsKey(pin)) {
            return outputPinCache.get(pin);
        }
        else {
            final GpioPinDigitalOutput pinConnector = GPIO_CONTROLLER.provisionDigitalOutputPin(pin);
            outputPinCache.put(pin, pinConnector);

            return pinConnector;
        }
    }
}
