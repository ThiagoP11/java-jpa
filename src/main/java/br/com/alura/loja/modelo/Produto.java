package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity//é uma entidade ou seja existe uma tabela no banco de dados que está mapeando essa classe (espelho dessa classe)
@Table(name="produtos") //Indicando que o nome da tabela é produtos
@NamedQuery(name = "Produto.produtosPorCategoria", query = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1")
public class Produto {

    @Id //Mapeando a chave primaria com o atributo
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Informando a JPA que o banco irá gerar automaticamente o valor e o mesmo é sequencial ou Identity
    private Long id;
    private String nome;
    //@Column(name="desc") //caso o nome da coluna no banco de dados seja diferente do atributo no java
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now();
    //@Enumerated(EnumType.STRING) -> Caso categoria fosse um enum
    @ManyToOne
    private Categoria categoria;

    public Produto(){

    }

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
