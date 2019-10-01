package Escalonamento;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String args[]) {

		Escalonamento main = new Escalonamento();

		int quantAlta, quantMedia, quantBaixa;

		while (true) {//ir� ler a quantidade de processos que se te� em cada fila
			quantAlta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de processos de prioridade alta: "));
			if(quantAlta>0) {//Se o usu�rio digitar um n�mero que n�o � maior que 0, o algoritmo ir� pedir para ele digitar novamente
				break;
			}
		}
		while (true) {
			quantMedia = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de processos de prioridade media: "));
			if(quantMedia>0) {
				break;
			}
		}
		while (true) {
			quantBaixa = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de processos de prioridade baixa: "));
			if(quantBaixa>0) {
				break;
			}
		}
		main.novo(quantAlta, quantMedia, quantBaixa);//chama a fun��o novo da classe Escalonamento
	}
}
