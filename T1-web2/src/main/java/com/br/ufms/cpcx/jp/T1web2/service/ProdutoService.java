package com.br.ufms.cpcx.jp.T1web2.service;


import com.br.ufms.cpcx.jp.T1web2.entity.Produto;

import com.br.ufms.cpcx.jp.T1web2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {

       return produtoRepository.findAll();

    }

    public List<Produto> produtosMenorIdade() {
        List<Produto> produtos = produtoRepository.produtosMenorIdade();
        return produtos;
    }


    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public Object alterar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Object buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
}
