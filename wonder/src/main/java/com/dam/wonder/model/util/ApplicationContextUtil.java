package com.dam.wonder.model.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2020/4/26
 * @Description: spring context util
 */
@Component
@Lazy(value = false)
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * @param clazz
     * @return T
     * @description: 获取 spring 容器中的 bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        ApplicationContextUtil.applicationContext = context;
    }
}
