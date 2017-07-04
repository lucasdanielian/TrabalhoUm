package br.ufla.dcc.ppoo.trabalhofinal.regranegocio;

import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteCasaBob;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteCasaWinchester;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteCeu;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteHouston;
import br.ufla.dcc.ppoo.trabalhofinal.jogador.JogadorDean;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteDenver;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteInferno;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.Ambiente;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbientePurgatorio;
import br.ufla.dcc.ppoo.trabalhofinal.itens.Item;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Comando;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Analisador;
import br.ufla.dcc.ppoo.trabalhofinal.ambientes.AmbienteCasaCaim;

/**
 *  Essa eh a classe principal(Para iniciar na lina de comando) do RegraNegocio "SuperNatural".
 *  "SuperNatural" eh um jogo de aventura muito simples, baseado em texto.
  Usuarios podem caminhar em um cenario, e precisam consquitar objetos para
  comprir a missão que é salvar seu irmão do inferno.
  Para isto o mesmo deve realizar a tarefa em um tempo inferior a 30. 
 
  Para iniciar esse jogo, crie uma instancia dessa classe e chame o metodo
  "iniciar".
 
  Essa classe principal cria e inicializa todas as outras: ela cria os
  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * Modificado: Valdeci Soares da Silva Junior e Lucas Danielian 
 * @version 2011.07.31 (2017.05.16)
 */
public class RegraNegocio  {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private int contador; //variavel que conta quantas ações o jogador ja fez
    private JogadorDean dean;
    Item carta, pena, denteLobo, cabecaVampiro, portadorAlmas;
    
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public RegraNegocio() {
        criarAmbientes();
        analisador = new Analisador();
        contador = 1;
        dean = new JogadorDean();
        carta = new Item("Carta de reconhecimento", "Carta de Bob para Caim, que"
                + "que cobra um favor que caim devia a Bob");
    }
    
    /**
     * Cria todos os ambientes
     */
    public void criarAmbientes() {
        Ambiente casaWinchester, denver, houston, casaCaim,casaBob, inferno, purgatorio, ceu;
      
        //Inserção do Ambiente casa Winchester
        casaWinchester = new AmbienteCasaWinchester("CasaWinchester");
        
        //Inserção do Ambiente Denver
        denver = new AmbienteDenver("Denver");
        denteLobo = new Item("Dente","O dente de lobisomem representa o mal do mundo");
        denver.setItem(denteLobo);
        
        //Inserção do Ambiente Houston
        houston = new AmbienteHouston("Houston");
        cabecaVampiro = new Item("CabecaVampiro", "Cabeca do vampiro oiginal");
        houston.setItem(cabecaVampiro);
        
        casaCaim = new AmbienteCasaCaim("CasaCaim");
        
        //Iserção casa bob
        casaBob = new AmbienteCasaBob("CasaBob");
        carta = new Item("Carta", "Carta de Bob para Caim, que"
                + "cedida pelo mesmo para ajudar Dean.");
        casaBob.setItem(carta);
        
        //Inserção do Ambiente Inferno
        inferno = new AmbienteInferno("PortalInferno");
        
        //Inserção do Ambiente Purgatorio
        purgatorio = new AmbientePurgatorio("Purgatorio");
        portadorAlmas = new Item("Almas", "Armazena 10 almas em seu interior.");
        purgatorio.setItem(portadorAlmas);
        
        //Inserção do Ambiente Ceu
        ceu = new AmbienteCeu("Ceu");
        pena = new Item("Pena", "pena do anjo Castiel");
        ceu.setItem(pena);
        
        // inicializa as saidas dos ambientes
        casaWinchester.ajustarSaidas(denver, houston, casaCaim, casaBob, inferno, purgatorio, null, ceu);
        denver.ajustarSaidas(null, houston, casaCaim, casaBob, inferno, purgatorio, casaWinchester, ceu);
        houston.ajustarSaidas(denver, null, casaCaim, casaBob, inferno, purgatorio, casaWinchester, ceu);
        casaCaim.ajustarSaidas(denver, houston, null, casaBob, inferno, purgatorio, casaWinchester, ceu);
        casaBob.ajustarSaidas(denver, houston, casaCaim, null, inferno, purgatorio, casaWinchester, ceu);
        inferno.ajustarSaidas(null, null, null, null, null, null, null, null);
        purgatorio.ajustarSaidas(denver, houston, casaCaim, casaBob, inferno, null, casaWinchester, ceu);
        ceu.ajustarSaidas(denver,houston,casaCaim,casaBob,inferno,purgatorio,casaWinchester,null);
     
        // o jogo comeca do lado de fora
        ambienteAtual = casaWinchester;
    }

