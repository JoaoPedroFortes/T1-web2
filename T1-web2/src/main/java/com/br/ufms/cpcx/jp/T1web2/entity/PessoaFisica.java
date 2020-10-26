package com.br.ufms.cpcx.jp.T1web2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA_FISICA")
@PrimaryKeyJoinColumn(name = "PES_ID")
@Builder
@DiscriminatorValue("FISICA")

public class PessoaFisica extends Pessoa {

    @Column(name = "PF_CPF")
    private String CPF;
    @Column(name= "PF_RG")
    private String RG;

}
