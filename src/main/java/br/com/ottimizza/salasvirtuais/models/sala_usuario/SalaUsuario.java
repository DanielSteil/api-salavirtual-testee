package br.com.ottimizza.salasvirtuais.models.sala_usuario;

import java.math.BigInteger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala_usuario")
@Data
@NoArgsConstructor @AllArgsConstructor
public class SalaUsuario {

	@EmbeddedId
	private SalaUsuarioId id;
	
    @JoinColumn(name = "fk_usuarios_id", insertable = false, updatable = false)
	private BigInteger usuarioId;
	
    @JoinColumn(name = "fk_salas_id", insertable = false, updatable = false)
	private BigInteger salaId;
}
