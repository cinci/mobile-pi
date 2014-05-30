package pi4droid.server.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GPIO index controller (only help)
 *
 * Created by jc on 14. 5. 2014.
 */
@RestController
public class IndexController extends AbstractController {

    private static final Logger log = Logger.getLogger(IndexController.class);

    @RequestMapping(value = {"/", "/index"})
    public String index() {

        return "help: " +
                "/pin/{pinNumber}/value/{pinValue}";
    }
}
