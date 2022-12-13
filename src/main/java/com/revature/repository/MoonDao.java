package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() throws SQLException{//sevice level handle exception
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<Moon> moonList = new ArrayList<>();
			while(rs.next()){
				Moon moon = new Moon();
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moon.setMyPlanetId(rs.getInt(3));
				moonList.add(moon);
			}
			return moonList;
		}
	}

	public Moon getMoonByName(String username, String moonName) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(3));
			return moon;
		} catch (SQLException e) {
			System.out.println(e.getMessage());//add logging
            return new Moon();
		}
	}

	public Moon getMoonById(String username, int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(3));
			return moon;
		} catch (SQLException e) {
			System.out.println(e.getMessage());//add logging
            return new Moon();
		}
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
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage());//add logging
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) throws SQLException{//sevice level handle exception
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where myplanetid = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			List<Moon> moonList = new ArrayList<>();
			while(rs.next()){
				Moon moon = new Moon();
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moon.setMyPlanetId(rs.getInt(3));
				moonList.add(moon);
			}
			return moonList;
		}
	}

	public static void main(String[] args) {
		MoonDao dao = new MoonDao();
		dao.deleteMoonById(2);
		
	}
}
