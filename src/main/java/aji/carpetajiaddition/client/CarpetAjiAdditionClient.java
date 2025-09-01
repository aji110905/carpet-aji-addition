package aji.carpetajiaddition.client;

import aji.carpetajiaddition.CarpetAjiAdditionServer;
import net.fabricmc.api.ClientModInitializer;

public class CarpetAjiAdditionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CarpetAjiAdditionServer.LOGGER.info("Carpet Aji Addition Client Initialized");
    }
}
