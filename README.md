[![Contributors][contributors-shield]][contributors-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- PROJECT LOGO -->
<p align="center">
  <a href="https://github.com/...">
    <img src="images/logo.png" alt="Logo" width="w" height="h">
  </a>

  <h3 align="center">Projeto Physiometrics</h3>
</p>


Autor: Victor Moreira (UFPR)
Colaborador: Rodrigo Carvalho (UFPA)
Orientadora: Maria Lúcia Okimoto (PPG-MEC UFPR)

Sobre: Este é o resultado da pesquisa de Mestrado em Design de Victor Moreira. O Projeto Phsiometrics é composto por Hardware e Software que tem como objetivo principal a mensuração de emoções fisiológicas de jogadores de videogame. O diferencial dessa ferramenta é o baixo custo, replicabilidade e possibilidade de modificação, incrementação e evolução. 

Dissertação de Mestrado:
https://www.prppg.ufpr.br/siga/visitante/trabalhoConclusaoWS?idpessoal=57615&idprograma=40001016053P0&anobase=2019&idtc=51

Artigos Científicos 
https://link.springer.com/chapter/10.1007/978-3-319-94944-4_14

Página no Facebook
https://www.facebook.com/physiometrics

<!-- ABOUT THE PROJECT -->
## Como usar

# 1° Passo: Reconhecimento do sistema
Utilizamos o Arduino Nano para ligar três sensores: batimentos cardíacos (HR), resposta galvânica da pele (GSR) e eletromiograma (EMG); e realizamos as ligações elétricas (FIGURA 1). Nesse esquema as linhas pretas representam os fios ligados ao GND, as linhas vermelhas os fios ligados a 3V3 (ou 3,3 volts) e as linhas amarelas ligadas nas portas lógicas. Os fios pretos e vermelhos fornecem energia para os sensores, enquanto que os fios amarelos enviam os dados coletados pelos sensores


FIGURA 1: Ligações elétricas dos sensores de HR e GSR no Arduino Uno.
FONTE: Elaborado pelo autor.


### Links para Comprar os Dispositivos

*Arduino Nano
https://www.filipeflop.com/produto/placa-nano-v3-0-cabo-usb-para-arduino/?gclid=CjwKCAjw8ZHsBRA6EiwA7hw_sZfrv0z6qXOixUKzI5DDepx6PAaZvYPJeiHsdBKxV8wVnraIBOHKWxoCZqkQAvD_BwE

*Grove - Ear-clip Heart Rate Sensor
https://www.seeedstudio.com/Grove-Ear-clip-Heart-Rate-Sensor-p-1116.html

*Grove - GSR sensor
https://www.seeedstudio.com/Grove-GSR-sensor-p-1614.html

Após conectar o Arduino ao computador via porta USB, torna-se possível a comunicação Arduino-Computador. Para usar o código que projetamos para o Reconhecimento do Sistema é necessário instalar um complemento no Arduino IDE. Através do link https://github.com/jrullan/neotimer é possível baixar o Timer without delay-master, esse complemento fornece a possibilidade de imprimir dados temporais mais exatos. No caso da nossa aplicação iremos imprimir 10 dados por segundo, e por isso usamos esse complemento a fim de tornar os dados mais consistentes temporalmente.

No código para o Arduino programamos duas formas de saída de dados, uma para a aplicação Java (na linha 86) e outra para o Reconhecimento do sistema (na linha 98). Na FIGURA 2 o código do Arduino está do lado direito, nele é possível verificar que as linhas 89 até 96 estão comentadas para que não interfiram na visualização dos dados. Do lado esquerdo da FIGURA 2 está aberto a função Plotter Serial do Arduino IDE, verificamos que o sensor HR em Azul e o GSR em Vermelho. Esse teste preliminar serve para verificar as respostas dos sensores, se algum deles não estiver ligado ou estiver com defeito, será possível verificar esse tipo de erro.


FIGURA 2: Reconhecendo o sistema.
FONTE: Elaborado pelo autor.


# 2° Passo: Coleta de dados

Antes de começar esse passo, é necessário reenviar o código para o Arduino “Comentando” as impressões para o Plotter do Arduino e “Descomentando” as Informações para o Java. Dentro da pasta do projeto (Programa Java) estão todos os arquivos necessários para executar o programa Physiometrics, que pode ser executado diretamente pelo arquivo Sensor.jar na pasta \dist. Na pasta raiz estão todos os arquivos necessários para abrir o projeto no programa NetBeans. Ao abrir o projeto é necessário importar todas as bibliotecas contidas na pasta \lib, e em Propriedades do Projeto – Executar – Opções de VM, colocar o caminho para a pasta \cv do computador. Ao executar o programa, abre-se a tela ilustrada na FIGURA 3. Essa tela é composta por 3 principais zonas: Na zona superior há um espaço para colocar algumas informações da coleta de dados como: Nome do Projeto, Indivíduo, Coleta, Número e Pesquisador. Abaixo, em “Dados”, são exibidos os dados em tempo real vindos dos sensores do Arduino. Na zona central à esquerda é exibido o vídeo da webcam em tempo real. E ao lado direito, alguns comandos para a comunicação Arduino-Computador, o primeiro comando é referente à porta serial que o Arduino está conectado, a segunda Conecta ou Desconecta o recebimento de dados do Arduino, o botão “Start” faz começar o processo de gravação dos dados e o botão “Stop” interrompe a gravação.


FIGURA 3: Execução do programa Physiometrics através do NetBeans.
FONTE: Elaborado pelo autor.

Como resultado são gerados 3 arquivos, um com o vídeo do gameplay (ou da tela do computador), um vídeo da webcam e um arquivo .xls com os dados fisiológicos. Os dois vídeos têm a mesma duração e os dados fisiológicos começam a ser gravados no início do vídeo, portanto estes dados estão sincronizados.

<!-- CONTACT -->
## Contact

Victor Moreira - - victoremmoreira@gmail.com

Project Link: [https://github.com/victoremmoreira/physiometrics](https://github.com/victoremmoreira/physiometrics)

