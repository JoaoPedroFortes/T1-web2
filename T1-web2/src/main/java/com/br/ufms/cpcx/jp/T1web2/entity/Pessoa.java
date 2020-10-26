package com.br.ufms.cpcx.jp.T1web2.entity;

import com.br.ufms.cpcx.jp.T1web2.enuns.Situacao;
import com.br.ufms.cpcx.jp.T1web2.enuns.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PES_TIPO_JPA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PES_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PES_RESP_ID")
    private Pessoa responsavel;

    @Column(name = "PES_TIPO")
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(name = "PES_SITUACAO")
    private Situacao situacao;

    @Column(name = "PES_NOME")
    private String nome;

    @Column(name = "PES_APELIDO")
    private String apelido;

    @Column(name = "PES_DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    //@Column(name="PES_PED")
    //private ArrayList<Pedido> pedidoList;

//    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = true)
//    private Usuario usuario;

    public Pessoa(Long id) {
        this.id = id;
    }
}
