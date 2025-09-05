package aji.carpetajiaddition.validators.RecipeRule;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import com.google.gson.JsonObject;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;

import java.io.*;

public class SimpleRecipeRuleValidator<T> extends Validator<T> implements RecipeRuleValidator {
    private String targetPath;

    private SimpleRecipeRuleValidator() {
    }

    @Override
    public T validate(ServerCommandSource source, CarpetRule<T> currentRule, T newValue, String string) {
        if (source == null) return currentRule.defaultValue();
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

    @Override
    public void loadRecipe(String ruleName){
        JsonObject jsonObject = readRecipeFile(ruleName + ".json");
        if (jsonObject == null) return;
        File dir = new File(targetPath);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(targetPath + "\\" + jsonObject.get("name").getAsString() + ".json");
        String data = jsonObject.get("data").toString();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unloadRecipe(String ruleName){
        JsonObject jsonObject = readRecipeFile(ruleName + ".json");
        if (jsonObject == null) return;
        File file = new File(targetPath + "\\" + jsonObject.get("name").getAsString() + ".json");
        if (file.exists()) file.delete();
    }
}
