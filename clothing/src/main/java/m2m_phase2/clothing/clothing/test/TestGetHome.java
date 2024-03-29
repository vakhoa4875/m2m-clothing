package m2m_phase2.clothing.clothing.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestGetHome {

    @GetMapping("/testapi")
    public String getHome(){
        return "Front_End/testapi";
    }
}
