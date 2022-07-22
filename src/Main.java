import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // fazer a requisição HTTP com o metodo GET dos top 250 filmes de acordo com o site imdb.

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam (titulo, poster, classificação)

        Gson gsonParser = new Gson();
        ListaDeFilmes filmes = gsonParser.fromJson(body, ListaDeFilmes.class);

        // manipular e exibir os dados

        Attribute[] myFormat1 = new Attribute[]{BLACK_TEXT(), CYAN_BACK(), BOLD()};
        Attribute[] myFormat2 = new Attribute[]{BRIGHT_CYAN_TEXT(), BOLD()};

        for (Filme filme : filmes.getItems()) {
            System.out.println(colorize("Nome: " + filme.getTitle(), myFormat1));
            System.out.println(colorize("Capa[url]: ", myFormat2) + filme.getImage());
            System.out.println(colorize("Nota: " + filme.getImDbRating(),myFormat2));
            System.out.println("\n");
        }
    }
}