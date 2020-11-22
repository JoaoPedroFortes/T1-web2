package com.br.ufms.cpcx.jp.T1web2.dto;

import com.br.ufms.cpcx.jp.T1web2.entity.Produto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@Builder

public class ProdutoPessoaJuridicaDTO {
    private long id;
    private String descricao;
    private int quantidadeEstoque;
    private int idadePermitida;
    private Double precoVendaJuridica;


    public ProdutoPessoaJuridicaDTO gerarProdutoPessoaJuridica(Produto p ){
        ProdutoPessoaJuridicaDTO produtoPessoaJuridicaDTO = new ProdutoPessoaJuridicaDTO();
        produtoPessoaJuridicaDTO.setId(p.getId());
        produtoPessoaJuridicaDTO.setDescricao(p.getDescricao());
        produtoPessoaJuridicaDTO.setQuantidadeEstoque(p.getQuantidadeEstoque());
        produtoPessoaJuridicaDTO.setIdadePermitida(p.getIdadePermitida());
        produtoPessoaJuridicaDTO.setPrecoVendaJuridica(p.getPrecoVendaJuridica());
        return produtoPessoaJuridicaDTO;
    }
}
