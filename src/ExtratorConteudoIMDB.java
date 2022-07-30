import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ExtratorConteudoIMDB implements ExtratorConteudo {

    public List<Conteudo> extrairConteudos(String json) {

        // extrair os dados que interessam (titulo, poster, classificação)
        Gson gson = new Gson();
        ListaDeFilmes filmeList = gson.fromJson(json, ListaDeFilmes.class);

        List<Conteudo> conteudos = new ArrayList<>();

        //  popular a lista
        for (Filme filme : filmeList.items) {
            String titulo = filme.getTitle()
                    .replace(":", "-");
            String urlImagem = filme.getImage()
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }

}
