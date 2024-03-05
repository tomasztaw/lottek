/**
 * Created by tomasz_taw
 * Date: 04.03.2024
 * Time: 23:30
 * Project Name: lottek
 * Description:
 */
package pl.taw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
