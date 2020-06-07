package com.company;

import java.util.*;
import java.math.BigInteger;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String alphabet="abcdefghijklmnopqrstuvwxyz";
        System.out.println("Enter p and q such that they are prime");
        int p=in.nextInt();
        int q=in.nextInt();
        System.out.println("Enter your plain text");
        String plainT=in.nextLine();
        plainT+=in.nextLine();
        in.close();
        String plainT1=plainT.toLowerCase().replaceAll("\\s", "");
        int n = p*q;
        int f = (p-1)*(q-1);
        int []temp = new int[f];
        int count = 0;
        int count1=0;
        //finding e such that it is relatively prime to f
        for (int i = 1;i<f;i++) {
            int gcd = findGcd(i,f);
            if(gcd==1) {
                temp[count]=i;
                count++;

            }
        }
        int []exponents = new int[count];
        for(int i = 0;i<temp.length;i++) {

            if(temp[i]>0) {
                exponents[count1]=temp[i];
                count1++;

            }
        }
        //randomizing e
        int rnd=new Random().nextInt(exponents.length);
        int e = exponents[rnd];
        //generating d
        int []temp1=new int [f];
        int count2=0;
        for(int i = 1;i<f;i++) {
            int remaining=(e*i)%f;
            if(remaining==1) {
                temp1[count2]=i;
                count2++;
            }
        }
        int count3=0;
        int []decExponents=new int [count2];
        for(int i = 0 ;i<count2;i++) {
            if(temp1[i]>0) {
                decExponents[count3]=temp1[i];
                count3++;
            }

        }
        //randomizing d
        int rnd1 = new Random().nextInt(decExponents.length);
        int d = decExponents[rnd1];
        for(int i=0;i<count1;i++) {
            System.out.println("Possible value of e = " + exponents[i]);

        }
        System.out.println("____________________________________");
        System.out.println("Random value of e is "+ e + " ");
        System.out.println("____________________________________");

        for(int i=0;i<count3;i++) {
            System.out.println("d is " + decExponents[i]);
        }
        System.out.println("_____________________________________");
//Encryption

        String decryptText = "";
        System.out.println("Encrypted message is: ");
        for(int i = 0;i<plainT1.length();i++) {
            for(int j=0;j<alphabet.length();j++) {
                if(plainT1.charAt(i)==alphabet.charAt(j)) {
                    BigInteger y = BigInteger.valueOf(j+1);
                    BigInteger res=y.pow(e);
                    BigInteger result=res.mod(BigInteger.valueOf(n));
                    System.out.println(result);
                    decryptText+=decryption(result,d,alphabet,n);
                }

            }

        }
        System.out.println("Decrypted text is: " + decryptText);


    }
    public static String decryption(BigInteger result, int d, String alphabet, int n) {
        String decrypted = "";
        BigInteger prod = result.pow(d);
        BigInteger mod1=prod.mod(BigInteger.valueOf(n));
        for(int i = 0;i<alphabet.length();i++) {
            if(BigInteger.valueOf(i).equals(mod1)) {
                decrypted+= alphabet.charAt(i-1);

            }

        }

        return decrypted;
    }
    public static int findGcd(int n1, int n2) {
        if(n2 == 0){
            return n1;
        }
        return findGcd(n2, n1%n2);
    }

}
