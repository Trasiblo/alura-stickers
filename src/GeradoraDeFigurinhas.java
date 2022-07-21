import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
        // InputStream inputStream = 
        //             new FileInputStream(new File("entrada/filme-maior.jpg"));
        // InputStream inputStream = 
        //                 new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        //                 .openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // redimencionar imagem para facilitar a centralização do texto
        // largura comporta 18 caracteres na fonte Impact
        imagemOriginal = resizeImage(imagemOriginal, 600,1000);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        //var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        var fonte = new Font("Consolas", Font.BOLD, 64);
        graphics.setColor(Color.GREEN);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        String Texto = "EXCELENTE";
        
        int qtdCaracter = Texto.length();
        //a largura cabe 18 caracter / 2 para centralizar texto
        int qtdTotal = 18;
        //qtd espacos para centralizar
        int qtdCentralizar = (qtdTotal - qtdCaracter) / 2 ;
        String Espacos = "";
        for (int i = 0; i < qtdCentralizar; i++) {
            Espacos += " ";
        }

        //texto ajustado com os espaços para ficar centralizado
        Texto = Espacos + Texto;

        graphics.drawString(Texto, 0, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        //ajuste para salvar na pasta de saída
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));

    }

    // função creditos: https://github.com/Quenupp/MGQC.ImersaoJavaAlura/blob/main/MG%20Proj%202/src/GeradoraDeFigurinhas.java
    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }   

}
