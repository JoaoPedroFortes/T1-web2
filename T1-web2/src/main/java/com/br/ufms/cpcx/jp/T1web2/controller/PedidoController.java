package com.br.ufms.cpcx.jp.T1web2.controller;


import com.br.ufms.cpcx.jp.T1web2.entity.Pedido;
import com.br.ufms.cpcx.jp.T1web2.entity.Usuario;
import com.br.ufms.cpcx.jp.T1web2.service.PedidoService;
import com.br.ufms.cpcx.jp.T1web2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/pedido")
@Controller
public class PedidoController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(
            @RequestHeader("login") String login,
            @RequestHeader("senha") String senha) {

        if (usuarioService.validaLogin(login, senha)) {
            return new ResponseEntity<>(pedidoService.buscarTodos(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity buscarPorId(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                      @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(pedidoService.buscarPorId(id), HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Pedido pedido,
                                    @RequestHeader("login") String login,
                                    @RequestHeader("senha") String senha) {

        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(pedidoService.salvar(pedido), HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha)){
            pedidoService.deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else
            return null;
    }


    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Pedido pedido, @RequestHeader("login") String login,
                                     @RequestHeader("senha") String senha) {
        if (usuarioService.validaLogin(login, senha))
            return new ResponseEntity(pedidoService.alterar(pedido), HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
