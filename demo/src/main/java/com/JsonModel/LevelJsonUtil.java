package com.JsonModel;

import java.util.HashMap;
import java.util.Set;

import com.alibaba.fastjson2.JSONObject;

public class LevelJsonUtil {
	private static HashMap<Integer, LevelJsonModel> map = new HashMap<>();

	public static void loadJson(String text) {
		map.clear();
		JSONObject object = JSONObject.parseObject(text);
		Set<String> set = object.keySet();
		for (String key : set) {
			JSONObject row = object.getJSONObject(key);
			map.put(Integer.parseInt(key), row.to(LevelJsonModel.class));
		}
	}

	public static LevelJsonModel getModel(Integer id) {
		return map.get(id);
	}

	public static HashMap<Integer, LevelJsonModel> getMap() {
		return map;
	}
}
