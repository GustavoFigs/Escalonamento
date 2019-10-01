package Escalonamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Escalonamento {
	//Criação das listas
	static ArrayList<Processo> processosAlto = new ArrayList<Processo>();
	static ArrayList<Processo> processosMedio = new ArrayList<Processo>();
	static ArrayList<Processo> processosBaixo = new ArrayList<Processo>();
	static ArrayList<Processo> processosFinalizados = new ArrayList<Processo>();
	//Criação do processo Temporario
	static Processo processoTemp;

	public void novo(int quantAlta, int quantMedia, int quantBaixa) {
		//Aqui cada fila chamou uma função para preecher sua lista com processos
		processosAlto = preencheArray(quantAlta, 3);

		processosMedio = preencheArray(quantMedia, 2);

		processosBaixo = preencheArray(quantBaixa, 1);

		pronto();

	}

	public void pronto() {
		//Essas variáveis foram criadas para verificar se as listas tem  processos
		//Se tiver processos=0
		// Senão tiver=1
		int VerificaAlto = 0, VerificaMedio = 0, VerificaBaixo = 0;

		while (true) {

			VerificaAlto = 0;
			VerificaMedio = 0;
			VerificaBaixo = 0;

			if (processosAlto.size() > 0) {// Verifica se nessa lista tem processos

				for (int i = 0; i < 3; i++) {//nessa lista o algoritmo irá executar 3 processos
					if (processosAlto.size() > 0) {//Aqui ele pergunta denovo se há processos nessa lista, porque se tiver apenas um processo o for irá dar erro pois irá executar 3 repetições
						processoTemp = processosAlto.get(0);//O processos temporário guarda o processo da primeira posição
						processosAlto.remove(0);//Aqui será removido o processo da primeira posição
						executando();
					}
				}

			} else {
				VerificaAlto = 1;//Se não tiver processos nessa lista a variável tera o valor de 1
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
			if (VerificaAlto == 1 && VerificaMedio == 1 && VerificaBaixo == 1) {//Se todas as variáveis de todas as filas for um, irá chamar a função finalizado para encerrar o algoritmo
				break;
			}
		}
		finalizado();
	}

	public void executando() {
		processoTemp.RetiraTemp();//Irá retirar uma unidade de tempo do processo, essa função está na classe main
		if (processoTemp.getTempo() > 0) {//verifica se ainda o processo não foi finalizado
			suspenso();
		} else {
			processosFinalizados.add(processoTemp);//Se ele tiver finalizado, ele será adicionado na lista de processos finalizados
		}

	}

	public void suspenso() {//Se o processo não tiver finalizado era irá ser colocado de volta na sua fila
		if (processoTemp.getPrioridade() == 3) {//verifica qual prioridade do processo para ser adcionado na sua respectiva fila
			processosAlto.add(processoTemp);
		} else if (processoTemp.getPrioridade() == 2) {
			processosMedio.add(processoTemp);
		} else {
			processosBaixo.add(processoTemp);
		}
	}

	public void finalizado() {//Quando todos os processos forem finalizados irá mostrar a lista de processos finalizados
		for (int i = 0; i < processosFinalizados.size(); i++) {
			System.out.println(processosFinalizados.get(i));
		}
	}

	public ArrayList<Processo> preencheArray(int quantProcessos, int prioridade) {//Função para preencher cada lista de processos

		Random r = new Random();

		ArrayList<Processo> processosPreenchidos = new ArrayList<Processo>();//cria uma lista auxiliar
		Processo processo;//Cria um processo do tipo Processo

		for (int i = 0; i < quantProcessos; i++) {
			int Tempo = r.nextInt(10) + 1;//Gera um tempo aleatório para o processo
			processo = new Processo();//Cria um noco processo
			processo.setID("" + i + "");//Define seu ID de acordo com a sua posição na fila
			processo.setPrioridade(prioridade);//Define sua prioridade
			processo.setTempo(Tempo);//Define seu Tempo

			processosPreenchidos.add(processo);//Adiciona o processo na fila 
		}

	return processosPreenchidos;//Retorna a lista para que as outras lista possam "Pegar" seus processos
	}

}
