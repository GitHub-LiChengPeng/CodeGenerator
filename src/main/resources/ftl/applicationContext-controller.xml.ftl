<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
		 http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 扫描Controller层的包 -->
    <context:component-scan base-package="${controllerPackage}"/>

    <!-- 配置注解驱动,它与处理器适配器和处理器映射器有关. -->
    <mvc:annotation-driven/>

    <!-- 配置视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--
        设置资源映射
        location:静态资源的位置
        mapping:请求静态资源的格式
    -->
    <mvc:resources location="/Swagger/css/" mapping="css/**"/>
    <mvc:resources location="/Swagger/fonts/" mapping="fonts/**"/>
    <mvc:resources location="/Swagger/images/" mapping="images/**"/>
    <mvc:resources location="/Swagger/lang/" mapping="lang/**"/>
    <mvc:resources location="/Swagger/lib/" mapping="lib/**"/>
    <mvc:resources location="/Swagger/" mapping="swagger-ui.js"/>
    <mvc:resources location="/Swagger/" mapping="swagger-ui.min.js"/>
</beans>