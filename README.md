# 🔴 Pokedex 2990 - Clean Architecture & Full Data Integration

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Architecture-Clean_Architecture-blue?style=for-the-badge)

Aplicação Android nativa desenvolvida para ser uma enciclopédia Pokémon definitiva, integrando de forma robusta e escalável todos os dados fornecidos pela **PokeAPI**. O projeto aplica rigorosamente os princípios de **Clean Architecture** e **MVVM** para garantir um sistema performático e de fácil manutenção.

## 📱 O Projeto

O foco central deste app é a **completude**. Ele foi projetado para mapear e exibir todas as informações disponíveis na PokeAPI, abrangendo todas as gerações, tipos, habilidades e cadeias evolutivas. A estrutura modular permite que o aplicativo processe grandes volumes de dados mantendo uma interface fluida e organizada.

## 🏗️ Arquitetura e Boas Práticas

Para sustentar a complexidade de um banco de dados completo de Pokémon, o projeto foi estruturado nas seguintes camadas:

* **Data:** Gerenciamento de múltiplas fontes de dados (Remote com **Retrofit** e Local com **Room** para persistência e cache).
* **Domain:** Regras de negócio puras e **Use Cases** que isolam a lógica da aplicação da infraestrutura.
* **UI (Presentation):** Implementação em **MVVM** com View Binding e Fragments, garantindo que a interface seja reativa e desacoplada.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **Injeção de Dependência:** [Dagger Hilt](https://dagger.dev/hilt/)
- **Banco de Dados Local:** [Room](https://developer.android.com/training/data-storage/room)
- **Rede:** [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
- **Assincronismo:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Navegação:** Navigation Component e Fragments

## 🐍 Integração com Python

O projeto conta com um script **Python** integrado via **Chaquopy**, utilizado de forma estratégica em uma das Fragments para otimizar processos de busca e filtragem de dados. Essa implementação demonstra a versatilidade de integrar diferentes linguagens para tarefas específicas dentro do ecossistema Android.

## 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/douglas2990/Pokedex2990cleanArchitecure.git](https://github.com/douglas2990/Pokedex2990cleanArchitecure.git)
    ```
2.  **Configuração do Ambiente:**
    * Certifique-se de que o ambiente Android Studio esteja configurado para suportar o plugin **Chaquopy**.
    * É necessário ter o **Python** instalado na máquina de build.
3.  **Build:**
    * Sincronize o Gradle e aguarde o download das dependências.
4.  **Execução:**
    * Execute o app no emulador ou dispositivo físico.

## 📸 Demonstração

|                                                         |                                           |
|:-------------------------------------------------------:|:-----------------------------------------:|
| <img src="screenshots/Screenshot1 (1).jpg" width="250"> |<img src="screenshots/Screenshot1 (4).jpg" width="250"> 
| <img src="screenshots/Screenshot1 (2).jpg" width="250"> |<img src="screenshots/Screenshot1 (5).jpg" width="250"> 
| <img src="screenshots/Screenshot1 (3).jpg" width="250"> |<img src="screenshots/Screenshot1 (6).jpg" width="250"> 