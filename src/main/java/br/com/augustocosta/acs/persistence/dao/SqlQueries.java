package br.com.augustocosta.acs.persistence.dao;

public class SqlQueries {
    public static final String QUERY_USUARIO_BY_PERFIL =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "CASE WHEN usr.PerfilId NOT IN (4,6) THEN usr.Nome + ' ' + usr.Sobrenome + '(' + crg.Nome + ')' ELSE usr.Nome + ' ' + usr.Sobrenome END AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId = :perfilId " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_USUARIO_BY_COLABORADOR =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "CASE WHEN usr.PerfilId NOT IN (4,6) THEN usr.Nome + ' ' + usr.Sobrenome + '(' + crg.Nome + ')' ELSE usr.Nome + ' ' + usr.Sobrenome END AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId NOT IN(4,6) " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_USUARIO_BY_PERFIL_CPF =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "usr.Nome + ' ' + usr.Sobrenome AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId = :perfilId " +
                    "AND usr.CpfCnpj = :cpfCnpj " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_USUARIO_BY_PERFIL_EMAIL =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "usr.Nome + ' ' + usr.Sobrenome AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId = :perfilId " +
                    "AND usr.Email Like = :email + '%' " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_USUARIO_BY_PERFIL_NOME =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "usr.Nome + ' ' + usr.Sobrenome AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END AS GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId = :perfilId " +
                    "AND usr.Nome Like = :nome + '%' " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_USUARIO_BY_PERFIL_SOBRENOME =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "usr.Nome + ' ' + usr.Sobrenome AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId = :perfilId " +
                    "AND usr.Sobrenome Like = :sobrenome + '%' " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_USUARIO_BY_PERFIL_CELULAR =
            "SELECT usr.UsuarioId, " +
                    "usr.CpfCnpj, " +
                    "usr.Nome, " +
                    "usr.Sobrenome, " +
                    "usr.Nome + ' ' + usr.Sobrenome AS NomeCompleto, " +
                    "usr.Genero, " +
                    "CASE usr.Genero WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Feminino' ELSE 'Outros' END GeneroDescricao, " +
                    "usr.DataNascimento, " +
                    "usr.Email, " +
                    "usr.Senha, " +
                    "usr.EnderecoId, " +
                    "edr.CEP, " +
                    "edr.Logradouro, " +
                    "edr.Numero, " +
                    "edr.Complemento, " +
                    "edr.Bairro, " +
                    "edr.Cidade, " +
                    "edr.UF, " +
                    "usr.DDICelular, " +
                    "usr.DDDCelular, " +
                    "usr.Celular, " +
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + usr.Celular AS CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + usr.Telefone AS TelefoneCompleto, " +
                    "usr.Profissao, " +
                    "usr.Observacao, " +
                    "usr.CargoId, " +
                    "crg.Nome CargoNome, " +
                    "usr.PerfilId, " +
                    "prf.Nome PerfilNome, " +
                    "usr.Ativo, " +
                    "usr.DataAlteracao, " +
                    "usr.AlteradoPor " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN dbo.tbl_endereco edr ON usr.EnderecoId = edr.EnderecoId " +
                    "JOIN dbo.tbl_cargo crg ON usr.CargoId = crg.CargoId " +
                    "JOIN dbo.tbl_perfil prf ON usr.PerfilId = prf.PerfilId " +
                    "WHERE usr.PerfilId = :perfilId " +
                    "AND usr.Celular = :celular " +
                    "AND usr.Ativo = 1 " +
                    "Order By 5";

