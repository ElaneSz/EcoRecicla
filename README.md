# EcoRecicla ♻️

Plataforma digital voltada à gestão e rastreamento de resíduos recicláveis, conectando **cidadãos**, **cooperativas de reciclagem**, **empresas** e **órgãos públicos** em um ecossistema colaborativo orientado à sustentabilidade.

Projeto desenvolvido para a disciplina de **Engenharia de Software**, do curso de Bacharelado em Ciência da Computação - Universidade do Estado de Santa Catarina (UDESC), Centro de Ciências Tecnológicas (CCT).

> **Docente:** Dra. Rebeca Schroeder
> **Discentes:** Elane Souza de Oliveira e Gabriela Pauli de Oliveira

---

## 📋 Sobre o projeto

A gestão inadequada de resíduos sólidos é um dos maiores desafios ambientais da atualidade. O **EcoRecicla** propõe uma plataforma centralizada que conecta todos os atores envolvidos no ciclo de reciclagem, facilitando a logística das cooperativas, incentivando o descarte correto por parte dos cidadãos e viabilizando a negociação de materiais recicláveis entre cooperativas e empresas.

### Funcionalidades principais

- Cadastro de usuários (cidadãos, cooperativas e empresas)
- Cadastro e geolocalização de pontos de coleta seletiva
- Registro e rastreamento de entregas de materiais recicláveis
- Sistema de pontuação e recompensas para descartes corretos
- Relatórios de impacto ambiental (CO₂ evitado, volume reciclado)
- Marketplace para negociação de materiais entre cooperativas e empresas
- Painel administrativo para gestão da plataforma

---

## 🗂️ Estrutura do projeto

O projeto segue a estrutura padrão Maven, organizado em dois pacotes principais:

```
EcoRecicla/
├── pom.xml
└── src/
    ├── main/java/ecorecicla/
    │   ├── model/
    │   │   ├── Usuario.java            (abstrata)
    │   │   ├── Cidadao.java
    │   │   ├── Cooperativa.java
    │   │   ├── Empresa.java
    │   │   ├── Administrador.java
    │   │   ├── Material.java
    │   │   ├── PontoDeColeta.java
    │   │   ├── EntregaMaterial.java
    │   │   ├── Pontuacao.java
    │   │   ├── Recompensa.java
    │   │   ├── Negociacao.java
    │   │   ├── RelatorioImpacto.java
    │   │   └── CalculadoraPontos.java  (interface)
    │   └── util/
    │       └── Validador.java
    └── test/java/ecorecicla/
        ├── model/
        │   ├── CidadaoTest.java
        │   ├── EntregaMaterialTest.java
        │   ├── MaterialTest.java
        │   ├── NegociacaoTest.java
        │   ├── PontoDeColetaTest.java
        │   ├── PontuacaoTest.java
        │   ├── RecompensaTest.java
        │   ├── RelatorioImpactoTest.java
        │   └── UsuarioTest.java
        └── util/
            └── ValidadorTest.java
```

### Diagrama de Classes (UML)

O diagrama de classes completo, com atributos, métodos e relacionamentos (herança, composição, associação e dependência), está disponível na documentação da **Fase 1** do projeto.

As classes estão organizadas conceitualmente em 4 grupos:

| Grupo | Classes |
|---|---|
| **Usuários** | `Usuario` (abstrata), `Cidadao`, `Cooperativa`, `Empresa`, `Administrador` |
| **Coleta** | `PontoDeColeta`, `EntregaMaterial`, `Material` |
| **Pontos e Marketplace** | `Pontuacao`, `Recompensa`, `Negociacao` |
| **Suporte** | `RelatorioImpacto`, `Validador`, `CalculadoraPontos` |

---

## 🛠️ Tecnologias e ferramentas

| Ferramenta | Uso |
|---|---|
| **Java 17** | Linguagem principal |
| **Maven** | Build e gerenciamento de dependências |
| **JUnit 4** | Testes unitários |
| **JaCoCo** | Análise de cobertura de testes |
| **NetBeans 25** | IDE utilizada no desenvolvimento |

---

## ✅ Testes unitários

Os testes unitários foram elaborados com **JUnit 4**, cobrindo as classes que concentram as principais regras de negócio, cálculos e validações de segurança da plataforma.

### Cobertura de testes (JaCoCo)

