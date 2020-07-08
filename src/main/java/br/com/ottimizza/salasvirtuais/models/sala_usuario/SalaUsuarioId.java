package br.com.ottimizza.salasvirtuais.models.sala_usuario;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;

public class SalaUsuarioId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="fk_usuarios_id")
	private BigInteger usuarioId;
	
	@Column(name="fk_salas_id")
	private String salaId;
	
}
