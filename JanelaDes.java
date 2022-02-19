import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Result5{

	/*
	 * Usa janela deslizante para manter os ultimos "d" gastos
	 * em um hash
	 * 
	 * Dps calcula para cada dia se o gasto deste dia foi igual ou maior que o dobro 
	 * da mediana da janela dos "d" dias anteriores
	 * 
	 * Exemplo 
	 * d = 3 (janela de 3 dias anteriores)
	 * gastos por dia
	 * 2,1,3,5
	 * 
	 * Resultado esperado
	 * o gasto de valor 5 do quarto dia é maior que o dobro da mediana dos
	 * 3 dias anteriores (2,1,3) que apos ordenacao temos a mediana "2"
	 * 
	 *  2*2 < 5 -> emitir alerta de gastoa 
	 *
	 *
	 *Alogoritimo
	 *
	 *Este algoritimo matem um TreeMap onde as chaves representam os gastos
	 *e os valores representam quantas vezes aquele gasto ocorreu na janela.
	 *
	 *Entao para cada verificacao diaria é preciso atualizar esta janela
	 *removendo os dias antigos(>-d) para que nao sejam mais considerados 
	 *no calculo da mediana
	 *
	 * Exemplo de janela e como ela é armazeanda no map
	 * 
	 *janela de 3 dias = 3,4,3
	 *
	 *no map será guardada assim
	 * CHAVE   - VALOR
	 * 3          2
	 * 4          1
	 *
	 *na proximo dia a jenala poderá ser
	 * 4,3,4
	 *
	 * e o map deverá ficar assim
	 * 
	 * CHAVE   - VALOR
	 * 3          1
	 * 4          2
	 * 
	 * 
	 * 
	 * Calculando a mediana
	 * 
	 * Caso queira calcula o valor da mediana de um janela de tamanho impar
	 * basta somar 1 em d e dividir por 2
	 * Ex (d+1) /2 => (3+1) /2 = 2
	 * 
	 * Sabemos que que precisamos extrair 2 unidades do nossa janela para chegar no 
	 * valor da mediana (extrair sempre em ordem crescente, por isto escolhemos um map ordenado TreeMap)
	 * 
	 * extraindo -1 no valor da key 3
	 * extraindo -1 no valor da key 4
	 * 
	 * Note que paramos na key=4 então a mediana será 4
	 * 
	 *  
	 * 
	 * a logica eh parecida para janela de tamanho par
	 * d/2 = 2, portanto a mediana ser a (key da contagem=2 + key da contagem =3)/2
	 * 	Ex
	 * d = 4
	 * Janela 3, 4,4,6 
	 * 
	  e o map deverá ficar assim
	 * 
	 * CHAVE   - VALOR
	 * 3          1
	 * 4          2
	 * 6          1 
	 * 
	 * contando até 2 iremos parar na chave 4, mas como é par precisamos contar +1 para descobrir o proximo nro
	 * Obs o proximo nro pode ter o mesmo valor repetido
	 * contando os valores crescentes até 2, temos o valor 4
	 * contando mais 1 ainda tem os o valor 4
	 * 
	 *  portanto (4+4)/2 = 4
	 */

	public static int activityNotifications(List<Integer> expenditure, int d) {
		int alerts =0;

		Map<Integer, Integer> janela = new TreeMap<>();

		//Carregar os gasto no TreeMap de janela   
		for(int i=0; i<d; i++){
			janela.compute(expenditure.get(i), (key, value) ->{if(value==null) return 1; else return value+1;});     }

		for(int i=d; i<expenditure.size(); i++){

			//calcula a mediana se o d for par
			if(d%2==0){
				int count = (d+1)/2;
				//calcula a mediana se o d for impar
				Iterator<Map.Entry<Integer,Integer>> it= janela.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<Integer,Integer> entry = it.next();
					count -= entry.getValue();
					if(count<0){ //mediana esta entre dois numeros repetidos
						if(expenditure.get(i)>=entry.getKey()*2){
							System.out.println("Estourou entry.getKey()" + entry.getKey() + "desp="+expenditure.get(i));
							alerts++;                        
							break;
						}
					}else{
						if(count==0){ //mediana esta entre dois numero diferentes
							Double med_a = Double.valueOf(entry.getKey());
							Double med_b = Double.valueOf(it.next().getKey());
							Double median = (med_a+med_b)/2;
							if(expenditure.get(i)>=(2*median)){
								System.out.println("Estourou entry.getKey()" + entry.getKey() + "desp="+expenditure.get(i));
								alerts++;
								break;
							}
						}
					} 
				} 
			}else{
				int count = (d+1)/2;
				//calcula a mediana se o d for impar
				for(Map.Entry<Integer,Integer> entry : janela.entrySet()){
					count -= entry.getValue();
					if(count<=0){
						if( expenditure.get(i)>=entry.getKey()*2){
							System.out.println("Estourou entry.getKey()" + entry.getKey() + "desp="+expenditure.get(i));  
							alerts++;
							break;
						}
					} 
				}
			}

			//Adicionando novos elementos na janela       
			janela.compute(expenditure.get(i), (key, value) ->{if(value==null) return 1; else return value+1;}); 


			//Remvoendo elementos da janela
			janela.compute(expenditure.get(i-d), (key, value) ->{if(value>0) return value-1; else return null;}); 
		}


		return alerts;
	}

}

public class JanelaDes {
	public static void main(String[] args) throws IOException {


		//Resultado esperado, alerta nos dias de gasto 4 e 11
//		int d = Integer.parseInt("3");        
//		List<Integer> expenditure = Arrays.asList(1,2,3,4,5,6,11,8,9);

		//Resultado esperado, alerta nos dias de gasto 5 e 11
//		int d = Integer.parseInt("4");        
//		List<Integer> expenditure = Arrays.asList(1,2,3,4,5,6,11,8,9);

		//Resultado esperado, alerta nos dias de gasto 11
		int d = Integer.parseInt("4");        
		List<Integer> expenditure = Arrays.asList(1,2,3,4,4,6,11,8,9);

		
		
		int result = Result5.activityNotifications(expenditure, d);


	}
}
