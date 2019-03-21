package br.com.cadastro.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.cadastro.domain.Pedido;
import br.com.cadastro.repositories.PedidoRepository;
import br.com.cadastro.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido find(final Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id "+ id + "Tipo: "+ Pedido.class.getName()));
		
	}
}
