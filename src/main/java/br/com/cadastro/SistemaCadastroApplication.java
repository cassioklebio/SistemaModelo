package br.com.cadastro;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.repositories.CategoriaRepository;

@SpringBootApplication
public class SistemaCadastroApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaCadastroApplication.class, args);
	}

	
	
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
