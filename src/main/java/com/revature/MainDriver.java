package com.revature;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.RequestMapping;

import io.javalin.Javalin;

public class MainDriver {

	//My loggger
	public static Logger logger = LoggerFactory.getLogger(MainDriver.class);

	public static void main(String[] args) {

		//test logger
		logger.info("starting");

		Javalin app = Javalin.create(config -> {
			config.requestLogger.http((ctx, ms) -> {
				logger.info("Path:{} Status:{} Latency:{}", ctx.path(), ctx.status(), Math.round(ms));
			});
		});
		// Javalin app = Javalin.create(confg ->{
		// 	confg.plugins.enableDevLogging();
		// });
		RequestMapping.setupEndpoints(app);
		app.start(7000);
	}
}
