package com.br.ufms.cpcx.jp.T1web2.repository;

import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select distinct u from Usuario as u where u.login like :login")
    Usuario getUsuario(@Param("login") String login);

    @Query("select distinct u.isAdministrador from Usuario u where u.login like :login and u.isAdministrador = true")
    Boolean isAdministrador(@Param("login")String login);

    @Query("select distinct p from Pessoa p where p.id = :usuarioId")
    Pessoa retornaPessoa(@Param("usuarioId")Long usuarioId);
}
