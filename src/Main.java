import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
    Scanner query = new Scanner(System.in);
    String search = "";
    List<String> listaDeEnderecos = new ArrayList<>();

    System.out.println("Digite seu cep para cadastrar o endereço");
    System.out.println("Para sair ou encerrar o programa, digite sair");

    while (!search.equalsIgnoreCase("sair")) {
      search = query.nextLine();

      if (search.equalsIgnoreCase("sair")) {
        break;
      }

      String address = "https://viacep.com.br/ws/" + search.replace("-", "") + "/json/";
      try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(address))
            .build();
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Digite o número da casa");

        String json = response.body();
        System.out.println(json);

        listaDeEnderecos.add(json);
      } catch (NumberFormatException | IllegalFormatException e) {
        System.out.println("Endereço Digitado errado tente novamente");
        System.out.println(e.getMessage());
      } catch (NoClassDefFoundError e) {
        System.out.println("Confira o Erro: ");
        System.out.println(e.getMessage());
      }
    }
    FileWriter escrita = new FileWriter("endereços.json");
    escrita.write(listaDeEnderecos.toString());
    escrita.close();
  }
}