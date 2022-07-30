import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExtratorConteudoNasa implements ExtratorConteudo {

    public List<Conteudo> extrairConteudos(String json) {

        // extrair os dados que interessam (titulo, poster, classificação)
        Gson gson = new Gson();
        Type apodListType = new TypeToken<List<Apod>>(){}.getType();
        List<Apod> apodList = gson.fromJson(json, apodListType);

        List<Conteudo> conteudos = new ArrayList<>();

        //  popular a lista
        for (Apod apod : apodList) {
            String titulo = apod.getTitle()
                    .replace(":", "-");
            String urlImagem = apod.getUrl();
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }

}
