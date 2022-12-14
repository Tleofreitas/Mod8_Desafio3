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
		Page<Client> result = repository.findAll(pageable);
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
		copyDtoToEntity(dto, entity);
		// Salvar o Cliente no Repository
		entity = repository.save(entity);
		// Converter e retornar em DTO
		return new ClientDTO(entity);
	}

	// ------- Atualizar um Cliente no Banco -------------
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
		// Tratamento para quando tentar atualizar um Cliente que não existe
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Dados Inválidos");
		}
	}

	// ------- Deletar um Cliente -------------
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cliente Inexistente");

		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}

}
