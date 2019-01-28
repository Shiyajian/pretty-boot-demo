package github.shiyajian.pretty.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author shiyajian
 * create: 2018-12-27
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static Object getBean(Class clazz) {
        return ctx.getBean(clazz);
    }
}
