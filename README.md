# :cat:CatsAPI 
#### Aplicativo Android para obter fatos sobre gatos:paw_prints:

Este aplicativo foi desenvolvido para testar o uso de APIs. Devido ao pouco tempo disponível, o projeto não possui configurações avançadas. Para simplificar o desenvolvimento, utilizou-se a API MeowFacts.

A <b>MeowFacts</b> é uma API que retorna fatos aleatórios sobre gatos através de requisições GET. Ela permite dois parâmetros:
- <b>counter</b>: Define a quantidade de fatos a serem retornados.
- <b>lang</b>: Configura a linguagem.

Na aplicação, o usuário pode definir o valor de <i>counter</i>, enquanto o parâmetro <i>lang</i> está fixado em português brasileiro `("pt-br")`. Ao pressionar o botão "Buscar", os resultados são exibidos como texto acima do botão.

O projeto foi desenvolvido utilizando a <a href="https://github.com/wh-iterabb-it/meowfacts?tab=MIT-1-ov-file">documentação oficial da API</a>.


## Código 

Devido à simplicidade do projeto, toda a lógica foi centralizada na MainActivity. Nesta classe, são criados os objetos, variáveis e métodos utilizados pela aplicação. O projeto possui apenas uma tela. 

Para realizar a conexão com o endpoint, inserir parâmetros e gerenciar callbacks, utilizamos: 
- <b>Retrofit</b>: Para requisições HTTP. 
- <b>GSON e GSON-Converter</b>: Para converter os dados recebidos em objetos Java.

## Telas
Abaixo, uma demonstração do funcionamento do aplicativo:

<p align="center"> <img alt="Tela inicial" src="app/src/main/res/drawable/images/screen1.png" width="250"> <img alt="Mensagem de erro sobre parâmetro counter" src="app/src/main/res/drawable/images/screen2.png" width="250"> <img alt="Resultado com 20 fatos" src="app/src/main/res/drawable/images/screen3.png" width="250"> </p>

- <b>Tela Inicial</b>: Entrada do parâmetro counter e botão de busca.
- <b>Mensagem de Erro</b>: Exemplo de validação para quando o parâmetro não é preenchido ou é inválido.
- <b>Resultado</b>: Exibição de 20 fatos sobre gatos retornados pela API.
