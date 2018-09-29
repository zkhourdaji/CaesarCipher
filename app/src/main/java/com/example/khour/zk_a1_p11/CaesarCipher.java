package com.example.khour.zk_a1_p11;

/*
This Caesar Cipher works only with lower case letters and spaces.
 */
public class CaesarCipher {

    /*
    this method takes a string message and a private key,
    it applies the caesar cipher and encrypt the message with the key
    spaces are NOT encrypted
     */
    public static String encrypt(String message, int key) {
        int[] messageIntArray = convertMessageToArrayOfInt(message);
        int[] encryptedMessageIntArray = new int[messageIntArray.length];
        String encryptedMessageStr = "";
        for (int i = 0; i < messageIntArray.length; i++) {
            // dont encrypt spaces
            if (messageIntArray[i] == ' ') {
                encryptedMessageStr += " ";
            } else {
                encryptedMessageIntArray[i] = (messageIntArray[i] + key) % 26;
                encryptedMessageStr += Character.toString((char) (encryptedMessageIntArray[i] + 97));
            }
        }
        return encryptedMessageStr;
    }

    /*
    this method takes a message string and a private key and applies the caesar cipher
    to decrypt the message using the key
    spaces are NOT decrypted
     */
    public static String decrypt(String message, int key) {
        int[] encryptedMessageIntArray = convertMessageToArrayOfInt(message);
        int[] decryptedMessageIntArray = new int[encryptedMessageIntArray.length];
        String decryptedMessageStr = "";
        for (int i = 0; i < encryptedMessageIntArray.length; i++) {
            // dont decrypt spaces
            if (encryptedMessageIntArray[i] == ' ') {
                decryptedMessageStr += " ";
            } else {
                int positive = getPositive(encryptedMessageIntArray[i] - key);
                decryptedMessageIntArray[i] = positive % 26;
                decryptedMessageStr += Character.toString((char) (decryptedMessageIntArray[i] + 97));
            }
        }
        return decryptedMessageStr;
    }

    /*
    this private method returns the first positive integer mod 26
     */
    private static int getPositive(int n) {
        int positive = n;
        while (positive < 0) {
            positive += 26;
        }
        return positive;
    }

    /*
    this private method takes a message string and returns an array of integers
    in which the lower case letters of the alphabet correspond to numbers
    a -> 0
    b -> 1
    c -> 2
    ...
    note that space character and is not converted and is kept as is
     */
    private static int[] convertMessageToArrayOfInt(String message) {

        int[] messageIntArray = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == ' ') {
                messageIntArray[i] += ' ';
            } else {
                messageIntArray[i] = c - 97;
            }
        }
        return messageIntArray;
    }
}
