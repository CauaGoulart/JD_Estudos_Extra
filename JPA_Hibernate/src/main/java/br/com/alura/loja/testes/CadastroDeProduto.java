package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preco do Produto: " + precoDoProduto);

		List<Produto> todosProdutos = produtoDao.buscarTodos();
		for (Produto produto : todosProdutos) {
			System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: " + produto.getPreco());
		}
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria eletronicos = new Categoria("ELETRÔNICOS");
		Categoria vestuarios = new Categoria("VESTUÁRIO");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("900"), celulares);
		Produto eletronico = new Produto("Torradeita", "Faz torradas", new BigDecimal("80"), eletronicos);
		Produto vestuario = new Produto("Calça jeans", "Muito bonita", new BigDecimal("65"), vestuarios);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(eletronicos);
		categoriaDao.cadastrar(vestuarios);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(eletronico);
		produtoDao.cadastrar(vestuario);

		em.getTransaction().commit();
		em.close();
	}

	

}
