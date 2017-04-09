<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <!--扫描Dao层的包-->
    <context:component-scan base-package="${daoPackage}"/>

    <!--配置数据源信息-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close">
        <property name="jdbcUrl" value="jdbc:mysql://127.0.0.1:3306/${database}?useUnicode=true&amp;characterEncoding=UTF8"/>
        <property name="driverClass" value="com.mysql.jdbc.Driver"/>
        <property name="user" value="${username}"/>
        <property name="password" value="${password}"/>
    </bean>

    <!--配置会话工厂信息-->
    <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
        <!--为会话工厂注入数据源信息-->
        <property name="dataSource" ref="dataSource"/>
        <!--扫描实体类所在的包-->
        <property name="packagesToScan">
            <list>
                <value>${entityPackage}</value>
            </list>
        </property>
        <!--配置其他属性-->
        <property name="hibernateProperties">
            <props>
                <!--设置数据库方言为MySQL-->
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</prop>
                <!--调试程序时显示SQL语句-->
                <prop key="hibernate.show_sql">true</prop>
                <!--格式化显示出来的SQL语句-->
                <prop key="hibernate.format_sql">true</prop>
            </props>
        </property>
    </bean>

    <!--配置事务管理器-->
    <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
        <!--注入会话工厂-->
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>

    <!--开启事务注解-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>