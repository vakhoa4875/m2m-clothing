package m2m_phase2.clothing.clothing.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.model.UserM;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api-admin/users")
public class AdminApi {

	final AccountService accountServ;
	final UserService userService;

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> doGetAllUsers() {
		List<?> listUserM = new ArrayList<>();
		try {
			listUserM = accountServ.findAll();
		} catch (Exception e) {
			listUserM = null;
		}
        return ResponseEntity.ok(listUserM);
    }
	@GetMapping("/getUserById")
	public ResponseEntity<?> doGetUserById(@RequestParam("UserID") int id) {
		UserM userM = new UserM();
		try {
			userM = userService.findUserById(id);
		} catch (Exception e) {
			System.out.println("exception found at: doGetUserById");
		}
		return ResponseEntity.ok(userM);
	}
	
	
}
