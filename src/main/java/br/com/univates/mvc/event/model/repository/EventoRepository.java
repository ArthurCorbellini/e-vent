package br.com.univates.mvc.event.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.univates.mvc.event.model.entity.Evento;

/**
 * @author Arthur
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Query(" SELECT e "
			+ " FROM  Evento e "
			+ " JOIN  e.users u "
			+ " WHERE u.username = :username ")
	List<Evento> findAllByUsernameUser(@Param("username") String username);

}
