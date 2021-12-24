package com.example.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyDescriptor;
public class MyBeanUtils {
    public static String[] getNullPropertyNames(Object object){
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        java.beans.PropertyDescriptor[] pds =beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames=new ArrayList<>();
        for(PropertyDescriptor pd:pds){
            String propertyName = pd.getDisplayName();
            if(beanWrapper.getPropertyValue(propertyName)==null){
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
