package com.sao.JsonModel;

import java.util.HashMap;
import java.util.Set;

import com.alibaba.fastjson2.JSONObject;

public class ItemJsonUtil {
	private static HashMap<Long, ItemJsonModel> map = new HashMap<>();

	public static void loadJson(String text) {
		map.clear();
		JSONObject object = JSONObject.parseObject(text);
		Set<String> set = object.keySet();
		for (String key : set) {
			JSONObject row = object.getJSONObject(key);
			map.put(Long.parseLong(key), row.to(ItemJsonModel.class));
		}
	}

	public static ItemJsonModel getModel(Long id) {
		return map.get(id);
	}

	public static HashMap<Long, ItemJsonModel> getMap() {
		return map;
	}
}
