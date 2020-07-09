package br.com.ottimizza.salasvirtuais.services;

import java.math.BigInteger;

import javax.inject.Inject;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ottimizza.salasvirtuais.domain.criterias.PageCriteria;
import br.com.ottimizza.salasvirtuais.domain.dto.SalaDTO;
import br.com.ottimizza.salasvirtuais.domain.mapper.SalaMapper;
import br.com.ottimizza.salasvirtuais.models.Sala;
import br.com.ottimizza.salasvirtuais.models.sala_usuario.SalaUsuario;
import br.com.ottimizza.salasvirtuais.repositories.SalaUsuarioRepository;
import br.com.ottimizza.salasvirtuais.repositories.sala.SalaRepository;

@Service
public class SalaService {

	@Inject
	SalaRepository repository;
	
	@Inject
	SalaUsuarioRepository salaUsuarioRepository;
	
	public Sala salvaSala(SalaDTO sala) throws Exception {
		return repository.save(SalaMapper.fromDto(sala));
	}
	
	public SalaDTO patch(BigInteger id, SalaDTO sala) throws Exception {
		Sala current = repository.findById(id).orElse(null);
		current = sala.patch(current);
		return SalaMapper.fromEntity(repository.save(current));
	}
	
	public Page<SalaDTO> buscaPorFiltro(SalaDTO filtro, PageCriteria pageCriteria) throws Exception {
		return repository.buscaPorFiltro(filtro, PageCriteria.getPageRequest(pageCriteria));
	}
	
	public void deletaSala(BigInteger salaId) throws Exception {
		try {
			repository.deleteById(salaId);
		}
		catch(Exception ex) { }
	}
	
	public void salvaUsuarioSala(SalaUsuario salaUser) throws Exception {
		try{
			if(salaUsuarioRepository.buscaUsuarioEmSalas(salaUser.getUsuarioId()) > 0) {
				salaUsuarioRepository.deletaSalaUsuario(salaUser.getUsuarioId());
			}
			salaUsuarioRepository.save(salaUser);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void deletaUsuarioSala(BigInteger usuarioId) throws Exception {
		try{
			salaUsuarioRepository.deletaSalaUsuario(usuarioId);
		}
		catch(Exception ex) { }
	}
	
}
