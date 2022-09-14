public class Main {

    public static void main(String[] args) {
        Cliente titular = new Cliente();
        titular.setNome("Venilton");

        Conta cc = new ContaCorrente(titular);
        Conta cp = new ContaPoupanca(titular);

        cc.depositar(100);
        cc.transferir(100, cp);

        cc.imprimirExtrato();
        cp.imprimirExtrato();
    }

}