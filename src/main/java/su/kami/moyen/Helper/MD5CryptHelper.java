package su.kami.moyen.Helper;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5CryptHelper {
    // 等一下 springboot自带这个玩意啊？     算了还是用这个吧 不想看文档了

    private static final String SALT = "#ZgW5gC6$^r#Q%L7%y";

    public static String encrypt(String content) {
        return DigestUtils.md2Hex(SALT + content);
    }

    public static boolean verify(String content, String md5) {
        return md5.equalsIgnoreCase(encrypt(content));
    }
}
