package spring.cloud.jfinal;

import com.jfinal.core.JFinalFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class JfinalApplication implements CommandLineRunner{

	@Bean
	public FilterRegistrationBean jfFilterReg(){
		FilterRegistrationBean reg = new FilterRegistrationBean();
		reg.setFilter(new JFinalFilter());

		reg.addInitParameter("configClass", "spring.cloud.jfinal.config.MainConfig");
		reg.addUrlPatterns("/*");
		return reg;
	}

	public static void main(String[] args) {
		SpringApplication.run(JfinalApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//do something after spring-boot started
	}
	
}