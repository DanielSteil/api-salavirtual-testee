package br.com.ottimizza.salasvirtuais.domain.mapper;

import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;
import br.com.ottimizza.salasvirtuais.domain.projections.SalaUsuarioProjection;
import br.com.ottimizza.salasvirtuais.models.Sala;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	public static SalaDTO fromProjection(SalaUsuarioProjection sala) {
		return SalaDTO.builder()
				.id(sala.getId())
				.nome(sala.getNome())
				.empresaId(sala.getEmpresaId())
				.publica(sala.getPublica())
				.usuariosSala(
						sala.getUsuariosSala() != null && !sala.getUsuariosSala().equals("")
						? Arrays.asList(sala.getUsuariosSala().split(",")).stream().map(u -> {
							String numero = u.substring(0, u.indexOf("."));
							return new BigInteger(numero);
						}).collect(Collectors.toList())
						: new ArrayList<>()
				)
			.build();
	}

	public static List<SalaDTO> fromEntities(List<Sala> salas){
		return salas.stream().map(sala -> fromEntity(sala)).collect(Collectors.toList());
	}
}
