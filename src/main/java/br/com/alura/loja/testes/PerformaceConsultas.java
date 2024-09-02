package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformaceConsultas {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();

        em.find(Pedido.class, 1l);

    }
    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videoGames = new Categoria("VIDEO GAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi Redmi","Muito Legal", new BigDecimal("800"), celulares);
        Produto ps5 = new Produto("Playstation 5", "Muito bom", new BigDecimal("4000"), videoGames);
        Produto notebook = new Produto("Vostro 3681", "Muito rapido", new BigDecimal("3000"), informatica);

        Cliente cliente = new Cliente("Thiago", "51068449837");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionaItem(new ItemPedido(10, pedido, celular));
        pedido.adicionaItem(new ItemPedido(40, pedido, ps5));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionaItem(new ItemPedido(2, pedido, notebook));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);



        em.getTransaction().begin();// pegando a transação e iniciando a mesma
        //em.persist(celular);//Persistindo no banco de dados com as informações que está no objeto celular)
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(informatica);
        categoriaDao.cadastrar(videoGames);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(ps5);
        produtoDao.cadastrar(notebook);

        clienteDao.cadastrar(cliente);

        pedidoDao.cadastrar(pedido);
        em.getTransaction().commit();// commit das informações inseridas
        em.close();// fechando conexão
    }
}
