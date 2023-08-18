package com.fahimProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginDatabase extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			PrintWriter out = res.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails","root","Fahim@ece1");
			String name = req.getParameter("uname");
			String password = req.getParameter("pword");
			PreparedStatement ps=con.prepareStatement("select uname, password from login");
			ResultSet rs = ps.executeQuery();
			boolean flag = false;
			while (rs.next()) {
                String storedUsername = rs.getString("uname");
                String storedPassword = rs.getString("password");
                
                if (name.equals(storedUsername) && password.equals(storedPassword)) {
                    flag = true;
                    break;
                }
            }
			
			if(flag) {
				out.println("Login Successful");
			}
			else {
				out.println("Login Failed");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
