package br.com.fidelity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.fidelity.entity.Associacao;
import br.com.fidelity.entity.Ponto;

public interface PontoRepository extends CrudRepository<Ponto, Long>{
	
	public List<Ponto> findAllByIdAssoc(Associacao idAssoc);
}
