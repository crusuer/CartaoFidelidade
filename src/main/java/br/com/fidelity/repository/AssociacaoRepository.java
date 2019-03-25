package br.com.fidelity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.fidelity.entity.Associacao;
import br.com.fidelity.entity.Cliente;
import br.com.fidelity.entity.Estabelecimento;

public interface AssociacaoRepository extends CrudRepository<Associacao, Long>{
	
    public List<Associacao> findAllByUsuarioEstab(Estabelecimento usuarioEstab);
    
    public List<Associacao> findAllByUsuarioCli(Cliente usuarioCli);
    
    @Query("SELECT DISTINCT(b.usuarioEstab) FROM Associacao b WHERE b.usuarioCli = :usuarioCli")
    public List<Estabelecimento> listEstabs(@Param("usuarioCli") Cliente usuarioCli);
}
