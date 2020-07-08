package br.com.ottimizza.salasvirtuais.models.sala_usuario;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salas_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaUsuario {

	@EmbeddedId
	private SalaUsuarioId id;
	
	@ManyToOne
    @Column(name = "fk_usuarios_id", insertable = false, updatable = false)
	private BigInteger usuarioId;
	
	@ManyToOne
    @Column(name = "fk_salas_id", insertable = false, updatable = false)
	private BigInteger salaId;
}
