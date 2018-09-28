package com.apps.morfiwifi.morfi_project_samane.util;

import android.util.Log;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class Security {
    static {
        System.loadLibrary("keys");
    }

    public static native String getraw();


    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public byte[] Encript ( String input){
        byte[] key = new byte[8];
        byte[] encrypt = new byte[8];
        try {
            byte[] keyStart = getraw().getBytes();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();
            encrypt = encrypt(key ,input.getBytes());
        }catch (Exception e){
            Log.d("EX ENC :" , e.getMessage());
        }

        return encrypt;
    }
    public static String Encript_STR (String input){
        byte[] key = new byte[8];
        byte[] encrypt = new byte[8];
        try {
            byte[] keyStart = getraw().getBytes();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();
            encrypt = encrypt(key ,input.getBytes());
        }catch (Exception e){
            Log.d("EX ENC :" , e.getMessage());
        }

        return byte_code(encrypt);
    }


    public static byte[] Decript ( String input){
        byte[] key = new byte[8];
        byte[] dec = new byte[8];
        try {
            byte[] keyStart = getraw().getBytes();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();
           dec = decrypt(key , input.getBytes());
        }catch (Exception e){
            Log.d("EX DEC :" , e.getMessage());
        }

        return dec;
    }
    public static void print (String s){
        System.out.println(s);
    }
    public static String bytToStr(byte[] bytes){
        /*String s = "";
        for (Byte b : bytes) {
            s = s + String.valueOf(((char) b));
        }*/
        return new String(bytes);
    }
    public static  void test (){
        /*String s = "https://parseapi.back4app.com/";
        print("INPUT : " + s);

        byte[] input = new byte[s.length()] ;
        int i = 0;
        for (char c: s.toCharArray()) {
            input[i] = (byte) c;
            i++;
        }*/




/*
        try {
            byte[] encrypt = encrypt(key ,input);
            print( "ENRYPTED : "+ bytToStr(encrypt));
            byte[] dec = decrypt(key , encrypt);
            print( "DECRIPTED : "+ bytToStr(dec));
//            print(byt_to_str_wrong(dec));


            String doc1 = new String(encrypt, "UTF-8");
            String doc2 = new String(dec, "UTF-8");

            print("UTF-8 1 : "+doc1);
            print("UTF-8 2 : "+doc2);

            String byte_code = "_56_-99_-126_64_40_-103_15_1_37_49_108_-118_12_-53_-18_-29_107_64_-77_-73_-6_-90_115_-75_5_-49_-16_-16_-48_125_28_98";
//                    byte_code(encrypt);
            print("BYTE CODE : "+byte_code);

            byte[] enc_decoded = string_decode(byte_code);
            byte[] dec2 = decrypt(key , enc_decoded);
            print( "DECRIPTED BYTECODE : "+ bytToStr(dec2));


//            byte[] decoded = string_decode(byte_code);
//            print("String To byte DECODE : "+new String(decoded));
//            String max = new String(decoded);
//            print("is EQUAL :" + max.equals(s) );

//            byte[] bytes = doc.getBytes("UTF-8");


            // Custom DEC
//            byte[] enc_cu =  string_byte("G���F�,�7\u0013+;�M*\u0015��M�\u0010�z*x*�l�r\f�$M���q\u001B>O�{\u000B����");
//            byte[] dec2 = decrypt(key , enc_cu);
//            print( "DECRIPTED CU : "+bytToStr(dec2));


        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    public static String Decript_STR ( String input){
        test();// just TEST ...
        byte[] key = new byte[8];
        byte[] dec = new byte[8];
        byte[] keyStart = "SOME ENQ KEY AS INPUT".getBytes();
//        byte[] keyStart = getraw().getBytes();
//        byte[] key = new byte[8];
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();

            Log.d("INPUT STR :" , input);
            byte[] decoded = string_decode(input);
            dec = decrypt(key ,decoded );
        }catch (Exception e){

            print(e.getMessage());
        }

        try {

        }catch (Exception e){
            print(e.getMessage());
        }



        return new String(dec);
    }

    public static byte[] string_decode (String s){
        String[] mes = s.split("_");
        boolean replase_index = false;
        if (mes.length > 0){
            if (mes[0].equals("")){
                replase_index = true;
            }
        }
        byte[] res;
        if (replase_index){
            res = new byte[mes.length-1];
        }else {
            res = new byte[mes.length];
        }

        if (replase_index){
            for (int i = 1; i < mes.length; i++) {
                res[i-1] = (byte) Integer.parseInt(mes[i].replace("_" , ""));
            }
        }else {
            for (int i = 0; i < mes.length; i++) {
                res[i] = (byte) Integer.parseInt(mes[i].replace("_" , ""));
            }
        }
        return res;
    }

    public static String byte_code (byte[] bytes){
        String s = "";
        for (byte b: bytes) {
            s = s +"_"+ ((int)b);
        }
        return s;
    }
}
