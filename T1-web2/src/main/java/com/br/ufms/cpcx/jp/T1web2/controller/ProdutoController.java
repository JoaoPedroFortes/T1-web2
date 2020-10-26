package com.br.ufms.cpcx.jp.T1web2.controller;

import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.entity.Produto;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import com.br.ufms.cpcx.jp.T1web2.service.PessoaService;
import com.br.ufms.cpcx.jp.T1web2.service.ProdutoService;
import com.br.ufms.cpcx.jp.T1web2.service.UsuarioService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {

        Usuario usuario = usuarioService.retornaUsuario(login, senha);
        Pessoa pessoa = usuarioService.retornaPessoa(usuario);
        if (usuarioService.validaLogin(login, senha)) {
            if (usuarioService.validaMaioridade(pessoa)) {
                return new ResponseEntity<>(produtoService.buscarTodos(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(produtoService.produtosMenorIdade(), HttpStatus.OK);
            }
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Produto produto,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha))
            return new ResponseEntity(produtoService.salvar(produto), HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha)) {
            produtoService.deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else
            return null;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Produto produto, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha))
            return new ResponseEntity(produtoService.alterar(produto), HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
