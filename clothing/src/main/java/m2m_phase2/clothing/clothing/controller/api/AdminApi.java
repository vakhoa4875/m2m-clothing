package m2m_phase2.clothing.clothing.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.model.UserM;
import m2m_phase2.clothing.clothing.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
}
