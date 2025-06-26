package lk.ijse.gdse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBean2 implements DisposableBean, InitializingBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {
    public SpringBean2() {
        System.out.println("Spring Bean2 Constructor Called");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory Called");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName Called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy Called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet Called");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println( "setApplicationContext Called");
    }
}
