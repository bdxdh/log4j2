package com.bdxdh;

import org.junit.Test;

import java.util.logging.*;

/**
 * JUL全称Java util Logging是java原生的日志框架，使用时不需要另外引用第三方类库，相对其他日志框 架使用方便，学习简单，能够在小型应用中灵活使用。
 * <p>
 * 在JUL中有以下组件，我们先做了解，慢慢学习：
 * Loggers：被称为记录器，应用程序通过获取Logger对象，调用其API来来发布日志信息。Logger 通常时应用程序访问日志系统的入口程序。
 * Appenders：也被称为Handlers，每个Logger都会关联一组Handlers，Logger会将日志交给关联 Handlers处理，由Handlers负责将日志做记录。Handlers在此是一个抽象，其具体的实现决定了 日志记录的位置可以是控制台、文件、网络上的其他日志服务或操作系统日志等。
 * Layouts：也被称为Formatters，它负责对日志事件中的数据进行转换和格式化。Layouts决定了 数据在一条日志记录中的最终形式。
 * Level：每条日志消息都有一个关联的日志级别。该级别粗略指导了日志消息的重要性和紧迫，我 可以将Level和Loggers，Appenders做关联以便于我们过滤消息。
 * Filters：过滤器，根据需要定制哪些信息会被记录，哪些信息会被放过。
 * </p>
 * <p>
 * 总结一下就是：
 * 用户使用Logger来进行日志记录，Logger持有若干个Handler，日志的输出操作是由Handler完成的。 在Handler在输出日志前，会经过Filter的过滤，判断哪些日志级别过滤放行哪些拦截，Handler会将日志内容输出到指定位置（日志文件、控制台等）。Handler在输出日志时会使用Layout，将输出内容进行排版。
 * </p>
 *
 * @author sb
 */
public class TestJUL {

    //获取一个logger对象
    private static final Logger logger = Logger.getLogger(TestJUL.class.getName());

    @Test
    public void testJUL() {
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
    }

    @Test
    public void testLogger() {
        Logger logger = Logger.getLogger(TestJUL.class.getName());
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void testLogConfig() throws Exception {
        // 1.创建日志记录器对象
        Logger logger = Logger.getLogger("com.ydlclass.log.JULTest");
        // 一、自定义日志级别
        // a.关闭系统默认配置
        logger.setUseParentHandlers(false);
        // b.创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // c.创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // d.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // e.设置日志级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        // 二、输出到日志文件
        FileHandler fileHandler = new FileHandler("G:\\log4j2\\logs\\jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void testLogParent() throws Exception {
        Logger logger1 = Logger.getLogger("com.ydlclass.service");
        Logger logger2 = Logger.getLogger("com.ydlclass");
        System.out.println("logger1 = " + logger1);
        System.out.println("logger1.getParent() = " + logger1.getParent());
        System.out.println("logger2 = " + logger2);
        System.out.println("logger2.getParent() = " + logger2.getParent());
        System.out.println(logger1.getParent() == logger2);
    }

    @Test
    public void testLogParent1() throws Exception {
        Logger logger1 = Logger.getLogger("com.ydlclass.service");
        Logger logger2 = Logger.getLogger("com.ydlclass");
        // 一、对logger2进行独立的配置
        // 1.关闭系统默认配置
        logger2.setUseParentHandlers(false);
        // 2.创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 3.创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 4.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);
        // 5.设置日志级别
        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        // 测试logger1是否被logger2影响
        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");
    }
}