    /**
     * Mensagem de abertura para o jogador.
     * @return Retorna a mensagem de abertura para o jogador.
     */
    public String mensagemBoasVindas() {
        return "\n Bem vindo à Supernatural : Morte Súbita. O jogo é do"
                + " tipo text adventure, ou seja,\né um jogo basicamente lógico "
                + "com ações básicas. A imersão na história começa… AGORA!\n"
                + "\nDean winchester está prioritariamente preocupado. Já\n"
                + "haviam duas semanas que seu irmão Sam havia saído de casa\n"
                + "para investigar um caso de ataques de bruxas na cidade de\n"
                + "Weston, no estado da Flórida. Seria apenas uma caçada a mais\n"
                + "na vida de um hunter, entretanto, Sam é um caçador extremamente\n"
                + "experiente e um grupo de bruxas não deveria custar mais de\n"
                + "poucos dias para ser resolvido." +"Ao meio de um sono\n"
                + "bastante turbulento, o telefone de Dean Winchester toca.\n"
                + "Ele atende. Após alguns segundos de silêncio absoluto, um\n"
                + "ruído semelhante à um aparelho de rádio fora de sintonia\n"
                + "toma conta do aparelho. Aos poucos uma voz bastante\n"
                + "distorcida diz algumas palavras que parecem estar em algum\n"
                + "idioma desconhecido por ele : “Vala noocenoquedam quisrim\n"
                + "tatstus. Hairo nai no demonai, ainote shiros. Saisudore\n"
                + "corena, naishteiros”. A frase se repete inúmeras vezes ao\n"
                + "fundo. Rapidamente, Dean anota a frase em um pedaço de papel.\n"
                + "Ao fazer isto, Dean percebe que há um barulho estranho ao\n"
                + "fundo do ambiente ao outro lado da linha. Um som agudo, que\n"
                + "vem e volta. Súbitamente ele identifica o ruído e entra em\n"
                + "completo desespero: Era a voz de seu irmão, que gritava com\n"
                + "toda a força existente no seu peito. A ligação é desligada.\n"
                + "“" +"Dean precisa salvar seu irmão, mas não sabe por\n"
                + "onde começar. Tudo que ele possui é a sua mochila e o diário\n"
                + "de seu pai, que possui algumas informações preciosas. Sua\n"
                + "mochila começa vazia e ele pode acrescentar apenas três itens nela.\n"
                + "Sabe-se que, em sua casa, Dean pode deixar guardado quantos\n"
                + "itens quiser, em seu armário. Sabe-se também que, a cada vez\n"
                + "que Dean tem que se deslocar de um lugar para outro,ele\n"
                + "precisa de 3 dias.  É seu papel guiar Dean no caminho correto\n"
                + "que salvará seu irmão.\n"
                + "\nBoa Sorte!\n"
                + "\n Digite 'ajuda' a qualquer momento se voce precisar de ajuda.\n"
                + descricaoAmbienteAtual();
    }
    
    /**
     * Ambiente em que o jogador está no momento
     * @return String contendo o ambiente
     */
    public String descricaoAmbienteAtual() {
        return "\n Voce esta " + ambienteAtual.getNomeAmbiente() + " no dia "
                + contador + "\n" + ambienteAtual.mensagemDeEntrada(dean)
                + "Saidas: " + ambienteAtual.getSaidas() + "\n";
    }
    
