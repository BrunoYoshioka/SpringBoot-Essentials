package br.com.devdojo.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
// Classe para personalização da paginação
@Configuration
/*public class SpringBootEssentialsAdapter extends WebMvcConfigurerAdapter { // versão do Java 7 */
public class SpringBootEssentialsAdapter implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
        /*phmar.setFallbackPageable(new PageRequest(0,5)); // versão do Java 7 */
        phmar.setFallbackPageable(PageRequest.of(0,5)); // cada paginação terá os dados de 5 em 5
        argumentResolvers.add(phmar);
    }
}
