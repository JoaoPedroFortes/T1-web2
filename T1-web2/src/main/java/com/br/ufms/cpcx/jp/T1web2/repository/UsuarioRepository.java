package com.br.ufms.cpcx.jp.T1web2.repository;

import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
