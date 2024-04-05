package com.github.service.renderer;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class HTMLObjectRenderer {

    public static final String HTML_LI_C = "</li>";
    public static final String HTML_LI_O = "<li>";
    public static final String HTML_UL_O = "<ul>";
    public static final String HTML_UL_C = "</ul>";

    public static String renderObject(Object obj) {
        StringBuilder html = new StringBuilder();
        renderObject(obj, html);
        return html.toString();
    }

    private static void renderObject(Object obj, StringBuilder html) {
        if (obj == null) {
            html.append("null");
            return;
        }

        Class<?> objClass = obj.getClass();
        if (objClass.isArray()) {
            renderArray(obj, html);
        } else if (obj instanceof Collection<?>) {
            renderCollection((Collection<?>) obj, html);
        } else if (obj instanceof Map<?, ?>) {
            renderMap((Map<?, ?>) obj, html);
        } else {
            renderFields(obj, html);
        }
    }

    private static void renderArray(Object array, StringBuilder html) {
        html.append(HTML_UL_O);
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            html.append(HTML_LI_O);
            renderObject(Array.get(array, i), html);
            html.append(HTML_LI_C);
        }
        html.append(HTML_UL_C);
    }

    private static void renderCollection(Collection<?> collection, StringBuilder html) {
        html.append(HTML_UL_O);
        for (Object item : collection) {
            html.append(HTML_LI_O);
            renderObject(item, html);
            html.append(HTML_LI_C);
        }
        html.append(HTML_UL_C);
    }

    private static void renderMap(Map<?, ?> map, StringBuilder html) {
        html.append(HTML_UL_O);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            html.append(HTML_LI_O).append(entry.getKey()).append(": ");
            renderObject(entry.getValue(), html);
            html.append(HTML_LI_C);
        }
        html.append(HTML_UL_C);
    }

    private static void renderFields(Object obj, StringBuilder html) {
        html.append(HTML_UL_O);
        Field[] fields2 = FieldUtils.getAllFields(obj.getClass());
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //field.trySetAccessible();
            html.append(HTML_LI_O).append(field.getName()).append(": ");
            try {
                Object obj1 = field.get(obj);
                renderObject(obj1, html);
            } catch (IllegalAccessException e) {
                html.append("Error accessing field");
            }
            html.append(HTML_LI_C);
        }
        html.append(HTML_UL_C);
    }
}
