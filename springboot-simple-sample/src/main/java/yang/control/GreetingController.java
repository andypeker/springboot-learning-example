package yang.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yang.svc.Greeting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Frankie Yang on 2019-06-21.
 */
@Controller
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public @ResponseBody
    Greeting greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }


    @RequestMapping("/")
    public @ResponseBody String defaultPage(){
        return "Default Page";
    }

}