package lk.ijse.gdse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyConnection implements DisposableBean , BeanNameAware , BeanFactoryAware , ApplicationContextAware , InitializingBean {

    // This is a custom bean that simulates a connection object
    // the state of instantiation..
    public MyConnection() {
        System.out.println("MyConnection");
    }

    // there is no method to find the state of populate properties...

    // This method is called by the Spring container to set the name of the bean
    // the state of set the bean id ==> bean aware
    @Override
    public void setBeanName(String name) {
        System.out.println("MyConnection bean name is: " + name);
    }

    // This method is called by the Spring container to set the BeanFactory
    // the state of set the bean factory ==> bean Factory aware
    //The state of adding the dependency injection...
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyConnection bean factory is: " + beanFactory);
    }

    // This method is called by the Spring container to set the ApplicationContext
    // the state of set the application context ==> application context aware
    //AOP & DP
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyConnection application context is: " + applicationContext);
    }

    // This method is called by the Spring container after the bean properties have been set
    // the state of after properties set ==> initializing bean
    // get the initialized bean from the application context
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyConnection is initialized");
    }

    // This method is called by the Spring container before the bean is destroyed
    // the state of before destroy ==> disposable bean
    @Override
    public void destroy() throws Exception {
        System.out.println("MyConnection is destroy");
    }



}
