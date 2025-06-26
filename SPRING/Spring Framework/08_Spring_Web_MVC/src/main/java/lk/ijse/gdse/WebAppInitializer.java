package lk.ijse.gdse;

import lk.ijse.gdse.config.WebAppConfig;
import lk.ijse.gdse.config.WebRootConfig;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // add webRootConfiguration class into the context
    @Override
    protected Class<?>  [] getRootConfigClasses() {
        return new Class[]{WebRootConfig.class};
    }

    // add webAppConfiguration class into the context
    @Override
    protected Class<?>  [] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}