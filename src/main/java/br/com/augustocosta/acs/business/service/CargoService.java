package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.persistence.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    // Método para criar ou atualizar um cargo
    @Transactional
    public tblCargo salvarCargo(tblCargo cargo) {
        return cargoRepository.save(cargo);
    }

    // Método para buscar um cargo pelo ID
    public Optional<tblCargo> buscarCargoPorId(Integer id) {
        return cargoRepository.findById(id);
    }

    // Método para buscar todos os cargos
    public List<tblCargo> listarTodosCargos() {
        return cargoRepository.findAll();
    }

    // Método para buscar cargos ativos
    public List<tblCargo> listarCargosAtivos() {
        return cargoRepository.findByAtivoTrue();
    }

    // Método para buscar cargos inativos
    public List<tblCargo> listarCargosInativos() {
        return cargoRepository.findByAtivoFalse();
    }

    // Método para atualizar um cargo
    @Transactional
    public tblCargo atualizarCargo(tblCargo cargo) {
        return cargoRepository.save(cargo);
    }

    // Método para deletar um cargo
    @Transactional
    public void deletarCargo(Integer id) {
        cargoRepository.deleteById(id);
    }

    // Método para verificar se um cargo com um determinado ID está ativo
    public boolean isCargoAtivo(Integer id) {
        Optional<tblCargo> cargo = cargoRepository.findById(id);
        return cargo.map(tblCargo::getAtivo).orElse(false);
    }
}