package com.br.ufms.cpcx.jp.T1web2.service;

import com.br.ufms.cpcx.jp.T1web2.entity.ItemPedido;
import com.br.ufms.cpcx.jp.T1web2.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Object salvar(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public Object buscarTodos() {
        return itemPedidoRepository.findAll();
    }

    public Object buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }

    public Object alterar(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }
}
