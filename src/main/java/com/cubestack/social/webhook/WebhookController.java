package com.cubestack.social.webhook;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhook/twitter")
@PermitAll
public class WebhookController {
    private static final Logger LOG = Logger.getLogger(WebhookController.class);

    private final String appSecret;
    private final CRCTokenGenerator crcTokenGenerator;

    public WebhookController(@Value("app.secret") String appSecret, CRCTokenGenerator crcTokenGenerator) {
        this.appSecret = appSecret;
        this.crcTokenGenerator = crcTokenGenerator;
    }

    @GetMapping
    @PermitAll
    public Map<String, String> handleCrc(@RequestParam("crc_token") String crcToken) {
        LOG.info(String.format("In handleCrc, with Request CrcToken %s", crcToken));
        Map<String, String> response = new HashMap<>();
        String SHAToken = "";
        try {
            SHAToken = crcTokenGenerator.generateSHA(appSecret, crcToken);
        } catch (Exception e) {
            LOG.error("In handleCrc, exception while generating SHA  ", e);
        }
        response.put("response_token", SHAToken);
        return response;
    }

    @PostMapping
    @PermitAll
    public Map<String, String> handleEvents(@RequestBody Map<String, Object> request) {
        LOG.info(String.format("In handleEvents, with Request %s", request));
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
