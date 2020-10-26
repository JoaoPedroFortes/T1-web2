package com.br.ufms.cpcx.jp.T1web2.controller;


import com.br.ufms.cpcx.jp.T1web2.dto.UsuarioDTO;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import com.br.ufms.cpcx.jp.T1web2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
        return new ResponseEntity(usuarioService.salvar(usuario), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("login") String login,
                                         @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(usuarioService.buscarTodos(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity buscarPorId(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                      @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(usuarioService.buscarPorId(id), HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Usuario body, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(usuarioService.alterar(body), HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)){
            usuarioService.deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else
            return null;
    }

}
