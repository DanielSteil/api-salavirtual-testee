package br.com.ottimizza.salasvirtuais.repositories.sala;

import br.com.ottimizza.salasvirtuais.domain.criterias.PageCriteria;
import br.com.ottimizza.salasvirtuais.domain.projections.SalaUsuarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;

import java.util.List;

public interface SalaRepositoryCustom {

	List<SalaUsuarioProjection> buscaPorFiltro(SalaDTO filtro, PageCriteria criteria)  throws Exception ;

	Long contarPorFiltro(SalaDTO filtro)  throws Exception ;
	
}
