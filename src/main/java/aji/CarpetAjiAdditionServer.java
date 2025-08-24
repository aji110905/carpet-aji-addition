package aji;

import aji.utils.CarpetAjiAdditionTranslations;
import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CarpetAjiAdditionServer implements ModInitializer, CarpetExtension {
	public static final String MOD_ID = "carpet-aji-addition";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        CarpetServer.manageExtension(new CarpetAjiAdditionServer());
	}

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetAjiAdditionSettings.class);
    }

    @Override
    public String version() {
        return MOD_ID;
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return CarpetAjiAdditionTranslations.getFabricCarpetTranslations(lang);
    }
}