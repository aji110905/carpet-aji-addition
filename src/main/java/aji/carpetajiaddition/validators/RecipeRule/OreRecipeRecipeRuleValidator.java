package aji.carpetajiaddition.validators.RecipeRule;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OreRecipeRecipeRuleValidator<T> extends Validator<T> implements RecipeRule{
    private String targetPath;
    private Map<String, Map<String, JsonObject>> recipeFiles;

    @Override
    public T validate(@Nullable ServerCommandSource source, CarpetRule<T> changingRule, T newValue, String userInput) {
        if (source == null) return changingRule.value();
        MinecraftServer server = source.getServer();
        targetPath = server.getSavePath(WorldSavePath.DATAPACKS).toString() + "\\CarpetAjiAdditionData\\data\\carpetajiaddition\\recipe";
        recipeFiles = readRecipeFiles(changingRule.name());
        if ("null".equals(newValue)){
            unloadRecipe(recipeFiles);
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("deepslate".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadDeepslateRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("nether".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadNetherRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("ore".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadOreRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("ore_and_deepslate".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadDeepslateRecipe();
            loadOreRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("deepslate_and_nether".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadDeepslateRecipe();
            loadNetherRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("ore_and_nether".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadNetherRecipe();
            loadOreRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else if("all".equals(newValue)){
            unloadRecipe(recipeFiles);
            loadDeepslateRecipe();
            loadNetherRecipe();
            loadOreRecipe();
            server.reloadResources(server.getDataPackManager().getEnabledIds());
            return newValue;
        }else{
            return null;
        }
    }

    public void loadOreRecipe() {
        loadRecipe(recipeFiles.get("ore"));
    }

    public void loadDeepslateRecipe() {
        loadRecipe(recipeFiles.get("deepslate"));
    }

    public void loadNetherRecipe() {
        loadRecipe(recipeFiles.get("nether"));
    }

    @Override
    public void loadRecipe(Map map) {
        if (map == null) return;
        Map<String, JsonObject> files = (Map<String, JsonObject>) map;
        files.forEach((fileName, jsonObject) -> {
            File file = new File(targetPath, fileName);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonObject.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void unloadRecipe(Map map) {
        if (map == null) return;
        Map<String, Map<String, JsonObject>> files = (Map<String, Map<String, JsonObject>>) map;
        files.forEach((filename, file) -> {
            file.forEach((fileName, jsonObject) -> {
                File file1 = new File(targetPath, fileName);
                if (file1.exists()) {
                    file1.delete();
                }
            });
        });
    }

    @Override
    public Map readRecipeFiles(String folderName) {
        Map<String, Map<String, JsonObject>> map = new HashMap<>();
        try {
            String resourcePath = OreRecipeRecipeRuleValidator
                    .class
                    .getClassLoader()
                    .getResource("assets/carpetajiaddition/RecipesTweak/" + folderName)
                    .getPath()
                    .substring(1);
            Arrays.stream(new File(resourcePath).listFiles()).forEach(file -> {
                Map<String, JsonObject> fileMap = new HashMap<>();
                Arrays.stream(file.listFiles()).forEach(file1 -> {
                    try {
                        JsonObject jsonObject = JsonParser.parseReader(new FileReader(file1)).getAsJsonObject();
                        fileMap.put(file1.getName(), jsonObject);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                map.put(file.getName(), fileMap);
            });
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
