package aji.carpetajiaddition.validators.RecipeRule;

import carpet.api.settings.Validator;
import java.util.Map;

public abstract class RecipeRuleValidator<T> extends Validator<T> {
    public abstract Map readRecipeFiles(String folderName);
}
