import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出系统");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose) {
                case "1" -> login(list);
                case "2" -> registration(list);
                case "3" -> updatePSW(list);
                case "4" -> {
                    System.out.println("谢谢使用,再见!");
                    System.exit(0);
                }
                default -> System.out.println("请输入正确的操作指令！");
            }
        }
    }

    private static void updatePSW(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = sc.next();
        boolean flag = contains(list,username);
        if(!flag){
            System.out.println("当前用户"+username+"未注册,请先注册");
            return;
        }
        System.out.println("请输入身份证和手机号码: ");
        String id_card = sc.next();
        String phoneNumber = sc.next();

        int index = getIndex(list,username);
        User u = list.get(index);
        if(!(u.getId_card().equalsIgnoreCase(id_card) && u.getPassword().equals(phoneNumber))){
            System.out.println("身份证号码或手机号码输入有误,不能修改密码");
            return;
        }
        String psw;
        while (true){
            System.out.println("请输入新的密码");
            psw = sc.next();
            System.out.println("请再次输入新的密码");
            String againPsw = sc.next();
            if(psw.equals(againPsw)){
                System.out.println("两次密码输入一致");
                break;
            }
            else{
                System.out.println("两次密码输入不一致,请重新输入");
                continue;
            }
        }

        u.setPassword(psw);





    }
    private static void registration(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username;
        String psw;
        String id;
        String phoneNumber;
        while (true) {
            System.out.println("请输入用户名:");
            username = sc.next();
            // 判断格式是否正确
            boolean flag = checkUserName(username);
            if(flag){
                System.out.println("用户名输入合法！");
                boolean flag1 = contains(list,username);
                if(flag1){
                    System.out.println("当前数据库中已包含用户名为"+username+"的用户，请重新输入！");
                }
                else{
                    System.out.println("用户名输入正确!进行后续输入！");
                }
                break;
            }
            else{
                System.out.println("用户名输入不正确,请重新输入!");
                continue;
            }
        }
        while (true) {
            System.out.println("请输入密码:");
            psw = sc.next();
            System.out.println("请再次输入密码:");
            String psw1 = sc.next();
            if(psw.equals(psw1)){
                System.out.println("密码一致！");
                break;
            }
            else{
                System.out.println("密码前后输入不一致,请重新输入！");
            }
        }
        while (true) {
            System.out.println("请输入身份证号码:");
            id = sc.next();
            boolean flag = checkID(id);
            if(flag){
                System.out.println("身份证输入正确！");
                break;
            }
            else{
                System.out.println("身份证输入不正确,请重新输入！");
            }
        }
        while (true) {
            System.out.println("请输入手机号:");
            phoneNumber = sc.next();
            boolean flag3 = checkPhoneNumber(phoneNumber);
            if(flag3){
                System.out.println("号码输入合法！");
                break;
            }
            else {
                System.out.println("号码输入非法,请重新输入!");
            }
        }
        User user = new User(username,psw,id,phoneNumber);
        list.add(user);
        System.out.println("注册成功！");
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()!= 11) return false;
        if(phoneNumber.startsWith("0")) return false;
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

    private static boolean checkID(String id) {
        if(id.length()!=18) return false;
        if(id.startsWith("0")) return false;
        for (int i = 0; i < id.length()-1; i++) {
            if(!isNumber(id.charAt(i))) return false;
        }
        char endChr = id.charAt(id.length()-1);
        if(isNumber(endChr) || endChr == 'X' || endChr == 'x') return true;
        return false;
    }

    private static boolean checkUserName(String username) {
        int strlen = username.length();
        if(strlen<3 || strlen>15) return false;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if(!(isAlpha(c) || isNumber(c))) return false;
        }
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if(isAlpha(c)) count++;
            if(count > 0) return true;
        }
        return false;
    }

    private static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名:");
            String username = sc.next();
            boolean flag = contains(list,username);
            if(!flag) {
                System.out.println("用户名"+username+"未注册,请先注册在登陆");
                return;
            }
            System.out.println("请输入密码:");
            String psw = sc.next();
            while (true) {
                String verify = createVerifyCode();
                System.out.print("请输入右边的验证码:    ");
                System.out.println(verify);
                String verifyCode = sc.next();
                if(verifyCode.equalsIgnoreCase(verify)){
                    System.out.println("验证码输入正确！");
                    break;
                }
                else{
                    System.out.println("验证码错误！请重新输入！");
                }
            }

            User userInfo = new User(username,psw,null,null);
            boolean res = checkUserInfo(list,userInfo);
            if(res){
                System.out.println("登陆成功,可以开始使用学生管理系统了");
                main_interface mi = new main_interface();
                mi.main();
                break;
            }
            else {
                System.out.println("登陆失败,用户名或密码错误");
                if(i == 2){
                    System.out.println("当前账号"+username+"被锁定，请练习客服进行修改");
                    return;
                }
                else{
                    System.out.println("用户名或者密码错误,还剩下" +(2-i)+"次机会");
                }
            }
        }
    }

    private static boolean checkUserInfo(ArrayList<User> list, User userInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getUsername().equals(userInfo.getUsername()) && user.getPassword().equals(userInfo)) return true;
        }
        return false;
    }

    public static boolean isNumber(char chr){
        if(chr>='0' && chr<='9') return true;
        return false;
    }

    // 判断一个字符是否为字母
    public static boolean isAlpha(char chr){
        if((chr>='a' && chr<='z') || (chr>='A' && chr<='Z')) return true;
        return false;
    }

    public static boolean contains(ArrayList<User> list, String username){
        return getIndex(list,username)>=0;
    }

    public static int getIndex(ArrayList<User> list, String username){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUsername().equals(username)) {
//                System.out.println(list.get(i).getUsername());
                return i;
            }
        }
        return -1;
    }


}