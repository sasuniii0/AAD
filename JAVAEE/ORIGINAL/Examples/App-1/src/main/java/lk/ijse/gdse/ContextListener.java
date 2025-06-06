package lk.ijse.gdse;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialized");

        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bs.setUrl("jdbc:mysql://localhost:3306/eventdb");
        bs.setUsername("root");
        bs.setPassword("Ijse@1234");
        bs.setInitialSize(50);
        bs.setMaxTotal(100);

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("DataSource", bs);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try{
            ServletContext sc = sce.getServletContext();
            BasicDataSource bs = (BasicDataSource) sc.getAttribute("dbcp");
            bs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
