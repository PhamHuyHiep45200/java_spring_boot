package br.com.feltex.user_api.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class UserUtil {
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper bw = new BeanWrapperImpl(source);
        return Arrays.stream(bw.getPropertyDescriptors())
                .filter(pd -> bw.getPropertyValue(pd.getName()) == null)
                .map(PropertyDescriptor::getName)
                .toArray(String[]::new);
    }
}
