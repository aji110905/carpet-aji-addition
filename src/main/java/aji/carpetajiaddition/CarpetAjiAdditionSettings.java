package aji.carpetajiaddition;

import carpet.api.settings.Rule;

import static aji.carpetajiaddition.settings.RuleCategory.CAA;
import static aji.carpetajiaddition.settings.RuleCategory.MINECART;

public class CarpetAjiAdditionSettings {
    @Rule(categories = {CAA, MINECART})
    public static boolean glowingHopperMinecart = false;

    @Rule(categories = {CAA})
    public static boolean sitOnTheGround = false;

    @Rule(categories = {CAA})
    public static boolean lockAllHopper = false;

    @Rule(categories = {CAA})
    public static boolean keepOpeningVault = false;

    @Rule(categories = {CAA, MINECART})
    public static boolean lockAllHopperMinecart = false;
}