    public static final String QUERY_INVENTARIO =
            "SELECT COALESCE(loc.LocalEstoqueId,1) AS LocalEstoqueId " +
                    ",COALESCE(loc.DescricaoLocal, 'Loja') AS DescricaoLocal " +
                    ",produto.ProdutoId " +
                    ",produto.CodigoInterno " +
                    ",produto.DescricaoProduto " +
                    ",COALESCE(entrada.QtdProduto - (CASE WHEN saida.QtdProduto IS NULL THEN 0 ELSE saida.QtdProduto END),0) AS QtdProduto " +
                    ",COALESCE(entrada.ValorProduto - (CASE WHEN saida.ValorProduto IS NULL THEN 0 ELSE saida.ValorProduto END),0) AS ValorProduto " +
                    "FROM dbo.tbl_produto produto " +
                    "LEFT JOIN ( " +
                    "SELECT etq.LocalEstoqueId " +
                    ",etq.ProdutoId " +
                    ",SUM(etq.Quantidade) AS QtdProduto " +
                    ",SUM(etq.ValorMovimentacao) AS ValorProduto " +
                    "FROM dbo.tbl_estoque etq " +
                    "JOIN dbo.tbl_movimentacao mov ON etq.MovimentacaoId = mov.MovimentacaoId " +
                    "WHERE mov.TipoMovimentacaoId = (SELECT TipoMovimentacaoID FROM dbo.tbl_tipomovimentacao WHERE DescricaoMovimentacao = 'Entrada') " +
                    "GROUP BY etq.LocalEstoqueId,etq.ProdutoId " +
                    ") entrada ON produto.ProdutoId = entrada.ProdutoId " +
                    "LEFT JOIN ( " +
                    "SELECT etq.LocalEstoqueId " +
                    ",etq.ProdutoId " +
                    ",SUM(etq.Quantidade) AS QtdProduto " +
                    ",SUM(etq.ValorMovimentacao) AS ValorProduto " +
                    "FROM dbo.tbl_estoque etq " +
                    "JOIN dbo.tbl_movimentacao mov ON etq.MovimentacaoId = mov.MovimentacaoId " +
                    "WHERE mov.TipoMovimentacaoId <> (SELECT TipoMovimentacaoID FROM dbo.tbl_tipomovimentacao WHERE DescricaoMovimentacao = 'Entrada') " +
                    "GROUP BY etq.LocalEstoqueId,etq.ProdutoId " +
                    ") saida ON (entrada.ProdutoId = saida.ProdutoId AND entrada.LocalEstoqueId = saida.LocalEstoqueId) " +
                    "LEFT JOIN dbo.tbl_localestoque loc ON entrada.LocalEstoqueId = loc.LocalEstoqueId " +
                    "ORDER BY produto.DescricaoProduto";

    public static final String QUERY_USUARIO_SOLICITANTE =
            "SELECT usr.* " +
                    "FROM dbo.tbl_usuario usr " +
                    "JOIN (SELECT * FROM dbo.tbl_Perfil WHERE Nome NOT IN ('Administrador','Cliente','Fornecedor')) sol ON usr.PerfilId = sol.PerfilId " +
                    "WHERE usr.Ativo = 1 " +
                    "ORDER BY usr.Nome";

    public static final String QUERY_COMANDAS =
            "SELECT cmd.ComandaId " +
                    ",cmd.AgendamentoId " +
                    ",age.DataAgendamento " +
                    ",age.HoraAgendamento " +
                    ",age.ClienteId " +
                    ",cli.Nome + ' ' + cli.Sobrenome AS NomeCliente " +
                    ",age.ColaboradorId " +
                    ",col.Nome + ' ' + col.Sobrenome AS NomeColaborador " +
                    ",cmd.ValorServicos " +
                    ",cmd.ValorProdutos " +
                    ",cmd.ValorDescontos " +
                    ",cmd.ValorComissao " +
                    ",cmd.ValorEncargos " +
                    ",(cmd.ValorServicos + cmd.ValorProdutos - cmd.ValorDescontos) AS ValorComanda " +
                    ",cmd.Situacao " +
                    ",cmd.Ativo " +
                    ",cmd.DataCriacao " +
                    ",cmd.DataAlteracao " +
                    ",cmd.CriadoPor " +
                    ",cmd.AlteradoPor " +
                    "FROM dbo.tbl_comanda cmd " +
                    "JOIN dbo.tbl_agendamento age ON cmd.AgendamentoId = age.AgendamentoId " +
                    "JOIN dbo.tbl_usuario cli ON age.ClienteId = cli.UsuarioId " +
                    "JOIN dbo.tbl_usuario col ON age.ColaboradorId = col.UsuarioId " +
                    "WHERE cmd.Ativo = 1 " +
                    "AND cmd.Situacao = 1 " +
                    "AND age.SituacaoId = 2 " +
                    "ORDER BY " +
                    "age.DataAgendamento DESC";

    public static final String QUERY_COMISSOES =
            "SELECT cmd.ComandaId " +
                    ",cmd.AgendamentoId " +
                    ",age.DataAgendamento " +
                    ",age.HoraAgendamento " +
                    ",age.ClienteId " +
                    ",cli.Nome + ' ' + cli.Sobrenome AS NomeCliente " +
                    ",age.ColaboradorId " +
                    ",col.Nome + ' ' + col.Sobrenome AS NomeColaborador " +
                    ",cmd.ValorServicos " +
                    ",cmd.ValorProdutos " +
                    ",cmd.ValorDescontos " +
                    ",cmd.ValorComissao " +
                    ",cmd.ValorEncargos " +
                    ",(cmd.ValorServicos + cmd.ValorProdutos - cmd.ValorDescontos) AS ValorComanda " +
                    ",cmd.Situacao " +
                    ",cmd.Ativo " +
                    ",cmd.DataCriacao " +
                    ",cmd.DataAlteracao " +
                    ",cmd.CriadoPor " +
                    ",cmd.AlteradoPor " +
                    "FROM dbo.tbl_comanda cmd " +
                    "JOIN dbo.tbl_agendamento age ON cmd.AgendamentoId = age.AgendamentoId " +
                    "JOIN dbo.tbl_usuario cli ON age.ClienteId = cli.UsuarioId " +
                    "JOIN dbo.tbl_usuario col ON age.ColaboradorId = col.UsuarioId " +
                    "WHERE cmd.Ativo = 1 " +
                    "AND cmd.Situacao = 0 " +
                    "AND age.ColaboradorId = :colaboradorId " +
                    "AND age.DataAgendamento BETWEEN :dataInc AND :dataFim " +
                    "ORDER BY " +
                    "age.DataAgendamento DESC";

