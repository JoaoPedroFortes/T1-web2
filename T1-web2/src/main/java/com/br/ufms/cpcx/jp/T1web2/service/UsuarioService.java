package com.br.ufms.cpcx.jp.T1web2.service;


import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import com.br.ufms.cpcx.jp.T1web2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaService pessoaService;

    public Object salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Object buscarPorId(long id) {
       return usuarioRepository.findById(id);
    }

    public Object alterar(Usuario body) {
        return usuarioRepository.save(body);
    }

    public boolean validaLogin(String login, String senha) {
        Usuario usuario = usuarioRepository.getUsuario(login);
        if(usuario.getSenha().equals(senha)){
            return true;
        }
        throw new RuntimeException("usuario ou senha incorretos");
    }

    public boolean isAdmin(String login, String senha) {
        validaLogin(login,senha);
        if(usuarioRepository.isAdministrador(login)){
            return true;
        }else{
            throw new RuntimeException("usuario não é admin");
        }

    }

    public Usuario retornaUsuario(String login, String senha) {
       Usuario usuario = usuarioRepository.getUsuario(login);
       if(usuario.getSenha().equals(senha)){
           return usuario;
       }
        throw new RuntimeException("usuario ou senha incorreto(a)");
    }

    public boolean validaMaioridade(Pessoa pessoa) {
        if (pessoaService.calcularIdade(pessoa.getDataNascimento()) > 18) {
            return true;
        }
        return false;
    }

    public Pessoa retornaPessoa(Long usuarioId) {
        return usuarioRepository.retornaPessoa(usuarioId);
    }
}
