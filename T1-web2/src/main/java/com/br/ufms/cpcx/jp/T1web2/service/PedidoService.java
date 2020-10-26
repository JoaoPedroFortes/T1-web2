package com.br.ufms.cpcx.jp.T1web2.service;

import com.br.ufms.cpcx.jp.T1web2.entity.Pedido;
import com.br.ufms.cpcx.jp.T1web2.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Object salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Object buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Object alterar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
