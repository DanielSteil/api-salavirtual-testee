package br.com.ottimizza.salasvirtuais.domain.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SalaDTO {

	private BigInteger id;
	
	private BigInteger empresaId;
	
	private String nome;
	
	private boolean publica;
	
	private List<BigInteger> usuariosSala;

	public SalaDTO(BigInteger id, BigInteger empresaId, String nome, boolean publica) {
		super();
		this.id = id;
		this.empresaId = empresaId;
		this.nome = nome;
		this.publica = publica;
	}
	
	
}
