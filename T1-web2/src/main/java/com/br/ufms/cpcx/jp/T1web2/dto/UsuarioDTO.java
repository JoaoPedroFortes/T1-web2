package com.br.ufms.cpcx.jp.T1web2.dto;

import com.br.ufms.cpcx.jp.T1web2.controller.PessoaController;
import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import com.br.ufms.cpcx.jp.T1web2.service.PessoaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data

public class UsuarioDTO {

    private long id;
    private long id_pessoa;
    private String login;
    private String senha;
    private boolean isAdministrador;

    @Autowired
    PessoaService pessoaService;


    public Usuario transformaUsuario() {
        Pessoa pessoa = new Pessoa();
        for(Pessoa p : pessoaService.buscarTodos()){
            System.out.println(p.getNome());
            if(id_pessoa == p.getId()){
                pessoa = p;
            }
        }
        return new Usuario(id, pessoa, login, senha);
    }
}
