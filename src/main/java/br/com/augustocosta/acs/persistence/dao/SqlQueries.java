package br.com.augustocosta.acs.persistence.dao;

public class SqlQueries {
    public static final String QUERY_USUARIO_BY_PERFIL =
            "SELECT usr.UsuarioId, " +
                    "usr.CPF, " +
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
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + CAST(usr.Celular AS varchar(20)) CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + CAST(usr.Telefone AS varchar(20)) TelefoneCompleto, " +
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
                    "WHERE usr.PerfilId = :perfilId";

    public static final String QUERY_USUARIO_BY_PERFIL_CPF =
            "SELECT usr.UsuarioId, " +
                    "usr.CPF, " +
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
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + CAST(usr.Celular AS varchar(20)) CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + CAST(usr.Telefone AS varchar(20)) TelefoneCompleto, " +
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
                    "AND usr.CPF = :cpf";

    public static final String QUERY_USUARIO_BY_PERFIL_EMAIL =
            "SELECT usr.UsuarioId, " +
                    "usr.CPF, " +
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
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + CAST(usr.Celular AS varchar(20)) CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + CAST(usr.Telefone AS varchar(20)) TelefoneCompleto, " +
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
                    "AND usr.Email Like = :email + '%'";

    public static final String QUERY_USUARIO_BY_PERFIL_NOME =
            "SELECT usr.UsuarioId, " +
                    "usr.CPF, " +
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
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + CAST(usr.Celular AS varchar(20)) CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + CAST(usr.Telefone AS varchar(20)) TelefoneCompleto, " +
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
                    "AND usr.Nome Like = :nome + '%'";

    public static final String QUERY_USUARIO_BY_PERFIL_SOBRENOME =
            "SELECT usr.UsuarioId, " +
                    "usr.CPF, " +
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
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + CAST(usr.Celular AS varchar(20)) CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + CAST(usr.Telefone AS varchar(20)) TelefoneCompleto, " +
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
                    "AND usr.Sobrenome Like = :sobrenome + '%'";

    public static final String QUERY_USUARIO_BY_PERFIL_CELULAR =
            "SELECT usr.UsuarioId, " +
                    "usr.CPF, " +
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
                    "'+'  + CAST(usr.DDICelular AS varchar(10)) + ' (' + CAST(usr.DDDCelular AS varchar(10)) + ') ' + CAST(usr.Celular AS varchar(20)) CelularCompleto, " +
                    "usr.DDITelefone, " +
                    "usr.DDDTelefone, " +
                    "usr.Telefone, " +
                    "'+'  + CAST(usr.DDITelefone AS varchar(10)) + ' (' + CAST(usr.DDDTelefone AS varchar(10)) + ') ' + CAST(usr.Telefone AS varchar(20)) TelefoneCompleto, " +
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
                    "AND usr.Celular = :celular";
}