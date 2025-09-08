package aji.carpetajiaddition;

import aji.carpetajiaddition.validators.RecipeRule.OreRecipeRecipeRuleValidator;
import aji.carpetajiaddition.validators.RecipeRule.RecipeRuleValidator;
import carpet.api.settings.Rule;

import static aji.carpetajiaddition.setting.RuleCategory.CAA;
import static aji.carpetajiaddition.setting.RuleCategory.RECIPE_RULE;

public class CarpetAjiAdditionSettings {

    @Rule(categories = {CAA})
    public static boolean glowingHopperMinecart = false;

    @Rule(categories = {CAA})
    public static boolean sitOnTheGround = false;

    @Rule(categories = {CAA})
    public static boolean lockAllHopper = false;

    @Rule(categories = {CAA})
    public static boolean keepOpeningVault = false;

    @Rule(categories = {CAA})
    public static boolean lockAllHopperMinecart = false;

    @Rule(categories = {CAA})
    public static boolean totemOfUndyingWrench = false;

    @Rule(
            categories = {CAA, RECIPE_RULE},
            validators = RecipeRuleValidator.class
    )
    public static boolean dragonEggRecipe = false;

    @Rule(
            categories = {CAA, RECIPE_RULE},
            validators = OreRecipeRecipeRuleValidator.class,
            options = {
                    "null",
                    "ore",
                    "deepslate",
                    "nether",
                    "ore_and_deepslate",
                    "deepslate_and_nether",
                    "ore_and_nether",
                    "ore_and_nether",
                    "all"
            }
    )
    public static String oreRecipe = "null";

    @Rule(
            categories = {CAA, RECIPE_RULE},
            validators = RecipeRuleValidator.class
    )
    public static boolean dragonBreathRecipe = false;
}