package br.com.alura.loja.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class ProdutoService {
    
    public static void listarProdutos() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		List<Produto> todosProdutos = produtoDao.buscarTodos();
		 if (todosProdutos.isEmpty()) {
			 System.out.println("╔════════════════════════════════════════╗");
			 System.out.println("║       Nenhum produto registrado.       ║");
			 System.out.println("╚════════════════════════════════════════╝");
		    } else {
		    	System.out.println("╔═══════════════════════════════════║");
		        for (Produto produto : todosProdutos) {
		            System.out.println(
		                "║ ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: " + produto.getPreco());
		        }
		        System.out.println("╚═══════════════════════════════════║");		    }
	}

	public static void cadastrarProduto(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Nome da categoria: ");
		String nomeCategoria = scanner.nextLine().toUpperCase();

		System.out.print("Nome do produto: ");
		String nomeProduto = scanner.nextLine();

		System.out.print("Descrição do produto: ");
		String descricaoProduto = scanner.nextLine();

		System.out.print("Preço do produto: ");
		BigDecimal precoProduto = new BigDecimal(scanner.nextLine());

		Categoria categoria = new Categoria(nomeCategoria);
		Produto produto = new Produto(nomeProduto, descricaoProduto, precoProduto, categoria);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(produto);

		em.getTransaction().commit();
		em.close();

	}
	
	public static void removerProduto(Scanner scanner, ProdutoDao produtoDao, EntityManager em) {
		    List<Produto> todosProdutos = produtoDao.buscarTodos();
		    
		    if (todosProdutos.isEmpty()) {
		    	System.out.println("╔═══════════════════════════╗");
		    	System.out.println("║  Nenhum produto listado.  ║");
		    	System.out.println("╚═══════════════════════════╝");

		        return;
		    }
		    
		    System.out.println("╔═══════════════════════════════════║");
	        for (Produto produto : todosProdutos) {
	            System.out.println(
	                "║ ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: " + produto.getPreco());
	        }
	        System.out.println("╚═══════════════════════════════════║");	
		    scanner.nextLine();
		    System.out.print("Digite o ID do produto a ser removido: ");
		    Long idProdutoRemover = Long.parseLong(scanner.nextLine());

		    Produto produtoRemover = produtoDao.buscarPorId(idProdutoRemover);
		    if (produtoRemover != null) {
		        em.getTransaction().begin();
		        produtoDao.remover(produtoRemover);
		        em.getTransaction().commit();
		        System.out.println("Produto removido com sucesso.");
		    } else {
		        System.out.println("Produto não encontrado.");
		    }

	}

	public static void buscarPorCategoria(Scanner scanner, ProdutoDao produtoDao) {
		scanner.nextLine();
		System.out.print("Nome da categoria: ");
		String nomeCategoria = scanner.nextLine().toUpperCase();

		List<Produto> produtosPorCategoria = produtoDao.buscarPorNomeDaCategoria(nomeCategoria);

		System.out.println("╔═════════════════════════════════╗");
		System.out.println("║ Produtos na categoria '" + nomeCategoria + "':");
		System.out.println("╠═════════════════════════════════╣");

		if (produtosPorCategoria.isEmpty()) {
		    System.out.println("║ Nenhum produto nesta categoria.  ║");
		} else {
		    produtosPorCategoria.forEach(produto -> System.out.println("║ " + produto.getNome()));
		}

		System.out.println("╚═════════════════════════════════╝");
	}
	
	public static void buscarPorPreco(Scanner scanner, ProdutoDao produtoDao) {
		scanner.nextLine();
		System.out.print("Preço a ser buscado: ");
	    BigDecimal precoBusca = new BigDecimal(scanner.nextLine());

	    List<Produto> produtosPorPreco = produtoDao.buscarPorPreco(precoBusca);

	    if (!produtosPorPreco.isEmpty()) {
	    	System.out.println("╔═══════════════════════════╗");
	        System.out.println("║Produtos com o preço de " + precoBusca + ":");
	        produtosPorPreco.forEach(produto -> System.out.println("║"+produto.getNome()));
	        System.out.println("╚═══════════════════════════╝");
	    } else {
	        System.out.println("Nenhum produto encontrado com o preço de " + precoBusca);
	    }
	}

	public static void buscarPrecoDoProduto(Scanner scanner, ProdutoDao produtoDao) {
		scanner.nextLine();
		System.out.print("Nome do produto: ");
		String nomeProduto = scanner.nextLine();

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome(nomeProduto);

		if (precoDoProduto != null) {
			System.out.println("╔═══════════════════════════╗");
		    System.out.println("║ Preço do Produto " + nomeProduto + ": " + precoDoProduto);
			System.out.println("╚═══════════════════════════╝");
		} else {
			System.out.println("Produto '" + nomeProduto + "' não encontrado.");
		}
	}

}
