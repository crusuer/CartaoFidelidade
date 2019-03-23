package br.com.fidelity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.fidelity.entity.Membro;

public interface MembroRepository extends CrudRepository<Membro, Long> {
    Membro findByUsuario(String usuario);

    List<Membro> findByTipoAndHabilitado(String tipo, boolean habilitado);
}
