package com.company;

import java.util.ArrayList;
import java.util.List;

// ASS 2 Q2b
class Q2b {



    //global variable for saving the number of failing branches
    public static int failing;



    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        letterCombinations("FHGDCEAB", list);
        System.out.println(list.toString());
        System.out.println(list.size());
        System.out.println(Q2b.failing);

    }



    // digits = "ABCDEFGH"
    public static List<String> letterCombinations(String digits, List<String> list) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        // 8 sets of "1234" is for the domain of 8 variables
        String[] numString = {"1234", "1234","1234", "1234","1234", "1234","1234", "1234"};
        StringBuilder temp = new StringBuilder();
        // pass the parameters to backtracking
        backTracking(digits, numString, 0, list, temp, new ArrayList<Character>());
        return list;

    }



    public static void backTracking(String digits, String[] numString, int num, List<String> list, StringBuilder temp, ArrayList<Character> path) {
        // num is the depth of the iteration. If we reach a depth that is deep as the length of the input,
        // it means we have got a complete path that is a solution for the CSP
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }

        //Using StringBuilder to save current assignment
        String str = numString[digits.charAt(num) - 'A'];
        path.add(digits.charAt(num));


        for (int i = 0; i < str.length(); i++) {

            // check if the current variable with its current value satisfied the constraints

            if(checkConstraint(digits.charAt(num), str.charAt(i), temp, digits, path)){
                temp.append(str.charAt(i));
                backTracking(digits, numString, num + 1, list, temp, path);
                temp.deleteCharAt(temp.length() - 1);
            } else {

                // if the current variable with its current value satisfied the constraints, we add a failing branch
                Q2b.failing++;

            }

        }
    }

    private static boolean checkConstraint(char curVar, char curVarVal, StringBuilder temp, String digits, ArrayList<Character> path) {
        System.out.println("current variable: "+curVar+ ", current variable value "+ curVarVal);
        System.out.println("temporary path: "+temp);


        if (curVar == 'H'){
            return curVarVal != temp.charAt(0);
        } else if (curVar == 'G'){
            return curVarVal != temp.charAt(0) && curVarVal<temp.charAt(1);
        } else if (curVar == 'D'){
            return curVarVal != temp.charAt(1) && curVarVal >= temp.charAt(2) && curVarVal != temp.charAt(0);
        } else if (curVar == 'C'){
            return Math.abs(curVarVal - temp.charAt(2)) == 1&& (curVarVal - temp.charAt(1))%2 == 0&& curVarVal != temp.charAt(3) && curVarVal != temp.charAt(0);
        } else if (curVar == 'E'){
            return curVarVal != temp.charAt(1) - 2 && Math.abs(curVarVal - temp.charAt(0))%2 == 1 && curVarVal != temp.charAt(4) && curVarVal <= temp.charAt(3) - 1;
        } else if(curVar == 'A'){
            return curVarVal >= temp.charAt(2) && curVarVal < temp.charAt(1);
        } else if (curVar == 'B'){
            return Math.abs(curVarVal - temp.charAt(0)) == 1;
        }

        return true;
    }


}