package m2m_phase2.clothing.clothing.test;

import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestFuntionLoginAdmin {
	
	@Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AccountRepo accRepo;


    @GetMapping("/test")
	public String testLogin(){
        return "Front_End/testapi";
    }
	
}
