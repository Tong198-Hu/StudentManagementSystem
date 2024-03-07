import java.util.ArrayList;

public class demo2 {
    public static void main(String[] args) {
//        int a =3;
//        while (true){
//            if(a>0) {
//                System.out.println(a);
//                a--;
//            }
//            else{break;}
//        }
        ArrayList<User> list = new ArrayList<>();
        User u = new User("0", "0", "0", "0");
        list.add(u);

        System.out.println(getIndex(list, "0"));
    }

    public static int getIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username)) {
//                System.out.println(list.get(i).getUsername());
                return i;
            }
        }
        return -1;
    }

}