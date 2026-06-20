import java.util.Scanner;

//узел дерева
class ContactNode{
  String name;
  String phone;
  ContactNode left,right; //ссылки на левые и правые узлы

  ContactNode(String name, String phone){
      this.name = name;
      this.phone = phone;
  }
} 

class PhoneBook{
  private ContactNode root;

  public void add(String name, String phone){
    root = addTo(root,name,phone);
  }

  private ContactNode addTo(ContactNode node, String name, String phone){
    if (node == null) return new ContactNode(name,phone);

    int compare = name.compareTo(node.name);
    /*compareTo возвращает разницу числовых кодов между первыми несовподающими символами
    если символы совпали, но одна строка длиннее другой, возвращает разницу длины*/

    if (compare<0) node.left = addTo(node.left, name,phone);
    else if(compare>0) node.right = addTo(node.right,name,phone);
    else {
        // Если контакт с таким именем уже существует, обновляем телефон
        node.phone = phone;
    }
    return node;
  }

  public String findPhone(String name){
    ContactNode result = find(root,name);
    return result != null ? result.phone: "Контакт не найден";
    // тернарный оператор | условие ? если true : если false
  }

  private ContactNode find(ContactNode node, String name){
    if(node == null) return null;

    int compare = name.compareTo(node.name);
    if (compare == 0) return node;
    if (compare < 0) return find(node.left, name);
    return find(node.right, name);
  }

  public boolean delete(String name){
    if (find(root, name) == null) return false;
    root = deleteFrom(root, name);
    return true;
  }

  private ContactNode deleteFrom(ContactNode node, String name){
    if (node == null) return null;

    int compare = name.compareTo(node.name);
    
    if (compare < 0) {
        node.left = deleteFrom(node.left, name);
    } else if (compare > 0) {
        node.right = deleteFrom(node.right, name);
    } else {
        // нашли узел для удаления
        if (node.left == null) return node.right;
        //если нет левого потомка "поднимаем" правый на место удаляемого
        if (node.right == null) return node.left;
        //если нет правого потомка "поднимаем" левый на место удаляемого

        /* если у узла два потомка - находим минимальный в правом поддереве, он будет больше всех потомков в левом
        поддереве, но меньше всех из правого, так мы сохраним свойства бинарного дерева*/
        node.name = findMin(node.right).name;
        node.phone = findMin(node.right).phone;
        node.right = deleteMin(node.right);
    }
    return node;
  }

  private ContactNode findMin(ContactNode node){
    while (node.left != null) {
        node = node.left;
    }
    return node;
  }

  private ContactNode deleteMin(ContactNode node){
    if (node.left == null) return node.right;
    node.left = deleteMin(node.left);
    return node;
  }

  // метод для отображения всех контактов (обход в порядке возрастания)
  public void displayAll(){
    System.out.println("\n=== Все контакты ===");
    displayInOrder(root);
    System.out.println("===================");
  }

  private void displayInOrder(ContactNode node){
    if (node != null) {
        displayInOrder(node.left);
        System.out.println(node.name + " : " + node.phone);
        displayInOrder(node.right);
    }
  }
}

public class Main{
  public static void main(String[] args) {
      PhoneBook pb = new PhoneBook();
      Scanner scanner = new Scanner(System.in);
      
      pb.add("Иван", "123456");
      pb.add("Петр", "789012");
      pb.add("Анна", "345678");
      
      System.out.println("Телефонный справочник");
      System.out.println("Доступные команды:");
      System.out.println("1 - Добавить контакт");
      System.out.println("2 - Найти контакт");
      System.out.println("3 - Удалить контакт");
      System.out.println("4 - Показать все контакты");
      System.out.println("0 - Выход");
      
      while (true) {
          System.out.print("\nВведите команду: ");
          int command = scanner.nextInt();
          scanner.nextLine();
          
          switch (command) {
              case 1:
                  System.out.print("Введите имя: ");
                  String name = scanner.nextLine();
                  System.out.print("Введите телефон: ");
                  String phone = scanner.nextLine();
                  pb.add(name, phone);
                  System.out.println("Контакт добавлен!");
                  break;
                  
              case 2:
                  System.out.print("Введите имя для поиска: ");
                  String searchName = scanner.nextLine();
                  System.out.println("Результат: " + pb.findPhone(searchName));
                  break;
                  
              case 3:
                  System.out.print("Введите имя контакта для удаления: ");
                  String deleteName = scanner.nextLine();
                  if (pb.delete(deleteName)) {
                      System.out.println("Контакт удален!");
                  } else {
                      System.out.println("Контакт не найден!");
                  }
                  break;
                  
              case 4:
                  pb.displayAll();
                  break;
                  
              case 0:
                  System.out.println("До свидания!");
                  scanner.close();
                  return;
                  
              default:
                  System.out.println("Неизвестная команда!");
          }
      }
  }
}
