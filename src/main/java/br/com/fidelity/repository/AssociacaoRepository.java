package br.com.fidelity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.fidelity.entity.Associacao;
import br.com.fidelity.entity.Estabelecimento;

public interface AssociacaoRepository extends CrudRepository<Associacao, Long>{
	
    public List<Associacao> findAllByUsuarioEstab(Estabelecimento usuarioEstab);
}
