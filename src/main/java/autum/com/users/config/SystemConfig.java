package autum.com.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;


@Configuration
public class SystemConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasenames("msg/exception_message");
        resource.setCacheSeconds(3600);
        resource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        resource.setDefaultLocale(Locale.ENGLISH);
        resource.setUseCodeAsDefaultMessage(true);
        return resource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }
}
