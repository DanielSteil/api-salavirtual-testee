package br.com.ottimizza.salasvirtuais.repositories.sala;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;
import br.com.ottimizza.salasvirtuais.models.QSala;
import br.com.ottimizza.salasvirtuais.repositories.SalaUsuarioRepository;

public class SalaRepositoryImpl implements SalaRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	QSala sala = QSala.sala;
	
	@Inject
	SalaUsuarioRepository salaUsuarioRepository;
	
	@Override
	public Page<SalaDTO> buscaPorFiltro(SalaDTO filtro, Pageable pageable) {
		JPAQuery<SalaDTO> query = new JPAQuery<SalaDTO>(em).from(sala);
		long totalElements = 0;
		List<SalaDTO> salas = new ArrayList<SalaDTO>();
		
		if(filtro.getId() != null)
			query.where(sala.id.eq(filtro.getId()));
		if(filtro.getEmpresaId() != null)
			query.where(sala.empresaId.eq(filtro.getEmpresaId()));
		if(filtro.getNome() != null && filtro.getNome() != "")
			query.where(sala.nome.containsIgnoreCase(filtro.getNome()));
		if(filtro.getPublica() != null)
			query.where(sala.publica.eq(filtro.getPublica()));
		
		totalElements = query.fetchCount();
		query.limit(pageable.getPageSize());
		query.offset(pageable.getPageSize() * pageable.getPageNumber());
		query.select(Projections.constructor(SalaDTO.class, sala.id, sala.empresaId, sala.nome, sala.publica));
		
		salas = query.fetch();
		for(SalaDTO sala : salas) {
			sala.setUsuariosSala(salaUsuarioRepository.usuariosPorSala(sala.getId()));
		}
		return new PageImpl<SalaDTO>(salas, pageable, totalElements);
	}

}
