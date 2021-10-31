package com.company;

import java.util.ArrayList;
import java.util.List;

// ASS 2 Q2b
class Q2b {



    //设置全局列表存储最后的结果
    public static int a;



    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        letterCombinations("FHGEDCAB", list);
        System.out.println(list.toString());
        System.out.println(list.size());
        System.out.println(Q2b.a);

    }



    // digits = "ABCDEFGH"
    public static List<String> letterCombinations(String digits, List<String> list) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"1234", "1234","1234", "1234","1234", "1234","1234", "1234"};
        StringBuilder temp = new StringBuilder();
        //迭代处理
        backTracking(digits, numString, 0, list, temp, new ArrayList<Character>());
        return list;

    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild


    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    public static void backTracking(String digits, String[] numString, int num, List<String> list, StringBuilder temp, ArrayList<Character> path) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的可使用字符串
        String str = numString[digits.charAt(num) - 'A'];
        path.add(digits.charAt(num));


        for (int i = 0; i < str.length(); i++) {

            if(checkConstraint(digits.charAt(num), str.charAt(i), temp, digits, path)){
                temp.append(str.charAt(i));
                backTracking(digits, numString, num + 1, list, temp, path);
                temp.deleteCharAt(temp.length() - 1);
            } else {
                Q2b.a++;

            }




            //c
//            backTracking(digits, numString, num + 1, list, temp);
            //剔除末尾的继续尝试

        }
    }

    private static boolean checkConstraint(char currChar, char currCharValue, StringBuilder temp, String digits, ArrayList<Character> path) {
        System.out.println("currChar: "+currChar);
        System.out.println("temp: "+temp);
        System.out.println("currChar - 'A': " + (currChar - 'A'));


        if (currChar == 'H'){
            return currCharValue != temp.charAt(0);
        } else if (currChar == 'G'){
            return currCharValue != temp.charAt(0);
        } else if (currChar == 'E'){
            return currCharValue != temp.charAt(1) - 2;
        } else if (currChar == 'D'){
            return currCharValue - 1> temp.charAt(3) && currCharValue != temp.charAt(1) && currCharValue >= temp.charAt(2) && currCharValue != temp.charAt(0);
        } else if (currChar == 'C'){
            return Math.abs(currCharValue - temp.charAt(2)) == 1&& (currCharValue - temp.charAt(1))%2 == 0&& currCharValue != temp.charAt(4) && currCharValue != temp.charAt(0);
        } else if(currChar == 'A'){
            return currCharValue >= temp.charAt(2) && currCharValue < temp.charAt(1);
        } else if (currChar == 'B'){
            return Math.abs(currCharValue - temp.charAt(0)) == 1;
        }

        return true;
    }


}