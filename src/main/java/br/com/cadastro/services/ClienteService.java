package br.com.cadastro.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Cliente;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(final Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id "+ id + "Tipo: "+ Cliente.class.getName()));
		
	}

}
