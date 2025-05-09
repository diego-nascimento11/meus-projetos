package application;

import model.entities.Client;
import model.entities.ItemOrder;
import model.entities.OrderProcessing;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        List<Client> clients = new ArrayList<>();

        Integer qtdOrder = 0, salesOption = 0, choice = 100, quantityItem, editOrder = 0, index = 0;
        String name, cpf, email;
        Date date;
        Double unitPrice;
        boolean validStatus = false;

        clients.add(new ItemOrder("Lucas", "303.987.302-13", "lucas_santos@gmail.com", new Date(), 20.00, 3, OrderProcessing.valueOf("ENTREGUE")));
        clients.add(new ItemOrder("Diego", "239.987.232-0", "lucas_santos@gmail.com", new Date(), 20.00, 3, OrderProcessing.valueOf("ENTREGUE")));
        clients.add(new ItemOrder("Raphael", "942.842.382-09", "lucas_santos@gmail.com", new Date(), 20.00, 3, OrderProcessing.valueOf("ENTREGUE")));


        do {
            System.out.println("===========================================================================");
            System.out.println("|                     Sistema de Gestão Empresarial                       |");
            System.out.println("===========================================================================");
            System.out.println("1. Entrar no módulo de Vendas (realizar ou consultar pedidos).");
            System.out.println("2. Acessar Controle de Estoque (entrada, relatórios e controle de produtos).");
            System.out.println("3. Gerenciar Recursos Humanos (cadastro, registro, cálculo).");
            System.out.println("4. Acessar Financeiro (controle, fluxo e relatório).");
            System.out.println("0. Sair");
            System.out.println("===========================================================================");
            System.out.print("Escolha uma opção para prosseguir: ");

            try {

                choice = Integer.parseInt(sc.nextLine());

                if (choice < 0 || choice > 4) {
                    System.out.println();
                    System.out.println("| Opção inválida, opções disponíveis: (1, 2, 3, 4 e 0).");
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("| A opção digitada não é reconhecida como número.");
                System.out.println();
                Thread.sleep(1000);
                continue;
            }

            switch (choice) {
                case 1:

                    for (int i = 0; i < 10; i++) {
                        System.out.println();
                    }

                    while (true) {

                        System.out.println("============================");
                        System.out.println("|   Operações Comerciais   |");
                        System.out.println("============================");
                        System.out.println("1. Realizar pedido.");
                        System.out.println("2. Consultar pedidos.");
                        System.out.println("0. Voltar ao menu principal.");
                        System.out.println("============================");
                        System.out.print("Escolha uma opção: ");

                        try {

                            salesOption = Integer.parseInt(sc.nextLine());

                            if (salesOption == 0) {
                                System.out.println();
                                System.out.println("| Retornando ao menu... ");
                                System.out.println();
                                Thread.sleep(1000);
                                break;
                            } else if (salesOption > 2) {
                                System.out.println();
                                System.out.println("| O número inserido não é considerado uma opção.");
                                System.out.println();
                                Thread.sleep(1000);
                                continue;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println();
                            System.out.println("| A opção digitada não é reconhecida como número.");
                            System.out.println();
                            Thread.sleep(1000);
                            continue;
                        }


                        switch (salesOption) {
                            case 1:

                                for (int i = 0; i < 10; i++) {
                                    System.out.println();
                                }

                                do {

                                    System.out.println("====================");
                                    System.out.println("| Cadastrar pedido |");
                                    System.out.println("=====================");
                                    System.out.print("|Quantos pedidos serão realizados, digite 0 para voltar ao menu: ");

                                    try {

                                        qtdOrder = Integer.parseInt(sc.nextLine());

                                        if (qtdOrder == 0) {
                                            System.out.println();
                                            System.out.println("| Retornando ao menu...");
                                            System.out.println();
                                            Thread.sleep(1000);
                                            break;
                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println();
                                        System.out.println("| A opção digitada não é reconhecida como número.");
                                        System.out.println();
                                        Thread.sleep(1000);
                                        continue;
                                    }

                                    for (int i = 1; i <= qtdOrder; i++) {

                                        System.out.println("Digite os dados do Cliente #" + i + "º Pedido:");
                                        System.out.print("Nome: ");
                                        name = sc.nextLine();

                                        System.out.print("CPF: ");
                                        cpf = sc.nextLine();

                                        System.out.print("Email: ");
                                        email = sc.nextLine();
                                        System.out.println();

                                        System.out.println("Digite os itens do #" + i + "º Pedido:");

                                        try {
                                            System.out.print("Preço p/unidade: ");
                                            unitPrice = Double.parseDouble(sc.nextLine());
                                            System.out.print("Quantia: ");
                                            quantityItem = Integer.parseInt(sc.nextLine());
                                        } catch (NumberFormatException e) {
                                            System.out.println();
                                            System.out.println("| Entrada inválida para preço ou quantidade. Pedido cancelado.");
                                            System.out.println();
                                            i--;
                                            continue;
                                        }

                                        do {

                                            try {
                                                System.out.print("Status do pedido (PENDENTE, PROCESSANDO, ENTREGUE, CANCELADO): ");
                                                String statusStr = sc.nextLine().toUpperCase();
                                                OrderProcessing orderProcessing = OrderProcessing.valueOf(statusStr);
                                                validStatus = true;
                                                clients.add(new ItemOrder(name, cpf, email, new Date(), unitPrice, quantityItem, orderProcessing));
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("| Status inválido, tente novamente.");
                                            }

                                            System.out.println("===============");

                                        } while (!validStatus);

                                    }

                                    if (qtdOrder == 1) {
                                        System.out.println("| O pedido foi cadastrado com sucesso.");
                                        Thread.sleep(1000);
                                    } else {
                                        System.out.println("| Os " + qtdOrder + " pedidos foram cadastrados com sucesso.");
                                        Thread.sleep(1000);
                                    }

                                } while (qtdOrder == 0);

                                break;

                            case 2:

                                if (clients.isEmpty()) {
                                    System.out.println();
                                    System.out.println("| Nenhum pedido cadastrado.");
                                    System.out.println();
                                    Thread.sleep(1000);
                                    continue;
                                } else {
                                    for (int i = 0; i < 10; i++) {
                                        System.out.println();
                                    }
                                    System.out.println("=====================");
                                    System.out.println("| Consultar Pedidos |");
                                    System.out.println("=====================");

                                    int count = 1;

                                    for (int i = 0; i < clients.size(); i++) {
                                        System.out.println("| Pedido: #" + count++);
                                        System.out.println(clients.get(i).toString());
                                    }

                                    while (true) {

                                        try {
                                            System.out.print("Digite o número do pedido a ser editado, digite 0 para voltar ao menu: ");
                                            editOrder = Integer.parseInt(sc.nextLine());
                                            if (editOrder == 0) {
                                                break;
                                            } else if (editOrder > clients.size()) {
                                                System.out.println("| Número de pedido inválido.");
                                            } else {
                                                index = editOrder - 1;
                                                System.out.println("=====================");
                                                System.out.println("Pedido: #" + editOrder);
                                                System.out.println(clients.get(index).toString());
                                                System.out.println("1. Editar nome.");
                                                System.out.println("2. Editar cpf.");
                                                System.out.println("3. Editar email.");
                                                System.out.println("4. Editar data do pedido.");
                                                System.out.println("5. Editar preço p/Unidade.");
                                                System.out.println("6. Editar quantidade.");
                                                System.out.println("7. Editar status do pedido.");
                                                System.out.println("0. Voltar.");
                                                while (true) {
                                                    System.out.print("Escolha uma opção: ");
                                                    Integer editInfoOrder = Integer.parseInt(sc.nextLine());
                                                    if (editInfoOrder == 0) {
                                                        System.out.println();
                                                        System.out.println("| Retornando ao menu...");
                                                        System.out.println();
                                                        Thread.sleep(1000);
                                                        break;
                                                    } else if (editInfoOrder < 1 || editInfoOrder > 7) {
                                                        System.out.println();
                                                        System.out.println("| Opção inválida, digite novamente.");
                                                        System.out.println();
                                                        continue;
                                                    }
                                                }
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println();
                                            System.out.println("| ERRO: Por favor, digite um número inteiro.");
                                            System.out.println();
                                            Thread.sleep(1000);
                                            continue;
                                        } catch (IndexOutOfBoundsException e) {
                                            System.out.println();
                                            System.out.println("| ERRO: Opção inválida.");
                                            System.out.println();
                                            Thread.sleep(1000);
                                            continue;
                                        } catch (NumberFormatException e) {
                                            System.out.println();
                                            System.out.println("| ERRO: O caractere inserido é inválido.");
                                            System.out.println();
                                            Thread.sleep(1000);
                                            continue;
                                        }

                                    }

                                    if (editOrder == 0) {
                                        System.out.println();
                                        System.out.println("| Retornando ao menu...");
                                        System.out.println();
                                        Thread.sleep(1000);
                                        break;
                                    }


                                }

                                break;

                            case 0:

                                break;

                        }
                    }





                    break;

                case 2:
                    break;

                case 0:
                    return;
            }
        } while (choice != 0);


        sc.close();
    }
}

