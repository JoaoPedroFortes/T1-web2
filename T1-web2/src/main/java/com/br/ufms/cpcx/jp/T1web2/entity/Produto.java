package com.br.ufms.cpcx.jp.T1web2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUTO")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long id;

    @Column(name = "PRO_DESCRICAO")
    private String descricao;

    @Column(name = "PRO_QUANTIDADE_ESTOQUE")
    private int quantidadeEstoque;

    @Column(name = "PRO_IDADE_PERMITIDA")
    private int idadePermitida;

    @Column(name = "PRO_PRECO_COMPRA")
    private Double precoCompra;

    @Column(name = "PRO_PRECO_VENDA_FISICA")
    private Double precoVendaFisica;

    @Column(name = "PRO_PRECO_VENDA_JURIDICA")
    private Double precoVendaJuridica;

    public Produto(Long id) {
        this.id = id;
    }
}
