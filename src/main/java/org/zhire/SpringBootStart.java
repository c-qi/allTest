package org.zhire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;

/**
 * fun.chenqi.SpringBootStart
 *
 * @SpringBootApplication 注解包含三个注解：
 * @Configuration 和 @bean注解 创建一个简单的spring配置类 可以替代xml配置文件
 * @EnableAutoConfiguration 能够自动配置spring的上下文，试图猜测和配置你想要的bean类，通常会自动根据你的类路径和你的bean定义自动配置
 * @ComponentScan 组件扫描，可自动发现和装配一些Bean。会自动扫描指定包下的全部标有@Component的类，并注册成bean，当然包括@Component下的子注解@Service,@Repository,@Controller。
 */
@ServletComponentScan
@SpringBootApplication // 当前启动类是配置类
//@EnableScheduling      // 开启定时任务
//@EnableCaching       // 开启缓存
//@EnableEurekaServer
@MapperScan("org.zhire.mapper")
public class SpringBootStart {
    public static void main(String[] args) {
        // 写本类的字节码【为的就是找到启动类上的注解】
        // run方法里面做的事情是初始化Spring框架
        /** // run方法分析：
         *  // 启动秒表，统计服务启动耗时
         *  StopWatch stopWatch = new StopWatch();
         * 		stopWatch.start();
         * 		// 创建springContext对象，该对象直译应用程序上下文，是根容器
         * 		ConfigurableApplicationContext context = null;
         * 		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
         * 		configureHeadlessProperty();
         * 	    // 创建监听器
         * 		SpringApplicationRunListeners listeners = getRunListeners(args);
         * 		listeners.starting();
         * 		try {
         * 			ApplicationArguments applicationArguments = new DefaultApplicationArguments(
         * 					args);
         * 				// 创建环境
         * 			ConfigurableEnvironment environment = prepareEnvironment(listeners,
         * 					applicationArguments);
         * 			configureIgnoreBeanInfo(environment);
         * 			Banner printedBanner = printBanner(environment);
         * 			context = createApplicationContext();
         * 			exceptionReporters = getSpringFactoriesInstances(
         * 					SpringBootExceptionReporter.class,
         * 					new Class[] { ConfigurableApplicationContext.class }, context);
         * 				// 准备上下文数据
         * 			prepareContext(context, environment, listeners, applicationArguments,
         * 					printedBanner);
         * 				// 设置上下文
         * 			refreshContext(context);
         * 	        	// 再次设置--源码是空方法，猜测保留方法
         * 			afterRefresh(context, applicationArguments);
         * 		       //停止秒表
         * 			stopWatch.stop();
         * 			if (this.logStartupInfo) {
         * 				new StartupInfoLogger(this.mainApplicationClass)
         * 						.logStarted(getApplicationLog(), stopWatch);
         *                        }
         * 			listeners.started(context);
         * 			callRunners(context, applicationArguments);* 		}
         * 		catch (Throwable ex) {
         * 			handleRunFailure(context, listeners, exceptionReporters, ex);
         * 			throw new IllegalStateException(ex);
         * 		}
         * 		listeners.running(context);
         * 		return context;
         */
         SpringApplication.run(SpringBootStart.class, args);

    }
}
