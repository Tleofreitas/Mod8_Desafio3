package com.devsupdesafio3.desafio3.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsupdesafio3.desafio3.dto.ClientDTO;
import com.devsupdesafio3.desafio3.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService service;

	// ------- Buscar clientes por ID -----------------------------------
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	// ------- Buscar todos os clientes de forma paginada---------------
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
		// Pageable = Listagem paginada
		Page<ClientDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto); // Retornar Status 200
	}

	// ------- Adicionar um novo Cliente no Banco -----------------------
	@PostMapping
	public ResponseEntity<ClientDTO> insert
		(@Valid /* Para checar as validações inseridas no DTO*/
		 @RequestBody ClientDTO dto) {
		// @RequestBody = Corpo da requisição
		dto = service.insert(dto); // Chamar o serviço de inserção e passar os dados
		// URI = link do recurso criado | Boa prática
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);// Retorno Customizado Status 201 Created
	}

	// ------- Atualizar um novo Cliente no Banco -----------------------
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
		dto = service.update(id, dto); // Chamar o serviço de atualização com o Id passado e as infos de atualização
		return ResponseEntity.ok(dto); // Retorno Customizado Status 200
	}
	
	// ------- Deletar um novo Cliente no Banco -----------------------
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); // Chamar o serviço de deletar com o Id passado
		return ResponseEntity.noContent().build(); // Retorno Customizado Status 204 = Sem retorno
    }
}
