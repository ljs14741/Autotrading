package com.bitcoin.autotrading.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonTransfer {

    public static Map<String, Object> getMapFromJSONObject(JSONObject obj) {
        if (ObjectUtils.isEmpty(obj)) {
            log.error("BAD REQUEST obj : {}", obj);
            throw new IllegalArgumentException(String.format("BAD REQUEST obj %s", obj));
        }

        try {
            /*
            unchecked or unsafe 에러로 인해 수정
            참고자료 : https://bjp5319.tistory.com/50
            */
            return new ObjectMapper().readValue(obj.toString(), new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) throws JSONException {
        log.info("JsonArray");
        if (ObjectUtils.isEmpty(jsonArray)) {
            log.error("jsonArray is null.");
            throw new IllegalArgumentException("jsonArray is null");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++) {
            Map<String, Object> map = getMapFromJSONObject((JSONObject)jsonArray.get(i));
            list.add(map);
        }
        return list;
    }

    public static <T> T getObjectFromJSONObject(JSONObject obj){
        if (ObjectUtils.isEmpty(obj)) {
            log.error("BAD REQUEST obj : {}", obj);
            throw new IllegalArgumentException(String.format("BAD REQUEST obj %s", obj));
        }

        try {
            return new ObjectMapper().readValue(obj.toString(), new TypeReference<T>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> getListObjectFromJSONObject(JSONArray jsonArray) throws JSONException{
        log.info("JsonArray");
        if (ObjectUtils.isEmpty(jsonArray)) {
            log.error("jsonArray is null.");
            throw new IllegalArgumentException("jsonArray is null");
        }
        List<T> list = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++) {
            list.add(getObjectFromJSONObject((JSONObject)jsonArray.get(i)));
        }
        return list;
    }
}
