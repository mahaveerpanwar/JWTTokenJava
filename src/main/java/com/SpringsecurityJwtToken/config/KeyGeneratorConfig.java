package com.SpringsecurityJwtToken.config;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
  public class KeyGeneratorConfig {


    @Value("${app.privateKey}")
    private String privateKey; //Encoded private key string


    @Value("${app.publicKey}")
    private String publicKey;//Encoded public key string
    
    
    String privately ="TUlJQ1hnSUJBQUtCZ1FEbmJjTFNsREZhRE1oYWxjbVFnY2xURm9icGtIUUhKdHhNVkdSbGJ2N3prbnR0QVZiWQ0KMWp6R2pKNkhWdXBuZHpEeEE5dGJpTWpRdWptR2xTLzhnNUlFYlZzUjlvNmRtY21idnVqdEVaMnJIWjgydE1ZUA0KVkF0MklvUy9XL3EyUnIxY0FaL3pUS0VtaDBaWmp6Q1pGdWVMZnJZUG0zYW01SkxjWGdWdGJLd3liUUlEQVFBQg0KQW9HQkFKNDQxb2V0dFlnQlVVRk5RdjgvSEd0bjdWamwzODI3N2NWcHRUSDhEdVpyOFdKM0ZlOHRtV09OWkJ6WA0KZVc2L2VJQnV5SnZ1Q28xWnBGYTB6SmZ4US9QaDZRbFF3ZE41MEdOZmg5UnpTUzZsRGRmeThCUmhjMjdzeXBYUw0KTDZjNWxqQjZxbCtwcDNEZHhGaEpNT3MzWm1CSmRleVdlN3VGcmtuZ3RuTTFueFpCQWtFQSsxaGJWMVEzMDV3YQ0KdThZTUYxU2xOSUFmZ0xKN2J1RDQzU0VYbGUwZWd6NDA1UEZHOGY4eURtdlJPd0RpUmNlSUxHVnJSYkluZDdDYg0KZHZKS3IzNFdPUUpCQU91MityZUc0NHJOdWlYZUdYMU1ZZzZUbFdZeUFCbTdQclRyaFBaa2Vkb2RPUUI4cDd6RA0KQXF0RFNLN1JuRENvVGhuZFBXNmtkTkFlQitrRzR1ZzVYZFVDUUhSRFU4VWFqTlJTa2o4bmhqSklrajZ0d1dTNw0KcXNNSVI3V3ArQW4rN0MxVFdnNUkyVU5aZzJNT1ZuTlBubHNleUF1WlFqeTBBdk9uZXRKVGsxNklHV2tDUVFDTA0KRlViT3I4cm5oZ2lHZTR5eXdEVkR3SlZ3M2FQdGl1eXZPQ0VXZWFia3FrV09JZitmZzdtNWNGUWN3eFhVS0JzZA0KYTh2cDB5UVNBUVpOMjRCYjRpMlpBa0VBOHhHSkZsRkRZOUhSRVdabkRleTVTVGdiVWVUMXdZa3lLY0RzVXJwMQ0Ka1IvM0JsaUdxU0lmamUrbVNLRElacWFQK2dhaS84YklBQllBc0RQL3Q2K2N1QT09";


    @Bean
    public PrivateKey generatePrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privKeySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privately));
        return kf.generatePrivate(privKeySpecPKCS8);
    }
    
    

    @Bean
    public PublicKey generatePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec pubKeySpecX509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        return kf.generatePublic(pubKeySpecX509EncodedKeySpec);
    }
    
    
}