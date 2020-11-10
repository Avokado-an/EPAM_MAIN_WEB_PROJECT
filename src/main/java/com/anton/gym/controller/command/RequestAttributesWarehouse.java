package com.anton.gym.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RequestAttributesWarehouse {
    private Map<String, Object> attributes;
    private static RequestAttributesWarehouse instance;

    private RequestAttributesWarehouse() {
        attributes = new HashMap<>();
        String startingDefaultPage = PagePath.MAIN;
        attributes.put(Attribute.CURRENT_PAGE, startingDefaultPage);
    }

    public static RequestAttributesWarehouse getInstance() {
        if (instance == null) {
            instance = new RequestAttributesWarehouse();
        }
        return instance;
    }

    public void fillMapWithRequestAttributes(HttpServletRequest request) {
        Enumeration<String> parametersNames = request.getAttributeNames();
        while (parametersNames.hasMoreElements()) {
            String name = parametersNames.nextElement();
            if (!name.equalsIgnoreCase(Attribute.MESSAGE)) {
                attributes.put(name, request.getAttribute(name));
            }
        }
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public int size() {
        return attributes.size();
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    public boolean containsKey(Object key) {
        return attributes.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return attributes.containsValue(value);
    }

    public Object get(Object key) {
        return attributes.get(key);
    }

    public Object put(String key, Object value) {
        return attributes.put(key, value);
    }

    public Object remove(Object key) {
        return attributes.remove(key);
    }

    public void putAll(Map<? extends String, ?> m) {
        attributes.putAll(m);
    }

    public void clear() {
        attributes.clear();
    }

    public Set<String> keySet() {
        return attributes.keySet();
    }

    public Collection<Object> values() {
        return attributes.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return attributes.entrySet();
    }

    public Object getOrDefault(Object key, Object defaultValue) {
        return attributes.getOrDefault(key, defaultValue);
    }

    public void forEach(BiConsumer<? super String, ? super Object> action) {
        attributes.forEach(action);
    }

    public void replaceAll(BiFunction<? super String, ? super Object, ?> function) {
        attributes.replaceAll(function);
    }

    public Object putIfAbsent(String key, Object value) {
        return attributes.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return attributes.remove(key, value);
    }

    public boolean replace(String key, Object oldValue, Object newValue) {
        return attributes.replace(key, oldValue, newValue);
    }

    public Object replace(String key, Object value) {
        return attributes.replace(key, value);
    }

    public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
        return attributes.computeIfAbsent(key, mappingFunction);
    }

    public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
        return attributes.computeIfPresent(key, remappingFunction);
    }

    public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
        return attributes.compute(key, remappingFunction);
    }

    public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
        return attributes.merge(key, value, remappingFunction);
    }
}
