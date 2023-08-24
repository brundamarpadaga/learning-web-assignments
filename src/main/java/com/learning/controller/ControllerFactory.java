package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
	
	private static final Map<String, IController> controllers = new HashMap<>();

    public static void register(String path, IController controller) {
        controllers.put(path, controller);
    }

    public static IController get(String path) {
        return controllers.get(path);
    }

}
