import java.util.ArrayList;
import java.util.Scanner;

public class main_interface {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        while (true) {
            System.out.println("----------------------欢迎来到学生管理系统-----------------------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("请输入你的选择: ");

            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose){
                case "1" -> addStu(list);
                case "2" -> deleteStu(list);
                case "3" -> updateStu(list);
                case "4" -> queryStu(list);
                case "5" -> System.exit(0);
                default -> System.out.println("没有该选项,请重新输入！");
            }
        }
    }

    //添加学生
    public static void addStu(ArrayList<Student> list){
        Student s = new Student();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入学生id:");
            String id = sc.next();
            if(contains(list,id)){
                System.out.println("当前学生id已存在,请重新输入!");

            }
            else{
                s.setId(id);
                break;
            }
        }
        System.out.println("请输入姓名:");
        String name = sc.next();
        s.setName(name);

        System.out.println("请输入年龄:");
        int age = sc.nextInt();
        s.setAge(age);

        System.out.println("请输入家庭住址:");
        String address = sc.next();
        s.setAddress(address);

        list.add(s);
        System.out.println("学生信息添加成功！");


    }
    //删除学生
    public static void deleteStu(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生id:");
        String id = sc.next();
        int index = getIndex(list,id);
        if(index>=0){
            list.remove(index);
            System.out.println("学生id:"+id+"的记录已删除！");
        }
        else{
            System.out.println("当前学生id不存在！");
        }
    }
    //更新学生
    public static void updateStu(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生id:");
        String id = sc.next();
        int index = getIndex(list,id);
        if(index>=0){
            Student stu = list.get(index);
            System.out.println("请更改姓名:");
            String name = sc.next();
            stu.setName(name);

            System.out.println("请更改年龄:");
            int age = sc.nextInt();
            stu.setAge(age);

            System.out.println("请更该家庭住址:");
            String address = sc.next();
            stu.setAddress(address);
            System.out.println("学生信息修改成功！");
        }
        else{
            System.out.println("当前学生id不存在！");
        }
    }
    //查询学生
    public static void queryStu(ArrayList<Student> list){
        if(list.size() == 0) {
            System.out.println("当前无学生信息,请添加后再查询！");
            return;
        }
        System.out.println("学号"+"\t\t"+"姓名"+"\t"+"年龄"+"\t"+"家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId()+"\t\t"+stu.getName()+"\t"+stu.getAge()+"\t"+stu.getAddress());
        }
    }
    public static boolean contains(ArrayList<Student> list, String id){
        return getIndex(list,id)>=0;
    }

    public static int getIndex(ArrayList<Student> list, String id){
        for(int i =0; i<list.size(); i++){
            if(list.get(i).getId().equals(id)) return i;
        }
        return -1;
    }


}
