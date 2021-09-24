package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            double x = Double.parseDouble(args[0]);
            char operation = args[1].charAt(0);
            double y = Double.parseDouble(args[2]);
            double result = calc(x, y, operation);
            System.out.println("result: " + result);
        } catch (Exception ex) {
            System.out.println("Что-то не то с аргументами");
        }
    }

    private  static double calc(double x, double y, char operation){
        switch (operation){
            case '+' :
                return x + y;
            case '-' :
                return x - y;
            case '*' :
                return x * y;
            case '/' :
                if (y == 0)
                    return Double.NaN;
                return x / y;
        }
        return Double.NaN;
    }

}
