package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon getMoonByName(String username, String moonName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon getMoonById(String username, int moonId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon createMoon(String username, Moon m) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "insert into moons values (default, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,m.getName());
			ps.setInt(2, m.getMyPlanetId());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			Moon newMoon = new Moon();
			rs.next();
			int newID = rs.getInt(1);
			newMoon.setId(newID);
			newMoon.setName(m.getName());
			newMoon.setMyPlanetId(m.getMyPlanetId());
			return newMoon;
		} catch (SQLException e) {
			System.out.println(e.getMessage());//add logging
            return new Moon();
		}
	}

	public void deleteMoonById(int moonId) {
		// TODO Auto-generated method stub
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		MoonDao dao = new MoonDao();
		Moon newMoon = new Moon();
		newMoon.setName("Moon");
		newMoon.setMyPlanetId(1);
		System.out.println(dao.createMoon("username", newMoon).getId());
	}
}
