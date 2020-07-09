package br.com.ottimizza.salasvirtuais.domain.mapper;

import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;
import br.com.ottimizza.salasvirtuais.models.Sala;

public class SalaMapper {

	public static Sala fromDto(SalaDTO sala) {
		return Sala.builder()
				.id(sala.getId())
				.empresaId(sala.getEmpresaId())
				.nome(sala.getNome())
				.publica(sala.getPublica())
				.build();
	}
	
	public static SalaDTO fromEntity(Sala sala) {
		return SalaDTO.builder()
				.id(sala.getId())
				.empresaId(sala.getEmpresaId())
				.nome(sala.getNome())
				.publica(sala.getPublica())
				.build();
	}
}
