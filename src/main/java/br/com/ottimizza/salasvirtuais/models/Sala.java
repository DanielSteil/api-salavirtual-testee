package br.com.ottimizza.salasvirtuais.models;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
	private boolean publica;
}
