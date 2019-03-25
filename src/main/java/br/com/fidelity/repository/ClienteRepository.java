package br.com.fidelity.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.fidelity.entity.Cliente;
import br.com.fidelity.entity.Estabelecimento;
import br.com.fidelity.entity.Membro;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	Cliente findByUsuario(Membro usuario);
}
