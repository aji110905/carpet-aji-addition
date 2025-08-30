package aji;

import carpet.api.settings.Rule;

import static aji.settings.RuleCategory.CAA;
import static aji.settings.RuleCategory.MINECART;

public class CarpetAjiAdditionSettings {
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
