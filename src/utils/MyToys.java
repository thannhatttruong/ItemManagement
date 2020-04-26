/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;

/**
 *
 * @author TruongTN
 */
public class MyToys {
    
    public static boolean checkString(String input, String format, int maxLength){
        input = input.trim();
        int lengthOfCode = input.length();
        if(input.isEmpty() || lengthOfCode == 0 || lengthOfCode > maxLength || !input.matches(format)){
            return false;
        }
        return true;
    }
 
    public static boolean checkDateFormat(String input, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
