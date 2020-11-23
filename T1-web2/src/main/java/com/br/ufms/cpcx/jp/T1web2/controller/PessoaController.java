package com.br.ufms.cpcx.jp.T1web2.controller;

import com.br.ufms.cpcx.jp.T1web2.entity.Pessoa;
import com.br.ufms.cpcx.jp.T1web2.pojo.PessoaPOJO;
import com.br.ufms.cpcx.jp.T1web2.service.PessoaService;
import com.br.ufms.cpcx.jp.T1web2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private UsuarioService usuarioService;

//    @GetMapping
//    @ResponseBody
//    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
//                                         @RequestHeader("senha") String senha) {
//
//        if (usuarioService.validaLogin(login, senha))
//            return new ResponseEntity(pessoaService.buscarTodos(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarStream(@RequestHeader("login") String login,
                                          @RequestHeader("senha") String senha,
                                          @RequestParam(value = "IdResponsavel", required = false) String idResponsavel,
                                          @RequestParam(value = "nomeResponsavel", required = false) String nomeResponsavel,
                                          @RequestParam(value = "tipoPessoa", required = false) String tipoPessoa,
                                          @RequestParam(value = "situacao", required = false) String situacao){
       return new ResponseEntity<>(pessoaService.bucasStream(idResponsavel, nomeResponsavel, tipoPessoa,situacao), HttpStatus.OK);

    }

//    @GetMapping("{id}")
//    @ResponseBody
//    public ResponseEntity buscarPorId(@PathVariable("id") Long id,
//                                      @RequestHeader("login") String login,
//                                      @RequestHeader("senha") String senha) {
//        if (usuarioService.validaLogin(login, senha))
//            return new ResponseEntity(pessoaService.buscarPorId(id), HttpStatus.OK);
//        else
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }



    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody PessoaPOJO pessoaPOJO) {
        return new ResponseEntity(pessoaService.salvar(pessoaPOJO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody PessoaPOJO body, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {

        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha))
            return new ResponseEntity(pessoaService.alterar(body), HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha) ) {
            pessoaService.deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
