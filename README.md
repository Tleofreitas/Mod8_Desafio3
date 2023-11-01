# Projeto CRUD de clientes
## Desafio proposto
Você deverá entregar um projeto Spring Boot contendo um CRUD completo de web services REST para acessar um recurso de clientes, contendo as cinco operações básicas aprendidas no capítulo:
- Busca paginada de recursos
- Busca de recurso por id
- Inserir novo recurso
- Atualizar recurso
- Deletar recurso

O projeto deverá estar com um ambiente de testes configurado acessando o banco de dados H2, deverá usar Maven como gerenciador de dependência, e Java como linguagem.

Um cliente possui nome, CPF, renda, data de nascimento, e quantidade de filhos. A especificação da entidade Client é mostrada a seguir (você deve seguir à risca os nomes de classe e atributos mostrados no
diagrama):

![image](https://github.com/Tleofreitas/Mod8_Desafio3/assets/88738577/d958d900-9b11-47fb-9450-713a63e994ce)

Seu projeto deverá fazer um seed de pelo menos 10 clientes com dados SIGNIFICATIVOS (não é para usar dados sem significado como “Nome 1”, “Nome 2”, etc.).

Seu projeto deverá tratar as seguintes exceções:
- Id não encontrado (para GET por id, PUT e DELETE), retornando código 404.
- Erro de validação, retornando código 422 e mensagens customizada para cada campo inválido. As regras de validação são:
  1) Nome: não pode ser vazio
  2) Data de nascimento: não pode ser data futura (dica: use @PastOrPresent)

<br></br>
CHECKLIST:
1. Busca por id retorna cliente existente
2. Busca por id retorna 404 para cliente inexistente
3. Busca paginada retorna listagem paginada corretamente
4. Inserção de cliente insere cliente com dados válidos
5. Inserção de cliente retorna 422 e mensagens customizadas com dados inválidos
6. Atualização de cliente atualiza cliente com dados válidos
7. Atualização de cliente retorna 404 para cliente inexistente
8. Atualização de cliente retorna 422 e mensagens customizadas com dados inválidos
9. Deleção de cliente deleta cliente existente
10. Deleção de cliente retorna 404 para cliente inexistente

---
## *Pré-requisitos para testes locais* 
Para testar localmente você deve ter instalados em sua máquina:

### 1) Descompactador de arquivos
O Windows 10 já possui um programa padrão para visualizar arquivos compactados. Caso seu sistema operacional seja anterior ao Windos 10, realize o passo abaixo:

Para visualizar o projeto você precisará extrair os arquivos através de um programa para arquivos compactados.

Recomendo Winrar, baixe a versão 32x ou 64x, de acordo com seu sistema.

Link para download: https://www.win-rar.com/download.html

### 2) IDE (Ambiente de Desenvolvimento Integrado)
Este será utilizado para executar a aplicação. Recomendo [STS](https://spring.io/tools) (Spring Tool Suit) ou [IntelliJ Community](https://www.jetbrains.com/idea/download/?section=windows)

### 3) Java JDK...
Caso você não tenha instalado e configurado em sua máquina, instale da seguinte maneira: [Instalar Java JDK 17](https://www.youtube.com/watch?v=QekeJBShCy4)

---
## 📦️ *Realizando teste localmente*
### 1) Clone (baixe) o repositório

Nesta página, clique no botão Code, depois em Download ZIP e salve o arquivo.

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/193577b7-e1a8-4b2c-a80c-b6363a6bed12)

Extrair arquivos: Abra a pasta onde o arquivo foi salvo.

### 2) Extração
### 2.1) Extrair arquivos sem Winrar
Clique com o botão direito sobre o arquivo e selecione Extrair Tudo.
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/03075095-3752-4ce1-83aa-dfda9e738466)

As informações foram extraídas para a pasta Mod8_Desafio3-main.

### 2.2) Extrair arquivos com Winrar
Clique com o botão direito sobre o arquivo e selecione Extrair Aqui (Extract Here).
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/6331c260-b1a5-4fc6-84e8-a383a8dadcf3)

As informações foram extraídas para a pasta Mod8_Desafio3-main.

