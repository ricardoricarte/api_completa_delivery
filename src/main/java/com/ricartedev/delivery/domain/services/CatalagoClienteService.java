package com.ricartedev.delivery.domain.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ricartedev.delivery.domain.exception.NegocioException;
import com.ricartedev.delivery.domain.models.Cliente;
import com.ricartedev.delivery.domain.repositories.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalagoClienteService {

	private ClienteRepository clienteRepository;

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
            .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
						.stream()
						.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if (emailEmUso) {
			throw new NegocioException("Ja existe um cliente cadastrado com este e-mail");
		}
		return clienteRepository.save(cliente);

	}

	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}


}
