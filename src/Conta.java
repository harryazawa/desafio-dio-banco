import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public abstract class Conta implements IConta {

    private static int SEQUENCIAL = 1;
    private static final int AGENCIA_PADRAO = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;

    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Conta: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    public void operacoes() {
        int resposta;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("--------------------Banco Digital---------------------");
        System.out.println("------------------------------------------------------");
        System.out.println("****** Selecione a operação que deseja realizar ******");
        System.out.println("------------------------------------------------------");
        System.out.println("|   Digite 1 - Saque        |");
        System.out.println("|   Digite 2 - Depositar    |");
        System.out.println("|   Digite 3 - Transferir   |");
        System.out.println("|   Digite 4 - PIX          |");
        System.out.println("|   Digite 5 - Extrato      |");
        System.out.println("|   Digite 6 - Sair          |");
        System.out.println("Deseja fazer alguma operação?");
        resposta = scanner.nextInt();
        switch (resposta) {
            case 1:
                System.out.println("Insira o valor:");
                double valor = scanner.nextDouble();
                if (this.saldo > 0 && this.saldo > valor) {
                    this.sacar(valor);
                    System.out.println(String.format("Saque de %.2f reais realizado!", valor));
                    System.out.println("===Imprimindo Extrato===");
                    this.imprimirExtrato();
                } else {
                    System.out.println("Saldo insuficiente, reinicie o aplicativo para tentar novamente.");
                }
                break;
            case 2:
                System.out.println("Insira o valor para depositar:");
                valor = scanner.nextDouble();
                this.depositar(valor);
                System.out.println(String.format("Deposito no valor de %.2f reais realizado.", valor));
                System.out.println("===Imprimindo Extrato===");
                this.imprimirExtrato();
                break;
            case 3:
                System.out.println("Insira o valor para transferir:");
                valor = scanner.nextDouble();
                if (valor >= 0 && valor >= this.saldo) {
                    System.out.println("Insira a conta destino:");
                    this.transferir(valor, this);
                } else {
                    System.out.println("Valor indisponível na conta, reinicie o aplicativo para tentar novamente");
                }
                break;
            case 4:
                valor = scanner.nextDouble();
                if (valor >= 0 && valor >= this.saldo) {
                    System.out.println("Insira a chave pix para qual vai transferir:");

                    this.transferir(valor, this);
                }
                break;
            case 5:
                this.imprimirExtrato();
                break;
        }

    }
}