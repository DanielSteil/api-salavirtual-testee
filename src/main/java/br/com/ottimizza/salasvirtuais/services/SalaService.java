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
	
	public Page<SalaDTO> buscaPorFiltro(SalaDTO filtro, PageCriteria pageCriteria) throws Exception {
		return repository.buscaPorFiltro(filtro, PageCriteria.getPageRequest(pageCriteria));
	}
	
	public JSONObject deletaSala(BigInteger salaId) throws Exception {
		JSONObject response = new JSONObject();
		try {
			repository.deleteById(salaId);
			response.put("status", "sucess");
            response.put("message", "Sala excluida com sucesso!");
		}
		catch(Exception ex) {
			response.put("status", "Error");
            response.put("message", "Houve um problema ao excluir!");
            }
		return response;
	}
	
	public JSONObject salvaUsuarioSala(BigInteger salaId, BigInteger usuarioId) throws Exception {
		JSONObject response = new JSONObject();
		try{
			if(salaUsuarioRepository.buscaUsuarioEmSalas(usuarioId) > 0)
				salaUsuarioRepository.deletaSalaUsuario(usuarioId);
			salaUsuarioRepository.salvaSalaUsuario(salaId, usuarioId);
			response.put("status", "sucess");
            response.put("message", "Usuario cadastrado na sala com sucesso!");
		}
		catch(Exception ex) {
			response.put("status", "Error");
            response.put("message", "Houve um problema ao cadastrar!");
		}
		return response;
	}
	
	public JSONObject deletaUsuarioSala(BigInteger usuarioId) throws Exception {
		JSONObject response = new JSONObject();
		try{
			salaUsuarioRepository.deletaSalaUsuario(usuarioId);
			response.put("status", "sucess");
            response.put("message", "Usuario retirado da sala com sucesso!");
		}
		catch(Exception ex) {
			response.put("status", "Error");
            response.put("message", "Houve um problema ao excluir!");
		}
		return response;
	}
	
}
