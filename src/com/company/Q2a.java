package com.company;

import java.util.ArrayList;
import java.util.List;

// ASS 2 Q2 a
class Q2a {



    //设置全局列表存储最后的结果
    public static int a;



    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        letterCombinations("ABCDEFGH", list);
        System.out.println(list.toString());
        System.out.println(list.size());
        System.out.println(Q2a.a);

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
        backTracking(digits, numString, 0, list, temp);
        return list;

    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild


    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    public static void backTracking(String digits, String[] numString, int num, List<String> list, StringBuilder temp) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的可使用字符串
        String str = numString[digits.charAt(num) - 'A'];


        for (int i = 0; i < str.length(); i++) {


//            temp.append(str.charAt(i));






            if(checkConstraint(digits.charAt(num), str.charAt(i), temp, digits)){
                temp.append(str.charAt(i));
                backTracking(digits, numString, num + 1, list, temp);
                temp.deleteCharAt(temp.length() - 1);
            } else {
                Q2a.a++;

            }




            //c
//            backTracking(digits, numString, num + 1, list, temp);
            //剔除末尾的继续尝试

        }
    }

    private static boolean checkConstraint(char currChar, char currCharValue,  StringBuilder temp, String digits) {
        System.out.println("currChar: "+currChar);
        System.out.println("temp: "+temp);
        System.out.println("currChar - 'A': " + (currChar - 'A'));



        if (currChar == 'D'){
            return currCharValue != temp.charAt('C' - 'A');
        } else if (currChar == 'E'){
            return currCharValue != temp.charAt('C' - 'A') && currCharValue < temp.charAt('D' - 'A')- 1;
        } else if (currChar == 'F'){
            return Math.abs(currCharValue - temp.charAt('B' - 'A')) == 1 && currCharValue != temp.charAt('C' - 'A') && currCharValue != temp.charAt('D' - 'A') && Math.abs(currCharValue - temp.charAt('E'-'A'))%2 == 1;
        } else if (currChar == 'G'){
            return currCharValue <= temp.charAt('A'-'A')&& Math.abs(currCharValue - temp.charAt('C' - 'A')) == 1&& currCharValue <= temp.charAt('D'-'A')&& currCharValue != temp.charAt('F' - 'A');
        } else if (currChar == 'H'){
            return currCharValue > temp.charAt('A' - 'A')&& currCharValue > temp.charAt('G' - 'A')&& Math.abs(currCharValue - temp.charAt('C' - 'A'))%2 == 0&& currCharValue != temp.charAt('D' - 'A') && currCharValue != temp.charAt('E' - 'A') - 2 && currCharValue != temp.charAt('F' - 'A');
        }


//        for(int numofAsc = 0; numofAsc < (currChar - 'A'); numofAsc++){
//            Character prevChar = digits.charAt(numofAsc);
//
//            if(prevChar == 'A' && currChar == 'G'){
//                return currCharValue >= temp.charAt(numofAsc);
//            } else if(prevChar == 'A' && currChar == 'H'){
//                return currCharValue > temp.charAt(numofAsc);
//            } else if(prevChar == 'B' && currChar == 'F'){
//                return Math.abs(currCharValue - temp.charAt(numofAsc)) == 1;
//            } else if(prevChar == 'G' && currChar == 'H'){
//                return currCharValue > temp.charAt(numofAsc);
//            } else if(prevChar == 'C' && currChar == 'G'){
//                return Math.abs(currCharValue - temp.charAt(numofAsc)) == 1;
//            } else if(prevChar == 'C' && currChar == 'H'){
//                return Math.abs(currCharValue - temp.charAt(numofAsc))%2 == 0;
//            } else if(prevChar == 'D' && currChar == 'H'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'D' && currChar == 'G'){
//                return currCharValue <= temp.charAt(numofAsc);
//            } else if(prevChar == 'C' && currChar == 'D'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'C' && currChar == 'E'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'D' && currChar == 'E'){
//                return currCharValue < temp.charAt(numofAsc)- 1;
//            } else if(prevChar == 'E' && currChar == 'H'){
//                return currCharValue != temp.charAt(numofAsc) - 2;
//            } else if(prevChar == 'F' && currChar == 'G'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'F' && currChar == 'H'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'C' && currChar == 'F'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'D' && currChar == 'F'){
//                return currCharValue != temp.charAt(numofAsc);
//            } else if(prevChar == 'E' && currChar == 'F'){
//                return Math.abs(currCharValue - temp.charAt(numofAsc))%2 == 1;
//            }
//
//        }
        return true;
    }


}