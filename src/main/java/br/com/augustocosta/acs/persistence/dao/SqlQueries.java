package br.com.augustocosta.acs.persistence.dao;

public class SqlQueries {
    public static final String QUERY_USUARIO_BY_PERFIL =
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
            "SELECT loc.LocalEstoqueId " +
                    ",loc.DescricaoLocal " +
                    ",produto.ProdutoId " +
                    ",produto.CodigoInterno " +
                    ",produto.DescricaoProduto " +
                    ",entrada.QtdProduto - (CASE WHEN saida.QtdProduto IS NULL THEN 0 ELSE saida.QtdProduto END) AS QtdProduto " +
                    ",entrada.ValorProduto - (CASE WHEN saida.ValorProduto IS NULL THEN 0 ELSE saida.ValorProduto END) AS ValorProduto " +
                    "FROM dbo.tbl_produto produto " +
                    "JOIN ( " +
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
                    ",SUM(etq.Quantidade) * -1 AS QtdProduto " +
                    ",SUM(etq.ValorMovimentacao) * -1 AS ValorProduto " +
                    "FROM dbo.tbl_estoque etq " +
                    "JOIN dbo.tbl_movimentacao mov ON etq.MovimentacaoId = mov.MovimentacaoId " +
                    "WHERE mov.TipoMovimentacaoId <> (SELECT TipoMovimentacaoID FROM dbo.tbl_tipomovimentacao WHERE DescricaoMovimentacao = 'Entrada') " +
                    "GROUP BY etq.LocalEstoqueId,etq.ProdutoId " +
                    ") saida ON (entrada.ProdutoId = saida.ProdutoId AND entrada.LocalEstoqueId = saida.LocalEstoqueId) " +
                    "JOIN dbo.tbl_localestoque loc ON entrada.LocalEstoqueId = loc.LocalEstoqueId " +
                    "ORDER BY loc.DescricaoLocal, produto.DescricaoProduto";

}