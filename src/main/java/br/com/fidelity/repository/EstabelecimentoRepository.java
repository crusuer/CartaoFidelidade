package br.com.fidelity.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.fidelity.entity.Estabelecimento;
import br.com.fidelity.entity.Membro;

public interface EstabelecimentoRepository extends CrudRepository<Estabelecimento, Long>{

	Estabelecimento findByUsuario(Membro usuario);
}
