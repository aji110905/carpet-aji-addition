package aji.carpetajiaddition.utils;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static aji.carpetajiaddition.CarpetAjiAdditionServer.LOGGER;
import static aji.carpetajiaddition.CarpetAjiAdditionServer.MOD_ID;

public class CarpetAjiAdditionTranslations {
    private static Map<String, String> getTranslationFromResourcePath(String lang) {
        Map<String, String> translations = Maps.newHashMap();
        String resourcePath = "assets/" + MOD_ID + "/lang/" + lang + ".json";

        try (InputStream inputStream = CarpetAjiAdditionTranslations.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                return translations;
            }
            Gson gson = new Gson();
            Map<String, Object> jsonMap = gson.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8),
                    new TypeToken<Map<String, Object>>(){}.getType());
            flattenMap("", jsonMap, translations);
        } catch (IOException e) {
            LOGGER.error("Unable to read translation file:{}", resourcePath, e);
        } catch (com.google.gson.JsonSyntaxException e) {
            LOGGER.error("Invalid JSON format in file:{}", resourcePath, e);
        }

        return translations;
    }

    private static void flattenMap(String parentKey, Object value, Map<String, String> translations) {
        if (value instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) value;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String newKey = parentKey.isEmpty() ? key : parentKey + "." + key;
                flattenMap(newKey, entry.getValue(), translations);
            }
        } else {
            if (value != null) {
                translations.put(parentKey, value.toString());
            }
        }
    }

    public static Map<String, String> getFabricCarpetTranslations(String lang) {
        Map<String, String> fabricCarpetTranslations = Maps.newHashMap();
        Map<String, String> translations = getTranslationFromResourcePath(lang);

        if (translations == null) {
            return fabricCarpetTranslations;
        }

        String targetPrefix = "carpet-aji-addition.carpet";
        String removePrefix = "carpet-aji-addition.";

        for (Map.Entry<String, String> entry : translations.entrySet()) {
            String originalKey = entry.getKey();
            if (originalKey != null && originalKey.startsWith(targetPrefix)) {
                String newKey = originalKey.substring(removePrefix.length());
                fabricCarpetTranslations.put(newKey, entry.getValue());
            }
        }

        return fabricCarpetTranslations;
    }

    public static Map<String, String> getCarpetAjiAdditionTranslations(String lang) {
        Map<String, String> CarpetAjiAddition = Maps.newHashMap();
        Map<String, String> translations = getTranslationFromResourcePath(lang);

        if (translations == null) {
            return CarpetAjiAddition;
        }

        String targetPrefix = "carpet-aji-addition.carpet";

        for (Map.Entry<String, String> entry : translations.entrySet()) {
            String originalKey = entry.getKey();
            if (!(originalKey != null && originalKey.startsWith(targetPrefix))) {
                CarpetAjiAddition.put(entry.getKey(), entry.getValue());
            }
        }

        return CarpetAjiAddition;
    }
}