    /**
     * Retorna o endereço da imagem do ambiente Atual
     * @return String contendo o endereco da imagem
     */
    public String imagemAmbienteAtual() {
        return ambienteAtual.imagemDoAmbiente();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    public String processarComando(Comando comando) {
        String querSair = null;

        if(comando.ehDesconhecido()) {
            return "\n Eu nao entendi o que voce disse... \n";
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            return imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            return irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            return querSair = sair(comando);
        }
        
        else if (palavraDeComando.equals("guardar")) {
            return guardar(comando);
        }
    
        else if (palavraDeComando.equals("pegar")) {
            return pegar(comando);
        }
        
        else if (palavraDeComando.equals("analisar")) {
            return analisar(comando);
        }
        
        else if (palavraDeComando.equals("ler")){
            return ler(comando);
        }
        
        return querSair;
    }

    /**
     * Aqui nos imprimimos algo bobo e enigmatico.
     * @return String contendo a lista de comandos
     */
    public String imprimirAjuda() {
        return "\nVoce é Dean. Voce precisa salvar seu irmao. Voce\n "
                + "deve descobrir como isso será possivel.\n"
                + "\nPara utilizar o comando 'ir' voce deve digitar em\n"
                + "seguida qual o ambiente a seguir\n"
                + "\nPara utilizar o comando 'guardar' você deve estar em\n"
                + "CasaWinchester e colocar o nome do item que vai ser guardada\n"
                + "\nPara utilizar o comando 'analisar', você deve colocar\n"
                + "em sequencia se quer listar os itens de 'mochila' ou 'armario'\n"
                + "\nPara utilizar o comando 'pegar' voce deve estar em\n"
                + "CasaWinchester e colocar o nome do item que será recolhido\n"
                + "\nPara utilizar o comando 'ler' você deve colocar em\n"
                + "sequencia a palavra 'diario'\n";
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no novo ambiente.
     * @param comando Recebe um comando como paramentro
     * @return caso contrario imprime mensagem de erro.
     */
    public String irParaAmbiente(Comando comando) {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            return "\nIr pra onde?\n";
        }else{
            String direcao = comando.getSegundaPalavra();

            // Tenta sair do ambiente atual
            Ambiente proximoAmbiente = null;

            proximoAmbiente = ambienteAtual.getAmbiente(direcao);

            if (proximoAmbiente == null) {
                return "\nNao ha passagem!\n";
            }
            else { 
                ambienteAtual = proximoAmbiente;
                contador = contador+3;

                if(contador <=30){
                    return descricaoAmbienteAtual();
                }
                else{
                    return "\nVoce excedeu o tempo limite. Sam Winchester está"
                            + "morto.\n GAME OVER! Digite 'sair' e tente novamente\n";
                }
            }
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @param comando Recebe o camando por parametro
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    public String sair(Comando comando) {
        if(comando.temSegundaPalavra()) {
            return "Sair o que?";
        }
        else {
            return null;  // sinaliza que nos queremos sair
        }
    }
    
    /**
     * Metodo que permite exibir o que o jogador carrega consigo, ou que tem no
     * armario
     * @param comando e passado por parametro para ser avaliado
     * @return  String com item analisado
     */
    public String analisar(Comando comando){
       if (!comando.temSegundaPalavra()){
           return "\nAnalisar o que? \n";
        }
        String itens = comando.getSegundaPalavra();
        if (itens.equals("mochila")){
            return dean.getMochila().exibirItens();
                        
        }else if (itens.equals("armario")){
            return ambienteAtual.getArmario().exibirItens();
        }else{
            return "\nPalavra Invalida\n";
        }
    }
    
    /**
     * Metodo que permite guardar um item que o jogador Dean carrega consigo,
     * no ambiente "CasaWinchester" quando ele precisar remover um item da mochila
     * Obs: pela dinâmica do jogo, ele só pode retirar um item da mochila e guardar
     * no armario que se encontra no ambiente elencado acima.
     * @param comando e passado por parametro para ser avaliado
     * @return  String contendo informacoes se o item foi guardado ou nao
     */
    public String guardar(Comando comando){
        if (!comando.temSegundaPalavra()){
           return "\n Guardar o que? \n";
        }
        String nomeItem = comando.getSegundaPalavra();
        Item itemAux = dean.getMochila().removerPeloNome(nomeItem);
        if (ambienteAtual.getNomeAmbiente().equals("CasaWinchester")){
            ambienteAtual.getArmario().inserirItens(itemAux);
            return "\n Item: " + nomeItem + " guardado com sucesso\n";
                        
        }else{
            return "\n Este ambiente nao lhe permite guardar nenhum item. \n";
        }
    }
    
    /**
     * Metodo que permite pegar um item que o jogador Dean precisa carregar consigo,
     * no ambiente "CasaWinchester", para conquistar seu objetivo de salvar seu irmão
     * @param comando e passado por parametro para ser avaliado
     * @return  String contendo o item que foi pego
     */
    public String pegar(Comando comando){
        if (!comando.temSegundaPalavra()){
           return "\n Pegar o que? \n";
        }
        String nomeItem = comando.getSegundaPalavra();
        if (ambienteAtual.getNomeAmbiente().equals("CasaWinchester")){
            Item aux = ambienteAtual.getArmario().removerPeloNome(nomeItem);
            boolean verificacao = dean.getMochila().inserirItens(aux);
            if (verificacao == true){
                return "\n Item: " + nomeItem + " coletado com sucesso\n";
            }else{
                return "\n Item " + nomeItem + " não foi coletado\n";
            }
                        
        }else{
            return "\n Este ambiente nao lhe permite coletar nenhum item. \n";
        }
    }
    
    /**
     * Metodo que Lê o Diario que o jogador Dean carrega consigo.
     * @param comando e passado por parametro para ser avaliado
     * @return  String com informacoes do diario ou de erro de leitura
     */
    public String ler(Comando comando){
        if (!comando.temSegundaPalavra()){
           return "\n Ler o que? \n";
        }
        String nomeItem = comando.getSegundaPalavra();
        if(nomeItem.equals("diario")){
            return dean.getDiario().getPaginas();
        }else{
            return "\n Este item nao e o diario, logo nao pode ser lido";
        }
        
    }
}