package aji.carpetajiaddition;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarpetAjiAdditionMod implements ModInitializer {
	public static final String MOD_ID = "carpetajiaddition";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String VERSION = "1.0.0";
    public static MinecraftServer minecraftServer;

	@Override
	public void onInitialize() {
        CarpetServer.manageExtension(new CarpetAjiAdditionExtension());
	}
}