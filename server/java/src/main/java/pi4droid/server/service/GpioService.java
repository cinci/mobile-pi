package pi4droid.server.service;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * GPIO service
 *
 * Created by jc on 28. 5. 2014.
 */
@Service
public class GpioService {

    private static final Map<Pin, GpioPinDigitalOutput> outputPinCache = new HashMap<>();

    private static final GpioController GPIO_CONTROLLER = GpioFactory.getInstance();

    public void setPinValue(Integer pin, Integer pinState) {
        setPinValue(getPin(pin), getPinState(pinState));
    }

    private void setPinValue(Pin pin, PinState pinState) {
        GpioPinDigitalOutput outputPin = provisionOutputPin(pin);
        outputPin.setState(pinState);
    }

    private GpioPinDigitalOutput provisionOutputPin(Pin pin) {
        if (outputPinCache.containsKey(pin)) {
            return outputPinCache.get(pin);
        }
        else {
            final GpioPinDigitalOutput pinConnector = GPIO_CONTROLLER.provisionDigitalOutputPin(pin);
            outputPinCache.put(pin, pinConnector);

            return pinConnector;
        }
    }

    private PinState getPinState(Integer value) {
        if (value == null) {
            return PinState.LOW;
        }
        else {
            return value == 0 ? PinState.LOW : PinState.HIGH;
        }
    }

    private Pin getPin(Integer value) {
        switch (value) {
            case 0:
                return RaspiPin.GPIO_00;
            case 1:
                return RaspiPin.GPIO_01;
            case 2:
                return RaspiPin.GPIO_02;
            case 3:
                return RaspiPin.GPIO_03;
            case 4:
                return RaspiPin.GPIO_04;
            case 5:
                return RaspiPin.GPIO_05;
            case 6:
                return RaspiPin.GPIO_06;
            case 7:
                return RaspiPin.GPIO_07;
            case 8:
                return RaspiPin.GPIO_08;
            case 9:
                return RaspiPin.GPIO_09;
            case 10:
                return RaspiPin.GPIO_10;
            case 11:
                return RaspiPin.GPIO_11;
            case 12:
                return RaspiPin.GPIO_12;
            case 13:
                return RaspiPin.GPIO_13;
            case 14:
                return RaspiPin.GPIO_14;
            case 15:
                return RaspiPin.GPIO_15;
            case 16:
                return RaspiPin.GPIO_16;
            case 17:
                return RaspiPin.GPIO_17;
            case 18:
                return RaspiPin.GPIO_18;
            case 19:
                return RaspiPin.GPIO_19;
            case 20:
                return RaspiPin.GPIO_20;
            default:
                throw new IllegalArgumentException("Unsupported pin number: " + value);
        }
    }
}
