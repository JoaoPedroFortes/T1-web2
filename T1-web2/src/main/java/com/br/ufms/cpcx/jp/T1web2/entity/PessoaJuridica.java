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
@Table(name = "TB_PESSOA_JURIDICA")
@PrimaryKeyJoinColumn(name = "PES_ID")
@Builder
@DiscriminatorValue("JURIDICA")
public class PessoaJuridica extends Pessoa {
    @Column(name = "PJ_CNPJ")
    private String CNPJ;

}
