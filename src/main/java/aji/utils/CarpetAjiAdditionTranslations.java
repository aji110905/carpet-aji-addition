package aji.utils;

import com.google.common.collect.Maps;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static aji.CarpetAjiAdditionServer.LOGGER;
import static aji.CarpetAjiAdditionServer.MOD_ID;

public class CarpetAjiAdditionTranslations {
    private static Map<String, String> getTranslationFromResourcePath(String lang) {
        Map<String, String> translations = Maps.newHashMap();
        String resourcePath = "assets/" + MOD_ID + "/lang/" + lang + ".yml";

        try (InputStream inputStream = CarpetAjiAdditionTranslations.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                return translations;
            }
            Yaml yaml = new Yaml();
            @SuppressWarnings("unchecked")
            Map<String, Object> yamlMap = yaml.loadAs(new InputStreamReader(inputStream, StandardCharsets.UTF_8), Map.class);
            flattenMap("", yamlMap, translations);
        } catch (IOException e) {
            LOGGER.error("Unable to read translation file:{}", resourcePath, e);
        } catch (YAMLException e) {
            LOGGER.error("Unable to parse translation file:{}", resourcePath, e);
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
}