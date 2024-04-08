package m2m_phase2.clothing.clothing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
/**
 * cái này là đẩy thư mục media lên web trính tình trạng không có ảnh khi load web
* */
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/media/**")
                .addResourceLocations("classpath:/templates/swappa/assests/media/");

    }
}
