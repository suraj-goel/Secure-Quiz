package mainClasses;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//      generates a unique hash by using mac address of pc and current timestamp//
public class HashGenerator {
    public static String hash(String s){
        String ans=null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] str=s.getBytes();
            byte[] hashstr=messageDigest.digest(str);
//            ans = new String(hashstr);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<str.length;i++){
//                truncating the MD5 hash generated so that it can be stored in SQL Database..
                sb.append(Integer.toString((str[i] & 0xff)+0x100,16)).substring(1);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
