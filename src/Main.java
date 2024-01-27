import Models.Endereco;
import Models.QueryCep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
  public static void main(String[] args) throws IOException, InterruptedException, NoClassDefFoundError {
    Scanner query = new Scanner(System.in);
    String search = "";
    List<QueryCep> listaDeEnderecos = new ArrayList<>();
    Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .create();

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

        String json = response.body();

        Endereco converter = gson.fromJson(json, Endereco.class);
        QueryCep convertido = new QueryCep(converter);
        System.out.println(convertido);

        listaDeEnderecos.add(convertido);
      } catch (NumberFormatException | IllegalFormatException e) {
        System.out.println("Endereço Digitado errado tente novamente");
        System.out.println(e.getMessage());
      } catch (NoClassDefFoundError e) {
        System.out.println("Confira o Erro: ");
        System.out.println(e.getMessage());
      }
    }

    FileWriter escrita = new FileWriter("endereços.json");
    escrita.write(gson.toJson(listaDeEnderecos));
    escrita.close();
  }
}