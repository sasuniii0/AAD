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
                    "jdbc:mysql://localhost:3306/eventdb?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Ijse@1234");
                 Statement stmt = connection.createStatement();
                 ResultSet rst = stmt.executeQuery("SELECT * FROM event")) {

                List<Map<String, String>> events = new ArrayList<>();
                while (rst.next()) {
                    Map<String, String> event = new HashMap<>();
                    event.put("id", rst.getString("id"));
                    event.put("ename", rst.getString("ename"));
                    event.put("edescription", rst.getString("edescription"));
                    event.put("edate", rst.getString("edate"));
                    event.put("eplace", rst.getString("eplace"));
                    events.add(event);
                }

                resp.setContentType("application/json");
                new ObjectMapper().writeValue(resp.getWriter(), events);
            }
        } catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Parse JSON from request body
            ObjectMapper mapper = new ObjectMapper();
            Map <String, String> eventData = mapper.readValue(req.getReader(), Map.class);

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eventdb?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Ijse@1234");
                 PreparedStatement pstmt = connection.prepareStatement(
                         "INSERT INTO event (id, ename, edescription, edate, eplace) VALUES (?, ?, ?, ?, ?)")) {

                pstmt.setString(1, eventData.toString());
                pstmt.setString(2, eventData.get("ename"));
                pstmt.setString(3, eventData.get("edescription"));
                pstmt.setString(4, eventData.get("edate"));
                pstmt.setString(5, eventData.get("eplace"));

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    resp.getWriter().write("{\"status\":\"success\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"error\":\"Failed to save event\"}");
                }
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }*/
}