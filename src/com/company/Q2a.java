package com.company;

import java.util.ArrayList;
import java.util.List;

// ASS 2 Q2 a
class Q2a {



    //global variable for saving the number of failing branches
    public static int failing;



    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        letterCombinations("ABCDEFGH", list);
        System.out.println(list.toString());
        System.out.println(list.size());
        System.out.println(Q2a.failing);

    }



    // digits = "FHGEDCAB"
    public static List<String> letterCombinations(String digits, List<String> list) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        // 8 sets of "1234" is for the domain of 8 variables
        String[] numString = {"1234", "1234","1234", "1234","1234", "1234","1234", "1234"};
        StringBuilder temp = new StringBuilder();
        // pass the parameters to backtracking
        backTracking(digits, numString, 0, list, temp);
        return list;

    }


    public static void backTracking(String digits, String[] numString, int num, List<String> list, StringBuilder temp) {
        // num is the depth of the iteration. If we reach a depth that is deep as the length of the input,
        // it means we have got a complete path that is a solution for the CSP
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //Using StringBuilder to save current assignment
        String assign = numString[digits.charAt(num) - 'A'];


        for (int i = 0; i < assign.length(); i++) {

            // check if the current variable with its current value satisfied the constraints

            if(checkConstraint(digits.charAt(num), assign.charAt(i), temp, digits)){
                temp.append(assign.charAt(i));
                backTracking(digits, numString, num + 1, list, temp);
                temp.deleteCharAt(temp.length() - 1);
            } else {
                System.out.println("");
                // if the current variable with its current value satisfied the constraints, we add a failing branch
                Q2a.failing++;

            }

        }
    }

    private static boolean checkConstraint(char curVar, char curVarVal,  StringBuilder temp, String digits) {
        System.out.println("current variable: "+curVar+ ", current variable value "+ curVarVal);
        System.out.println("temporary path: "+temp);



        if (curVar == 'D'){
            return curVarVal != temp.charAt('C' - 'A');
        } else if (curVar == 'E'){
            return curVarVal != temp.charAt('C' - 'A') && curVarVal < temp.charAt('D' - 'A')- 1;
        } else if (curVar == 'F'){
            return Math.abs(curVarVal - temp.charAt('B' - 'A')) == 1 && curVarVal != temp.charAt('C' - 'A') && curVarVal != temp.charAt('D' - 'A') && Math.abs(curVarVal - temp.charAt('E'-'A'))%2 == 1;
        } else if (curVar == 'G'){
            return curVarVal <= temp.charAt('A'-'A')&& Math.abs(curVarVal - temp.charAt('C' - 'A')) == 1&& curVarVal <= temp.charAt('D'-'A')&& curVarVal != temp.charAt('F' - 'A');
        } else if (curVar == 'H'){
            return curVarVal > temp.charAt('A' - 'A')&& curVarVal > temp.charAt('G' - 'A')&& Math.abs(curVarVal - temp.charAt('C' - 'A'))%2 == 0&& curVarVal != temp.charAt('D' - 'A') && curVarVal != temp.charAt('E' - 'A') - 2 && curVarVal != temp.charAt('F' - 'A');
        }

        return true;
    }


}