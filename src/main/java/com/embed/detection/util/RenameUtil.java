package com.embed.detection.util;
import java.util.Random;

public class RenameUtil {
    public static String  creatFileName(){
        Random random = new Random();
        int length  = 10;
        String numstr = "123456789";
        String chastr_b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        String chastr_s = "abcdefghijklmnopqrstuvwxyz";
        String specil = "_";
        String base = numstr+chastr_b+chastr_s+specil;
        StringBuffer sb =  new StringBuffer();
        sb.append(chastr_b.charAt(random.nextInt(chastr_b.length())));
        for(int i =0 ;i <length-2;i++){
            int num = random.nextInt(base.length());
            sb.append(base.charAt(num));
        }
        sb.append(numstr.charAt(random.nextInt(numstr.length())));
        return sb.toString();
    }
}