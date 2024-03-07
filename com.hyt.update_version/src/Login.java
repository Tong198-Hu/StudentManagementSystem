import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        while (true){
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出系统");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose){
                case "1" -> login(list);
                case "2" -> registration(list);
                case "3" -> updatePSW(list);
                case "4" -> System.exit(0);
                default -> System.out.println("请输入正确的操作指令！");
            }
        }
    }
    public static void updatePSW(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名: ");
        String username = sc.next();
        int index = getIndex(list,username);
        if(index >= 0){
            System.out.println("请输入身份证和手机号码: ");
            String id_card = sc.next();
            String phoneNumber = sc.next();
            if(id_card.equals(list.get(index).getId_card()) && phoneNumber.equals(list.get(index).getPhone_number())){
                System.out.println("请输入修改的密码: ");
                String psw = sc.next();
                list.get(index).setPassword(psw);
                System.out.println("密码修改成功！");
//                System.out.println(list.get(index).toString());
            }
            else{
                System.out.println("账号信息不匹配，修改失败！");
            }
        }
        else{
            System.out.println("当前用户名不存在,请前往大厅注册！");
        }
    }
    public static void login(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = sc.next();
        int index = getIndex(list,username);
//        System.out.println(index);
        if(index >= 0){
                System.out.print("请输入右边的验证码:    ");
                String verify = createVerifyCode();
                System.out.println(verify);
                String verifyCode = sc.next();
                if(verifyCode.equals(verify)) {
                    System.out.println("验证码输入正确！");
                    int count = 3;
                    for (int i = 0; i < count; i++) {
                        System.out.println("请输入密码:");
                        String psw = sc.next();
                        if(!psw.equals(list.get(index).getPassword())){
                            System.out.println("密码输入错误,请重新输入！");
                        }
                        else{
                            System.out.println("登陆成功！");
//                            new main_interface();

                            break;
                        }
                    }
                }
        }
        else{
            System.out.println(index);
            System.out.println("该用户名未注册，请先注册！");
        }
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
    public static void registration(ArrayList<User> list){
        User u = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = sc.next();
        if(contains(list,username)){
            System.out.println("当前用户名已存在，无法进行注册！");
        }
        else if(!(username.length() >= 3 && username.length() <= 15)){
            System.out.println("用户名长度非法！");
            }
        else if(!isLegalUsername(username)){
            System.out.println("输入的用户名必须是字母与数字的组合，不能是纯数字！");
        }
        else{
            u.setUsername(username);

            System.out.println("请输入密码:");
            String psw = sc.next();
            System.out.println("请再次输入密码:");
            String psw1 = sc.next();
            if(psw.equals(psw1)){
                System.out.println("密码一致！");
                u.setPassword(psw);
                System.out.println("请输入身份证号码:");
                String id_card = sc.next();
                if(id_card.length() == 18){
                    if(isLegalIdCard(id_card)){
                        u.setId_card(id_card);
                        System.out.println("请输入手机号码:");
                        String phoneNumber = sc.next();
                        if(phoneNumber.length() == 11){
                            if(isLegalPhoneNumber(phoneNumber)){
                                u.setPhone_number(phoneNumber);
                                list.add(u);
                                System.out.println("恭喜你注册成功！");
//                                System.out.println(list.get(0).toString());
                            }
                            else{
                                System.out.println("您输入的手机号码非法！");
                            }
                        }
                        else{
                            System.out.println("您输入的手机号码长度非法！");
                        }
                    }
                    else{
                        System.out.println("您输入的身份证号码非法！");
                    }
                }
                else{
                    System.out.println("身份证号码长度非法！");
                }

            }
            else{System.out.println("两次密码输入不一致！注册失败！");}
        }
        }

    // 判断一个字符是否为数字
    public static boolean isNumber(char chr){
        if(chr>='0' && chr<='9') return true;
        return false;
    }

    // 判断一个字符是否为字母
    public static boolean isAlpha(char chr){
        if((chr>='a' && chr<='z') || (chr>='A' && chr<='Z')) return true;
        return false;
    }
    // 判断一个用户名是否合法
    public static boolean isLegalUsername(String username){
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            if(isNumber(username.charAt(i)) || isAlpha(username.charAt(i))){
                if(isAlpha(username.charAt(i))) count++;
            }
        }
        return count >0;
    }
    // 判断一个身份证是否合法
    public static boolean isLegalIdCard(String id_card){
        for (int i = 0; i < id_card.length()-1; i++) {
            if(id_card.charAt(0) == '0' || !isNumber(id_card.charAt(i))) return false;
        }
        if(isNumber(id_card.charAt(id_card.length()-1)) || id_card.charAt(id_card.length()-1) == 'x' || id_card.charAt(id_card.length()-1) == 'X') return true;
        return false;
    }
    // 判断一个手机号码是否合法
    public static boolean isLegalPhoneNumber(String phoneNumber){
        if(phoneNumber.charAt(0) == '0') return false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(!isNumber(phoneNumber.charAt(i))) return false;
        }
        return true;
    }
    //判断是否包含用户名
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
