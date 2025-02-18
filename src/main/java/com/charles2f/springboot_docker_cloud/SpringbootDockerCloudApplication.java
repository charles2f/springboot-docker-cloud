package com.charles2f.springboot_docker_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.charles2f.springboot_docker_cloud")  // Adicionando o scan para o pacote onde está o Controller
public class SpringbootDockerCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDockerCloudApplication.class, args);
	}

}
