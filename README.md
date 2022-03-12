<div align="center">
<h1>Thymeleaf Website Template</h1>
</div>
<p align="center"> Você pode acessar o template <a href="https://thymeleaf-website-template.herokuapp.com/home">clicando aqui</a></p>

<div align="center">
<h2> Desenvolvido por</h2>
<a href="https://github.com/andreltcarvalho"><img style="border-radius: 50%;" src="https://avatars0.githubusercontent.com/u/53447567?s=460&v=4" width="100px;"   alt=""/><br /><sub><b>
André Luis Carvalho</b></sub></a>
</div>

:thinking: Como um desenvolvedor Backend em Java, sempre tive bastante dificuldade com a criação de projetos. Eram
infinitas dúvidas:

```text
- qual tecnologia aprender para o Frontend?
- como integrar esta tecnologia com o backend?
- qual banco de dados usar?
- como fazer autenticação e autorização?
- qual arquitetura usar?
- como subir essa aplicação em um servidor real?
```

Após alguns tempos de estudo e análise, acabei me deparando com o Thymeleaf, um framework para o frontend que se
encaixava perfeitamente com o Spring Boot, e desde então me propus à estudar e aprender.

```text
Após meses de bastante estudo em pesquisa dentro de blogs e fóruns, consegui juntar todo
o conhecimento adquirido em um template único, com todos os componentes essenciais para um site.
```

<h2 align="left"> Tecnologias Utilizadas no Template</h2>
<ul>
<li> Thymeleaf</li>
<li> CSS</li>
<li> Bootstrap</li>
<li> Spring Boot </li>
<li>  Spring MVC </li>
<li> Spring Data </li>
<li> Spring Security </li>
<li> Database:  Postgres / MySQL </li>
</ul>

<h2>Como utilizar esse template?</h2>

1: Certifique-se de que estejam corretamente configurados:

```text
1.1: Java.
1.2: Maven.
1.3: GIT.
1.4: MySQL.
1.5: IDE(Eclipse, STS ou IntelliJ).
```

2: Copie/Clone o código do template em um novo projeto adicione ao repositório do Github.

3: Crie um schema/database no MySQL, clique com o botão direito nesse Schema e selecione "Set as Default", em seguida
atualize o arquivo application-test.properties na seguinte propriedade:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nomeDoSeuSchemaAqui
```

4: Se você usa o IntelliJ, baste selecionar o menu do Maven à direita e escolher o comando mvn clean install.

```text
Caso não use esta IDE, basta rodar o comando "mvn clean install" dentro
da pasta raiz do seu projeto.
```

<h3>Pronto, seu template está configurado!</h3>

```text
Basta rodar a classe AppMain dentro do pacote src -> main -> java -> AppMain.Java
Run as -> Spring Boot Application.
```

<h3>Bom, eu gostaria de ir mais além, que tal fazer o primeiro deploy?</h3>

```text
Por incrível que pareça, é BEM mais simples do que parece, você vai precisar de:
```

- uma conta criada no [Heroku](https://www.heroku.com/)
- após criar a conta, crie um novo app na plataforma clicando [Aqui](https://dashboard.heroku.com/new-app)
- após criado o app, vá até a aba "Deploy" e clica em "Connect to Github"
- abaixo vai aparecer um campo de texto, e você digita o nome do seu repositório, mas atenção:
  o nome tem que ser idêntico ao que está no Github para funcionar.
- clique em "search", e em seguida, "connect".
- após a conexão, desça até o final da tela, e na aba "Manual Deploy" escolha uma branch e clica em "Deploy Branch".
- agora você irá para a aba "Settings", clique no botão "Reveal Config Vars" e adicione a seguinte linha:
  ![](src/main/resources/static/images/config-vars.png)
- Pronto, agora o seu projeto local usará o seu MySQL, e o projeto do heroku irá usar um servidor próprio do PostgreSQL.

<h3>Pronto! Agora é só subir de novo para o início da página e clicar em "Open App"!</h3>

```text
Á partir dessas dicas você já pode desenvolver o seu site, e toda vez que você
atualizar seu código no Github, basta ir ao heroku e clicar em Deploy novamente!
```

Caso você tenha alguma dúvida, fique à vontade para me chamar, que lhe ajudarei com todo o prazer!
Basta acessar o meu perfil e lá terão minhas redes sociais, abraço!