package br.com.ottimizza.salasvirtuais.repositories.sala;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;

public interface SalaRepositoryCustom {

	Page<SalaDTO> buscaPorFiltro(SalaDTO filtro, Pageable pageable);
	
}
