package com.cubestack.social.webhook;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
public final class CRCTokenGenerator {
    private static final Logger LOG = Logger.getLogger(CRCTokenGenerator.class);

    public String generateSHA(String appSecret, String crcToken) throws Exception {
        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(appSecret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256");
        sha256HMAC.init(secret_key);
        String hash = String.format("sha256=%s", Base64.encodeBase64String(sha256HMAC.doFinal(
                crcToken.getBytes(StandardCharsets.UTF_8))));
        LOG.info(String.format("Exiting  generateSHA with hash %s", hash));
        return  hash;
    }
}
