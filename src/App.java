import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        //definição das cores para o terminal
        String CorResetar = "\u001b[0m";
        String CorTitulo = CorResetar + " \u001b[31;4m";
        String CorCampo = CorResetar + "\u001b[30;1m";

        // fazer uma conexao http e buscar top 250 filmes
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //String url = "https://alura-imdb-api.herokuapp.com/movies";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            //ajuste para o Record da classe conteudo
            //InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            //String nomeArquivo = conteudo.getTitulo() + ".png";
            String nomeArquivo = conteudo.titulo() + ".png";

            //geradora.cria(inputStream, nomeArquivo, ClassificacaoF);
            geradora.cria(inputStream, nomeArquivo);

            //formatação dos campos
            //String Titulo = "Título.......: " + CorTitulo + conteudo.getTitulo();
            String Titulo = "Título.......: " + CorTitulo + conteudo.titulo();
                       
            Titulo = CorCampo + Titulo + CorResetar; 
                        
            //imprimir na tela
            System.out.println(Titulo);
            System.out.println();
        }

    }
}
