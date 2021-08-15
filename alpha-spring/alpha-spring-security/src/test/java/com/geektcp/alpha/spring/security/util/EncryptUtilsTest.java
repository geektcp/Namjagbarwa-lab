package com.geektcp.alpha.spring.security.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author haiyang on 2020-04-16 17:17
 */
@Slf4j
public class EncryptUtilsTest {

    public static void main(String[] args) {
        RSA rsa = EncryptUtils.getRsa();

        byte[] encrypt1 = rsa.encrypt(StrUtil.bytes("123", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt1 = rsa.decrypt(encrypt1, KeyType.PrivateKey);
        log.info("1111:{}", StrUtil.str(decrypt1, CharsetUtil.CHARSET_UTF_8));

        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("888888", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PrivateKey);
        log.info("2222:{}", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

        // 每次加密的结果字符串是不一样的，每次解密出的结果却是实际密码
        String aaa = HexUtil.encodeHexStr(encrypt1);
        String bbb = "0aa1ddc746dd643767cd92ad105020b7b28dfe0df817546ad093fb53b54083037ff3205547dedad6d6af63b0d729454b18421f01efe449ab446333e313b3ac429d2af4f1c97a71eb8b25bd6b73f795827f00b1646ff239034828f6bc2a4e0fcbc7bf8fa2b5f176527ab13af836747b1ae814b953d24f5ab524da64858322df59";
        byte[] encryptStr3 = HexUtil.decodeHex(aaa);
        byte[] decrypt3 = rsa.decrypt(encryptStr3, KeyType.PrivateKey);
        log.info("3333:{}", StrUtil.str(decrypt3, CharsetUtil.CHARSET_UTF_8));
        if (Objects.equals(aaa, bbb)) {
            log.info("eq");
        } else {
            log.info("not eq");
            log.info("aaa: {}", aaa);
            log.info("bbb: {}", bbb);
        }
    }

    @Test
    public void getKey() throws Exception {
        String name = "aaaa";
        HashMap<String, String> map = AsymmetricCryptoUtils.generateKeyPair(name);
        log.info("map: {}", JSON.toJSONString(map,true));
        PrivateKey privateKey = AsymmetricCryptoUtils.getPrivateKey(name);
        log.info("privateKey: {}", JSON.toJSONString(privateKey,true));

        PublicKey publicKey = AsymmetricCryptoUtils.getPublicKey(name);
        log.info("publicKey: {}", JSON.toJSONString(publicKey,true));

    }


    @Test
    public void generateToekn() {
        String secret = "Sayo111111111111111111111111Sayo111111111111111111111111Sayo111111111111111111111111Sayo111111111111111111111111";
        String token = Jwts.builder()
                .setSubject("test")
//                .setExpiration(expireTime)
//                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        log.info("token: {}", token);
        Assert.assertTrue(true);
    }
}
