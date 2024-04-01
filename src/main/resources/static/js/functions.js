function visualizarCaracteristica(caracteristicaId, descricao, ativo) {
    document.getElementById("field_Id").value = caracteristicaId;
    document.getElementById("field_Descricao").value = descricao;
    document.getElementById("field_Active").checked = ativo === 'true';
}

function visualizarCargo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarCategoria(categoriaId, nome, ativo) {
    document.getElementById("field_Id").value = categoriaId;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_Active").checked = ativo === 'true';
}

function visualizarDiasSemana(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarLinha(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarLocalEstoque(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarMarca(id, categoriaId, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_CategoriaId").value = categoriaId;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}


function visualizarPeriodo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarPermissoes(id, name, description, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Description").value = description;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarServico(id, nome, tempo, valor, desconto, comissao, observacao, ativo) {
    console.log(id);
    document.getElementById("field_Id'").value = id;
    document.getElementById("field_Nome").value = nome;
    document.getElementById("field_Tempo").value = tempo;
    document.getElementById("field_Valor").value = valor;
    document.getElementById("field_Desconto").value = desconto;
    document.getElementById("field_Comissao").value = comissao;
    document.getElementById("field_Observacao").value = observacao;
    document.getElementById("field_Ativo").checked = ativo === 'true';
}

function visualizarTipo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarPerfil(id, tipoperfilid, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_TipoPerfilId").value = tipoperfilid;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarDiasSemana(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function confirmarExclusao(event) {
    event.preventDefault();
    var confirmacao = confirm("Deseja excluir o registro?");
    if (confirmacao) {
        event.target.closest('form').submit();
    }
}

function visualizarProduto(id, codigoInterno, nome, codigoBarras, marcaId, linhaId, caracteristicaId, estoqueMinimo, custo, valorVenda, comissao, ativo) {
    console.log(id);
    document.getElementById("field_Id").value = id;
    document.getElementById("field_CodigoInterno").value = codigoInterno;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_CodigoBarras").value = codigoBarras;
    document.getElementById("field_MarcaId").value = marcaId;
    document.getElementById("field_LinhaId").value = linhaId;
    document.getElementById("field_CaracteristicaId").value = caracteristicaId;
    document.getElementById("field_EstoqueMinimo").value = estoqueMinimo;
    document.getElementById("field_Custo").value = custo;
    document.getElementById("field_valorVenda").value = valorVenda;
    document.getElementById("field_Comissao").value = comissao;
    document.getElementById("field_Active").checked = ativo === 'true';
}
