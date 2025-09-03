package aji.carpetajiaddition;

import aji.carpetajiaddition.utils.TranslationsUtils.getTranslationsMap;
import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CarpetAjiAdditionMod implements ModInitializer {
	public static final String MOD_ID = "carpetajiaddition";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String VERSION = "1.0.0";

	@Override
	public void onInitialize() {
        CarpetServer.manageExtension(new CarpetAjiAdditionExtension());
	}
}