package pi4droid.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pi4droid.server.service.GpioService;

/**
 * GPIO output pin controller
 *
 * Created by jc on 14. 5. 2014.
 */
@RestController
public class OutputPinController extends AbstractController {

    private static final Logger log = Logger.getLogger(OutputPinController.class);

    @Autowired
    private GpioService gpioService;

    @RequestMapping(value = {"/pin/{pin}/value/{value}"})
    public String setOutputPinValue(@PathVariable Integer pin, @PathVariable Integer value) {
        log.info("Setting PIN: " + pin + " to value: " + value);

        gpioService.setPinValue(pin, value);

        return "Setting PIN: " + pin + " to value: " + value;
    }
}
