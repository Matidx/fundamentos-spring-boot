package com.fundamentos.spring.fundamentos;

import com.fundamentos.spring.fundamentos.bean.MyBean;
import com.fundamentos.spring.fundamentos.bean.MyBeanImplement;
import com.fundamentos.spring.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.spring.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithProperties myBeanWithProperties;
	public FundamentosApplication (@Qualifier("componentTwoImplement")ComponentDependency componentDependency, MyBean myBean, MyBeanWithProperties myBeanWithProperties) {

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithProperties = myBeanWithProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		componentDependency.saludar();
		myBean.print();
		System.out.println(myBeanWithProperties.function());
	}
}
