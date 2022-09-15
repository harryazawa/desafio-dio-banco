public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    // private double taxaDeServico = 2;

    @Override
    public void transferir(double valor, Conta contaDestino) {
        super.transferir(valor, contaDestino);
        contaDestino.setSaldo(valor)/*(- taxaDeServico)*/;
    }

    /*@Override
    public void pix(double valor, Conta contaDestino) {

    }*/

    @Override
    public void imprimirExtrato() {
        System.out.println("===Extrato Conta Corrente===");
        super.imprimirInfosComuns();


    }

    @Override
    public void operacoes() {
        super.operacoes();
    }
}
