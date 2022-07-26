package com.fundamentos.spring.fundamentos;

import com.fundamentos.spring.fundamentos.bean.MyBean;
import com.fundamentos.spring.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.spring.fundamentos.component.ComponentDependency;
import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithProperties myBeanWithProperties;
	private UserRepository userRepository;

	public FundamentosApplication (@Qualifier("componentTwoImplement")ComponentDependency componentDependency, MyBean myBean, MyBeanWithProperties myBeanWithProperties, UserRepository userRepository) {

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		ejemplosAnteriores();
		saveDataInDataBase();
	}

	private void saveDataInDataBase () {
		User user1 = new User ("Jhon", "jhon@mail.com", LocalDate.of(2021, 03,20));
		User user2 = new User ("user2", "user2@mail.com", LocalDate.of(2021, 02,21));
		User user3 = new User ("user3", "user3@mail.com", LocalDate.of(2021, 11,29));
		User user4 = new User ("user4", "user4@mail.com", LocalDate.of(2021, 01,27));
		User user5 = new User ("user5", "user5@mail.com", LocalDate.of(2021, 04,20));
		User user6 = new User ("user6", "user6@mail.com", LocalDate.of(2021, 07,15));
		User user7 = new User ("user7", "user7@mail.com", LocalDate.of(2021, 8,12));
		User user8 = new User ("user8", "user8@mail.com", LocalDate.of(2021, 9,11));
		User user9 = new User ("user9", "user9@mail.com", LocalDate.of(2021, 06,9));
		User user10 = new User ("user10", "user10@mail.com", LocalDate.of(2021, 12,6));
		User user11 = new User ("user11", "user11@mail.com", LocalDate.of(2021, 01,5));

		List<User> list = Arrays.asList(user1,user2, user3, user4, user5, user6, user7, user8, user9, user10, user11);
		list.stream().forEach(userRepository::save);
	}

	public void ejemplosAnteriores (){
		componentDependency.saludar();
		myBean.print();
		System.out.println(myBeanWithProperties.function());

	}
}
