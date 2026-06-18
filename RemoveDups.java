import java.util.Scanner;

//Этот класс должен удалять дублирующиеся символы.
public class RemoveDups{
  
  public static String removeDups(String s){
    if (s.length()<=1){return s;} //если в строке всего 1 или 0 символов то дубликатов там быть не может

    char[] stack = new char[s.length()];
    int top = -1; //вершина стека, пока стек пуст, у его последнего элемента индекс -1

    for (int i = 0; i< s.length(); i++){
      char current = s.charAt(i); //charAt берёт берёт строку и достаёт из неё символ по номеру позиции
    
      if (top>=0 && stack[top] == current){top--;}
      else {stack[++top] = current;}
    }
    return new String(stack, 0, top + 1); /* stack - массив символов который мы превращаем в строку, 0 - offset, т.е.
    с какого символа начинать, top+1 - сколько символов взять*/
  }



 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Введите строку для удаления дублирующихся символов:");
    String input = scanner.nextLine();
    
    String result = removeDups(input);
    System.out.println("Результат: " + result);
    
    scanner.close();
  }
}