package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.PermissoesPerfil;
import br.com.augustocosta.acs.integration.entity.tblPermissoesPerfil;
import br.com.augustocosta.acs.persistence.repository.PermissoesPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissoesPerfilService {

    private final PermissoesPerfilRepository repository;

    @Autowired
    public PermissoesPerfilService(PermissoesPerfilRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblPermissoesPerfil create(tblPermissoesPerfil table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    public Optional<tblPermissoesPerfil> findById(Integer perfilId, Integer permissaoId) {
        PermissoesPerfil id = new PermissoesPerfil(perfilId, permissaoId);
        return repository.findById(id);
    }

    @Transactional
    public tblPermissoesPerfil update(Integer perfilId, Integer permissaoId, tblPermissoesPerfil dados) {
        PermissoesPerfil id = new PermissoesPerfil(perfilId, permissaoId);
        tblPermissoesPerfil table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão Perfil não encontrado com id: " + id));

        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }
}
