import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        //definição das cores para o terminal
        String CorResetar = "\u001b[0m";
        String CorTitulo = CorResetar + " \u001b[31;4m";
        String CorNota = CorResetar + "\u001b[92;1m";
        String CorCampo = CorResetar + "\u001b[30;1m";

        // fazer uma conexao http e buscar top 250 filmes
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        // pegar só os dados que interessam (titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.pase(body);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            //url.substring(0, url.indexOf(padrao)) + url.substring(url.length()-4);

            // remover o "._V1" das urls de imagens
            String urlCorrigida = urlImagem.substring(0,urlImagem.indexOf("._V1")) + urlImagem.substring(urlImagem.length()-4);

            InputStream inputStream = new URL(urlCorrigida).openStream();
            //replace no titulo para corrigir
            String nomeArquivo = titulo.replace(":", "-") + ".png";

            //converter classificação dos filmes
            Float ClassificacaoF = Float.parseFloat(filme.get("imDbRating"));

            geradora.cria(inputStream, nomeArquivo, ClassificacaoF);

            //formatação dos campos
            String Titulo = "Título.......: " + CorTitulo + filme.get("title");
            String Classificacao = "Classificação: " +  CorNota + filme.get("imDbRating");
            
            Titulo = CorCampo + Titulo + CorResetar; 
            Classificacao = CorCampo + Classificacao + CorResetar;
            
            //imprimir na tela
            System.out.println(Titulo);
            System.out.println(Classificacao);
            System.out.println();

            
        }

    }
}
