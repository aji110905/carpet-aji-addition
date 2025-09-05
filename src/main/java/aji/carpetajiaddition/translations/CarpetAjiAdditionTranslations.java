package aji.carpetajiaddition.translations;

import java.util.Map;

public class CarpetAjiAdditionTranslations {
    private static Map<String, String> CarpetAjiAdditionTranslations;

    public static void readLanguageFiles(String lang){
        CarpetAjiAdditionTranslations = getTranslationsMap.getCarpetAjiAdditionTranslations(lang);
    }
    public static String tr (String path){
        return CarpetAjiAdditionTranslations.get(path);
    }
}
