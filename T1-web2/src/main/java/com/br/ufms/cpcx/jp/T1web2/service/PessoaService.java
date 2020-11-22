package com.br.ufms.cpcx.jp.T1web2.service;

import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;

import com.br.ufms.cpcx.jp.T1web2.enuns.TipoPessoa;
import com.br.ufms.cpcx.jp.T1web2.pojo.PessoaPOJO;
import com.br.ufms.cpcx.jp.T1web2.repository.PessoaFisicaRepository;
import com.br.ufms.cpcx.jp.T1web2.repository.PessoaJuridicaRepository;
import com.br.ufms.cpcx.jp.T1web2.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Object salvar(PessoaPOJO pessoaPOJO) {
        if (calcularIdade(pessoaPOJO.getDataNascimento()) < 18 && pessoaPOJO.getResponsavel() == null) {
            throw new RuntimeException("Menor de idade precisa de um responsavel");
        } else {
            if (TipoPessoa.FISICA.equals(pessoaPOJO.getTipoPessoa())) {
                return pessoaFisicaRepository.save(pessoaPOJO.gerarPessoaFisica());
            } else {
                return pessoaJuridicaRepository.save(pessoaPOJO.gerarPessoaJuridica());
            }
        }

    }


    public Pessoa alterar(PessoaPOJO pessoaPOJO) {

        if (TipoPessoa.FISICA.equals(pessoaPOJO.getTipoPessoa())) {
            return pessoaFisicaRepository.save(pessoaPOJO.gerarPessoaFisica());
        } else {
            return pessoaJuridicaRepository.save(pessoaPOJO.gerarPessoaJuridica());
        }
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Object buscarPorId(long id) {
        return pessoaRepository.findById(id);
    }

    public int calcularIdade(LocalDate dataNascimento) {
        LocalDate dataDeHoje = LocalDate.now();
        int ano = dataDeHoje.getYear();
        if (dataDeHoje.getMonthValue() < dataNascimento.getMonthValue()) {
            ano--;
        } else if (dataDeHoje.getMonthValue() == dataNascimento.getMonthValue() && dataDeHoje.getDayOfMonth() < dataNascimento.getDayOfMonth()) {
            ano--;
        }
        int anoNascimento = dataNascimento.getYear();
        return ano - anoNascimento;
    }
}
