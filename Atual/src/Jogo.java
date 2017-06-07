/**
 *  Essa eh a classe principal do Jogo "SuperNatural".
 *  "SuperNatural" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario, e precisam consquitar objetos para
 *  comprir a missão que é salvar seu irmão do inferno.
 *  Para isto o mesmo deve realizar a tarefa em um tempo inferior a 30. 
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2017.05.16)
 * @Modificado: Valdeci Soares da Silva Junior & Lucas Danielian 
 */
public class Jogo  {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private boolean terminado; // variavel que encerra o jogo
    private int contador; //variavel que conta quantas ações o jogador ja fez
    
    
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        criarAmbientes();
        analisador = new Analisador();
        terminado = false;
        contador = 1;
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes() {
        Ambiente casaWinchester, denver, houston, casaCaim,casaBob, inferno, purgatorio, ceu;
      
        // cria os ambientes
        casaWinchester = new CasaWinchester("CasaWinchester");
        denver = new Denver("Denver");
        houston = new Houston("Houston");
        casaCaim = new CasaCaim("CasaCaim");
        casaBob = new CasaBob("CasaBob");
        inferno = new Inferno("PortalInferno");
        purgatorio = new Purgatorio("Purgatorio");
        ceu = new Ceu("Ceu");
        
        // inicializa as saidas dos ambientes
        casaWinchester.ajustarSaidas(denver, houston, casaCaim, casaBob, inferno, purgatorio, null, ceu);
        denver.ajustarSaidas(null, houston, casaCaim, casaBob, inferno, purgatorio, casaWinchester, ceu);
        houston.ajustarSaidas(denver, null, casaCaim, casaBob, inferno, purgatorio, casaWinchester, ceu);
        casaCaim.ajustarSaidas(denver, houston, null, casaBob, inferno, purgatorio, casaWinchester, ceu);
        casaBob.ajustarSaidas(denver, houston, casaCaim, null, inferno, purgatorio, casaWinchester, ceu);
        inferno.ajustarSaidas(null, null, null, null, null, null, null, null);
        purgatorio.ajustarSaidas(denver, houston, casaCaim, casaBob, inferno, null, casaWinchester, ceu);
        ceu.ajustarSaidas(denver,houston,casaCaim,casaBob,inferno,purgatorio,casaWinchester,null);

        ambienteAtual = casaWinchester;  // o jogo comeca do lado de fora
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem vindo à Supernatural : Morte Súbita. O jogo é do tipo text adventure, ou seja,\né um jogo basicamente lógico com ações básicas. A imersão na história começa… AGORA!\n");
        System.out.println("Dean winchester está prioritariamente preocupado. Já haviam duas semanas que seu\nirmão Sam havia saído de casa para investigar um caso de ataques de\nbruxas na cidade de Weston, no estado da Flórida. Seria apenas uma\ncaçada a mais na vida de um hunter, entretanto, Sam é um\ncaçador extremamente experiente e um grupo de bruxas não deveria \ncustar mais de poucos dias para ser resolvido. \n" +
" \n" +
"Ao meio de um sono bastante turbulento, o telefone\nde Dean Winchester toca. Ele atende. Após alguns segundos de silêncio\nabsoluto, um ruído semelhante à um aparelho de rádio fora de sintonia\n toma conta do aparelho. Aos poucos uma voz bastante distorcida\ndiz algumas palavras que parecem estar em algum idioma desconhecido\npor ele : “Vala noocenoquedam quisrim tatstus. Hairo nai no demonai,\nainote shiros. Saisudore corena, naishteiros”. A frase se repete\ninúmeras vezes ao fundo. Rapidamente, Dean anota a frase em um pedaço\nde papel. Ao fazer isto, Dean percebe que há um barulho estranho ao fundo do\nambiente ao outro lado da linha. Um som agudo, que vem e volta. \nSúbitamente ele identifica o ruído e entra em completo desespero\n: Era a voz de seu irmão, que gritava com toda a força existente no seu \npeito. A ligação é desligada. “\n" +
" \n" +
"Dean precisa salvar seu irmão, mas não sabe por onde\ncomeçar. Tudo que ele possui é a sua mochila. Dentro dela, está o\ndiário de seu pai, que possui algumas informações sobre criaturas místicas. Dean\nnunca pode retirar o diário de dentro de sua mochila,\nentretanto, ele pode acrescentar mais dois itens nela. Sabe-se que, em sua\ncasa, Dean pode deixar guardado quantos itens quiser, em seu armário.\nSabe-se também que, a cada vez que Dean tem que se deslocar de um lugar para outro,\nele precisa de 3 dias. É seu papel guiar Dean no caminho correto que salvará seu irmão.\nBoa sorte.");
        
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        exibirAmbienteAtual();
    }
    
    private void exibirAmbienteAtual() {
        System.out.println("Voce esta " + ambienteAtual.getNomeAmbiente());
        
        System.out.print("Saidas: ");
        System.out.println(ambienteAtual.getSaidas());
        System.out.println();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = null;
        
            proximoAmbiente = ambienteAtual.getAmbiente(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            
            exibirAmbienteAtual();
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
}
