package com.devsupdesafio3.desafio3.dto;

import java.time.LocalDate;

import com.devsupdesafio3.desafio3.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class ClientDTO {
    private Long id;
    @NotBlank(message = "Nome NÃO pode ser vazio!")
    @Size(min = 3, message = "O nome precisa ter pelo menos 3 caracteres!")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Datas FUTURAS não são aceitas!")
    private LocalDate birthDate;
    private Integer children;
	
    public ClientDTO() {
	}

	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
		children = entity.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

}
