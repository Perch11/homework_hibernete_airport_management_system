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
    public static void checkParamGetMethodAddress(int offset, int perPage, String sort){
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
    public static void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be a positive number: ");
        }
    }

    public static void checkNull(Object item) {
        if (item == null) {
            throw new NullPointerException("Null Pointer Exception cavd tanem");
        }
    }
    public static void validateString(String str) {
        checkNull(str);
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Passed empty value: ");
        }
    }


}
