package com.JsonModel;

import java.io.InputStream;

public class LoadAllJsonModel {
	public static void load() {
		InputStream streamLevel = LoadAllJsonModel.class.getClassLoader().getResourceAsStream("json/Level.json");
		try {
			LevelJsonUtil.loadJson(new String(streamLevel.readAllBytes(), "UTF-8"));
		} catch (Exception e) {
			System.err.println(e);
		}
		InputStream streamItem = LoadAllJsonModel.class.getClassLoader().getResourceAsStream("json/Item.json");
		try {
			ItemJsonUtil.loadJson(new String(streamItem.readAllBytes(), "UTF-8"));
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
