// package com.DB;

// import java.util.Scanner;

// import org.slf4j.LoggerFactory;

// import com.saoModel.ItemsSystem.ItemTypes.Food;
// import com.saoModel.ItemsSystem.ItemTypes.Potion;

// import ch.qos.logback.classic.Level;
// import ch.qos.logback.classic.Logger;
// import ch.qos.logback.classic.LoggerContext;

// public class Mongo {
// public static void main(String[] args) {
// Scanner scan = new Scanner(System.in);
// LoggerContext loggerContext = (LoggerContext)
// LoggerFactory.getILoggerFactory();
// Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
// rootLogger.setLevel(Level.OFF);
// MongoUtils.CreatePlayer(0, "晓桐");
// MongoUtils.CreatePlayer(1, "二大爷");
// MongoUtils.insertItemsToPlayerBag(1, new Potion(0, "红药", "补血", 50, 0, 0));
// MongoUtils.insertItemsToPlayerBag(1, new Potion(1, "蓝药", "补魔", 50, 0, 0));
// MongoUtils.insertItemsToPlayerBag(1, new Food(0, "茶叶蛋", "饱腹", 20, 0, 0));

// while (true) {
// String input = scan.next();
// System.out.println(input);
// if (input.equals("0")) {
// break;
// } else if (input.equals("1")) {
// int id = scan.nextInt();
// String name = scan.next();
// System.out.println(id + " " + name);
// MongoUtils.CreatePlayer(id, name);
// }
// }
// }
// }
