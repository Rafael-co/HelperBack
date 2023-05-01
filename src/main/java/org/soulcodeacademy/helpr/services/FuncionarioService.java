package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // instanciar automaticamente minha classe
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService;

    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll();
    }

    public List<Funcionario> listarPorFaixaSalarial(Double valor1, Double valor2) {
        return this.funcionarioRepository.findBySalarioEntreFaixas(valor1, valor2);
    }

    public Funcionario buscasFuncionarioPorId(Integer idFuncionario) {
        return this.funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Funcionario não encontrado."));
    }

    public Funcionario salvar(FuncionarioDTO dto) {
        Cargo cargo = this.cargoService.buscarCargoPorId(dto.getIdCargo()); // verifica se o cargo existe mesmo
        // id, nome, email,cpf, String senha, foto, cargo
        // Transferindo informações do DTO para nossa entidade
        Funcionario funcionario = new Funcionario(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha(), dto.getFoto(), cargo);
        Funcionario salvo = this.funcionarioRepository.save(funcionario);

        return salvo;
    }

    public Funcionario atualizar(Integer idFuncionario, FuncionarioDTO dto) {
        // Busca o funcionario com o idFuncionario
        Funcionario funcionarioAtual = this.buscasFuncionarioPorId(idFuncionario);
        // Busca os dados do cargo a ser alterado
        Cargo cargo = this.cargoService.buscarCargoPorId(dto.getIdCargo());

        funcionarioAtual.setNome(dto.getNome());
        funcionarioAtual.setEmail(dto.getEmail());
        funcionarioAtual.setCpf(dto.getCpf());
        funcionarioAtual.setSenha(dto.getSenha());
        funcionarioAtual.setFoto(dto.getFoto());
        funcionarioAtual.setCargo(cargo);

        Funcionario atualizado = this.funcionarioRepository.save(funcionarioAtual);
        return atualizado;
    }

    public void deletar(Integer idFuncionario) {
        Funcionario funcionario = this.buscasFuncionarioPorId(idFuncionario);
        this.funcionarioRepository.delete(funcionario);
    }
}
