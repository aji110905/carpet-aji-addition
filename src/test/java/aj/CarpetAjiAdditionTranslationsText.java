package aj;

import aji.utils.CarpetAjiAdditionTranslations;
import org.junit.Test;

import java.util.Map;

public class CarpetAjiAdditionTranslationsText {
    @Test
    public void test (){
        Map<String, String> map = CarpetAjiAdditionTranslations.getFabricCarpetTranslations("zh_cn");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
