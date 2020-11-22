package com.br.ufms.cpcx.jp.T1web2.controller;

import com.br.ufms.cpcx.jp.T1web2.dto.ProdutoPessoaFisicaDTO;
import com.br.ufms.cpcx.jp.T1web2.dto.ProdutoPessoaJuridicaDTO;
import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.entity.Produto;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import com.br.ufms.cpcx.jp.T1web2.enuns.TipoPessoa;
import com.br.ufms.cpcx.jp.T1web2.service.PessoaService;
import com.br.ufms.cpcx.jp.T1web2.service.ProdutoService;
import com.br.ufms.cpcx.jp.T1web2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        Pessoa pessoa = usuarioService.retornaPessoa(usuario.getPessoa().getId());
        if (usuarioService.validaLogin(login, senha)) {
            if (pessoa.getTipoPessoa().equals(TipoPessoa.FISICA)) {
                List<ProdutoPessoaFisicaDTO> produtoPessoaFisicaDTOList = new ArrayList<>();
                if (!usuarioService.validaMaioridade(pessoa)) {
                    List<Produto> listMenorIdade = produtoService.produtosMenorIdade();
                    for (Produto p : listMenorIdade) {
                        produtoPessoaFisicaDTOList.add(new ProdutoPessoaFisicaDTO().gerarProdutoPessoaFisica(p));
                    }
                } else {
                    List<Produto> list = produtoService.buscarTodos();
                    for (Produto p : list) {
                        produtoPessoaFisicaDTOList.add(new ProdutoPessoaFisicaDTO().gerarProdutoPessoaFisica(p));
                    }
                }
                return new ResponseEntity(produtoPessoaFisicaDTOList, HttpStatus.OK);
            } else {
                List<Produto> list = produtoService.buscarTodos();
                List<ProdutoPessoaJuridicaDTO> produtoPessoaJuridicaDTOList = new ArrayList<>();
                for (Produto p : list) {
                    produtoPessoaJuridicaDTOList.add(new ProdutoPessoaJuridicaDTO().gerarProdutoPessoaJuridica(p));
                }
                return new ResponseEntity(produtoPessoaJuridicaDTOList, HttpStatus.OK);
            }


        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity buscarPorId(@PathVariable("id") Long id,
                                      @RequestHeader("login") String login,
                                      @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(produtoService.buscarPorId(id), HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Produto produto,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha) && usuarioService.isAdmin(login, senha))
            return new ResponseEntity(produtoService.salvar(produto), HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha) && usuarioService.isAdmin(login, senha)) {
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
        if (usuarioService.validaLogin(login, senha) && usuarioService.isAdmin(login, senha))
            return new ResponseEntity(produtoService.alterar(produto), HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
