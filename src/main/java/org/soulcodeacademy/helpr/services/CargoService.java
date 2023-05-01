package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    // Listar todos
    public List<Cargo> listar() {
        // Retorna os dados da tabela em forma de lista
        // SELECT * FROM cargo;
        return this.cargoRepository.findAll();
    }

    // Listar um pelo ID
    public Cargo buscarCargoPorId(Integer idCargo) {
        return this.cargoRepository.findById(idCargo)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Cargo não encontrado."));
    }
    // Salvar
    public Cargo salvar(CargoDTO dto) {
        // INSERT INTO cargo
        Cargo cargo = new Cargo(null, dto.getNome(), dto.getDescricao(), dto.getSalario());
        Cargo cargoSalvo = this.cargoRepository.save(cargo);
        return cargoSalvo;
    }
    // Atualizar
    // Precisa do ID do cargo e dos dados atualizados
    public Cargo atualizar(Integer idCargo, CargoDTO dto) {
        // Verificar se o cargo existe mesmo
        Cargo cargoAtual = this.buscarCargoPorId(idCargo);

        cargoAtual.setNome(dto.getNome());
        cargoAtual.setDescricao(dto.getDescricao());
        cargoAtual.setSalario(dto.getSalario());

        // Atualiza a entidade pois ela possui um ID diferente de nulo
        Cargo atualizado = this.cargoRepository.save(cargoAtual);
        return atualizado;
    }
    // Deletar
    public void deletar(Integer idCargo) {
        Cargo cargo = this.buscarCargoPorId(idCargo);
        // DELETE FROM cargo WHERE idCargo = ?
        this.cargoRepository.delete(cargo);
    }
}
