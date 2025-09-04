package aji.carpetajiaddition;

import aji.carpetajiaddition.utils.TranslationsUtils.CarpetAjiAdditionTranslations;
import aji.carpetajiaddition.utils.TranslationsUtils.getTranslationsMap;
import carpet.CarpetExtension;
import carpet.CarpetServer;

import java.util.Map;

public class CarpetAjiAdditionExtension implements CarpetExtension {

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetAjiAdditionSettings.class);
    }

    @Override
    public String version() {
        return CarpetAjiAdditionMod.MOD_ID;
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        CarpetAjiAdditionTranslations.readLanguageFiles(lang);
        return getTranslationsMap.getFabricCarpetTranslations(lang);
    }
}
