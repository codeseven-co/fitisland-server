package happyperson.fitisland.domain.oauthjwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .exposedHeaders("Set-Cookie", "Authentication") // 반환하는 헤더 값 지정
                .allowedOrigins("http://localhost:3000", "https://fitisland-client.vercel.app")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE") // 모든 메서드 허용
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 인증 정보 허용
    }
}
