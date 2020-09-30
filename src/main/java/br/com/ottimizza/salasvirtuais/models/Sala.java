package br.com.ottimizza.salasvirtuais.models;

import java.math.BigInteger;

import javax.persistence.*;

import br.com.ottimizza.salasvirtuais.domain.projections.SalaUsuarioProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SqlResultSetMapping(
		name = "salaUsuarioProjection",
		classes = @ConstructorResult(targetClass = SalaUsuarioProjection.class,
				columns = {
						@ColumnResult(name = "id", type = BigInteger.class),
						@ColumnResult(name = "nome", type = String.class),
						@ColumnResult(name = "publica", type = Boolean.class),
						@ColumnResult(name = "empresaId", type = BigInteger.class),
						@ColumnResult(name = "usuariosSala", type = String.class)
				}
		))
@Table(name = "salas")
@NoArgsConstructor @AllArgsConstructor
@Data
@Builder
public class Sala {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "salas_sequence", sequenceName = "salas_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salas_sequence")
	private BigInteger id;
	
	@Column(name = "fk_empresa_id")
	private BigInteger empresaId;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "publica")
	private Boolean publica;


}
