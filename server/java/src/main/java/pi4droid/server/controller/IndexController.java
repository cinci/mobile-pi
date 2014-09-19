package pi4droid.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GPIO index controller (only help)
 * <p>
 * Created by jc on 14. 5. 2014.
 */
@RestController
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String index() {

        return "GPIO controller - use /static/index.html for example";
    }
}
