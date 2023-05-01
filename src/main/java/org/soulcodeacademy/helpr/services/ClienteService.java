package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // torna o objeto da classe injetavel
public class ClienteService {
    @Autowired // injeção
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Integer idCliente) {
        return this.clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Cliente não encontrado."));
    }

    public Cliente salvar(ClienteDTO dto) {
        // Criação da entidade Cliente, a partir dos dados validados do DTO
        Cliente novoCliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha(), dto.getTelefone());

        return this.clienteRepository.save(novoCliente);
    }

    public Cliente atualizar(Integer idCliente, ClienteDTO dto) {
        Cliente clienteAtual = this.buscarClientePorId(idCliente);
        clienteAtual.setNome(dto.getNome());
        clienteAtual.setEmail(dto.getEmail());
        clienteAtual.setCpf(dto.getCpf());
        clienteAtual.setSenha(dto.getSenha());
        clienteAtual.setTelefone(dto.getTelefone());

        return this.clienteRepository.save(clienteAtual);
    }

    public void deletar(Integer idCliente) {
        Cliente cliente = this.buscarClientePorId(idCliente);
        this.clienteRepository.delete(cliente);
    }
}

