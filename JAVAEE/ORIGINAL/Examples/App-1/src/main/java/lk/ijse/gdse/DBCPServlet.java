package lk.ijse.gdse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/dbcp")
public class DBCPServlet extends HttpServlet {
    BasicDataSource basicDataSource ;

    @Override
    public void init() throws ServletException {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/eventdb");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Ijse@1234");
        basicDataSource.setInitialSize(50);
        basicDataSource.setMaxTotal(100);
        // ram ekath ekk balala tamai define krgnna one

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Connection connection = basicDataSource.getConnection();
            ResultSet rst = connection.prepareStatement("SELECT * FROM event").executeQuery();

            List<Map<String, String>> events = new ArrayList<>();

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

            req.setAttribute("events", events);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
