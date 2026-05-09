```markdown
# 🔥 MazeWars

Um simulador de batalha de criaturas místicas por turnos, desenvolvido em Java com testes unitários (JUnit) e simulações de dependências complexas (Mockito). Parte do desafio da disciplina de Qualidade e Testes de Software.

---

## ✨ Diferenciais

- **6 tipos elementais com relação de vantagem circular** – diferentemente do clássico "pedra-papel-tesoura", aqui cada elemento interage com vários outros de forma única.
- **Habilidades especiais com efeitos de status** – queimaduras, congelamentos, envenenamento e cura adicionam camadas táticas a cada turno.
- **Sistema de iniciativa por velocidade** – o turno não é fixo; criaturas mais rápidas agem primeiro.
- **Inventário de itens utilizáveis** – permite curar, fortalecer ou aplicar efeitos durante o combate.
- **Cobertura completa de testes** – testes unitários com JUnit e mocks com Mockito para cenários complexos como efeitos contínuos e dependências externas (ex: registro de batalha).

---

## 🎯 Público-Alvo

- Jogadores fãs de RPG tático e estratégia por turnos.
- Desenvolvedores e estudantes de testes de software que buscam um exemplo prático de automação de testes com JUnit + Mockito.
- Qualquer pessoa que goste de duelos com criaturas e sistemas elementais profundos.

---

## 🎮 Exemplo de Gameplay

1. **Apresentação das criaturas**: cada jogador escolhe sua criatura (ex: Dragão de Fogo vs. Elemental da Água).
2. **Cálculo de iniciativa**: a criatura com maior velocidade inicia.
3. **Turno do atacante**:
   - Pode usar uma habilidade especial (ex: "Lança-chamas") ou um item (ex: "Poção de Cura").
   - O dano é calculado com base no ATK, DEF e na vantagem elemental.
4. **Aplicação de efeitos**: se a habilidade causar um status (queimado, envenenado), ele é aplicado.
5. **Efeitos entre turnos**: dano por veneno/queimadura é processado.
6. **Alternância de turno** até que uma criatura tenha HP ≤ 0.
7. **Vitória/derrota**: vencedor é declarado, e a batalha é registrada.

**Fluxo simplificado:**
```
Início → Iniciativa → Ação (Habilidade/Item) → Cálculo de Dano → Efeitos → Troca de Turno → ...
```

---

## 🧪 Demonstração Técnica (JUnit + Mockito)

O projeto conta com testes unitários nas classes:
- `CriaturaTest`
- `CalculadoraElementalTest`
- `BatalhaServiceTest`
- `GerenciadorEfeitosTest`

Utilizado **Mockito** para simular dependências complexas, como:
- O registrador de batalhas (`RegistroBatalhaInterface`) para verificar se os logs são gerados corretamente, sem depender de um banco de dados real.
- Efeitos de status (ex: mock do `GerenciadorEfeitos` para isolar o comportamento do veneno).
- Cálculo de dano com vantagem elemental sem precisar criar todas as combinações reais.

Exemplo de método de teste com mock:
```java
@Test
@DisplayName("Deve chamar o registro ao finalizar uma batalha")
void deveRegistrarBatalhaAoFinalizar() {
    RegistroBatalhaInterface mockRegistro = mock(RegistroBatalhaInterface.class);
    BatalhaService service = new BatalhaService(mockRegistro);
    // ... executa batalha ...
    verify(mockRegistro, times(1)).registrar(any());
}
```

Todos os testes seguem a convenção de nomes descritivos e cobrem casos de valores limites e cenários excepcionais.

---

## 📁 Estrutura do Projeto

```
MazeWars/
├── src/
│   ├── model/                  # Entidades do jogo
│   │   ├── Criatura.java
│   │   ├── EfeitoStatus.java
│   │   ├── Habilidade.java
│   │   ├── Item.java
│   │   ├── Main.java
│   │   └── TipoElemental.java
│   ├── service/                # Lógica de batalha e cálculos
│   │   ├── BatalhaService.java
│   │   ├── CalculadoraElemental.java
│   │   ├── GerenciadorEfeitos.java
│   │   └── RegistroBatalhaInterface.java
│   └── test/                   # Testes unitários
│       ├── BatalhaServiceTest.java
│       ├── CalculadoraElementalTest.java
│       ├── CriaturaTest.java
│       └── GerenciadorEfeitosTest.java
├── Mockito/                    # Dependências do Mockito (jars)
├── bin/                        # Classes compiladas
├── .classpath / .project       # Configurações Eclipse
└── README.md
```

---

## 👥 Desenvolvedor

| Nome | Contato |
|------|-----------|
| Anisio Oliveira Albuquerque Filho | anisioalbuquerque71@gmail.com |

---
