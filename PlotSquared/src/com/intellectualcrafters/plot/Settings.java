/*
 * Copyright (c) IntellectualCrafters - 2014. You are not allowed to distribute
 * and/or monetize any of our intellectual property. IntellectualCrafters is not
 * affiliated with Mojang AB. Minecraft is a trademark of Mojang AB.
 * 
 * >> File = Settings.java >> Generated by: Citymonstret at 2014-08-09 01:44
 */

package com.intellectualcrafters.plot;

/**
 * Updater and DB settings
 *
 * @author Citymonstret
 * @author Empire92
 */
public class Settings {
    public static String SCHEMATIC_SAVE_PATH = "/var/www/schematics";
	public static int MAX_PLOTS = 20;
	/**
	 * WorldGuard region on claimed plots
	 */
	public static boolean WORLDGUARD = false;
	/**
	 * metrics
	 */
	public static boolean METRICS = true;
	/**
	 * plot specific resource pack
	 */
	public static String PLOT_SPECIFIC_RESOURCE_PACK = "";
	/**
	 * Kill road mobs?
	 */
	public static boolean KILL_ROAD_MOBS;
	/**
	 * Default kill road mobs: true
	 */
	public static boolean KILL_ROAD_MOBS_DEFAULT = true;
	/**
	 * mob pathfinding?
	 */
	public static boolean MOB_PATHFINDING;
	/**
	 * Default mob pathfinding: true
	 */
	public static boolean MOB_PATHFINDING_DEFAULT = true;
    /**
     * Delete plots on ban?
     */
    public static boolean DELETE_PLOTS_ON_BAN = false;
	/**
	 * Update settings
	 * 
	 * @author Citymonstret
	 */
	public static String URL = "http://dev.bukkit.org/bukkit-plugins/plotsquared/";

	public static boolean DEBUG = true;

	public static boolean AUTO_CLEAR = false;
	public static int AUTO_CLEAR_DAYS = 365;
    public static String API_URL = "http://www.intellectualsites.com/minecraft.php";

	public static class Update {
		/**
		 * Update plugin?
		 * 
		 * @deprecated
		 */
		@Deprecated
		public static boolean AUTO_UPDATE = false;
	}

	/**
	 * Database settings
	 * 
	 * @author Citymonstret
	 */
	public static class DB {
		public static boolean USE_MONGO = false; /*
												 * TODO: Implement Mongo
												 * 
												 * @Brandon
												 */;
		public static boolean USE_SQLITE = false;
		public static boolean USE_MYSQL = true; /* NOTE: Fixed connector */
		public static String SQLITE_DB = "storage";
		public static String HOST_NAME = "localhost";
		public static String PORT = "3306";
		public static String DATABASE = "plot_db";
		public static String USER = "root";
		public static String PASSWORD = "password";
        public static String PREFIX = "";
	}
}
