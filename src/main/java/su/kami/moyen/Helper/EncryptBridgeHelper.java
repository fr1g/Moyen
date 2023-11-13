package su.kami.moyen.Helper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptBridgeHelper implements PasswordEncoder {
    @Override
    public String encode(CharSequence raw) {
        return MD5CryptHelper.encrypt(raw.toString());
    }

    @Override
    public boolean matches(CharSequence raw, String s) {
        return MD5CryptHelper.verify(raw.toString(), s.replace("{noop}", ""));
    }

}
