package m2m_phase2.clothing.clothing.security;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import m2m_phase2.clothing.clothing.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;
    private boolean isRedirectSent = false;
    private boolean isUserExist(String email) {
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        try {
            return userService.getUserByEmail(userDto) != null;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Object source = event.getSource();
        String eventType = source.getClass().getSimpleName();
        if (eventType.equals("OAuth2AuthenticationToken") || eventType.equals("OAuth2LoginAuthenticationToken")) {
            Map<String, Object> attributes = ((OAuth2LoginAuthenticationToken) event.getSource()).getPrincipal().getAttributes();
            String email  = (String) attributes.get("email");
            if(!isUserExist(email)){
                try {
                    saveAccGG(attributes);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            httpSession.setAttribute("loggedInUser", email);
        } else {
            System.out.println("Không phải là OAuth2AuthenticationToken hoặc OAuth2LoginAuthenticationToken");
        }

    }
    private void saveAccGG(Map<String, Object> attributes) throws SQLException {
        UserE user = new UserE();
        PasswordEncoderUtil passwordEncoder = new PasswordEncoderUtil();
        String password = PasswordUtils.generateRandomPassword(10);
        String encodedPassword = passwordEncoder.encodePassword(password);
            user.setFullname((String) attributes.get("name"));
            user.setEmail((String) attributes.get("email"));
            user.setGgToken((String) attributes.get("sub"));
            user.setUsername((String) attributes.get("sub"));
            user.setHashedPassword(encodedPassword);
        userService.saveUserGG(user);
    }
}
