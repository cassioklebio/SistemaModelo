package br.com.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.domain.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}

