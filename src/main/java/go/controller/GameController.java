package go.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by moby on 05/04/15.
 */
@RestController
public class GameController {

    @RequestMapping("/")
    public String index() {
        return "Hello";
    }
}
