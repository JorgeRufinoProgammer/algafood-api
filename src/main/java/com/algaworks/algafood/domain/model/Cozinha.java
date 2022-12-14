package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

//Como queremos o Equals e HashCode apenas do ID, precisamos adicionar 2 annotations pra especificar isto

//Esta Annotation é o conjunto de: @ Getters, Setters, toString, EqualsAndHashCode e RequiredArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;	
	
	//O "mappedBy" recebe o nome da variavel do objeto Cozinha na classe Restaurante para fazer o vinculo
	//"JsonIgnore" server para evitar o loop de serialização dos objetos pois Restaurante vai chamar Cozinha e Cozinha
	//vai chamar Restaurantes e vai entrar em loop. Assim dizemos para o Spring não serializar esta lista de Restaurantes
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes =  new ArrayList<>();
}
