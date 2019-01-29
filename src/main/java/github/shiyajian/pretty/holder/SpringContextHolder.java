package github.shiyajian.pretty.holder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * @author shiyajian
 * create: 2018-12-27
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static Object getBean(@Nonnull Class clazz) {
        return ctx.getBean(clazz);
    }
}
