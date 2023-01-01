package com.ricartedev.delivery.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricartedev.delivery.domain.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  List<Cliente> findByNome(String nome);

  List<Cliente> findByNomeContaining(String nome);

  Optional<Cliente> findByEmail(String email);

}
