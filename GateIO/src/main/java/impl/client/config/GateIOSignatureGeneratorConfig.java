package impl.client.config;

import feign.RequestInterceptor;
import impl.utils.HmacUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class GateIOSignatureGeneratorConfig
{
    RequestInterceptor requestInterceptor(GateIOConfigProperties.AccountConfig accountConfig) {
        return requestTemplate -> {
            System.out.println("\tgenerating signature.....");
            System.out.println("\t\tkey   : " + accountConfig.getKey());
            System.out.println("\t\tsecret: " + accountConfig.getSecretKey());

            long expires = System.currentTimeMillis() / 1000 + 300;
            var path = requestTemplate.path();
            var queryLine = requestTemplate.queryLine();
            var signatureContent = requestTemplate.method() + path + queryLine + expires;
            if (requestTemplate.body() != null) {
                signatureContent += new String(requestTemplate.body(), StandardCharsets.UTF_8);
            }
            requestTemplate.header("api-key", accountConfig.getKey());
            requestTemplate.header("api-signature", getSignature(signatureContent, accountConfig.getSecretKey()));
            requestTemplate.header("api-expires", String.valueOf(expires));
        };
    }

    private String getSignature(String signatureContent, String secretKey) {
        return Hex.encodeHexString(HmacUtils.getSha256(signatureContent, secretKey));
    }
}
