import java.net.URI;
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
        String CorLink = CorResetar + "\u001b[38;1;46m";
        String CorNota = CorResetar + "\u001b[92;1m";
        String CorCampo = CorResetar + "\u001b[30;1m";

        // fazer uma conexao http e buscar top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
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
        for (Map<String, String> filme : listaDeFilmes) {

            //formatação dos campos
            String Titulo = "Título.......: " + CorTitulo + filme.get("title");
            String Imagem = "Imagem.......: " + CorLink + filme.get("image");
            String Classificacao = "Classificação: " +  CorNota + filme.get("imDbRating");
            
            Titulo = CorCampo + Titulo + CorResetar; 
            Imagem = CorCampo + Imagem + CorResetar;
            Classificacao = CorCampo + Classificacao + CorResetar;
            
            //imprimir na tela
            System.out.println("=========================================================================");
            System.out.println(Titulo);
            System.out.println(Imagem);
            System.out.println(Classificacao);
            
        }

    }
}
