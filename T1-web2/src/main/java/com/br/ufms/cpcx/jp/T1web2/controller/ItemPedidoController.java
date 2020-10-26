package com.br.ufms.cpcx.jp.T1web2.controller;

import com.br.ufms.cpcx.jp.T1web2.entity.ItemPedido;
import com.br.ufms.cpcx.jp.T1web2.service.ItemPedidoService;
import com.br.ufms.cpcx.jp.T1web2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/itemPedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody ItemPedido itemPedido,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {

        if (usuarioService.validaLogin(login, senha) )
            return new ResponseEntity(itemPedidoService.salvar(itemPedido), HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(
            @RequestHeader("login") String login,
            @RequestHeader("senha") String senha) {

        if (usuarioService.validaLogin(login, senha)) {
            return new ResponseEntity<>(itemPedidoService.buscarTodos(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity buscarPorId(@PathVariable("id") Long id,
                                      @RequestHeader("login") String login,
                                      @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(itemPedidoService.buscarPorId(id), HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha)) {
            itemPedidoService.deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else
            return null;
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody ItemPedido itemPedido, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)  && usuarioService.isAdmin(login,senha))
            return new ResponseEntity(itemPedidoService.alterar(itemPedido), HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
