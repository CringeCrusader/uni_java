import java.util.Scanner;

//реализую очередь
class MyQueue{
  private char[] elements;
  private int first;
  private int size;

  public MyQueue(int maxSize){
    this.elements = new char [maxSize];
    this.first = 0;
    this.size = 0;
  }

  public void enqueue(char element){
    elements[size] = element;
    size++;
  }

  public char dequeue(){
    if (isEmpty()) throw new RuntimeException("Очередь пуста");
    char element = elements[first];
    first++;
    size--;
    return element;
  }

  public boolean isEmpty(){return size ==0;}
}

public class Main{
  public static boolean isPalindrome(String string){
    if (string == null) return false;

    String cleaned = string.replaceAll("\\s+", "").toLowerCase();/* "/s" - любой пробельный символ, заменяем их на пустоту,
    чтобы было возможно работать с палиндромами (они не учитывают пробелы)*/
    if(cleaned.length() <=1) return true; // строка из 1 или 0 символов это палиндром

    MyQueue queue = new MyQueue(cleaned.length());
    for (int i = 0; i < cleaned.length();i++){
      queue.enqueue(cleaned.charAt(i));
    }// добавляем строку в очередь

    for(int i = cleaned.length() - 1; i >=0; i--){
        if(queue.dequeue() != cleaned.charAt(i)){return false;}
    };
    
    return true;
  }
      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String input = scanner.nextLine();

        boolean result = isPalindrome(input);
        if (result == true){System.out.println("Это палиндром");}
        else {System.out.println("Это не палиндром");}

        scanner.close();
    }
}