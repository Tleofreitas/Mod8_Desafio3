package com.devsupdesafio3.desafio3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsupdesafio3.desafio3.dto.ClientDTO;
import com.devsupdesafio3.desafio3.entities.Client;
import com.devsupdesafio3.desafio3.repositories.ClientRepository;
import com.devsupdesafio3.desafio3.services.exceptions.DataBaseException;
import com.devsupdesafio3.desafio3.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	// ------- Buscar o Cliente por ID -------------
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Cliente inexistente"));
		return new ClientDTO(client);
	}

	// ------- Buscar todos os Clientes de forma Paginada -------------
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		// Buscar no banco de dados a lista de Produtos | Pageable = Listagem paginada
		Page<Client> result = repository.findAll(pageable);
		// Converter a lista de  Product para ProductDTO e retornar para o controlador
		return result.map(x -> new ClientDTO(x));
	}

	// ------- Auxiliar de Cópia -------------
	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		// Passar o dto recebido para o produto
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

	// ------- Adicionar um novo Cliente no Banco -------------
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		// Criar um Cliente
		Client entity = new Client();
		// Copiar os dados do dto para a entidade
		copyDtoToEntity(dto, entity);
		// Salvar o Cliente no Repository (banco de dados)
		entity = repository.save(entity);
		// Converter e retornar em DTO
		return new ClientDTO(entity);
	}

	// ------- Atualizar um Cliente no Banco -------------
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			// Instanciar um Cliente com a referência do Id
			Client entity = repository.getReferenceById(id);
			// Copiar os dados do dto para a entidade
			copyDtoToEntity(dto, entity);
			// Salvar no banco de dados
			entity = repository.save(entity);
			// Retornar como DTO
			return new ClientDTO(entity);
		}
		// Tratamento para quando tentar atualizar um Cliente que não existe
		catch (EntityNotFoundException e) {
			// Se não encontrar o Id, lança exceção
			throw new ResourceNotFoundException("Dados Inválidos");
		}
	}

	// ------- Deletar um Cliente -------------
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			// Se não encontrar o Id, lança exceção
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}
}
