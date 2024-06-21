package com.aluracursos.literalura.errors;

public class ErrorsTypeData {
    public static boolean isNumeberInteger (String stringToEvaluate) {
        try {
            Integer numeberInteger = Integer.valueOf(stringToEvaluate);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
