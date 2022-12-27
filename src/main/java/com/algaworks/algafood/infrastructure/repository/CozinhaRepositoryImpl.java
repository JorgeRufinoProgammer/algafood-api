package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository{
			
		@PersistenceContext
		private EntityManager manager;
	
		@Override
		public List<Cozinha> listar(){
			return manager.createQuery("from Cozinha", Cozinha.class)
				   .getResultList();
		}
		
		@Transactional
		@Override
		public Cozinha salvar(Cozinha cozinha) {
			return manager.merge(cozinha);
		}
		
		@Override
		public Cozinha buscarPorId(Long id) {
			return manager.find(Cozinha.class, id);
		}
		
		@Transactional
		@Override
		public void deletar(Long id) {			
			Cozinha cozinha = buscarPorId(id);
			
			if (cozinha == null) {	
//				Esta "exception" recebe como parametro a quantidade minima que esperamos, no caso, esperamos que tivesse 1 cozinha para ser removida
				throw new EmptyResultDataAccessException(1);
			}
			
			manager.remove(cozinha);
		}

		@Override
		public List<Cozinha> consultarPorNome(String nomeCozinha) {
			return manager.createQuery("from Cozinha where nome like  :nome", Cozinha.class)
					.setParameter("nome", "%" + nomeCozinha + "%")
					.getResultList();
		}
	
}
