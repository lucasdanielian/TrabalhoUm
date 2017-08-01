//Apagado o teste
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.regranegocio;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteCasaBob;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteCasaWinchester;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteCeu;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteHouston;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.JogadorDean;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteDenver;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteInferno;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.Ambiente;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbientePurgatorio;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Comando;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Analisador;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteCasaCaim;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao.lista.UsuarioDAOLista;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.modelo.Usuario;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.seguranca.SessaoUsuario;
import java.io.Serializable;

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
public class RegraNegocio implements Serializable{
    private static final long serialVersionUID = 1L;
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private int diasCorridos; //variavel que conta quantas ações o jogador ja fez
    private JogadorDean dean;
    private Ambiente denver, houston, casaCaim,casaBob, inferno, purgatorio, ceu, casaWinchester;
    private UsuarioDAOLista usuarioDAOLista;
    private SessaoUsuario sessaoUsuario;
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public RegraNegocio() {
        criarAmbientes();
        analisador = new Analisador();
        diasCorridos = 0;
        dean = new JogadorDean();
        
    }
    
    public void rankingJogadores(){
        
    }
    
    /**
     * Cria todos os ambientes
     */
    private void criarAmbientes() {
      
        //Inserção do Ambiente casa Winchester
        casaWinchester = new AmbienteCasaWinchester("CasaWinchester");
        
        //Inserção do Ambiente Denver
        denver = new AmbienteDenver("Denver");
        
        
        //Inserção do Ambiente Houston
        houston = new AmbienteHouston("Houston");
        
        
        casaCaim = new AmbienteCasaCaim("CasaCaim");
        
        //Iserção casa bob
        casaBob = new AmbienteCasaBob("CasaBob");
        
        
        //Inserção do Ambiente Inferno
        inferno = new AmbienteInferno("PortalInferno");
        
        //Inserção do Ambiente Purgatorio
        purgatorio = new AmbientePurgatorio("Purgatorio");
        
        
        //Inserção do Ambiente Ceu
        ceu = new AmbienteCeu("Ceu");
        
        // inicializa as saidas do ambiente casaWinchester
        casaWinchester.ajustarSaidas(ceu);
        casaWinchester.ajustarSaidas(inferno);
        casaWinchester.ajustarSaidas(denver);
        casaWinchester.ajustarSaidas(casaBob);
        casaWinchester.ajustarSaidas(houston);
        casaWinchester.ajustarSaidas(casaCaim);
        casaWinchester.ajustarSaidas(purgatorio);
        
        // inicializa as saidas do ambiente denver
        denver.ajustarSaidas(ceu);
        denver.ajustarSaidas(inferno);
        denver.ajustarSaidas(casaBob);
        denver.ajustarSaidas(houston);
        denver.ajustarSaidas(casaCaim);
        denver.ajustarSaidas(purgatorio);
        denver.ajustarSaidas(casaWinchester);
        
        // inicializa as saidas do ambiente houston
        houston.ajustarSaidas(ceu);
        houston.ajustarSaidas(inferno);
        houston.ajustarSaidas(casaBob);
        houston.ajustarSaidas(casaCaim);
        houston.ajustarSaidas(casaWinchester);
        houston.ajustarSaidas(purgatorio);
        houston.ajustarSaidas(denver);
        
        // inicializa as saidas do ambiente casaCaim
        casaCaim.ajustarSaidas(houston);
        casaCaim.ajustarSaidas(ceu);
        casaCaim.ajustarSaidas(casaBob);
        casaCaim.ajustarSaidas(casaWinchester);
        casaCaim.ajustarSaidas(denver);
        casaCaim.ajustarSaidas(purgatorio);
        casaCaim.ajustarSaidas(inferno);
        
        // inicializa as saidas do ambiente casaBob
        casaBob.ajustarSaidas(ceu);
        casaBob.ajustarSaidas(denver);
        casaBob.ajustarSaidas(casaWinchester);
        casaBob.ajustarSaidas(casaCaim);
        casaBob.ajustarSaidas(inferno);
        casaBob.ajustarSaidas(purgatorio);
        casaBob.ajustarSaidas(houston);
        
        // O ambiente inferno não tem saidas disponíveis
        /*inferno.ajustarSaidas(ceu);
        inferno.ajustarSaidas(denver);
        inferno.ajustarSaidas(casaWinchester);
        inferno.ajustarSaidas(casaCaim);
        inferno.ajustarSaidas(casaBob);
        inferno.ajustarSaidas(houston);
        inferno.ajustarSaidas(purgatorio);
        */
        
        // inicializa as saidas do ambiente purgatorio
        purgatorio.ajustarSaidas(ceu);
        purgatorio.ajustarSaidas(denver);
        purgatorio.ajustarSaidas(casaWinchester);
        purgatorio.ajustarSaidas(casaBob);
        purgatorio.ajustarSaidas(casaCaim);
        purgatorio.ajustarSaidas(inferno);
        purgatorio.ajustarSaidas(houston);
        
        // inicializa as saidas do ambiente ceu
        ceu.ajustarSaidas(houston);
        ceu.ajustarSaidas(inferno);
        ceu.ajustarSaidas(casaCaim);
        ceu.ajustarSaidas(casaBob);
        ceu.ajustarSaidas(casaWinchester);
        ceu.ajustarSaidas(denver);
        ceu.ajustarSaidas(purgatorio);
     
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
        return "\n Voce esta " + ambienteAtual.getNomeAmbiente() +
               "\n" + ambienteAtual.mensagemDeEntrada(dean) +
               "\n Saidas: " + ambienteAtual.saidasValidas() +
               "\n";
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
        return "\nVoce é Dean. Voce precisa salvar seu irmao."
                + "\nVoce deve descobrir como isso será possivel."
                + "\nSuas palavras de comando sao: " + comandosDisponiveis();
    }
    
