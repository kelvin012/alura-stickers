import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // fazer a requisição HTTP com o metodo GET dos top 250 filmes de acordo com o site imdb.
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorConteudo extrator = new ExtratorConteudoIMDB();

//        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-20&end_date=2022-07-22";
//        ExtratorConteudo extrator = new ExtratorConteudoNasa();

        var http = new ClienteHttp();
        String jsonText = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extrairConteudos(jsonText);

        var generator = new StickerGenerator();

        for (int k = 0; k < 3; k++) {

            Conteudo conteudo = conteudos.get(k);

            try (InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream()) {
                System.out.println(conteudo.getTitulo());
                generator.criar(inputStream, "saida/" + conteudo.getTitulo() + ".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
