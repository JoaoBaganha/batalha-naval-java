import java.util.InputMismatchException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        int variavelMenu;
        
        try{
            exibirMenuInicial();
            variavelMenu = scannerMenu.nextInt();

            while (variavelMenu != 1 && variavelMenu != 9) {
                System.out.println("Erro, insira um numero valido.");
                exibirMenuInicial();
                variavelMenu = scannerMenu.nextInt();
            }

            switch (variavelMenu) {
                case 1:
                    exibirSelecaoTabuleiro();
                    break;


                case 9:
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido.");
        }


    }


    // ESTE É O MENU PARA O JOGADOR INICIAR
    static void exibirMenuInicial() {
        System.out.println("####################################");
        System.out.println("###  BEM-VINDO AO BATALHA-NAVAL  ###");
        System.out.println("####################################");
        System.out.println("1 - JOGAR!");
        System.out.println("9 - Sair do jogo");
        System.out.println("####################################");


    }


    // MENU SOMENTE PARA SELEÇÃO DO TIPO DE TABULEIRO (PEQUENO, MEDIO, GRANDE)
    static void exibirSelecaoTabuleiro() {
        System.out.println("####################################");
        System.out.println("### SELECIONAR TIPO DE TABULEIRO ###");
        System.out.println("####################################");
        System.out.println("1 - Cenário padrão (tabuleiro 10x10)");
        System.out.println("2 - Cenário mínimo (tabuleiro 7x7)");
        System.out.println("3 - Cenário máximo (tabuleiro 20x20)");
        System.out.println("####################################");


        Scanner scannerTabuleiro = new Scanner(System.in);
        int variavelTabuleiro;

        try{
            variavelTabuleiro = scannerTabuleiro.nextInt();

            while (variavelTabuleiro != 1 && variavelTabuleiro != 2 && variavelTabuleiro != 3) {
                System.out.println("Erro, insira um numero valido");
                variavelTabuleiro = scannerTabuleiro.nextInt();
            }

        
            switch (variavelTabuleiro) {
                case 1:
                    JogoBatalhaNaval.tamanhoTabuleiro = 10;
                    break;


                case 2:
                    JogoBatalhaNaval.tamanhoTabuleiro = 7;
                    break;


                case 3:
                    JogoBatalhaNaval.tamanhoTabuleiro = 20;
                    break;


            }
            
            JogoBatalhaNaval.inicializarTabuleiro();
            exibirSelecaoTipoPosicionamento();
            
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido.");
            exibirSelecaoTabuleiro();
        }
        
        


    }


    // MENU SOMENTE PARA O USUÁRIO ESCOLHER ENTRE ALEATORIO, INTERMEDIÁRIO E MANUAL
    static void exibirSelecaoTipoPosicionamento() {
        System.out.println("####################################");
        System.out.println("###   SELECIONAR POSICONAMENTO   ###");
        System.out.println("####################################");
        System.out.println("1 - Posicionamento de frotas aleatório");
        System.out.println("2 - Posicionamento de frotas intermediário");
        System.out.println("3 - Posicionamento de frotas manual");
        System.out.println("####################################");


        Scanner scannerPosicionamento = new Scanner(System.in);
        int variavelPosicionamento;

        try{
            variavelPosicionamento = scannerPosicionamento.nextInt();

            while (variavelPosicionamento != 1 && variavelPosicionamento != 2 && variavelPosicionamento != 3) {

                System.out.println("Erro, insira um valor valido.");
                variavelPosicionamento = scannerPosicionamento.nextInt();

            }

            switch (variavelPosicionamento) {
                case 1:
                    JogoBatalhaNaval.posicionarFrotasAleatoriamente();
                    realizarAtaquePlayerUm();
                    break;


                case 2:
                    JogoBatalhaNaval.posicionarFrotasHibrido();
                    realizarAtaquePlayerUm();
                    break;


                case 3:
                    JogoBatalhaNaval.posicionarFrotasManualmente();
                    realizarAtaquePlayerUm();
                    break;
            }
        
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido.");
            exibirSelecaoTipoPosicionamento();
        }
    }

