package Escalonamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Escalonamento {
	//Cria��o das listas
	static ArrayList<Processo> processosAlto = new ArrayList<Processo>();
	static ArrayList<Processo> processosMedio = new ArrayList<Processo>();
	static ArrayList<Processo> processosBaixo = new ArrayList<Processo>();
	static ArrayList<Processo> processosFinalizados = new ArrayList<Processo>();
	//Cria��o do processo Temporario
	static Processo processoTemp;

	public void novo(int quantAlta, int quantMedia, int quantBaixa) {
		//Aqui cada fila chamou uma fun��o para preecher sua lista com processos
		processosAlto = preencheArray(quantAlta, 3);

		processosMedio = preencheArray(quantMedia, 2);

		processosBaixo = preencheArray(quantBaixa, 1);

		pronto();

	}

	public void pronto() {
		//Essas vari�veis foram criadas para verificar se as listas tem  processos
		//Se tiver processos=0
		// Sen�o tiver=1
		int VerificaAlto = 0, VerificaMedio = 0, VerificaBaixo = 0;

		while (true) {

			VerificaAlto = 0;
			VerificaMedio = 0;
			VerificaBaixo = 0;

			if (processosAlto.size() > 0) {// Verifica se nessa lista tem processos

				for (int i = 0; i < 3; i++) {//nessa lista o algoritmo ir� executar 3 processos
					if (processosAlto.size() > 0) {//Aqui ele pergunta denovo se h� processos nessa lista, porque se tiver apenas um processo o for ir� dar erro pois ir� executar 3 repeti��es
						processoTemp = processosAlto.get(0);//O processos tempor�rio guarda o processo da primeira posi��o
						processosAlto.remove(0);//Aqui ser� removido o processo da primeira posi��o
						executando();
					}
				}

			} else {
				VerificaAlto = 1;//Se n�o tiver processos nessa lista a vari�vel tera o valor de 1
			}
			if (processosMedio.size() > 0) {

				for (int i = 0; i < 2; i++) {
					if (processosMedio.size() > 0) {
						processoTemp = processosMedio.get(0);
						processosMedio.remove(0);
						executando();
					}
				}

			} else {
				VerificaMedio = 1;
			}
			if (processosBaixo.size() > 0) {
				processoTemp = processosBaixo.get(0);
				processosBaixo.remove(0);
				executando();

			} else {
				VerificaBaixo = 1;
			}
			if (VerificaAlto == 1 && VerificaMedio == 1 && VerificaBaixo == 1) {//Se todas as vari�veis de todas as filas for um, ir� chamar a fun��o finalizado para encerrar o algoritmo
				break;
			}
		}
		finalizado();
	}

	public void executando() {
		processoTemp.RetiraTemp();//Ir� retirar uma unidade de tempo do processo, essa fun��o est� na classe main
		if (processoTemp.getTempo() > 0) {//verifica se ainda o processo n�o foi finalizado
			suspenso();
		} else {
			processosFinalizados.add(processoTemp);//Se ele tiver finalizado, ele ser� adicionado na lista de processos finalizados
		}

	}

	public void suspenso() {//Se o processo n�o tiver finalizado era ir� ser colocado de volta na sua fila
		if (processoTemp.getPrioridade() == 3) {//verifica qual prioridade do processo para ser adcionado na sua respectiva fila
			processosAlto.add(processoTemp);
		} else if (processoTemp.getPrioridade() == 2) {
			processosMedio.add(processoTemp);
		} else {
			processosBaixo.add(processoTemp);
		}
	}

	public void finalizado() {//Quando todos os processos forem finalizados ir� mostrar a lista de processos finalizados
		for (int i = 0; i < processosFinalizados.size(); i++) {
			System.out.println(processosFinalizados.get(i));
		}
	}

	public ArrayList<Processo> preencheArray(int quantProcessos, int prioridade) {//Fun��o para preencher cada lista de processos

		Random r = new Random();

		ArrayList<Processo> processosPreenchidos = new ArrayList<Processo>();//cria uma lista auxiliar
		Processo processo;//Cria um processo do tipo Processo

		for (int i = 0; i < quantProcessos; i++) {
			int Tempo = r.nextInt(10) + 1;//Gera um tempo aleat�rio para o processo
			processo = new Processo();//Cria um noco processo
			processo.setID("" + i + "");//Define seu ID de acordo com a sua posi��o na fila
			processo.setPrioridade(prioridade);//Define sua prioridade
			processo.setTempo(Tempo);//Define seu Tempo

			processosPreenchidos.add(processo);//Adiciona o processo na fila 
		}

	return processosPreenchidos;//Retorna a lista para que as outras lista possam "Pegar" seus processos
	}

}
