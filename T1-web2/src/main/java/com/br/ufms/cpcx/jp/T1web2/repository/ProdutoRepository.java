package com.br.ufms.cpcx.jp.T1web2.repository;


import com.br.ufms.cpcx.jp.T1web2.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //HQL
     @Query("select distinct p from Produto as p where p.idadePermitida<18") List<Produto> produtosMenorIdade();
}
