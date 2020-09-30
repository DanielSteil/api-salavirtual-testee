package br.com.ottimizza.salasvirtuais.repositories.sala;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ottimizza.salasvirtuais.domain.criterias.PageCriteria;
import br.com.ottimizza.salasvirtuais.domain.mapper.SalaMapper;
import br.com.ottimizza.salasvirtuais.domain.projections.SalaUsuarioProjection;
import br.com.ottimizza.salasvirtuais.models.Sala;
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
	
	@Inject
	SalaUsuarioRepository salaUsuarioRepository;

	@Override
	public List<SalaUsuarioProjection> buscaPorFiltro(SalaDTO filtro, PageCriteria criteria) throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT    														");
		sb.append("		s.id AS id,     											");
		sb.append("		s.nome AS nome, 											");
		sb.append("		s.publica AS publica, 										");
		sb.append("		s.fk_empresa_id AS empresaId, 								");
		sb.append("     ARRAY_TO_STRING(              								");
		sb.append("     	ARRAY(SELECT fk_usuarios_id               			    ");
		sb.append(" 			  FROM salas_usuario  								");
		sb.append("               WHERE fk_salas_id = s.id), ',')	AS usuariosSala ");
		sb.append(" FROM salas s 													");
		sb.append(" WHERE s.fk_empresa_id = :empresaId								");

		if(filtro.getId() != null)
			sb.append("AND s.id = :id ");

		if(filtro.getNome() != null && !filtro.getNome().equals(""))
			sb.append("AND UPPER(s.nome) LIKE unaccent(:nome) ");

		if(filtro.getPublica() != null)
			sb.append("AND s.publica = :publica ");

		// ----------------------------------------------------------------------------------

		Query query = em.createNativeQuery(sb.toString(), "salaUsuarioProjection");
		query.setParameter("empresaId", filtro.getEmpresaId());

		if(filtro.getId() != null)
			query.setParameter("id", filtro.getId());

		if(filtro.getNome() != null && !filtro.getNome().equals(""))
			query.setParameter("nome", "%"+filtro.getNome().toUpperCase()+"%");

		if(filtro.getPublica() != null)
			query.setParameter("publica",filtro.getPublica());

		query.setFirstResult(criteria.getPageIndex() * criteria.getPageSize());
		query.setMaxResults(criteria.getPageSize());

		return query.getResultList();
	}

	@Override
	public Long contarPorFiltro(SalaDTO filtro)  throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(*)													");
		sb.append(" FROM salas s 													");
		sb.append(" WHERE s.fk_empresa_id = :empresaId								");

		if(filtro.getId() != null)
			sb.append("AND s.id = :id ");

		if(filtro.getNome() != null && !filtro.getNome().equals(""))
			sb.append("AND UPPER(s.nome) LIKE unaccent(:nome) ");

		if(filtro.getPublica() != null)
			sb.append("AND s.publica = :publica ");

		// ----------------------------------------------------------------------------------

		Query query = em.createNativeQuery(sb.toString());
		query.setParameter("empresaId", filtro.getEmpresaId());

		if(filtro.getId() != null)
			query.setParameter("id", filtro.getId());

		if(filtro.getNome() != null && !filtro.getNome().equals(""))
			query.setParameter("nome", "%"+filtro.getNome().toUpperCase()+"%");

		if(filtro.getPublica() != null)
			query.setParameter("publica",filtro.getPublica());

		return ((BigInteger) query.getSingleResult()).longValue();
	}
}