/* 
    static void posicionarPortaAvioesPlayerUm() {
        System.out.println("####################################");
        System.out.println("###    Player 1: PORTA-AVIÕES    ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerPortaAvioesPlayerUm = new Scanner(System.in);
        int coordenadaXPortaAvioesPlayerUm;


        coordenadaXPortaAvioesPlayerUm = scannerPortaAvioesPlayerUm.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYPortaAvioesPlayerUm;


        coordenadaYPortaAvioesPlayerUm = scannerPortaAvioesPlayerUm.nextInt();


        posicionarEncouracadoPlayerUm();
    }

    static void posicionarEncouracadoPlayerUm() {
        System.out.println("####################################");
        System.out.println("###     Player 1: ENCOURAÇADO    ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerEncouracadoPlayerUm = new Scanner(System.in);
        int coordenadaXEncouracadoPlayerUm;


        coordenadaXEncouracadoPlayerUm = scannerEncouracadoPlayerUm.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYEncouracadoPlayerUm;


        coordenadaYEncouracadoPlayerUm = scannerEncouracadoPlayerUm.nextInt();


        posicionarDestroyerPlayerUm();
    }

    static void posicionarDestroyerPlayerUm() {
        System.out.println("####################################");
        System.out.println("###      Player 1: DESTROYER     ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerDestroyerPlayerUm = new Scanner(System.in);
        int coordenadaXDestroyerPlayerUm;


        coordenadaXDestroyerPlayerUm = scannerDestroyerPlayerUm.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYDestroyerPlayerUm;


        coordenadaYDestroyerPlayerUm = scannerDestroyerPlayerUm.nextInt();


        posicionarSubmarinoPlayerUm();
    }

    static void posicionarSubmarinoPlayerUm() {
        System.out.println("####################################");
        System.out.println("###     Player 1: SUBMARINO      ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerSubmarinoPlayerUm = new Scanner(System.in);
        int coordenadaXSubmarinoPlayerUm;


        coordenadaXSubmarinoPlayerUm = scannerSubmarinoPlayerUm.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYSubmarinoPlayerUm;


        coordenadaYSubmarinoPlayerUm = scannerSubmarinoPlayerUm.nextInt();


        consoleLimpo();
    }

    final static void consoleLimpo() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        posicionarPortaAvioesPlayerDois();
    }

    static void posicionarPortaAvioesPlayerDois() {
        System.out.println("####################################");
        System.out.println("###    Player 2: PORTA-AVIÕES    ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerPortaAvioesPlayerDois = new Scanner(System.in);
        int coordenadaXPortaAvioesPlayerDois;


        coordenadaXPortaAvioesPlayerDois = scannerPortaAvioesPlayerDois.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYPortaAvioesPlayerDois;


        coordenadaYPortaAvioesPlayerDois = scannerPortaAvioesPlayerDois.nextInt();


        posicionarEncouracadoPlayerDois();
    }

    static void posicionarEncouracadoPlayerDois() {
        System.out.println("####################################");
        System.out.println("###     Player 2: ENCOURAÇADO    ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerEncouracadoPlayerDois = new Scanner(System.in);
        int coordenadaXEncouracadoPlayerDois;


        coordenadaXEncouracadoPlayerDois = scannerEncouracadoPlayerDois.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYEncouracadoPlayerDois;


        coordenadaYEncouracadoPlayerDois = scannerEncouracadoPlayerDois.nextInt();


        posicionarDestroyerPlayerDois();


    }

    static void posicionarDestroyerPlayerDois() {
        System.out.println("####################################");
        System.out.println("###      Player 2: DESTROYER     ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerDestroyerPlayerDois = new Scanner(System.in);
        int coordenadaXDestroyerPlayerDois;


        coordenadaXDestroyerPlayerDois = scannerDestroyerPlayerDois.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYDestroyerPlayerDois;


        coordenadaYDestroyerPlayerDois = scannerDestroyerPlayerDois.nextInt();


        posicionarSubmarinoPlayerDois();


    }

    static void posicionarSubmarinoPlayerDois() {
        System.out.println("####################################");
        System.out.println("###     Player 2: SUBMARINO      ###");
        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA X: ");


        Scanner scannerSubmarinoPlayerDois = new Scanner(System.in);
        int coordenadaXSubmarinoPlayerDois;


        coordenadaXSubmarinoPlayerDois = scannerSubmarinoPlayerDois.nextInt();


        System.out.println("####################################");
        System.out.println("DIGITE A COORDENADA Y: ");


        int coordenadaYSubmarinoPlayerDois;


        coordenadaYSubmarinoPlayerDois = scannerSubmarinoPlayerDois.nextInt();


        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");


        realizarAtaquePlayerUm();
    }
*/

    static void realizarAtaquePlayerUm() {
        try{
            System.out.println("####################################");
            System.out.println("###       Player 1: ATACAR       ###");
            System.out.println("####################################");
            System.out.println("DIGITE A COORDENADA X DO ATAQUE: ");


            Scanner scannerAtaquePlayerUm = new Scanner(System.in);
            int coordenadaXAtaquePlayerUm;

        
            coordenadaXAtaquePlayerUm = scannerAtaquePlayerUm.nextInt();


            System.out.println("####################################");
            System.out.println("DIGITE A COORDENADA Y DO ATAQUE: ");


            int coordenadaYAtaquePlayerUm;


            coordenadaYAtaquePlayerUm = scannerAtaquePlayerUm.nextInt();
            JogoBatalhaNaval.realizarAtaque(coordenadaXAtaquePlayerUm, coordenadaYAtaquePlayerUm, 1);
            
            if(JogoBatalhaNaval.verificarEstadoJogo()){
                JogoBatalhaNaval.resetaJogo();
                exibirSelecaoTabuleiro();
            }else{
                realizarAtaquePlayerDois();
            }
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido, tente novamente.");
            realizarAtaquePlayerUm();
        }
        
    }


    static void realizarAtaquePlayerDois() {
        try{
            System.out.println("####################################");
            System.out.println("###       Player 2: ATACAR       ###");
            System.out.println("####################################");
            System.out.println("DIGITE A COORDENADA X DO ATAQUE: ");


            Scanner scannerAtaquePlayerDois = new Scanner(System.in);
            int coordenadaXAtaquePlayerDois;


            coordenadaXAtaquePlayerDois = scannerAtaquePlayerDois.nextInt();


            System.out.println("####################################");
            System.out.println("DIGITE A COORDENADA Y DO ATAQUE: ");


            int coordenadaYAtaquePlayerDois;


            coordenadaYAtaquePlayerDois = scannerAtaquePlayerDois.nextInt();
            JogoBatalhaNaval.realizarAtaque(coordenadaXAtaquePlayerDois, coordenadaYAtaquePlayerDois, 2);
            if(JogoBatalhaNaval.verificarEstadoJogo()){
                JogoBatalhaNaval.resetaJogo();
                exibirSelecaoTabuleiro();
            }else{
                realizarAtaquePlayerUm();
            }
        }catch(InputMismatchException e){
            System.out.println("Erro, valor invalido, tente novamente.");
            realizarAtaquePlayerDois();
        }
        
    }


}
