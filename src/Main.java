import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        System.out.println("===Abrir Conta===");
        Scanner scanner = new Scanner(System.in);
        System.out.println("===Digite 1 para Sim ou 2 para Não?===");
        int resposta = scanner.nextInt();

        if (resposta == 1) {
            scanner.nextLine();
            System.out.println("===Digite seu Nome===");
            Cliente cliente = new Cliente();
            cliente.setNome(scanner.nextLine());
            System.out.println("===Digite seu CPF===");
            cliente.setCpf(scanner.nextLine());
            System.out.println("===Digite seu E-Mail===");
            cliente.setEmail(scanner.nextLine());
            if (cliente.getEmail() != null && cliente.getEmail().length() > 0) {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(cliente.getEmail());
                if (matcher.matches()) {
                    System.out.println("Cliente Registrado Com Sucesso!");
                    System.out.println(cliente);
                    System.out.println(cliente.toString());
                } else {
                    do {
                        System.out.println("E-Mail Inválido! \n" + "===Digite seu E-Mail===");
                        cliente.setEmail(scanner.nextLine());
                        if (cliente.getEmail() != null && cliente.getEmail().length() > 0) {
                            expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                            pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                            matcher = pattern.matcher(cliente.getEmail());
                        }
                    } while (matcher.matches() == false); {

                    }
                    System.out.println("Cliente Registrado Com Sucesso!");
                    System.out.println(cliente.toString());
                    System.out.println(String.format("Olá %s, qual Conta você quer abrir?", cliente.getNome()));
                    System.out.println("===Digite 1 para Conta Corrente, 2 para Conta Poupança===");
                    resposta = scanner.nextInt();
                    if (resposta == 1) {
                        Conta cc = new ContaCorrente(cliente);
                        System.out.println(String.format("Conta Corrente criada em nome de: %s", (cliente.getNome())));
                        cc.operacoes();
                    } else if (resposta == 2) {
                        Conta cp = new ContaPoupanca(cliente);
                        System.out.println(String.format("Conta Poupança criada em nome de: %s", (cliente.getNome())));
                        cp.operacoes();
                    } else {
                        System.out.println("Opção Inválida. Aplicativo encerrado!");
                    }
                }
            }
        } else if (resposta == 2) {
            System.out.println("Aplicativo Encerrado!");
        } else {
            System.out.println("Opção Inválida. Reinicie o aplicativo!");
        }




        /*.depositar(100);
        cc.transferir(100, cp);

        cc.imprimirExtrato();
        cp.imprimirExtrato();*/
    }
}