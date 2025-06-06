package lk.ijse.gdse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/newevent")
public class InBuildDBCP extends HttpServlet {
    @Resource(name ="java:comp/env/jdbc/pool")
    private DataSource ds;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Connection connection = ds.getConnection();
            ResultSet rst = connection.prepareStatement("SELECT * FROM event").executeQuery();

            List<Map<String, String>> events = new ArrayList<>();

            while (rst.next()){
                while (rst.next()) {
                    Map<String, String> event = new HashMap<>();
                    event.put("eid", rst.getString("eid"));
                    event.put("ename", rst.getString("ename"));
                    event.put("edescription", rst.getString("edescription"));
                    event.put("edate", rst.getString("edate"));
                    event.put("eplace", rst.getString("eplace"));
                    events.add(event);
                }

                resp.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(resp.getWriter(), events);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
