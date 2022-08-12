package project.AMS;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //반드시 필요
public class webConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheck())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/kakaoLogin", "/member/join", "/member/logout", "/member/login", "/css/**" ,"/*.ico","/error");
//
//    }


}
