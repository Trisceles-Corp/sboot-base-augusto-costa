package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.ServicosAgendamento;
import br.com.augustocosta.acs.integration.entity.tblServicosAgendamento;
import br.com.augustocosta.acs.persistence.repository.ServicosAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServicosAgendamentoService {

    private final ServicosAgendamentoRepository repository;

    @Autowired
    public ServicosAgendamentoService(ServicosAgendamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblServicosAgendamento create(tblServicosAgendamento table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    public Optional<tblServicosAgendamento> findById(Integer servicoId, Integer agendamentoId) {
        ServicosAgendamento id = new ServicosAgendamento(servicoId, agendamentoId);
        return repository.findById(id);
    }

    @Transactional
    public tblServicosAgendamento update(Integer servicoId, Integer agendamentoId, tblServicosAgendamento dados) {
        ServicosAgendamento id = new ServicosAgendamento(servicoId, agendamentoId);
        tblServicosAgendamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda Produtos não encontrado com id: " + id));

        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer servicoId, Integer agendamentoId) {
        ServicosAgendamento id = new ServicosAgendamento(servicoId, agendamentoId);
        repository.deleteById(id);
    }
}
