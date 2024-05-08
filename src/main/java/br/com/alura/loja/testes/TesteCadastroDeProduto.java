package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteCadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();// pegando a transação e iniciando a mesma

        em.persist(celulares);
        celulares.setNome("xpto");

        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNome("iphone");
        //em.clear();
        em.remove(celulares);
        em.flush();
    }
}
