package com.br.ufms.cpcx.jp.T1web2.repository;


import com.br.ufms.cpcx.jp.T1web2.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
}
