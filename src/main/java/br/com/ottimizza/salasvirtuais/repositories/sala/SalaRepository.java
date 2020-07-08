package br.com.ottimizza.salasvirtuais.repositories.sala;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.salasvirtuais.models.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, BigInteger>, SalaRepositoryCustom{

	
}
