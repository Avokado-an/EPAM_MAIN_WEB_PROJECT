package com.anton.gym.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The {@code RequestAttributesWarehouse} class represents attributes, which are saved from last request.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class RequestAttributesWarehouse {
    private Map<String, Object> attributes;
    private static RequestAttributesWarehouse instance;

    private RequestAttributesWarehouse() {
        attributes = new HashMap<>();
        String startingDefaultPage = PagePath.MAIN;
        attributes.put(Attribute.CURRENT_PAGE, startingDefaultPage);
    }

    /**
     * get instance of class
     *
     * @return instance
     */
    public static RequestAttributesWarehouse getInstance() {
        if (instance == null) {
            instance = new RequestAttributesWarehouse();
        }
        return instance;
    }

    /**
     * fills map with request attributes
     *
     * @param request which parameters to put into map
     */
    public void fillMapWithRequestAttributes(HttpServletRequest request) {
        Enumeration<String> parametersNames = request.getAttributeNames();
        while (parametersNames.hasMoreElements()) {
            String name = parametersNames.nextElement();
            if (!name.equalsIgnoreCase(Attribute.MESSAGE)) {
                attributes.put(name, request.getAttribute(name));
            }
        }
    }

    /**
     * get value by key from attributes
     *
     * @param key the key of the value
     * @return value
     */
    public Object get(Object key) {
        return attributes.get(key);
    }

    /**
     * get entry set
     *
     * @return the entry set
     */
    public Set<Map.Entry<String, Object>> entrySet() {
        return attributes.entrySet();
    }

    /**
     * clears the attributes
     */
    public void clear() {
        attributes.clear();
    }
}
