package com.pdabrowski.WeatherApp.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class Decryptor {

    public static String decrypt(String encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String secret = "b14ca5898a4e4133bbce2ea2315a1916";
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

        String ivString = "some16byteString";
        byte[] ivBytes = ivString.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decodedEncryptedData = Base64.getDecoder().decode(encrypted);
        byte[] original = cipher.doFinal(decodedEncryptedData);

        return new String(original, StandardCharsets.UTF_8);
    }
}