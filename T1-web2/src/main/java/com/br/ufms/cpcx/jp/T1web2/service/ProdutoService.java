package com.br.ufms.cpcx.jp.T1web2.service;


import com.br.ufms.cpcx.jp.T1web2.entity.Produto;

import com.br.ufms.cpcx.jp.T1web2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;


    public List<Produto> buscarTodos(String descricao, Double precoMinimo, Double precoMaximo) {
        Stream<Produto> busca;
        busca = produtoRepository.findAll().stream().filter(produto -> {
            if (Objects.nonNull(descricao)) {
                return produto.getDescricao().contains(descricao);
            } else return true;
        }).filter(produto -> {
            if (Objects.nonNull(precoMinimo)) {
                Double menorValor = retValorMinimo(produto.getPrecoVendaFisica(), produto.getPrecoVendaJuridica());
                return menorValor >= precoMinimo;
            }
            return true;
        }).filter(produto -> {
            if (Objects.nonNull(precoMaximo)) {
                Double maiorValor = retValorMaximo(produto.getPrecoVendaFisica(), produto.getPrecoVendaJuridica());
                return maiorValor <= precoMaximo;
            }
            return true;
        }).sorted(Comparator.comparing(Produto::getId));
        return busca.collect(Collectors.toList());
    }

    private Double retValorMaximo(Double valorFisico, Double valorJuridico) {
        if (valorFisico > valorJuridico) {
            return valorFisico;
        } else {
            return valorJuridico;
        }
    }

    private Double retValorMinimo(Double valorFisico, Double valorJuridico) {
        if (valorFisico < valorJuridico) {
            return valorFisico;
        } else {
            return valorJuridico;
        }
    }

    public List<Produto> produtosMenorIdade(String descricao, Double precoMinimo, Double precoMaximo) {

        Stream<Produto> busca;
        busca = produtoRepository.produtosMenorIdade().stream().filter(produto -> {
            if (Objects.nonNull(descricao)) {
                return produto.getDescricao().contains(descricao);
            } else return true;
        }).filter(produto -> {
            if (Objects.nonNull(precoMinimo)) {
                Double menorValor = retValorMinimo(produto.getPrecoVendaFisica(), produto.getPrecoVendaJuridica());
                return menorValor <= precoMinimo;
            }
            return true;
        }).filter(produto -> {
            if (Objects.nonNull(precoMinimo)) {
                Double maiorValor = retValorMaximo(produto.getPrecoVendaFisica(), produto.getPrecoVendaJuridica());
                return maiorValor <= precoMaximo;
            }
            return true;
        }).sorted(Comparator.comparing(Produto::getId));
        return busca.collect(Collectors.toList());
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
