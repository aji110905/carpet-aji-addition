package aji.carpetajiaddition.validators.RecipeRule;

import carpet.api.settings.CarpetRule;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SimpleRecipeRuleValidator<T> extends RecipeRuleValidator<T> {
    private String targetPath;

    private SimpleRecipeRuleValidator() {
    }

    @Override
    public T validate(ServerCommandSource source, CarpetRule<T> currentRule, T newValue, String string) {
        if (source == null) return currentRule.value();
        String ruleName = currentRule.name();
        MinecraftServer server = source.getServer();
        targetPath = server.getSavePath(WorldSavePath.DATAPACKS).toString() + "\\CarpetAjiAdditionData\\data\\carpetajiaddition\\recipe";

        if (newValue instanceof Boolean) {
            if ((Boolean) newValue) {
                loadRecipe(ruleName);
            } else {
                unloadRecipe(ruleName);
            }
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else{
            return null;
        }
    }

    public void loadRecipe(String ruleName){
        Map<String, JsonObject> recipeFiles = readRecipeFiles(ruleName);
        if (recipeFiles == null) return;
        recipeFiles.forEach((fileName, jsonObject) -> {
            File file = new File(targetPath, fileName);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonObject.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void unloadRecipe(String ruleName){
        Map<String, JsonObject> recipeFiles = readRecipeFiles(ruleName);
        if (recipeFiles == null) return;
        recipeFiles.forEach((fileName, jsonObject) -> {
            File file = new File(targetPath, fileName);
            if (file.exists()) {
                file.delete();
            }
        });
    }

    @Override
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
                    try (InputStream inputStream = SimpleRecipeRuleValidator.class.getClassLoader().getResourceAsStream(filePath)) {
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
