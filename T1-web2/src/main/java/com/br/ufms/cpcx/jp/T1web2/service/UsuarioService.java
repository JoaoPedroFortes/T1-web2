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
        List<Usuario> usuarios = buscarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(String login, String senha) {
        List<Usuario> usuarios = buscarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                if (usuario.isAdministrador()) {
                    return true;
                }else return false;

            }
        }
        return false;

    }

    public Usuario retornaUsuario(String login, String senha) {
        List<Usuario> usuarios = buscarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean validaMaioridade(Pessoa pessoa) {
        if (pessoaService.calcularIdade(pessoa.getDataNascimento()) > 18) {
            return true;
        }
        return false;
    }

    public Pessoa retornaPessoa(Usuario usuario) {
        List<Pessoa> pessoas = pessoaService.buscarTodos();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId().equals(usuario.getPessoa().getId())) {
                return pessoa;
            }
        }
        return null;
    }
}
