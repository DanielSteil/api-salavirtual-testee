package br.com.ottimizza.salasvirtuais.repositories;

import java.math.BigInteger;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ottimizza.salasvirtuais.models.sala_usuario.SalaUsuario;

@Repository
public interface SalaUsuarioRepository extends JpaRepository<SalaUsuario, BigInteger>{

	@Query(value = "SELECT su.fk_usuarios_id FROM salas_usuario su WHERE su.fk_salas_id = :salaId", nativeQuery = true)
	List<BigInteger> usuariosPorSala(@Param("salaId") BigInteger salaId);
	
	@Query(value = "SELECT COUNT(*) FROM salas_usuario su WHERE su.fk_usuarios_id = :userId", nativeQuery = true)
	Short buscaUsuarioEmSalas(@Param("userId") BigInteger userId);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO salas_usuario (fk_salas_id , fk_usuarios_id) VALUES (:salaId , :usuarioId)", nativeQuery = true)
	void salvaSalaUsuario(@Param("salaId") BigInteger salaId, @Param("usuarioId") BigInteger usuarioId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM salas_usuario su WHERE su.fk_usuarios_id = :usuarioId", nativeQuery = true)
	void deletaSalaUsuario(@Param("usuarioId") BigInteger usuarioId);
	
}
