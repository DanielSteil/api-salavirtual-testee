package br.com.ottimizza.salasvirtuais.repositories;

import java.math.BigInteger;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.salasvirtuais.models.sala_usuario.SalaUsuario;
import br.com.ottimizza.salasvirtuais.models.sala_usuario.SalaUsuarioId;

@Repository
public interface SalaUsuarioRepository extends JpaRepository<SalaUsuario, SalaUsuarioId>{

	@Query(value = "SELECT su.fk_usuarios_id FROM sala_usuario su WHERE su.fk_salas_id = :salaId", nativeQuery = true)
	List<BigInteger> usuariosPorSala(@Param("salaId") BigInteger salaId);
	
	@Query(value = "SELECT COUNT(*) FROM sala_usuario su WHERE su.fk_usuarios_id = :userId", nativeQuery = true)
	Short buscaUsuarioEmSalas(@Param("userId") BigInteger userId);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO sala_usuario (fk_salas_id , fk_usuarios_id) VALUES (:salaId , :usuarioId)", nativeQuery = true)
	void salvaSalaUsuario(@Param("salaId") BigInteger salaId, @Param("usuarioId") BigInteger usuarioId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM sala_usuario su WHERE su.usuarioId = :usuarioId)", nativeQuery = true)
	void deletaSalaUsuario(@Param("usuarioId") BigInteger usuarioId);
	
}