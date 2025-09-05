package aji.carpetajiaddition.validators.RecipeRule;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;

public interface RecipeRuleValidator {
    void loadRecipe(String ruleName);

    void unloadRecipe(String ruleName);


    default JsonObject readRecipeFile(String fileName) {
        try {
            String resourcePath = "assets\\carpetajiaddition\\RecipesTweak\\" + fileName;
            try (InputStream inputStream = RecipeRuleValidator.class.getClassLoader().getResourceAsStream(resourcePath)) {
                if (inputStream != null) {
                    String content = new String(inputStream.readAllBytes());
                    return JsonParser.parseString(content).getAsJsonObject();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
