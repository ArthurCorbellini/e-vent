package br.com.univates.mvc.event.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.univates.mvc.event.model.entity.Acesso;

/**
 * @author Arthur
 */
@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

}
