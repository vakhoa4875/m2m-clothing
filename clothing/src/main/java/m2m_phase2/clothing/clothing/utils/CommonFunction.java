package m2m_phase2.clothing.clothing.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;

public class CommonFunction {
    @SneakyThrows
    public static String handleBase64Img(String str, String savedDir) {
        String name = str.substring(0, str.indexOf(","));
        int viTriDauPhayThuHai = str.indexOf(",", str.indexOf(",") + 1);
        String data = str.substring(viTriDauPhayThuHai + 1);
        byte[] bytes = Base64.decodeBase64(data);
        File file = new File(savedDir, name);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        return name;
    }
}
