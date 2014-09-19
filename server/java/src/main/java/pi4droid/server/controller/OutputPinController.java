package pi4droid.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pi4droid.server.service.GpioService;
import pi4droid.server.util.PinMapper;

/**
 * GPIO output pin controller
 * <p>
 * Created by jc on 14. 5. 2014.
 */
@RestController
public class OutputPinController {

    private static final Logger log = Logger.getLogger(OutputPinController.class);

    @Autowired
    private GpioService gpioService;

    @RequestMapping(value = "/set/pin/{pin}/value/{value}")
    public String setOutputPinValue(@PathVariable Integer pin, @PathVariable Integer value) {
        log.info("Setting PIN: " + pin + " to value: " + value);

        gpioService.setPinValue(PinMapper.getPin(pin), PinMapper.getPinState(value));

        return getOutpupPinValue(pin);
    }

    @RequestMapping(value = "/get/pin/{pin}")
    public String getOutpupPinValue(@PathVariable Integer pin) {
        log.info("Getting value of PIN: " + pin);

        return gpioService.getPinValue(PinMapper.getPin(pin)).toString();
    }
}