    public static final String QUERY_CAIXAS =
            "SELECT cax.CaixaId " +
                    ",cax.Nome " +
                    ",NomeIndice " +
                    ",cax.ResponsavelAbertura " +
                    ",abe.Nome + ' ' + abe.Sobrenome AS NomeRespAbertura " +
                    ",abe.Email AS Email " +
                    ",cax.DataAbertura " +
                    ",CONVERT(date, cax.DataAbertura) AS Data " +
                    ",CONVERT(time, cax.DataAbertura) AS Hora " +
                    ",cax.ResponsavelFechamento " +
                    ",fec.Nome + ' ' + fec.Sobrenome AS NomeRespFechamento " +
                    ",cax.DataFechamento " +
                    ",cax.ValorAbertura " +
                    ",COALESCE(mov.Credito, 0) AS Credito " +
                    ",COALESCE(mov.Debito, 0) AS Debito " +
                    ",COALESCE(mov.Dinheiro, 0) AS Dinheiro " +
                    ",COALESCE(mov.Pix, 0) AS Pix " +
                    ",(cax.ValorAbertura + COALESCE(mov.Credito, 0) + COALESCE(mov.Debito, 0) + COALESCE(mov.Dinheiro, 0) + COALESCE(mov.Pix, 0)) AS ValorProvisorio " +
                    ",cax.ValorFechamento " +
                    ",cax.Ativo " +
                    ",cax.DataCriacao " +
                    ",cax.DataAlteracao " +
                    ",cax.CriadoPor " +
                    ",cax.AlteradoPor " +
                    "FROM dbo.tbl_caixa cax " +
                    "JOIN dbo.tbl_usuario abe ON cax.ResponsavelAbertura = abe.UsuarioId " +
                    "LEFT JOIN dbo.tbl_usuario fec ON cax.ResponsavelFechamento = fec.UsuarioId " +
                    "LEFT JOIN ( " +
                    "SELECT CaixaId, [Credito], [Debito], [Dinheiro], [Pix] FROM " +
                    "(SELECT cxm.CaixaId " +
                    "		,fpg.Nome " +
                    "		,cxm.ValorMovimentacao " +
                    "	FROM dbo.tbl_caixamovimentacao cxm " +
                    "	JOIN dbo.tbl_formaspagamento fpg ON cxm.FormaPagamentoId = fpg.FormaPagamentoId) src   " +
                    "PIVOT   " +
                    "(SUM (ValorMovimentacao)  " +
                    "FOR Nome IN   " +
                    "( [Credito], [Debito], [Dinheiro], [Pix])   " +
                    ") AS pvt   " +
                    ") mov ON cax.CaixaId = mov.CaixaId " +
                    "WHERE cax.Ativo = 1 " +
                    "ORDER BY cax.Nome";

    public static final String SP_GRIDAGENDA =
            "SELECT * " +
            "FROM dbo.tbl_gridagendamento grd " +
            "JOIN dbo.tbl_agendamento age ON grd.AgendamentoId = age.AgendamentoId " +
            "WHERE age.DataAgendamento = :dataAgenda";

    public static final String QUERY_MAXINDICECAIXA =
            "SELECT COALESCE(MAX(c.NomeIndice),0) " +
                    " FROM tbl_caixa c  " +
                    "WHERE CONVERT(DATE, c.DataAbertura) = CONVERT(DATE, GETDATE())";

    public static final String SP_OBTERAGENDA =
            "EXEC sp_ObterAgendaPorData :dataAgenda";

    public static final String SP_HORARIOSLIVRESCOLABORADOR =
            "EXEC sp_AgendaLivreColaborador :dataAgenda, :colaboradorId";

    public static final String SP_EFETIVACOMPRAPRODUTOS =
            "EXEC spCompraProdutoEfetivacao :compraId";

    public static final String SP_EFETIVASAIDAPRODUTOS =
            "EXEC spSaidaProdutoEfetivacao :saidaId";

}