package com.airport.validator;

public class Validator {

    public static void checkParamGetMethodCompany(int offset, int perPage, String sort) {
        if (offset <= 0 || perPage <= 0) {
            throw new IllegalArgumentException("Passed non-positive value as 'offset' or 'perPage': ");
        }
        if (sort == null || sort.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'sort': ");
        }
        if (!sort.equals("id") && !sort.equals("name") && !sort.equals("found_date")) {
            throw new IllegalArgumentException("Parameter 'sort' must be 'id' or 'country' or 'city': ");
        }
    }

    public static void checkParamGetMethodAddress(int offset, int perPage, String sort) {
        if (offset <= 0 || perPage <= 0) {
            throw new IllegalArgumentException("Passed non-positive value as 'offset' or 'perPage': ");
        }
        if (sort == null || sort.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'sort': ");
        }
        if (!sort.equals("id") && !sort.equals("country") && !sort.equals("city")) {
            throw new IllegalArgumentException("Parameter 'sort' must be 'id' or 'country' or 'city': ");
        }
    }

    public static void checkParamGetMethodTrip(int offset, int perPage, String sort) {
        if (offset <= 0 || perPage <= 0) {
            throw new IllegalArgumentException("Passed non-positive value as 'offset' or 'perPage': ");
        }
        if (sort == null || sort.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'sort': ");
        }
        if (
                !sort.equals("tripNumber") && !sort.equals("company") && !sort.equals("airplane") &&
                        !sort.equals("townFrom") && !sort.equals("townTo") && !sort.equals("timeOut") && !sort.equals("timeIn")
        ) {
            throw new IllegalArgumentException("Parameter 'sort' must be " +
                    "'tripNumber' or 'company' or 'airplane' or 'townFrom' or 'townTo' or 'timeOut' or 'timeIn': ");
        }
    }

    public static void checkParamGetMethodPassenger(int offset, int perPage, String sort) {
        if (offset <= 0 || perPage <= 0) {
            throw new IllegalArgumentException("Passed non-positive value as 'offset' or 'perPage': ");
        }
        if (sort == null || sort.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'sort': ");
        }
        if (
                !sort.equals("id") && !sort.equals("name") && !sort.equals("phone")
        ) {
            throw new IllegalArgumentException("Parameter 'sort' must be " +
                    "'id' or 'company' or 'name' or 'phone': ");
        }
    }


    /**
     Validates whether the input id is a positive number.
     @param id the id to validate
     @throws IllegalArgumentException if the input id is not a positive number
     */
    public static void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be a positive number: ");
        }
    }

    /**
     Validates whether the input string is null or empty.
     @param item the string to validate
     @throws NullPointerException if the input string is null
     */
    public static void checkNull(Object item) {
        if (item == null) {
            throw new NullPointerException("Null Pointer Exception cavd tanem");
        }
    }

    /**
     Validates whether the input string is null or empty.
     @param str the string to validate
     @throws NullPointerException if the input string is null
     @throws IllegalArgumentException if the input string is empty
     */
    public static void validateString(String str) {
        checkNull(str);
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Passed empty value: ");
        }
    }

    /**
     Validates whether the input string is null or empty.
     @param str the string to validate
     @return true if the input string is null or empty, false otherwise
     */
    public static boolean validateStringIsEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * this method returns passed object null or not
     * @param obj type Object
     * @return type boolean true or false
     */
    public static boolean validateObjectNull(Object obj) {
        return obj == null;
    }



}
