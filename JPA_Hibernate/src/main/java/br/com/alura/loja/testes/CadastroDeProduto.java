package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		boolean continuar = true;
		Scanner scanner = new Scanner(System.in);
		ProdutoDao produtoDao = new ProdutoDao(em);

		while (continuar) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Listar produtos");
			System.out.println("3 - Buscar por categoria");
			System.out.println("4 - Pesquisar preço de produto");
			System.out.println("6 - Sair");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.print("Opção: ");

			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				cadastrarProduto(scanner);
				break;
			case 2:
				listarProdutos();
				break;
			case 3:
				buscarPorCategoria(scanner, produtoDao);
				break;
			case 4:
				buscarPrecoDoProduto(scanner, produtoDao);
				break;
			case 6:
				continuar = false;
				break;

			default:
				System.out.println("Opção inválida. Escolha novamente.");
			}
		}

		scanner.close();
	}

	private static void listarProdutos() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		List<Produto> todosProdutos = produtoDao.buscarTodos();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		for (Produto produto : todosProdutos) {
			System.out.println(
			"ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: " + produto.getPreco());
		}
		System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvv");
	}

	private static void cadastrarProduto(Scanner scanner) {
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

	private static void buscarPorCategoria(Scanner scanner, ProdutoDao produtoDao) {
		scanner.nextLine();
		System.out.print("Nome da categoria: ");
		String nomeCategoria = scanner.nextLine().toUpperCase();

		List<Produto> produtosPorCategoria = produtoDao.buscarPorNomeDaCategoria(nomeCategoria);

		if (!produtosPorCategoria.isEmpty()) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Produtos na categoria '" + nomeCategoria + "':");
			produtosPorCategoria.forEach(produto -> System.out.println(produto.getNome()));
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		} else {
			System.out.println("Nenhum produto encontrado na categoria '" + nomeCategoria + "'.");
		}
	}

	private static void buscarPrecoDoProduto(Scanner scanner, ProdutoDao produtoDao) {
		scanner.nextLine();
		System.out.print("Nome do produto: ");
		String nomeProduto = scanner.nextLine();

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome(nomeProduto);

		if (precoDoProduto != null) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Preço do Produto '" + nomeProduto + "': " + precoDoProduto);
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		} else {
			System.out.println("Produto '" + nomeProduto + "' não encontrado.");
		}
	}

}
