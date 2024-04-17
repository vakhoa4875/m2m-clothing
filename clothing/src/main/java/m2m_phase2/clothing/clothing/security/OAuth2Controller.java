//package m2m_phase2.clothing.clothing.security;
//
//
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.Map;
//
//public class OAuth2Controller {
//
//
//    @GetMapping("/oauth2/authorization/google")
//    public void startOAuth2Authorization() {
//        OAuth2AuthorizationRequest authorizationRequest = OAuth2AuthorizationRequest
//                .authorizationCode()
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .clientId("321877704749-2v71bd2jvjvhg1qs8ntnlokcbv3qb4el.apps.googleusercontent.com")
//                .redirectUri(redirectUri())
//                .scope("openid", "profile", "email")
//                .state("your-state")
//                .build();
//
//        URI authorizationUri = URI.create(authorizationRequest.getAuthorizationRequestUri());
//        System.out.println("Redirect to: " + authorizationUri);
//        // Redirect người dùng đến Google OAuth2
//        // Thường thì bạn sẽ thực hiện redirect ở phía client (trình duyệt)
//        // Trong ví dụ này, tôi không thực hiện redirect mà chỉ in ra URI để minh họa
//    }
//
//    @GetMapping("/login/oauth2/code/google")
//    public void handleAuthorizationResponse(
//            @RequestParam("code") String code,
//            OAuth2AuthenticationToken authenticationToken)
//    {
//        OAuth2User oauth2User = authenticationToken.getPrincipal();
//        String accessToken = authenticationToken.getCredentials().toString();
//
//        // You can use the accessToken to make requests to Google APIs on behalf of the user
//        // For example, you can get user info or access other resources
//
//        System.out.println("Access Token: " + accessToken);
//    }
//
//    private String redirectUri() {
//        return ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("http://localhost:8083/trangchu")
//                .build()
//                .toUriString();
//    }
//}