| Pacote | Cobertura de Instruções | Cobertura de Branches |
|---|---|---|
| `ecorecicla.model` | 75% | 79% |
| `ecorecicla.util` | 97% | 92% |
| **Total do projeto** | **80%** | **85%** |

### Cobertura por classe (`ecorecicla.model`)

| Classe | Cobertura Instr. | Cobertura Branch |
|---|---|---|
| PontoDeColeta | 100% | 100% |
| Cidadao | 100% | 100% |
| Usuario | 100% | 100% |
| EntregaMaterial | 100% | 100% |
| Material | 100% | 100% |
| Negociacao | 100% | 100% |
| Pontuacao | 100% | 100% |
| RelatorioImpacto | 100% | n/a |
| Recompensa | 100% | 100% |
| Cooperativa | 0% | 0% |
| Empresa | 0% | 0% |
| Administrador | 0% | n/a |

> **Nota:** As classes `Cooperativa`, `Empresa` e `Administrador` não possuem testes unitários dedicados, por decisão deliberada da equipe. Seus métodos são predominantemente compostos por operações de delegação a outras classes já exaustivamente testadas (`Pontuacao`, `Negociacao`, `RelatorioImpacto`, `PontoDeColeta`) ou por operações de efeito colateral sem lógica de negócio crítica associada (mensagens via `System.out.println`). O esforço de testes foi direcionado prioritariamente às classes que concentram regras de negócio, cálculos e validações de segurança.

### Classes com maior prioridade de teste

- **`Validador`** - validação de CPF, CNPJ, autenticação, login e geolocalização; é a classe mais crítica do sistema por garantir a segurança e integridade dos dados de entrada.
- **`Cidadao`** - concentra o fluxo principal de negócio da plataforma (registro de entregas, resgate de recompensas, busca de pontos de coleta).
- **`Pontuacao`** - gerencia o saldo de pontos, base do sistema de recompensas.
- **`Material`** - calcula pontos por quilo e valida tipos de resíduos aceitos.
- **`PontoDeColeta`** - calcula distância geográfica (fórmula de Haversine) e gerencia materiais aceitos.
- **`Negociacao`** - controla a máquina de estados das transações do marketplace.
- **`RelatorioImpacto`** - calcula o impacto ambiental (CO₂ evitado) e exporta relatórios.

---

## 🚀 Como executar o projeto

### Pré-requisitos

- JDK 17 ou superior
- Maven 3.8+ (ou utilizar o Maven embutido do NetBeans)

### Compilar o projeto

```bash
mvn clean compile
```

### Executar os testes unitários

```bash
mvn clean test
```

### Gerar o relatório de cobertura (JaCoCo)

```bash
mvn clean test
```

O relatório é gerado automaticamente em:

```
target/site/jacoco/index.html
```

Abra esse arquivo no navegador para visualizar a cobertura detalhada por pacote, classe e método.

---

## 📄 Documentação completa

A documentação completa do projeto (Fase 1), incluindo:

- Introdução, contexto e escopo
- Stakeholders internos e externos
- Requisitos funcionais e não funcionais (F1 a F15)
- Requisitos suplementares (S1 a S8)
- Estimativa de duração do projeto (Análise de Pontos de Função + COCOMO)
- Diagrama de Classes UML

está disponível no arquivo [`EcoRecicla - Projeto de Engenharia de Software.pdf`], na raiz deste repositório.

### Resumo da estimativa de esforço

| Métrica | Valor |
|---|---|
| Pontos de Função Não Ajustados (PFNA) | 221 |
| Pontos de Função Ajustados (PF) | ≈ 247,52 |
| Linhas de código estimadas (KLOC) | ≈ 13,1 |
| Esforço estimado (COCOMO Básico) | ≈ 35,7 pessoas-mês |
| Tempo de desenvolvimento estimado | ≈ 9 a 10 meses |
| Equipe | 2 desenvolvedoras |

---

## 👥 Equipe

| Nome | Função |
|---|---|
| Elane Souza de Oliveira | Desenvolvimento e documentação |
| Gabriela Pauli de Oliveira | Desenvolvimento e documentação |

**Orientação:** Profa. Dra. Rebeca Schroeder

---

## 📜 Licença

Projeto acadêmico desenvolvido para fins educacionais na disciplina de Engenharia de Software - UDESC, 2026.