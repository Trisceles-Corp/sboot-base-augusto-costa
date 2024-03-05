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

function visualizarMarca(id, name, active) {
    document.getElementById("field_Id").value = id;
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

function visualizarDiasSemana(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}
