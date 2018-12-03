
import java.util.ArrayList;
import java.util.Scanner;

public class Realization {
	
	public Realization() {
		
		ArrayList<String> mainList = new ArrayList<String>();
		Integer[] mas;
		int index = 0, 
				nom = 0, 
				allCount = 0;
		int[] resultMatching = new int[2];
		
		// Ввод массива значений в консоль
		mainList = inputStr();
		
		// Начало поиска совпадений
		while (resultMatching[0] != 1) {
		
			// чтение последней строки значений из ArrayList
			mas = readLastStr(mainList);
		
			// Поиск максимума и распределение максимального значения
			mas = allocation(mas,index);
		
			// перевод массива в строку и кладем в список
			mainList = transferMasToMas(mainList,mas);
		
			// произвести сравнение последнего элемента ArrayList с другими
			resultMatching = getMatch(mainList, nom);
			
			if (resultMatching[0] != 1) {
				nom=0;
			}
		
			allCount ++;
		}
		System.out.print("Количество шагов до обнаружения бесконечного цикла: " + allCount + "\nДлина цикла: " + (resultMatching[1]+1));
}
	// Ввод массива значений в консоль
	public static ArrayList<String> inputStr() {
		Scanner in = new Scanner(System.in);
		ArrayList<String> mainList = new ArrayList<String>();
		
		System.out.print("Введите массив значений: ");
		String str = in.nextLine();
		mainList.add(str);
		
		in.close();
		return mainList;
	} // конец inputStr
	
	// чтение последней строки значений из ArrayList
	public static Integer[] readLastStr(ArrayList<String> mainList)  {
		String lastStr[] = ((String) mainList.get(mainList.size()-1)).split(" ");
		Integer[] mas = new Integer[lastStr.length];
		
		for (int i = 0; i < lastStr.length; i++) {
			mas[i] = Integer.parseInt(lastStr[i]);
		}	
		return mas;
	} // конец readLastStr
	
	//Поиск максимума и распределение
	public Integer[] allocation (Integer[] mas, int index)	 {
		
		int max =0, pass = 1, divMax;
		
		// Поиск максимума
		for (int i = 0; i < mas.length; i++) {
			if (mas[i] > max) {
				max = mas[i];
				index = i;
			}
		 }
		
		mas[index] = 0;
		
		// Распределение
		if ( max % (mas.length) == 0) {
			divMax = max/mas.length;
			for (int i = 0; i<mas.length; i++) {
				mas[i] = mas[i] + divMax;
			}
		} else {
		while (max!=0) {
			if (pass == 1) {
				for (int i = index+1; i<mas.length; i++) {
					if (max > 0) {    
						mas[i] = mas[i] + 1;
						max = max - 1;
					} else break;
				}
				pass ++;
			}
			if (pass >1) {
				for (int i = 0; i<mas.length; i++) {
					if (max >0) {
						mas[i] = mas[i] + 1;
						max = max - 1;
					} else break;
				}
			}	
			}
		} // if else
			pass = 1;
			
			return mas;			
	} // конец allocation()

	// перевод массива в строку и кладем в список
	public static ArrayList<String> transferMasToMas(ArrayList<String> mainList,Integer[] mas) {
		String str2 = "";
		for (int ii=0;ii<mas.length;ii++)
		  {
		    str2+=mas[ii].toString()+" ";
		  }
		mainList.add(str2);
		return mainList;
	} // конец transferMasToMas
	
	// произвести сравнение последнего элемента ArrayList с другими
	public int[] getMatch(ArrayList<String> mainList, int nom2) {
		int count = 0; int res = 0;
	
		for (int i = 0; i < mainList.size() - 2; i++) {
			if (mainList.get(i).equals(mainList.get(mainList.size()-1)) ) {
				count ++;
			}
			
			if (count == 1) {
				nom2 ++;
				res = nom2;
			}
		}
		return new int[]{count,res};
	} // конец getMatch

}
