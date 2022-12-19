package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exceptions.NotLoggedInException;

import io.javalin.Javalin;

public class RequestMapping {
	
	private static AuthenticateController authController = new AuthenticateController();
	private static PlanetController planetController = new PlanetController();
	private static MoonController moonController = new MoonController();

	public static Logger logger = LoggerFactory.getLogger(RequestMapping.class);
	//public static long s;
	
	public static void setupEndpoints(Javalin app) {
		// Authenticate user and create a session for the user, sending username/password in the body as JSON
		app.post("/login", ctx -> {
			//long start = System.currentTimeMillis();
			authController.authenticate(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});

		// Register a new user, sending username/password in the body as JSON
		app.post("/register", ctx -> {
			//long start = System.currentTimeMillis();
			authController.register(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Invalidate session
		app.post("/logout", ctx -> {
			//long start = System.currentTimeMillis();
			authController.invalidateSession(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Check for valid sessions
		// Throw a custom exception if a session is not valid
		// This exception will be handled separately
		app.before("/api/*", ctx -> {	
			//s = System.currentTimeMillis();
			if(!authController.verifySession(ctx)) {
				throw new NotLoggedInException();
			}
		});
		
		// Handling the exception when a session doesn't exist
		app.exception(NotLoggedInException.class, (e,ctx) -> {
			ctx.json(e.getMessage()).status(401);
			//long finish = System.currentTimeMillis();
			//long latency = finish - s;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		
		// Get all Planets
		app.get("api/planets", ctx -> {
			//long start = System.currentTimeMillis();
			planetController.getAllPlanets(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Get a planet with matching name
		app.get("api/planet/{name}", ctx -> {
			//long start = System.currentTimeMillis();
			planetController.getPlanetByName(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Get a planet with matching ID
		app.get("api/planet/id/{id}", ctx -> {
			//long start = System.currentTimeMillis();
			planetController.getPlanetByID(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Get moons associated with a planet
		app.get("api/planet/{id}/moons", ctx -> {
			//long start = System.currentTimeMillis();
			moonController.getPlanetMoons(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Get all moons
		app.get("api/moons", ctx -> {
			//long start = System.currentTimeMillis();
			moonController.getAllMoons(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Get a moon with matching name
		app.get("api/moon/{name}", ctx -> {
			//long start = System.currentTimeMillis();
			moonController.getMoonByName(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Get a moon with matching ID
		app.get("api/moon/id/{id}", ctx -> {
			//long start = System.currentTimeMillis();
			moonController.getMoonById(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		

		// Create a new planet, sending the data in the body as JSON
		app.post("api/planet", ctx -> {
			//long start = System.currentTimeMillis();
			planetController.createPlanet(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Create a new moon, sending the data in the body as JSON
		app.post("api/moon", ctx -> {
			//long start = System.currentTimeMillis();
			moonController.createMoon(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		

		// Delete a planet and all of its moons
		app.delete("api/planet/{id}", ctx -> {
			//long start = System.currentTimeMillis();
			planetController.deletePlanet(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
		
		// Delete a moon
		app.delete("api/moon/{id}", ctx -> {
			//long start = System.currentTimeMillis();
			moonController.deleteMoon(ctx);
			//long finish = System.currentTimeMillis();
			//long latency = finish - start;
			//logger.info("Path:{} Status:{} Latency:{}", ctx.matchedPath(), ctx.status(), latency);
		});
	}
}
