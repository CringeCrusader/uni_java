import java.util.Scanner;

public class ShoppingList{
    private static class Node{
        String item;
        Node prev;
        Node next;

        Node(String item){
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public ShoppingList(){
        this.head=null;
        this.tail=null;
        this.size =0;
    }
    //добавление предмета в начало
    public void addFirst(String item){
        Node newNode = new Node(item);

        if(head == null){head = tail = newNode;} //если голова пуста - список пуст, новый предмет это единственный элемент списка
        else {
            newNode.prev = head;
            head.next=newNode;
            head = newNode;
        }
        /*если голова не пуста, то у нового узла пред. элемент это голова, следующий элемент для головы - новый узел,
        головой становится - новый узел */
        size++;
    }
    //добавление предмета в конец
    public void addLast(String item){
        Node newNode=new Node(item);
        
        if(tail==null){head=tail=newNode;}
        else{
            newNode.next=tail;
            tail.prev=newNode;
            tail=newNode;
        }
        size++;
    }
    //удаление первого элемента
    public boolean removeFirst(){
        if(head ==null){return false;}
        else{
            head = head.prev;
            head.next=null;
        }
        size--;
        return true;
    }
    //удаление последнего элемента;
    public boolean removeLast(){
        if(tail==null){return false;}
        else{
            tail=tail.next;
            tail.prev=null;
        }
        size--;
        return true;
    }
    //удаление конкретног элемента
    public boolean remove(String item){
        if(head == null){return false;}
        Node current = head;
        while(current != null && !current.item.equals(item)){
            current = current.prev;
        }
        if (current == null){return false;}
        if (current == head){return removeFirst();}
        if(current==tail){return removeLast();}
        else{
            current.next.prev=current.prev;
            current.prev.next=current.next;
            //таким способом мы избавляемся от указывания на узел, который удаляем
        }
        return true;
    }

    public boolean contains(String item){
        Node current = head;
        while(current !=null){
            if(current.item.equals(item)){return true;}
            current=current.prev;
        }
        return false;
    }

    public int count(){return size;}

    public void clear(){
        head=tail=null;
        size=0;
    }

    public void printList() {
    if (head == null) {
        System.out.println("Список покупок пуст.");
        return;
    }
        System.out.println("Список покупок:");
        Node current = head;
        int index = 1;
        while (current !=null){
            System.out.println(index +"."+current.item);
            current = current.prev;
            index++;
        }
        System.out.println();
    }

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingList list = new ShoppingList();
        
        System.out.println("=== Список покупок ===");
        System.out.println("Доступные команды:");
        System.out.println("1 - Добавить в начало"); 
        System.out.println("2 - Добавить в конец");     
        System.out.println("3 - Удалить первый");
        System.out.println("4 - Удалить последний");
        System.out.println("5 - Удалить по названию");
        System.out.println("6 - Проверить наличие");
        System.out.println("7 - Количество элементов");
        System.out.println("8 - Очистить список");
        System.out.println("9 - Показать список");
        System.out.println("0 - Выход");
        System.out.println();
        
        while (true) {
            System.out.print("Выберите команду: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 0:
                    System.out.println("Бибуп.");
                    scanner.close();
                    return;
                    
                case 1:
                    System.out.print("Введите предмет для добавления в начало: ");
                    String itemAddFirst = scanner.nextLine();
                    list.addFirst(itemAddFirst);
                    System.out.println("Предмет \"" + itemAddFirst + "\" добавлен в начало.");
                    break;

                 case 2:
                    System.out.print("Введите товар для добавления в конец: ");
                    String itemAddLast = scanner.nextLine();
                    list.addLast(itemAddLast);
                    System.out.println("Предмет \"" + itemAddLast + "\" добавлен в конец.");
                    break;
                        
                case 3:
                    if (list.removeFirst()) {
                        System.out.println("Первый элемент удален.");
                    } else {
                        System.out.println("Список пуст, нечего удалять.");
                    }
                    break;
                    
                case 4:
                    if (list.removeLast()) {
                        System.out.println("Последний элемент удален.");
                    } else {
                        System.out.println("Список пуст, нечего удалять.");
                    }
                    break;
                    
                case 5:
                    System.out.print("Введите название предмета для удаления: ");
                    String itemToRemove = scanner.nextLine();
                    if (list.remove(itemToRemove)) {
                        System.out.println("Предмет \"" + itemToRemove + "\" удален.");
                    } else {
                        System.out.println("Предмет \"" + itemToRemove + "\" не найден.");
                    }
                    break;
                    
                case 6:
                    System.out.print("Введите название предмета для проверки: ");
                    String itemToCheck = scanner.nextLine();
                    if (list.contains(itemToCheck)) {
                        System.out.println("Предмет \"" + itemToCheck + "\" найден в списке.");
                    } else {
                        System.out.println("Предмет \"" + itemToCheck + "\" не найден.");
                    }
                    break;
                    
                case 7:
                    System.out.println("Количество элементов в списке: " + list.count());
                    break;
                    
                case 8:
                    list.clear();
                    System.out.println("Список очищен.");
                    break;
                    
                case 9:
                    list.printList();
                    break;
                    
                default:
                    System.out.println("Неверная команда. Попробуйте снова.");
            }
            System.out.println();
        }
    }
}