### 3) Abrir Projeto
### 3.1) Abrir projeto com STS
Clique em File, Import, Maven, Existing Maven Projects, Next...
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/31ff2aae-32fb-44e2-944b-421516888f78)

Selecione a pasta onde você salvou o projeto, Finish
<br></br>
![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/94f03e9f-7468-432a-bfc4-c063ba5d2661)

Aguarde a importação do projeto (acompanhe a barra de carregamento no canto inferior direito).

### 3.2) Abrir projeto com IntelliJ
Clique em Open, selecione a pasta onde você salvou o projeto, Ok...
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/42c768dd-e2d0-4b83-aeb2-9f0d1b74cf00)

Caso apareça a tela abaixo, selecione Maven Project...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/78d9394f-3a72-48db-bd6b-887931ee1537)

Aguarde a importação do projeto (acompanhe a barra de carregamento no canto inferior direito).

### 4) Executar o Projeto
### 4.1) Executar projeto com STS
No menu Boot Dashboard, clique com botão direito em desafio3, (Re)start e aguarde o programa ser iniciado...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/32f2e54f-d599-421f-a3d7-996015c5f1e5)

Neste mesmo menu, a indicação de em execução é uma seta verde para cima

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/61604bf0-1b70-4eef-a191-586cc54e17a6)

No menu Console, pode-se ver o tempo de inicialização e a indicação de processo rodando...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/0cd3e4bf-6d7e-4704-8da3-3d94793d5cdd)

### 4.2) Executar projeto com IntelliJ
Acesse Mod8_Desafio3-main > src > main > java > Desafio3Application, clique com botão direito e clique em Run 'Sistema...' e aguarde o programa ser iniciado...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/3394cfa6-2a32-48fa-9c44-186b998c255f)

No menu Run, pode-se ver o tempo de inicialização e a indicação de processo rodando...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/e42bb25a-a81d-4c80-8cb1-98c625535218)

### 5) Com o Programa em Execução
Acesse o H2 DataBase com o link http://localhost:8080/h2-console

Informações de acesso:
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Senha:

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/4ee2657b-ada6-46a9-b782-889ac83f75f1)

### Criação de tabelas e Seed de dados
As tabelas que foram criadas podem ser vistas no lado esquerdo. Neste caso é apenas a de Client

Para ver o seed de dados, selecione a tabela e clique em Run. O retorno da consulta aparece logo abaixo.

![image](https://github.com/Tleofreitas/Mod8_Desafio3/assets/88738577/d7cb3a09-6c42-4bab-9f4f-20a640538c9d)

---
### Realizando Testes
Video tutorial aqui

---
## ⚠️ *Erros comuns* ⚠️
### No passo Como Testar o Código>
Se não houver a opção *Extrair Tudo*, significa que não há programa instalado para manipulação de arquivos compactados.
Neste cado, seguir com o passo *Pré-requisitos para testes locais*.

### Abrir projeto com IntelliJ Community
Após o término da importação, pode ocorrer do IntelliJ não localizar o JDK, neste caso, vá em File, Settings, pesquise por JDK, selecione Importing e em JDK for importer selecione seu JDK. Aplique (Apply) e Ok.

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/93b85b1c-8515-470f-bc40-b7ae460fbb24)

Caso você não tenha o JDK instalado, siga com o passo <i><b>Pré-requisitos para testes locais - Java JDK</b></i>.

### Abrir o projeto
Após os arquivos serem carregados, pode acontecer de aparecer um X ou um ! vermelho. Caso isso ocorra, tente as soluções deste
tutorial: https://www.youtube.com/watch?v=Je4JWWJcyo0

---
## *Dúvidas? Contate-me*
Me envie uma mensagem no [WhatsApp](https://api.whatsapp.com/send?phone=5511951221949)

---
## *Contribuintes* 🔥👊
Este projeto foi desenvolvido durante o curso Formação Desenvolvedor Moderno da escola [DevSuperior](https://devsuperior.com.br), sobe orientação do tutor [Nelio Alves](https://www.linkedin.com/in/nelio-alves/?originalSubdomain=br).
