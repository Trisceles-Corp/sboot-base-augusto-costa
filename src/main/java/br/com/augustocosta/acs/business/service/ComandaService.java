package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoComanda;
import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.integration.projections.prjComanda;
import br.com.augustocosta.acs.persistence.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private final CaixaMovimentacaoRepository caixaMovimentacaoRepository;
    private final CaixaRepository caixaRepository;
    private final TipoMovimentacaoRepository tipoMovimentacaoRepository;
    private final SituacaoAgendamentoRepository situacaoAgendamentoRepository;
    private final VendaRepository vendaRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final VendaProdutoRepository vendaProdutoRepository;
    private final EstoqueRepository estoqueRepository;

    @Autowired
    public ComandaService(ComandaRepository repository, AgendamentoRepository agendamentoRepository, FormasPagamentoRepository formasPagamentoRepository, BandeirasRepository bandeirasRepository, ComandaPagamentoRepository comandaPagamentoRepository, CaixaMovimentacaoRepository caixaMovimentacaoRepository, CaixaRepository caixaRepository, TipoMovimentacaoRepository tipoMovimentacaoRepository, SituacaoAgendamentoRepository situacaoAgendamentoRepository, VendaRepository vendaRepository, MovimentacaoRepository movimentacaoRepository, VendaProdutoRepository vendaProdutoRepository, EstoqueRepository estoqueRepository) {
        this.repository = repository;
        this.agendamentoRepository = agendamentoRepository;
        this.formasPagamentoRepository = formasPagamentoRepository;
        this.bandeirasRepository = bandeirasRepository;
        this.comandaPagamentoRepository = comandaPagamentoRepository;
        this.caixaMovimentacaoRepository = caixaMovimentacaoRepository;
        this.caixaRepository = caixaRepository;
        this.tipoMovimentacaoRepository = tipoMovimentacaoRepository;
        this.situacaoAgendamentoRepository = situacaoAgendamentoRepository;
        this.vendaRepository = vendaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.vendaProdutoRepository = vendaProdutoRepository;
        this.estoqueRepository = estoqueRepository;
    }
    @Autowired
    private EntityManager entityManager;

    public tblCaixa findCaixaById(Integer id) {
        return entityManager.find(tblCaixa.class, id);
    }

    public tblSituacaoAgendamento findSituacaoAgendamentoByNome(String nome) {
        String jpql = "SELECT s FROM tblSituacaoAgendamento s WHERE s.nome = :nome";
        return entityManager.createQuery(jpql, tblSituacaoAgendamento.class)
                .setParameter("nome", nome)
                .getSingleResult();
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
    public void update(dtoComanda dados) throws IllegalArgumentException {
        if (dados.getPagamentos() == null || CollectionUtils.isEmpty(dados.getPagamentos())) {
            throw new IllegalArgumentException("Nenhum pagamento informado para a comanda.");
        }
        final Integer caixaId = dados.getCaixaId();
        tblComanda comanda = repository.findById(dados.getComandaId()).orElseThrow(() -> new RuntimeException("Comanda não encontrado"));

        // Processa e salva os pagamentos
        dados.getPagamentos().forEach(pagamento -> {
            tblFormasPagamento formasPagamento = formasPagamentoRepository.getReferenceById(pagamento.getFormaPagamento().getId());
            tblBandeiras bandeiras = bandeirasRepository.getReferenceById(pagamento.getBandeira().getId());
            ComandaPagamento id = new ComandaPagamento(comanda.getId(), pagamento.getFormaPagamento().getId());
            tblComandaPagamento comandaPagamento = new tblComandaPagamento();
            comandaPagamento.setId(id);
            comandaPagamento.setComanda(comanda);
            comandaPagamento.setFormaPagamento(formasPagamento);
            comandaPagamento.setBandeira(bandeiras);
            comandaPagamento.setParcelas(pagamento.getParcelas());
            comandaPagamento.setValorPagamento(pagamento.getValorPagamento());
            comandaPagamento.setAtivo(true);
            comandaPagamento.setDataCriacao(LocalDateTime.now());
            comandaPagamento.setDataAlteracao(LocalDateTime.now());
            comandaPagamento.setCriadoPor(1);
            comandaPagamento.setAlteradoPor(1);
            comandaPagamentoRepository.save(comandaPagamento);

            // Processa e salva os caixa
            tblCaixaMovimentacao caixaMovimentacao = new tblCaixaMovimentacao();
            tblTipoMovimentacao tipoMovimentacaoEntrada = tipoMovimentacaoRepository.findByDescricaoMovimentacao("Entrada");
            caixaMovimentacao.setCaixaId(caixaId);
            caixaMovimentacao.setComanda(comanda);
            caixaMovimentacao.setTipoMovimentacao(tipoMovimentacaoEntrada);
            caixaMovimentacao.setFormaPagamento(formasPagamento);
            caixaMovimentacao.setValorMovimentacao(pagamento.getValorPagamento());
            caixaMovimentacao.setAtivo(true);
            caixaMovimentacao.setDataCriacao(LocalDateTime.now());
            caixaMovimentacao.setDataAlteracao(LocalDateTime.now());
            caixaMovimentacao.setCriadoPor(1);
            caixaMovimentacao.setAlteradoPor(1);
            caixaMovimentacaoRepository.save(caixaMovimentacao);
        });

        // Processa e salva os comanda
        comanda.setSituacao(false);
        comanda.setDataAlteracao(LocalDateTime.now());
        comanda.setAlteradoPor(1);
        repository.save(comanda);

        // Processo e salva agendamento
        tblSituacaoAgendamento situacaoAgendamento = findSituacaoAgendamentoByNome("Finalizado");
        tblAgendamento agendamento = agendamentoRepository.findById(dados.getAgendamentoId()).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamento.setSituacao(situacaoAgendamento);
        agendamento.setDataAlteracao(LocalDateTime.now());
        agendamento.setAlteradoPor(1);
        agendamentoRepository.save(agendamento);

        // Processa e salva venda produtos quando existir
        tblVenda venda = vendaRepository.findByAgendamentoId(agendamento.getId());
        if(venda != null){
            Integer maxNrNotaFiscal = movimentacaoRepository.findMaxNrNotaFiscal();
            if (maxNrNotaFiscal == null) {
                maxNrNotaFiscal = 1; // Caso não existam registros, definimos como 0 ou outro valor inicial adequado
            }
            else {
                maxNrNotaFiscal += 1;
            }
            tblTipoMovimentacao tipoMovimentacaoSaida = tipoMovimentacaoRepository.findByDescricaoMovimentacao("Saida");
            tblMovimentacao movimentacao = new tblMovimentacao();
            movimentacao.setTipoMovimentacao(tipoMovimentacaoSaida);
            movimentacao.setVenda(venda);
            movimentacao.setObservacao("Vanda de produtos no agendamento.");
            movimentacao.setValorTotal(venda.getValorTotal());
            movimentacao.setNrNotaFiscal(maxNrNotaFiscal);
            movimentacao.setSerieNotaFiscal("2");
            movimentacao.setAtivo(true);
            movimentacao.setDataCriacao(LocalDateTime.now());
            movimentacao.setDataAlteracao(LocalDateTime.now());
            movimentacao.setCriadoPor(1);
            movimentacao.setAlteradoPor(1);
            movimentacaoRepository.save(movimentacao);

            // Processa e salva estoque
            List<tblVendaProduto> vendaProdutos = vendaProdutoRepository.findByVenda(venda);
            vendaProdutos.forEach(vendaProduto -> {
               tblEstoque estoque = new tblEstoque();
               estoque.setProduto(vendaProduto.getProduto());
               estoque.setLocalEstoque(venda.getLocalEstoque());
               estoque.setMovimentacao(movimentacao);
               estoque.setQuantidade(vendaProduto.getQuantidade());
               estoque.setValorMovimentacao(vendaProduto.getValorTotal());
               estoque.setAtivo(true);
               estoque.setDataCriacao(LocalDateTime.now());
               estoque.setDataAlteracao(LocalDateTime.now());
               estoque.setCriadoPor(1);
               estoque.setAlteradoPor(1);
               estoqueRepository.save(estoque);
            });
        }
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