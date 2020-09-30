package br.com.ottimizza.salasvirtuais.domain.projections;

import lombok.Value;

import java.math.BigInteger;

@Value
public class SalaUsuarioProjection {

    private BigInteger id;

    private String nome;

    private Boolean publica;

    private BigInteger empresaId;

    private String usuariosSala;
}
