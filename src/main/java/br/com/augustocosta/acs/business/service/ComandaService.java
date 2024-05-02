package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoComanda;
import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.integration.projections.prjComanda;
import br.com.augustocosta.acs.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    private final ComandaRepository repository;
    private final AgendamentoRepository agendamentoRepository;
    private final FormasPagamentoRepository formasPagamentoRepository;
    private final BandeirasRepository bandeirasRepository;
    private final ComandaPagamentoRepository comandaPagamentoRepository;

    @Autowired
    public ComandaService(ComandaRepository repository, AgendamentoRepository agendamentoRepository, FormasPagamentoRepository formasPagamentoRepository, BandeirasRepository bandeirasRepository, ComandaPagamentoRepository comandaPagamentoRepository) {
        this.repository = repository;
        this.agendamentoRepository = agendamentoRepository;
        this.formasPagamentoRepository = formasPagamentoRepository;
        this.bandeirasRepository = bandeirasRepository;
        this.comandaPagamentoRepository = comandaPagamentoRepository;
    }

    @Transactional
    public tblComanda create(tblComanda table) {
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setCriadoPor(1);
        table.setAlteradoPor(1);
        return repository.save(table);
    }

    public Optional<tblComanda> getById(Integer id) {
        return repository.findById(id);
    }

    public tblComanda getTableById(Integer id) {
        return repository.getReferenceById(id);
    }

    public List<tblComanda> getByAgendamento(tblAgendamento agendamento) {
        return repository.findByAgendamento(agendamento);
    }

    public List<tblComanda> getAll() {
        return repository.findAll();
    }

    public List<dtoComanda> getAllByAtivo() {
        List<prjComanda> projections = repository.findComandasByAtivo();
        return convertProjectionToDto(projections);
    }

    public List<tblComanda> getOpenedActives() {
        return repository.findBySituacaoTrueAndAtivoTrue();
    }

    public List<tblComanda> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblComanda> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public void update(dtoComanda dados) {
        tblComanda comanda = repository.getReferenceById(dados.getComandaId());

        // Processa e salva os pagamentos
        dados.getPagamentos().forEach(pagamento -> {
            tblFormasPagamento formasPagamento = formasPagamentoRepository.getReferenceById(pagamento.getFormaPagamento().getId());
            tblBandeiras bandeiras = bandeirasRepository.getReferenceById(pagamento.getBandeira().getId());
            tblComandaPagamento table = new tblComandaPagamento();
            ComandaPagamento id = new ComandaPagamento(comanda.getId(), pagamento.getFormaPagamento().getId());
            table.setId(id);
            table.setComanda(comanda);
            table.setFormaPagamento(formasPagamento);
            table.setBandeira(bandeiras);
            table.setParcelas(pagamento.getParcelas());
            table.setValorPagamento(pagamento.getValorPagamento());
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setDataAlteracao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setAlteradoPor(1);
            comandaPagamentoRepository.save(table);
        });

        // Processa e salva os comanda
        comanda.setSituacao(false);
        comanda.setDataAlteracao(LocalDateTime.now());
        comanda.setAlteradoPor(1);
        repository.save(comanda);

        // Processa e salva os caixa

    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblComanda table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comanda não encontrada com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblComanda> table = repository.findById(id);
        return table.map(tblComanda::getAtivo).orElse(false);
    }

    public List<dtoComanda> convertProjectionToDto(List<prjComanda> projections) {
        List<dtoComanda> dtos = new ArrayList<>();
        for(prjComanda prj : projections) {
            dtoComanda dto = new dtoComanda();
            dto.setComandaId(prj.getComandaId());
            dto.setAgendamentoId(prj.getAgendamentoId());
            dto.setDataAgendamento(prj.getDataAgendamento());
            dto.setHoraAgendamento(prj.getHoraAgendamento());
            dto.setClienteId(prj.getClienteId());
            dto.setNomeCliente(prj.getNomeCliente());
            dto.setColaboradorId(prj.getColaboradorId());
            dto.setNomeColaborador(prj.getNomeColaborador());
            dto.setValorServicos(prj.getValorServicos());
            dto.setValorProdutos(prj.getValorProdutos());
            dto.setValorDescontos(prj.getValorDescontos());
            dto.setValorComissao(prj.getValorComissao());
            dto.setValorEncargos(prj.getValorEncargos());
            dto.setValorComanda(prj.getValorComanda());
            dto.setSituacao(prj.getSituacao());
            dto.setAtivo(prj.getAtivo());
            dto.setDataCriacao(prj.getDataCriacao());
            dto.setCriadoPor(prj.getCriadoPor());
            dto.setDataAlteracao(prj.getDataAlteracao());
            dto.setAlteradoPor(prj.getAlteradoPor());
            dtos.add(dto);
        }
        return dtos;
    }
}