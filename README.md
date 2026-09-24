# MazeWars

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-5.19.0-blue)

Projeto em Java para modelar um sistema de batalha por turnos com criaturas, tipos elementais, habilidades, efeitos de status e testes automatizados.

O foco principal é **regra de negócio + testes**, com separação entre entidades de domínio e serviços responsáveis pela batalha, cálculo elemental e processamento de efeitos.

## O que este projeto demonstra

- modelagem de domínio em Java;
- separação entre `model` e `service`;
- uso de interfaces para desacoplar registro de batalha;
- injeção de dependências por construtor;
- testes unitários com **JUnit 5**;
- uso de **Mockito** para isolar colaboradores;
- validação de comportamento, não apenas de valores estáticos.

## Estrutura

```text
src/
├── model/
│   ├── Criatura.java
│   ├── EfeitoStatus.java
│   ├── Habilidade.java
│   ├── Item.java
│   ├── Main.java
│   └── TipoElemental.java
├── service/
│   ├── BatalhaService.java
│   ├── CalculadoraElemental.java
│   ├── GerenciadorEfeitos.java
│   └── RegistroBatalhaInterface.java
└── test/
    ├── BatalhaServiceTest.java
    ├── CalculadoraElementalTest.java
    ├── CriaturaTest.java
    └── GerenciadorEfeitosTest.java
```

## Regras implementadas

### Ordem de ação

A criatura com maior velocidade age primeiro em cada turno.

### Dano elemental

O cálculo de dano considera atacante, alvo, habilidade e o relacionamento entre tipos elementais.

### Efeitos de status

O projeto modela efeitos temporários, incluindo cenários em que uma criatura pode morrer por efeito antes de agir.

### Bloqueio de ação

Criaturas afetadas por determinados estados podem ficar impossibilitadas de agir naquele turno.

### Itens

As criaturas possuem inventário e podem receber itens como cura ou buff. A estrutura de itens faz parte do domínio, embora o fluxo principal de `BatalhaService` atualmente priorize habilidades e efeitos.

## Testes

Os testes exercitam cenários como:

- criatura mais rápida atacando primeiro;
- batalha encerrada quando uma criatura morre por efeito;
- criatura congelada sem executar ação;
- cálculo elemental;
- comportamento de efeitos;
- estado e regras das criaturas.

### Exemplo de uso de Mockito

```java
@Mock
private RegistroBatalhaInterface registro;

@Mock
private GerenciadorEfeitos gerenciadorEfeitos;

@BeforeEach
void setup() {
    MockitoAnnotations.openMocks(this);
    batalhaService = new BatalhaService(registro, gerenciadorEfeitos);
}
```

Isso permite testar `BatalhaService` isoladamente, controlando o comportamento de efeitos e verificando interações com o registrador.

## Decisões de design

### Interface para registro

`RegistroBatalhaInterface` evita que o serviço de batalha dependa diretamente de uma implementação específica de log/persistência.

### Gerenciador de efeitos separado

O processamento de efeitos fica fora de `BatalhaService`, permitindo testar essa responsabilidade isoladamente.

### Serviço de batalha como orquestrador

`BatalhaService` coordena:

1. processamento de efeitos;
2. verificação de vida;
3. definição da ordem por velocidade;
4. execução da habilidade;
5. aplicação de efeitos;
6. registro do estado da batalha.

## Como executar

O projeto está estruturado como projeto Java/Eclipse e inclui as bibliotecas do Mockito no repositório.

Para executar em uma IDE:

1. importe o projeto Java;
2. configure JUnit 5 no classpath;
3. execute `model.Main` para o fluxo de demonstração;
4. execute as classes em `src/test` para os testes.

## Limitações atuais

- não há sistema de build padronizado com Maven ou Gradle;
- os JARs do Mockito estão versionados no repositório;
- o fluxo principal ainda não usa todas as possibilidades da classe `Item`;
- não há persistência real do histórico de batalhas;
- não há interface gráfica.

## Próximos passos

- migrar dependências para Maven ou Gradle;
- remover binários/JARs versionados desnecessariamente;
- expandir o uso de itens durante a batalha;
- aumentar a cobertura de casos limite;
- adicionar persistência opcional para histórico de batalhas.

## Autor

**Anísio Oliveira Albuquerque Filho**  
GitHub: [@Lugarty](https://github.com/Lugarty)
