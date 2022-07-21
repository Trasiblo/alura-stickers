# alura-stickers
Nesta primeira aula, vamos construir uma aplicação do zero para consumir a API do IMDb e exibir os filmes mais populares, destacando seus pôsteres e visualizando sua classificação... Tudo isso sem usar nenhuma biblioteca externa!

# Resultado aula 03

- Transformar a classe que representa os conteúdos em um Record

https://github.com/Trasiblo/alura-stickers/blob/42dbae1680e9914ae6443bdab2120578ca16b94d/src/Conteudo.java#L1 

ajustes para atender funcionar com o Record

https://github.com/Trasiblo/alura-stickers/blob/ff3b0d4dce07628f98fd863e242950728541e28b/src/App.java#L35

https://github.com/Trasiblo/alura-stickers/blob/ff3b0d4dce07628f98fd863e242950728541e28b/src/App.java#L37

https://github.com/Trasiblo/alura-stickers/blob/ff3b0d4dce07628f98fd863e242950728541e28b/src/App.java#L44

- Criar uma Enum que une, como configurações, a URL da API e o extrator utilizado
```java
public enum Dados {
        IMDB("https://alura-imdb-api.herokuapp.com/movies", new ExtratorDeConteudoDoIMDB()),
        NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json", new ExtratorDeConteudoDaNasa());
         
        private final String url;
        private final ExtratorDeConteudo extratorDeConteudo;
 
        Dados(String url, ExtratorDeConteudo extratorDeConteudo) {
            this.url = url;
            this.extratorDeConteudo = extratorDeConteudo;
        }
 
        public String getUrl() {
            return this.url;
        }
        public ExtratorDeConteudo getExtratorDeConteudo() {
            return this.extratorDeConteudo;
        }
    }
```
Modo de usar no código

```java
// para NASA
String url = Dados.NASA.url;
ExtratorDeConteudo extrator = Dados.NASA.extratorDeConteudo;

// para IMDB
String url = Dados.IMDB.url;
ExtratorDeConteudo extrator = Dados.IMDB.extratorDeConteudo;
```