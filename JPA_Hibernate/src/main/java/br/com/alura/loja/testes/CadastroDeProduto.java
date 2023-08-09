package br.com.alura.loja.testes;


import java.util.Scanner;
import javax.persistence.EntityManager;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.service.ProdutoService;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		boolean continuar = true;
		Scanner scanner = new Scanner(System.in);
		ProdutoDao produtoDao = new ProdutoDao(em);


		while (continuar) {
			System.out.println("╔═══════════════════════════╗");
			System.out.println("║      Menu de Opções       ║");
			System.out.println("╠═══════════════════════════╣");
			System.out.println("║ 1 - Cadastrar produto     ║");
			System.out.println("║ 2 - Remover produto       ║");
			System.out.println("║ 3 - Listar produtos       ║");
			System.out.println("║ 4 - Buscar por categoria  ║");
			System.out.println("║ 5 - Buscar por preço      ║");
			System.out.println("║ 6 - Consultar preço       ║");
			System.out.println("║ 7 - Sair                  ║");
			System.out.println("╚═══════════════════════════╝");
			System.out.print("Opção: ");


			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				ProdutoService.cadastrarProduto(scanner);
				break;
			case 2:
				ProdutoService.removerProduto(scanner, produtoDao, em);
				break;
			case 3:
				ProdutoService.listarProdutos();
				break;
			case 4:
				ProdutoService.buscarPorCategoria(scanner, produtoDao);
				break;
			case 5:
				ProdutoService.buscarPorPreco(scanner, produtoDao);
				break;
			case 6:
				ProdutoService.buscarPrecoDoProduto(scanner, produtoDao);
				break;
			case 7:
				continuar = false;
				break;

			default:
				System.out.println("Opção inválida. Escolha novamente.");
			}
		}

		scanner.close();
	}

}
