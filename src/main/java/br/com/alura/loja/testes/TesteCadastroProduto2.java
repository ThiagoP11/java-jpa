package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TesteCadastroProduto2 {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p2.getNome()));


        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println("Preço do Produto " + precoDoProduto);



    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto("Xiaomi Redmi","Muito Legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);


        em.getTransaction().begin();// pegando a transação e iniciando a mesma
        //em.persist(celular);//Persistindo no banco de dados com as informações que está no objeto celular)
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();// commit das informações inseridas
        em.close();// fechando conexão
    }
}
