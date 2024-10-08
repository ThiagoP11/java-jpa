package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        //Thiago - teste - teste
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto("Xiaomi Redmi","Muito Legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);


        Cliente cliente = new Cliente("Thiago", "51068449837");
        Pedido pedido = new Pedido(cliente);
        ItemPedido itemPedido = new ItemPedido(1, pedido, new Produto("Celular", "celular bom",new BigDecimal("2000"), celulares));
        pedido.adicionaItem(itemPedido);

        em.getTransaction().begin();// pegando a transação e iniciando a mesma
        //em.persist(celular);//Persistindo no banco de dados com as informações que está no objeto celular)
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();// commit das informações inseridas
        em.close();// fechando conexão
    }
}
