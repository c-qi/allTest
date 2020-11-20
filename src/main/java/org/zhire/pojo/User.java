package org.zhire.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class User implements Serializable {
    private String name;
    private String password;
    private String userName;
    private String passWord;
    private String email;
    private Integer age;
    private Long managerId;


    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public static void remove(List<User> userList) {
        // 删除userList年龄大于20的用户，即留下年龄小于20的用户
        userList = userList.stream().filter(user -> user.getAge() < 20).collect(Collectors.toList());
        System.out.println(userList);
    }

    public static void print(int[] array) {
        // 排序
        Arrays.sort(array);
        // 使用set保存最小的10个数 自动去重
        HashSet<Integer> set = new HashSet<>();
        for (int anArray : array) {
            if (set.size() == 10) {
                break;
            }
            set.add(anArray);
        }
        System.out.println(set);
    }

    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        User user2 = new User();
        User user3 = new User();
        user.setUserName("11");
        user.setAge(19);
        user2.setUserName("22");
        user2.setAge(18);
        user3.setUserName("33");
        user3.setAge(20);
        list.add(user);
        list.add(user2);
        list.add(user3);
        remove(list);
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = i;
        }
        print(array);
    }
}
