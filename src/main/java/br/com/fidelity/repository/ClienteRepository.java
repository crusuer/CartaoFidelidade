package br.com.fidelity.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.fidelity.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
