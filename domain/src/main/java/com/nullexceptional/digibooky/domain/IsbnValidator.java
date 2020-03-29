package com.nullexceptional.digibooky.domain;

import com.nullexceptional.digibooky.domain.book.exceptions.InvalidIsbnException;

public class IsbnValidator {

    public static void validateIsbn13 (String isbn) throws InvalidIsbnException{
        if (isbn == null) throwInvalidIsbnException("is null");
        if (isbn.length() != 13) throwInvalidIsbnException("should contain 13 characters");

        String isbnWithoutHyphens = isbn.replace("-", "");

        try {
            checkSumCheck(isbnWithoutHyphens);
        }catch (NumberFormatException numberFormatException){
            throwInvalidIsbnException("invalid characters. Should only contain numbers.");
        }
    }

    private static void checkSumCheck(String isbnWithoutHyphens) throws InvalidIsbnException {
        int total = 0;
        for (int i = 0; i < 12; i++){
            int digit = Integer.parseInt( isbnWithoutHyphens.substring(i, i+1));
            total += (i % 2 == 0) ? digit *1 : digit*3;
        }

        int checksum = 10 - (total % 10);
        if (checksum == 10) checksum = 0;

        if (!(checksum == (Integer.parseInt(isbnWithoutHyphens.substring(12))))) throwInvalidIsbnException("invalid checksum digit");
    }

    private static void throwInvalidIsbnException(String reason) throws InvalidIsbnException{
        throw new InvalidIsbnException("Invalid ISBN: " + reason);
    }
}
