package com.gmatieso.mwanzo.common.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;


public class CommonUtils {
    public static <T> Page<T> createPage(List<T> content, Pageable pageable, long totalElements) {
        return new PageImpl<>(content, pageable, totalElements);
    }

//    public static List<String> formatPhoneNumbersWithPlusSign(List<String> phoneNumbers) {
//        return phoneNumbers.stream()
//                .map(phoneNumber -> formatPhoneNumberWithPlusSign(phoneNumber, true))
//                .filter(Objects::nonNull)
//                .toList();
//    }
//
//    public static String formatPhoneNumber(String phoneNumber) {
//        return formatPhoneNumberWithPlusSign(phoneNumber, false);
//    }
//
//    private static String formatPhoneNumberWithPlusSign(String phoneNumber, Boolean hasPlusSign) {
//        if(Strings.isNullOrEmpty(phoneNumber)) {
//            return null;
//        }
//
//        int countryCode = 254;
//
//        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//        try {
//            String regionCode =  phoneUtil.getRegionCodeForCountryCode(countryCode);
//            Phonenumber.PhoneNumber number = phoneUtil.parse(phoneNumber, regionCode);
//            if (!phoneUtil.isValidNumber(number)) {
//                throw new BadRequestException("Invalid phone number: " + phoneNumber);
//            }
//            String formattedNumber = phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
//            return hasPlusSign != null && hasPlusSign ? formattedNumber : formattedNumber.replace("+", "");
//        } catch (NumberParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static String getDeletionTimeStamp(){
//        return "del_" + System.currentTimeMillis() + "_";
//    }
//
//    public static String dateToFormattedString(LocalDateTime localDateTime) {
//        String format = "yyyy-MM-dd HH:mm:ss";
//        return formatDate(localDateTime, format);
//    }
//
//    public static String formatDate(LocalDateTime localDateTime, String format) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
//        return localDateTime.format(formatter);
//    }
//
//    public static String getOriginalValue(String input) {
//        if (input == null || !input.startsWith("del_") || !input.contains("_")) {
//            return input;
//        }
//        int lastUnderscore = input.lastIndexOf("_");
//        String originalValue = input.substring(lastUnderscore + 1);
//        return originalValue + " (deleted)";
//    }

}