    /**
     * Metodo utilizado para retornar os comandos disponiveis
     * @return uma string com os respectivos comandos
     */
    private String comandosDisponiveis(){
        String retornaComandos = "\n\t";
        String [] comando = analisador.comandosDisponiveis();
        for(int i = 0; i < comando.length; i++){
            retornaComandos += comando[i] + " ";
        }
        return retornaComandos;
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

            proximoAmbiente = ambienteAtual.irProximoAmbiente(direcao);

            if (proximoAmbiente == null) {
                if(ambienteAtual.getNomeAmbiente().equals("PortalInferno")){
                    return "\nVocê está preso em: " + ambienteAtual.getNomeAmbiente() + 
                        "\n Digite sair para finalisar e tente novamente.";
                }else{
                    return "\n Nao ha passagem para o ambiente: " + direcao;
                }
            }
            else { 
                ambienteAtual = proximoAmbiente;
                diasCorridos = diasCorridos+3;

                if(diasCorridos <=30){
                    return descricaoAmbienteAtual();
                }
                else{
                    return "\nVoce excedeu o tempo limite. Sam Winchester está "
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
            return dean.exibirItensMochila();
                        
        }else if (itens.equals("armario")){
            return casaWinchester.disponibilizarItemAmbiente(dean);
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
        Item itemAux = dean.removerPeloNomeDaMochila(nomeItem);
        if(itemAux != null){
            if (ambienteAtual.getNomeAmbiente().equals("CasaWinchester")){
                boolean foi = casaWinchester.inserirItensAmbiente(itemAux);
                if(foi){
                    return "\n Item: " + itemAux.getNomeItem() + " guardado com sucesso\n"; 
                }
                else{
                    dean.inserirItensMochila(itemAux);
                    return "erro " + itemAux.getNomeItem() + " nao inserido";
                }
            }else{
                dean.inserirItensMochila(itemAux);
                return "\n O ambiente: " + ambienteAtual.getNomeAmbiente() + " nao lhe permite guardar nenhum item. \n";
            }
        }
        else{
            return "O item " + nomeItem +" não está na mochila";
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
        Item aux = ambienteAtual.pegarItemAmbiente(nomeItem);
        if (ambienteAtual.getNomeAmbiente().equals("CasaWinchester")){
            if(aux != null){
                String verificacao = dean.inserirItensMochila(aux);
                if (verificacao.contains("adicionado")){
                    return "\n Item: " + nomeItem + " coletado com sucesso\n";
                }else{
                    boolean retornaItem = ambienteAtual.inserirItensAmbiente(aux);
                    if(retornaItem){
                        return "\n Item " + nomeItem + " não foi coletado\n"
                                + "O item continua no ambiente";
                    }else{
                        return "\n Item " + nomeItem + " não foi coletado\n"
                                + "O item se perdeu e não retornou ao ambiente";
                    }
                }
            }
            else{
                return "\n Item: " + nomeItem + " nao esta no armario\n";
            }
        }else{
            if(aux != null){
                String verificacao = dean.inserirItensMochila(aux);
                if (verificacao.contains("adicionado")){
                    return "\n Item: " + nomeItem + " coletado com sucesso\n";
                }else{
                    boolean retornaItem = ambienteAtual.inserirItensAmbiente(aux);
                    if(retornaItem){
                        return "\n Item " + nomeItem + " não foi coletado\n"
                                + "O item continua no ambiente";
                    }else{
                        return "\n Item " + nomeItem + " não foi coletado\n"
                                + "O item se perdeu e não retornou ao ambiente";
                    }
                }
            }
            else{
                return "\n Item: " + nomeItem + " nao esta neste ambiente\n";
            }
        }    

    }
    
    /**
     * Metodo qu verifica se um Item está disponível em um Ambiente
     * @return string para que quem chamou o metodo possa realizar a verificacao
     */
    public String verificaDisponibilidadeItemAmbiente(){
        String verificacao = ambienteAtual.disponibilizarItemAmbiente(dean);
        return verificacao;
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
            return dean.lerPaginasDiario();
        }else{
            return "\n Este item nao e o diario, logo nao pode ser lido";
        }
        
    }
    
    /**
     * Metodo que informa quantos dias corridos ao longo do jogo
     * @return Inteiro contendo o numero de dias
     */
    public int getDiasCorridos() {
        return diasCorridos;
    }
    
    /**
     * Metodo para exibir o nome do ambiente em que se está no momento do jogo
     * @return uma string com o nome do ambiente atual
     */
    public String getNomeAmbienteAtual(){
        return ambienteAtual.getNomeAmbiente();
    }
    
    /**
     * Metodo que informa quantos dias faltam para terminar o jogo
     * @return Inteiro contendo o numero de dias
     */
    public int diasRestantes() {
        if (diasCorridos<=30){
            return 30 - diasCorridos;
        }
        return 0;
    }
}
