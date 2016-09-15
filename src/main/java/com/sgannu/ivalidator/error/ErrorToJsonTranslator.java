package com.sgannu.ivalidator.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ErrorToJsonTranslator {

    private final Logger logger = LoggerFactory.getLogger(ErrorToJsonTranslator.class);

    public String transformToJson(List<FieldError> fieldErrors) {

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>(fieldErrors.size());
        try {
            processKeyValue("error", String.valueOf(fieldErrors.size()), map);
            for (FieldError fieldError : fieldErrors) {

                processKeyValue(fieldError.getField(), fieldError.getDefaultMessage(), map);
            }

            return objectMapper.writeValueAsString(map);

        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }

        return "";
    }

    static void processKeyValue(String key, String value, Map<String, Object> map) {
        if (key.indexOf('.') > 0) {
            String[] arr = key.split("\\.", 2);

            Map<String, Object> correspondingMap = getSubmap(arr[0], map);

            processKeyValue(arr[1], value, correspondingMap);
        } else {
            map.put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    static Map<String, Object> getSubmap(String key, Map<String, Object> map) {

        if (key.indexOf('[') > 0) {
            String arrayKey = StringUtils.substringBefore(key, "[");
            int arrayIndex = Integer.parseInt(StringUtils.substringBetween(key, "[", "]"));

            if (!map.containsKey(arrayKey)) {
                map.put(arrayKey, new ArrayList<Map<String, Object>>());
            }

            ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) map.get(arrayKey);

            for (int i = list.size(); i < arrayIndex + 1; i++) {
                list.add(new HashMap<String, Object>());
            }

            return (Map<String, Object>) list.get(arrayIndex);
        } else if (map.containsKey(key)) {
            return (Map<String, Object>) map.get(key);
        } else {
            map.put(key, new HashMap<String, Object>());
            return (Map<String, Object>) map.get(key);
        }
    }
}
