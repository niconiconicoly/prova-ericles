```java
import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;

@Entity
@Table(name = "produtos")
public class Produto {
@Id
@Column(name = "codigo_barras")
private String codigoBarras;

@Column(name = "nome")
private String nome;

@Column(name = "marca")
private String marca;

@Column(name = "quantidade")
private int quantidade;

@Column(name = "data_cadastro")
private Date dataCadastro;

// getters e setters
}

public class Main {
private static EntityManagerFactory
entityManagerFactory;
private static EntityManager
entityManager;
private static Scanner scanner = 
new Scanner(System.in);

public static void main(String[] args) {
entityManagerFactory =
Persistence.createEntityManagerFactory("CadastroDeProdutosPU");
entityManager = entityManagerFactory.createEntityManager();

while (true) {
cadastrarProduto();
}
}

private static void cadastrarProduto() {
System.out.println("Digite o código de barras:");
String codigoBarras = scanner.nextLine();

// Verifica se o código de barras já existe
if (produtoExistente(codigoBarras)) {
System.out.println("Produto com código de barras " + codigoBarras + " já existe.");
return;
}

System.out.println("Digite o nome do produto:");
String nome = scanner.nextLine();

System.out.println("Digite a marca:");
String marca = scanner.nextLine();

System.out.println("Digite a quantidade:");
int quantidade = scanner.nextInt();

Produto produto = new Produto();
produto.setCodigoBarras(codigoBarras);
produto.setNome(nome);
produto.setMarca(marca);
produto.setQuantidade(quantidade);
produto.setDataCadastro(new Date());

entityManager.getTransaction().begin();
entityManager.persist(produto);
entityManager.getTransaction().commit();

System.out.println("Produto cadastrado com sucesso!");
}

private static boolean produtoExistente(String codigoBarras) {
Produto produto = entityManager.find(Produto.class, codigoBarras);
return produto != null;
}
}
```