import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

class JogoBatalhaNaval {
    public static int tamanhoTabuleiro;
    public static char[][] tabuleiroJogador1;
    public static char[][] tabuleiroJogador2;
    public static int pontuacaoJogador1;
    public static int pontuacaoJogador2;


    public static void inicializarTabuleiro() {
        tabuleiroJogador1 = new char[tamanhoTabuleiro][tamanhoTabuleiro];
        tabuleiroJogador2 = new char[tamanhoTabuleiro][tamanhoTabuleiro];
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiroJogador1[i][j] = '~';
                tabuleiroJogador2[i][j] = '~';
            }
        }
    }

    public static void posicionarFrotasAleatoriamente() {

        posicionarNavioAleatoriamente(5);
        posicionarNavioAleatoriamente(4);
        posicionarNavioAleatoriamente(3);
        posicionarNavioAleatoriamente(2);
        
        exibirTabuleiro(tabuleiroJogador1);
        System.out.println();
        exibirTabuleiro(tabuleiroJogador2);
    }

    private static void posicionarNavioAleatoriamente(int tamanhoNavio) {
        Random random = new Random();
        boolean posicaoValida = false;
        //Jogador 1:
        while (!posicaoValida) {
            int linha = random.nextInt(tamanhoTabuleiro);
            int coluna = random.nextInt(tamanhoTabuleiro);
            boolean horizontal = random.nextBoolean();

            posicaoValida = verificarPosicaoValida(tabuleiroJogador1, linha, coluna, tamanhoNavio, horizontal);

            if (posicaoValida) {
                // Posiciona o navio no tabuleiro
                for (int i = 0; i < tamanhoNavio; i++) {
                    if (horizontal) {
                        tabuleiroJogador1[linha][coluna + i] = '*';
                    } else {
                        tabuleiroJogador1[linha + i][coluna] = '*';
                    }
                }
            }
        }
        //Jogador 2
        posicaoValida = false;
        while (!posicaoValida) {
            int linha = random.nextInt(tamanhoTabuleiro);
            int coluna = random.nextInt(tamanhoTabuleiro);
            boolean horizontal = random.nextBoolean();

            posicaoValida = verificarPosicaoValida(tabuleiroJogador2, linha, coluna, tamanhoNavio, horizontal);

            if (posicaoValida) {
                // Posiciona o navio no tabuleiro
                for (int i = 0; i < tamanhoNavio; i++) {
                    if (horizontal) {
                        tabuleiroJogador2[linha][coluna + i] = '*';
                    } else {
                        tabuleiroJogador2[linha + i][coluna] = '*';
                    }
                }
            }
        }


    }

    private static boolean verificarPosicaoValida(char[][] tabuleiro, int linha, int coluna, int tamanhoNavio,
            boolean horizontal) {
        if (horizontal) {
            if (coluna + tamanhoNavio > tamanhoTabuleiro) {
                return false;
            }

            for (int i = 0; i < tamanhoNavio; i++) {
                if (tabuleiro[linha][coluna + i] != '~') {
                    return false; // Sobreposição
                }
            }
        } else {
            if (linha + tamanhoNavio > tamanhoTabuleiro) {
                return false; // Fora dos limites do tabuleiro
            }

            for (int i = 0; i < tamanhoNavio; i++) {
                if (tabuleiro[linha + i][coluna] != '~') {
                    return false; // Sobreposição
                }
            }
        }

        /*for (int i = -1; i <= tamanhoNavio; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaLinha = linha + i;
                int novaColuna = coluna + j;

                if (novaLinha >= 0 && novaLinha < tamanhoTabuleiro && novaColuna >= 0
                        && novaColuna < tamanhoTabuleiro) {
                    if (tabuleiro[novaLinha][novaColuna] != '~') {
                        return false;
                    }
                }
            }
        }*/

        return true;
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void realizarAtaque(int linha, int coluna, int jogador) {
        try{

        
            if(jogador == 1){
                if (tabuleiroJogador2[linha][coluna] == '*') {
                    System.out.println("Ataque acertou!");
                    tabuleiroJogador2[linha][coluna] = 'X';
                    pontuacaoJogador1++;
                    
                } else {
                    System.out.println("Ataque errou!");
                }
            }else if(jogador == 2){
                if (tabuleiroJogador1[linha][coluna] == '*') {
                    System.out.println("Ataque acertou!");
                    tabuleiroJogador1[linha][coluna] = 'X';
                    pontuacaoJogador2++;
                    
                } else {
                    System.out.println("Ataque errou!");
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Erro, coordenada invalida, tente novamente.");
            if(jogador == 1){
                App.realizarAtaquePlayerUm();
            }else{
                App.realizarAtaquePlayerDois();
            }
        }
    }

    public static boolean verificarEstadoJogo() {
        if (verificarFrotaAfundada(tabuleiroJogador1)) {
            System.out.println("Jogador 2 venceu! Todos os barcos do Jogador 1 foram afundados.");
            exibirPontuacoes();
            return true;
        } else if (verificarFrotaAfundada(tabuleiroJogador2)) {
            System.out.println("Jogador 1 venceu! Todos os barcos do Jogador 2 foram afundados.");
            exibirPontuacoes();
            return true;
        }

        return false;
    }

    private static boolean verificarFrotaAfundada(char[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j] == '*') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void exibirPontuacoes() {
        System.out.println("Pontuação Jogador 1: " + pontuacaoJogador1);
        System.out.println("Pontuação Jogador 2: " + pontuacaoJogador2);
    }

    public static void posicionarFrotasHibrido(){
        try{
            Scanner scan = new Scanner(System.in);
            int escolhaFrota;
            int escolhaHorizontal;
            boolean horizontal;
            //Player 1
            System.out.println("Player 1: selecione qual frota você deseja posicionar: ");
            System.out.println("1- Porta-Aviões");
            System.out.println("2- Encouraçado");
            System.out.println("3- Destroyer");
            System.out.println("4- Submarino");
            escolhaFrota = scan.nextInt();

            while (escolhaFrota != 1 && escolhaFrota != 2 && escolhaFrota != 3 && escolhaFrota != 4) {
                System.out.println("Erro, insira um valor valido para a frota:");
                System.out.println("1- Porta-Aviões");
                System.out.println("2- Encouraçado");
                System.out.println("3- Destroyer");
                System.out.println("4- Submarino");
                escolhaFrota = scan.nextInt();
            }
            

            switch (escolhaFrota) {
                case 1:
                    posicionarNavioManualmente(5, "Porta-Aviões", "Player 1");
                    break;
                case 2:
                    posicionarNavioManualmente(4, "Encouraçado", "Player 1");
                    break;
                case 3:
                    posicionarNavioManualmente(3, "Destroyer", "Player 1");
                    break;
                case 4:
                    posicionarNavioManualmente(2, "Submarino", "Player 1");
                    break;
                default:
                    break;
            }
            posicionarRestoAleatorio(escolhaFrota, 1);
            exibirTabuleiro(tabuleiroJogador1);

            //Player 2
            System.out.println("Player 2: selecione qual frota você deseja posicionar: ");
            System.out.println("1- Porta-Aviões");
            System.out.println("2- Encouraçado");
            System.out.println("3- Destroyer");
            System.out.println("4- Submarino");
            escolhaFrota = scan.nextInt();

            while (escolhaFrota != 1 && escolhaFrota != 2 && escolhaFrota != 3 && escolhaFrota != 4) {
                System.out.println("Erro, insira um valor valido para a frota:");
                System.out.println("1- Porta-Aviões");
                System.out.println("2- Encouraçado");
                System.out.println("3- Destroyer");
                System.out.println("4- Submarino");
                escolhaFrota = scan.nextInt();
            }

            

            switch (escolhaFrota) {
                case 1:
                    posicionarNavioManualmente(5, "Porta-Aviões", "Player 2");
                    break;
                case 2:
                    posicionarNavioManualmente(4, "Encouraçado", "Player 2");
                    break;
                case 3:
                    posicionarNavioManualmente(3, "Destroyer", "Player 2");
                    break;
                case 4:
                    posicionarNavioManualmente(2, "Submarino", "Player 2");
                    break;
                default:
                    break;
            }
            posicionarRestoAleatorio(escolhaFrota, 2);
            exibirTabuleiro(tabuleiroJogador2);
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido, tente novamente.");
            posicionarFrotasHibrido();
        }

    }
    private static void posicionarRestoAleatorio(int jaPosicionado, int player){
        jaPosicionado--;
        int[] navios = new int[4];
        navios[0] = 5;
        navios[1] = 4;
        navios[2] = 3;
        navios[3] = 2;

        Random random = new Random();
        boolean posicaoValida;
        int tamanhoNavio;
        //Jogador 1:
        if(player == 1){

            for(int j=0;j<4;j++){
                if(j != jaPosicionado){
                    tamanhoNavio = navios[j];
                    posicaoValida = false;
                    while (!posicaoValida) {
                        int linha = random.nextInt(tamanhoTabuleiro);
                        int coluna = random.nextInt(tamanhoTabuleiro);
                        boolean horizontal = random.nextBoolean();

                        posicaoValida = verificarPosicaoValida(tabuleiroJogador1, linha, coluna, tamanhoNavio, horizontal);

                        if (posicaoValida) {
                            // Posiciona o navio no tabuleiro
                            for (int i = 0; i < tamanhoNavio; i++) {
                                if (horizontal) {
                                    tabuleiroJogador1[linha][coluna + i] = '*';
                                } else {
                                    tabuleiroJogador1[linha + i][coluna] = '*';
                                }
                            }
                        }
                    }
                }
            }
        }
        //Jogador 2
        else{
            for(int j=0;j<4;j++){
                if(j != jaPosicionado){
                    tamanhoNavio = navios[j];
                    posicaoValida = false;
                    while (!posicaoValida) {
                        int linha = random.nextInt(tamanhoTabuleiro);
                        int coluna = random.nextInt(tamanhoTabuleiro);
                        boolean horizontal = random.nextBoolean();

                        posicaoValida = verificarPosicaoValida(tabuleiroJogador2, linha, coluna, tamanhoNavio, horizontal);

                        if (posicaoValida) {
                            // Posiciona o navio no tabuleiro
                            for (int i = 0; i < tamanhoNavio; i++) {
                                if (horizontal) {
                                    tabuleiroJogador2[linha][coluna + i] = '*';
                                } else {
                                    tabuleiroJogador2[linha + i][coluna] = '*';
                                }
                            }
                        }
                    }
                }
            }
        }
        
    }

    public static void posicionarFrotasManualmente() {
        
            //PLAYER 1
        System.out.println("Player 1, posicione suas frotas manualmente.");
            

        posicionarNavioManualmente(5, "Porta-Aviões", "Player 1");
        posicionarNavioManualmente(4, "Encouraçado", "Player 1");
        posicionarNavioManualmente(3, "Destroyer", "Player 1");
        posicionarNavioManualmente(2, "Submarino", "Player 1");
            
            //PLAYER 2
            System.out.println("Player 2, posicione suas frotas manualmente.");
            

        posicionarNavioManualmente(5, "Porta-Aviões", "Player 2");
        posicionarNavioManualmente(4, "Encouraçado", "Player 2");
        posicionarNavioManualmente(3, "Destroyer", "Player 2");
        posicionarNavioManualmente(2, "Submarino", "Player 2");
        
    }

    private static void posicionarNavioManualmente(int tamanhoNavio, String nomeNavio, String player) {
        try{
            Scanner sc1 = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            Scanner sch = new Scanner(System.in);
            int escolhaHorizontal;
            boolean horizontal;
            char[][] tabuleiroSendoMontado;

            if(player.equals("Player 1")){
                tabuleiroSendoMontado = tabuleiroJogador1;
            }else{
                tabuleiroSendoMontado = tabuleiroJogador2;
            }
            System.out.println(player + ", posicione o " + nomeNavio + " (tamanho: " + tamanhoNavio + "):");
            
            System.out.println("Informe a coordenada X do ponto inicial: ");
            int linha = sc1.nextInt();
            System.out.println("Informe a coordenada Y do ponto inicial: ");
            int coluna = sc2.nextInt();
            
            System.out.println("Escolha como posicionar:");
            System.out.println("1- Vertical");
            System.out.println("2- Horizontal");
            escolhaHorizontal = sch.nextInt();
            while(escolhaHorizontal != 1 && escolhaHorizontal != 2){
                System.out.println("Insira um numero valido para:");
                System.out.println("1- Vertical");
                System.out.println("2- Horizontal");
                escolhaHorizontal = sch.nextInt();
            }
            if(escolhaHorizontal == 1){
                horizontal = true;
            }else{
                horizontal = false;
            }
            
            

            //O ponto inicial é o ponto mais acima ou à esquerda do navio.

            while (!verificarPosicaoValida(tabuleiroSendoMontado, linha, coluna, tamanhoNavio, horizontal)) {
                System.out.println("Posição inválida. Tente novamente.");
                System.out.println("Informe a coordenada X do ponto inicial: ");
                linha = sc1.nextInt();
                System.out.println("Informe a coordenada Y do ponto inicial: ");
                coluna = sc2.nextInt();
            }
            if(player.equals("Player 1")){
                for(int i=0;i<tamanhoNavio;i++){
                    if(horizontal){
                        tabuleiroJogador1[linha+i][coluna] = '*';
                    }else{
                        tabuleiroJogador1[linha][coluna+i] = '*';
                    }
                }
                exibirTabuleiro(tabuleiroJogador1);
            }else{
                for(int i=0;i<tamanhoNavio;i++){
                    if(horizontal){
                        tabuleiroJogador2[linha+i][coluna] = '*';
                    }else{
                        tabuleiroJogador2[linha][coluna+i] = '*';
                    }
                }
                exibirTabuleiro(tabuleiroJogador2);
            }
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido, tente novamente.");
            posicionarNavioManualmente(tamanhoNavio, nomeNavio, player);
        }catch(ArrayIndexOutOfBoundsException f){
            System.out.println("Erro, valor invalido, tente novamente.");
            posicionarNavioManualmente(tamanhoNavio, nomeNavio, player);
        }
            
        
    }
    public static void resetaJogo(){
        tamanhoTabuleiro = 0;
        tabuleiroJogador1 = null;
        tabuleiroJogador2 = null;
        pontuacaoJogador1 = 0;
        pontuacaoJogador2 = 0;
    }

}


