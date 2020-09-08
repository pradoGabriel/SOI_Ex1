package View;

import java.util.Scanner;

import Controller.RedesController;

public class Principal {
	public static void main (String[] args) {
		RedesController redesController = new RedesController();
        Scanner ler = new Scanner(System.in);
        int escolha = 0;
        while (escolha != 9) {
        	System.out.println("Escolha uma opção:"
                    + "\n1- IP"
                    + "\n2- Ping"
                    +"\n9- Encerrar");
        	escolha = ler.nextInt();
            String os = redesController.os();
            switch (escolha) {
                case 1:
                	redesController.ip(os);
                    break;
                case 2:
                	redesController.ping(os);
                    break;
                case 9:
                    System.out.print("Encerrando");
                    break;
            }
        }	
	}
}
