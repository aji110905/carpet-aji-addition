package aji.carpetajiaddition.validators.RecipeRule;

import carpet.api.settings.Validator;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class RecipeRuleValidator<T> extends Validator<T> {
    public Map<String, JsonObject> readRecipeFiles(String folderName) {
        Map<String, JsonObject> fileMap = new HashMap<>();
        try {
            String resourcePath = "assets/carpetajiaddition/RecipesTweak/" + folderName;
            URL resourceUrl = RecipeRuleValidator.class.getClassLoader().getResource(resourcePath);
            if (resourceUrl == null) return null;
            try (Stream<Path> stream = Files.list(Paths.get(resourceUrl.toURI()))) {
                stream.forEach(path -> {
                    String fileName = path.getFileName().toString();
                    String filePath = resourcePath + "/" + fileName;
                    try (InputStream inputStream = RecipeRuleValidator.class.getClassLoader().getResourceAsStream(filePath)) {
                        if (inputStream != null) {
                            String content = new String(inputStream.readAllBytes());
                            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
                            fileMap.put(fileName, jsonObject);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return fileMap;
    }
}
