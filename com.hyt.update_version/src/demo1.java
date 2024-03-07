import java.util.Random;
import java.util.StringJoiner;

public class demo1 {
    public static void main(String[] args) {
//        String s= "1235n";
//        System.out.println(isLegalPhoneNumber(s));
//
//        char[] alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//        for (int i = 0; i < alpha.length; i++) {
//            System.out.println(alpha[i]);
//        }
        System.out.println(createVerifyCode());




    }
    public static boolean isNumber(char chr){
        if(chr>='0' && chr<='9') return true;
        return false;
    }
    public static boolean isLegalPhoneNumber(String phoneNumber){
        if(phoneNumber.charAt(0) == '0') return false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(!isNumber(phoneNumber.charAt(i))) return false;
        }
        return true;
    }
    public static String createVerifyCode(){
        char[] alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random r = new Random();
        int index2 = r.nextInt(0,5);
        int index3 = r.nextInt(0,10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index1 = r.nextInt(0,52);
            sb.append(alpha[index1]);
        }
        sb.insert(index2,index3);
        return sb.toString();
    }



}
