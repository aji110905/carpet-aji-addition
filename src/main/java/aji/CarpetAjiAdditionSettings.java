package aji;

import carpet.api.settings.Rule;

import static aji.CarpetAjiAdditionSettings.RuleCategory.CAA;
import static aji.CarpetAjiAdditionSettings.RuleCategory.MINECART;

public class CarpetAjiAdditionSettings {
    static class RuleCategory {
        public static final String CAA = "CAA";
        public static final String MINECART = "MINECART";
    }

    @Rule(categories = {CAA, MINECART})
    public static boolean betterHopperMinecart = false;

    @Rule(categories = {CAA})
    public static boolean sitOnTheGround = false;

    @Rule(categories = {CAA})
    public static boolean lockAllHopper = false;

    @Rule(categories = {CAA})
    public static boolean endlessVault = false;

    @Rule(categories = {CAA, MINECART})
    public static boolean lockAllHopperMinecart = false;
}
