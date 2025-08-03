# Sudoku em Java

## Sobre o Projeto

Este é um jogo de Sudoku totalmente funcional, desenvolvido em Java como parte de um bootcamp de Java da [Digital Innovation One (DIO)](https://www.dio.me/).

O projeto implementa tanto uma interface gráfica para o usuário (GUI) utilizando **Java Swing**, quanto uma versão que pode ser executada via console. Ele foi estruturado para separar as responsabilidades, com uma clara divisão entre a lógica do jogo, a interface do usuário e os serviços de controle.

## ✨ Funcionalidades

O jogo oferece as seguintes funcionalidades:

* **Iniciar um Novo Jogo**: Carrega um tabuleiro pré-configurado para o jogador começar.
* **Interface Gráfica Completa**: A interface com Swing (`UIMain.java`) permite que o jogador interaja com o tabuleiro de forma visual.
* **Entrada e Validação de Números**: Os campos de texto (`NumberText.java`) aceitam apenas números de 1 a 9 e estão ligados diretamente à lógica do tabuleiro.
* **Verificação de Status**: O jogador pode, a qualquer momento, verificar se o jogo está completo, incompleto ou se contém erros.
* **Resetar o Jogo**: Uma opção para limpar todo o progresso e recomeçar o desafio.
* **Finalizar o Jogo**: Verifica se o tabuleiro está correto e exibe uma mensagem de parabéns ao jogador.
* **Modo Console**: Além da GUI, há uma versão totalmente jogável pelo terminal (`Sudoku.java`).

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando os seguintes conceitos:

* **Interface Gráfica**: `Java Swing` para a criação de todos os componentes visuais, como a janela principal (`MainFrame.java`), painéis (`MainPanel.java`, `SudokuSector.java`) e botões customizados.
* **Programação Orientada a Objetos (POO)**: O código é fortemente baseado em POO, com classes que modelam o `Tabuleiro`, os `Espaços` (`Spaces.java`) e os serviços de controle.
* **Tratamento de Eventos**: Uso de `ActionListener` e `DocumentListener` para responder às interações do usuário em tempo real.
* **Java Streams API**: Utilizada para processar coleções e realizar validações de forma funcional e eficiente.
* **Enumerações**: Para gerenciar estados do jogo (`GameStatusEnum.java`) e tipos de eventos (`EventEnum.java`).
