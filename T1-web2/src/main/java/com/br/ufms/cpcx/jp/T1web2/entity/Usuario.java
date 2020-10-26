package com.br.ufms.cpcx.jp.T1web2.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Column(name = "USU_LOGIN")
    private String login;

    @Column(name = "USU_SENHA")
    private String senha;

    @Column(name = "USU_ISADMIN")
    private boolean isAdministrador;

    public Usuario(long id, Pessoa pessoa, String login, String senha ) {
        this.id = id;
        this.pessoa = pessoa;
        this.login = login;
        this.senha = senha;
        this.isAdministrador = false;
    }
}
