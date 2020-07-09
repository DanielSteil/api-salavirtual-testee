package br.com.ottimizza.salasvirtuais.models.sala_usuario;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ottimizza.salasvirtuais.models.Sala;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salas_usuario")
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SalaUsuario {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "salasUsuario_sequence", sequenceName = "salasUsuario_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salasUsuario_sequence")
	private BigInteger id;
	
    @Column(name = "fk_usuarios_id")
	private BigInteger usuarioId;
	
    @Column(name = "fk_salas_id")
	private BigInteger salaId;
}
