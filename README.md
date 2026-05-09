# 🔥 MazeWars

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![JUnit](https://img.shields.io/badge/JUnit-5-green?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-5.19.0-blue?logo=mockito)

> ⚔️ Um simulador de batalha de criaturas místicas por turnos, desenvolvido em Java com testes unitários (JUnit) e mocks inteligentes (Mockito). Projeto da disciplina de Qualidade e Testes de Software.

---

## ✨ Diferenciais

- 🔁 **6 tipos elementais com relação de vantagem circular** – cada elemento interage de forma única com os demais, indo muito além do clássico "pedra-papel-tesoura".
- 🔥❄️ **Habilidades especiais com efeitos de status** – queimaduras, congelamentos, veneno e cura adicionam camadas táticas a cada turno.
- ⚡ **Sistema de iniciativa por velocidade** – o turno não é fixo; criaturas mais rápidas atacam primeiro.
- 🎒 **Inventário de itens utilizáveis** – poções, buffs e outros itens podem virar o jogo durante o combate.
- 🧪 **Cobertura completa de testes** – testes unitários com JUnit e mocks com Mockito para isolar dependências (ex: registro de batalha) e cobrir cenários complexos.

---

## 🎯 Público-Alvo

- 🎲 Jogadores fãs de RPG tático e estratégia por turnos.
- 💻 Desenvolvedores(as) e estudantes de testes de software que buscam um exemplo prático de automação com JUnit + Mockito.
- 🧠 Qualquer pessoa que curta duelos de criaturas e sistemas elementais profundos.

---

## 🎮 Exemplo de Gameplay

1. **Apresentação das criaturas** – cada jogador escolhe seu combatente (ex: 🐉 Dragão de Fogo vs. 🌊 Elemental da Água).
2. **Cálculo de iniciativa** – a criatura com maior velocidade abre o duelo.
3. **Turno do atacante**:
   - Pode usar uma **habilidade especial** (ex: *Lança-chamas*) ou um **item** (ex: *Poção de Cura*).
   - O dano é calculado com base no ATK, DEF e na **vantagem elemental**.
4. **Aplicação de efeitos** – se a habilidade causar um status (🔥 queimado, ☠️ envenenado), ele é aplicado imediatamente.
5. **Efeitos entre turnos** – dano por veneno/queimadura é processado automaticamente.
6. **Alternância de turno** até que uma criatura chegue a HP ≤ 0.
7. **Vitória/Derrota** – o vencedor é declarado e a partida é registrada.

---

## 🧪 Demonstração Técnica (JUnit + Mockito)

### Classes testadas
- `CriaturaTest`
- `CalculadoraElementalTest`
- `BatalhaServiceTest`
- `GerenciadorEfeitosTest`

### Uso de Mockito
Simulamos dependências complexas, como:
- 📋 **Registrador de batalhas** (`RegistroBatalhaInterface`) – verificamos a geração de logs sem um banco de dados real.
- ☠️ **Efeitos de status** – mock do `GerenciadorEfeitos` para isolar o comportamento do veneno.
- ⚖️ **Cálculo de dano elemental** – testamos vantagens sem precisar criar todas as combinações.

**Exemplo de teste com mock:**
```java
@Test
@DisplayName("Deve chamar o registro ao finalizar uma batalha")
void deveRegistrarBatalhaAoFinalizar() {
    RegistroBatalhaInterface mockRegistro = mock(RegistroBatalhaInterface.class);
    BatalhaService service = new BatalhaService(mockRegistro);
    // ... executa batalha ...
    verify(mockRegistro, times(1)).registrar(any());
}
```java

📦 MazeWars
 ┣ 📂 src
 ┃ ┣ 📂 model                 # Entidades do jogo
 ┃ ┃ ┣ 📜 Criatura.java
 ┃ ┃ ┣ 📜 EfeitoStatus.java
 ┃ ┃ ┣ 📜 Habilidade.java
 ┃ ┃ ┣ 📜 Item.java
 ┃ ┃ ┣ 📜 Main.java
 ┃ ┃ ┗ 📜 TipoElemental.java
 ┃ ┣ 📂 service               # Lógica de batalha e cálculos
 ┃ ┃ ┣ 📜 BatalhaService.java
 ┃ ┃ ┣ 📜 CalculadoraElemental.java
 ┃ ┃ ┣ 📜 GerenciadorEfeitos.java
 ┃ ┃ ┗ 📜 RegistroBatalhaInterface.java
 ┃ ┗ 📂 test                  # Testes unitários
 ┃   ┣ 📜 BatalhaServiceTest.java
 ┃   ┣ 📜 CalculadoraElementalTest.java
 ┃   ┣ 📜 CriaturaTest.java
 ┃   ┗ 📜 GerenciadorEfeitosTest.java
 ┣ 📂 Mockito                 # JARs do Mockito
 ┣ 📂 bin                     # Classes compiladas
 ┣ 📄 .classpath
 ┣ 📄 .project
 ┗ 📄 README.md

 👥 Desenvolvedor
🌟 Nome	📧 Contato
Anisio Oliveira Albuquerque Filho	anisioalbuquerque71@gmail.com
