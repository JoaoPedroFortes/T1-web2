package com.br.ufms.cpcx.jp.T1web2.dto;

import com.br.ufms.cpcx.jp.T1web2.entity.Produto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data

public class ProdutoPessoaFisicaDTO {
    private long id;
    private String descricao;
    private int quantidadeEstoque;
    private int idadePermitida;
    private Double precoVendaFisica;


    public ProdutoPessoaFisicaDTO gerarProdutoPessoaFisica(Produto p) {
        ProdutoPessoaFisicaDTO produtoPessoaFisicaDTO = new ProdutoPessoaFisicaDTO();
        produtoPessoaFisicaDTO.setId(p.getId());
        produtoPessoaFisicaDTO.setDescricao(p.getDescricao());
        produtoPessoaFisicaDTO.setQuantidadeEstoque(p.getQuantidadeEstoque());
        produtoPessoaFisicaDTO.setIdadePermitida(p.getIdadePermitida());
        produtoPessoaFisicaDTO.setPrecoVendaFisica(p.getPrecoVendaFisica());
        return produtoPessoaFisicaDTO;
    }
}