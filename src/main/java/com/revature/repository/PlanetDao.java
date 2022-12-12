package com.revature.repository;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets() {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetByName(String owner, String planetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetById(String username, int planetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet createPlanet(String username, Planet p) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			UserDao userDao = new UserDao();
			User user = userDao.getUserByUsername(username);
			String sql = "insert into planets values (default,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getName());
			ps.setInt(2, user.getId());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			Planet newPlanet = new Planet();
			rs.next();
			int newId = rs.getInt(1);
			newPlanet.setId(newId);
			newPlanet.setName(p.getName());
			newPlanet.setOwnerId(user.getId());
			return newPlanet;
		} catch (SQLException e) {
			System.out.println(e.getMessage());//add logging
            return new Planet();
		}
	}

	public void deletePlanetById(int planetId) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		PlanetDao dao = new PlanetDao();
		Planet newPlanet = new Planet();
		newPlanet.setName("Pluto");
		System.out.println(dao.createPlanet("lomback", newPlanet));
	}
}
