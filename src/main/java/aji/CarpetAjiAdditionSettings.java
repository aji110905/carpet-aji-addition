package aji;

import carpet.api.settings.Rule;

import static aji.CarpetAjiAdditionSettings.RuleCategory.CAA;

public class CarpetAjiAdditionSettings {
    static class RuleCategory {
        public static final String CAA = "CAA";
    }

    @Rule(categories = {CAA})
    public static boolean betterHopperMinecart = false;

    @Rule(categories = {CAA})
    public static boolean sitOnTheGround = false;
}
