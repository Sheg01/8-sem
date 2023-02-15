package com.test.salon.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private final Pattern pricePattern = Pattern.compile("^[0-9]{1,3}$");

    private final Pattern wordPattern = Pattern.compile("^[a-zA-Z]$");

    private final Pattern datePattern = Pattern.compile("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");

    private final Pattern timePattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9] ");

    private final Pattern mailPattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");

    private final Pattern phoneNumberPattern = Pattern.compile("375[0-9]{2}[0-9]{7}");

    public boolean isValidPrice(Integer price){
        Matcher priceMatcher = pricePattern.matcher(String.valueOf(price));
        return priceMatcher.matches();
    }

    public boolean isValidWord(String word){
        Matcher wordMatcher = wordPattern.matcher(word);
        return wordMatcher.matches();
    }

    public boolean isValidDate(String date){
        Matcher dateMatcher = datePattern.matcher(date);
        return dateMatcher.matches();
    }

    public boolean isValidTime(String time){
        Matcher timeMatcher = timePattern.matcher(time);
        return timeMatcher.matches();
    }

    public boolean isValidMail(String mail){
        Matcher mailMatcher = mailPattern.matcher(mail);
        return mailMatcher.matches();
    }

    public boolean isValidPhoneNumber(String number){
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(number);
        return phoneNumberMatcher.matches();
    }

    public boolean validateEmptyLines(String value){
        return value.isEmpty();
    }

}
