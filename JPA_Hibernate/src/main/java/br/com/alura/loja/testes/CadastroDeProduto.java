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
		boolean continuar = true;
		Scanner scanner = new Scanner(System.in);

		while (continuar) {
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Listar produtos");
			System.out.println("3 - Sair");
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
				continuar = false;
				break;
			default:
				System.out.println("Opção inválida. Escolha novamente.");
			}
		}

		scanner.close();
	}

//	List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
//	todos.forEach(p2 -> System.out.println(p.getNome()));
//
//	BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
//	System.out.println("Preco do Produto: " + precoDoProduto);

	private static void listarProdutos() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		List<Produto> todosProdutos = produtoDao.buscarTodos();
		for (Produto produto : todosProdutos) {
			System.out.println(
			"ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: " + produto.getPreco());
		}
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

}
