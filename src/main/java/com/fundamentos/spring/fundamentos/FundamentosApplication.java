package com.fundamentos.spring.fundamentos;

import com.fundamentos.spring.fundamentos.bean.MyBean;
import com.fundamentos.spring.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.spring.fundamentos.component.ComponentDependency;
import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.pojo.UserPojo;
import com.fundamentos.spring.fundamentos.repository.UserRepository;
import com.fundamentos.spring.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithProperties myBeanWithProperties;
	private UserRepository userRepository;
	private UserPojo userPojo;
	private UserService userService;

	public FundamentosApplication (@Qualifier("componentTwoImplement")ComponentDependency componentDependency, MyBean myBean, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		ejemplosAnteriores();
		saveDataInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();

	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		} catch (Exception e) {
			LOGGER.error("Esta es una excepcion dentro del metodo transaccional" + e);
		}
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional "+ user));
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Usuario con el metodo findByUserEmail" + userRepository.findByUserEmail("user2@mail.com").orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending()).stream().forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		userRepository.findByName("Jhon").stream().forEach(user -> LOGGER.info("Usuario con query method "+ user));

		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("user3@mail.com", "user3").orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
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
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		LOGGER.error("Esto es un error del aplicativo");

	}
}