package com.br.ufms.cpcx.jp.T1web2.pojo;

import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.entity.PessoaFisica;
import com.br.ufms.cpcx.jp.T1web2.entity.PessoaJuridica;
import com.br.ufms.cpcx.jp.T1web2.enuns.Situacao;
import com.br.ufms.cpcx.jp.T1web2.enuns.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaPOJO {
    private long id;
    private Pessoa Responsavel;
    private TipoPessoa tipoPessoa;
    private Situacao situacao;
    private String nome;
    private String apelido;
    private LocalDate dataNascimento;
    private String RG;
    private String CPF;
    private String CNPJ;


    public PessoaFisica gerarPessoaFisica(){

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setId(this.getId());
        pessoaFisica.setNome(this.getNome());
        pessoaFisica.setTipoPessoa(this.getTipoPessoa());
        pessoaFisica.setApelido(this.getApelido());
        pessoaFisica.setSituacao(this.getSituacao());
        pessoaFisica.setDataNascimento(this.getDataNascimento());
        pessoaFisica.setCPF(this.getCPF());
        pessoaFisica.setRG(this.getRG());
        pessoaFisica.setResponsavel(this.getResponsavel());

        return pessoaFisica;

    }

    public PessoaJuridica gerarPessoaJuridica(){
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(this.getId());
        pessoaJuridica.setNome(this.getNome());
        pessoaJuridica.setTipoPessoa(this.getTipoPessoa());
        pessoaJuridica.setApelido(this.getApelido());
        pessoaJuridica.setSituacao(this.getSituacao());
        pessoaJuridica.setDataNascimento(this.getDataNascimento());
        pessoaJuridica.setResponsavel(this.getResponsavel());
        pessoaJuridica.setCNPJ(this.getCNPJ());

        return pessoaJuridica;

    }
}
