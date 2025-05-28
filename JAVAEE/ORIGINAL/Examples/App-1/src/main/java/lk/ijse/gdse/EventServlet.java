package lk.ijse.gdse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet("/event")
public class EventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventdb",
                    "root",
                    "Ijse@1234");
                 Statement stmt = connection.createStatement();
                 ResultSet rst = stmt.executeQuery("SELECT * FROM event")) {

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
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> event = mapper.readValue(req.getInputStream(), Map.class);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventdb",
                    "root",
                    "Ijse@1234");

            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO event (eid, ename, edescription, edate, eplace) VALUES (?,?,?,?,?)"
            );
            pst.setString(1, event.get("eid"));
            pst.setString(2, event.get("ename"));
            pst.setString(3, event.get("edescription"));
            pst.setString(4, event.get("edate"));
            pst.setString(5, event.get("eplace"));

            int rows = pst.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> event = objectMapper.readValue(req.getInputStream(), Map.class);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventdb",
                    "root",
                    "Ijse@1234");

            PreparedStatement pst = connection.prepareStatement(
                    "UPDATE event SET ename = ?, edescription = ?, edate = ?, eplace = ? WHERE eid = ?"
            );
            pst.setString(1, event.get("ename"));
            pst.setString(2, event.get("edescription"));
            pst.setString(3, event.get("edate"));
            pst.setString(4, event.get("eplace"));
            pst.setString(5, event.get("eid"));

            int rows = pst.executeUpdate();
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getWriter(), rows);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> event = objectMapper.readValue(req.getInputStream(), Map.class);
        String id = event.get("eid");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventdb",
                    "root",
                    "Ijse@1234");

            PreparedStatement pst = connection.prepareStatement(
                    "DELETE FROM event WHERE eid = ?"
            );
            pst.setString(1, id);
            int rows = pst.executeUpdate();
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getWriter(), rows);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }
}