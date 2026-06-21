import java.util.Scanner;

public class SandwichMaker3000{
    static class MySet{
        private String[] elements;
        private int size;
        private static final int INITIAL_CAPACITY = 10;

        public MySet(){
            elements = new String[INITIAL_CAPACITY];
            size=0;
        }

        public void add(String element){
            if(element == null|| element.isEmpty()) return; //остановка если пустой элемент
            if(contains(element)) return; //останавка если есть дубликат

            if(size==elements.length){
                resize();
            }
            elements[size++]=element;
        }

        public boolean contains(String element) {
            for(int i=0; i<size;i++){
                if(elements[i].equalsIgnoreCase(element)){return true;}
            }
            return false;
        }

        public boolean containsAll(MySet other){
            for(int i = 0; i < other.size; i++){
                if(!contains(other.elements[i])){return false;}
            }
            return true;
        }


        public void removeAll(MySet other){
            String[] newElements = new String[elements.length];
            int newSize =0;

            for(int i=0;i<size;i++){
                if(!other.contains(elements[i])){newElements[newSize++]=elements[i];}
            }
            elements = newElements;
            size=newSize;
        }

        public void retainAll(MySet other) {
            String[] newElements = new String[elements.length];
            int newSize = 0;
            
            for (int i = 0; i < size; i++) {
                if (other.contains(elements[i])) {
                    newElements[newSize++] = elements[i];
                }
            }
            
            elements = newElements;
            size = newSize;
        }

        public void addAll(MySet other){
            for(int i =0;i<other.size;i++){add(other.elements[i]);}
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size(){return size;}

        @Override
        public String toString(){
            if(size==0) return "[]";

            StringBuilder sb = new StringBuilder("[");
            for(int i=0;i<size;i++){
                sb.append(elements[i]);
                if(i<size-1) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        private void resize(){
            String[] newElements = new String[elements.length*2];
            System.arraycopy(elements,0,newElements,0,size);
            elements = newElements;
        }

        public String[] toArray(){
            String[] result = new String[size];
            System.arraycopy(elements,0,result,0,size);
            return result;
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // множество всех продуктов в холодильнике
        MySet fridge = new MySet();

        System.out.println("Введите продукты в холодильнике (по одному, для завершения введите 'стоп'):");

        // ввод продуктов с клавиатуры
        while (true) {
            String product = scanner.nextLine().trim();
            if (product.equalsIgnoreCase("стоп")) {
                break;
            }
            if (!product.isEmpty()) {
                fridge.add(product);
            }
        }
        // множество обязательных продуктов
        MySet required = new MySet();
        required.add("хлеб");

        //множества возможных начинок
        MySet toppings1 = new MySet();
        toppings1.add("колбаса");
        toppings1.add("сыр");

        MySet toppings2 = new MySet();
        toppings2.add("колбаса");
        toppings2.add("сыр");
        toppings2.add("помидор");

        MySet toppings3 = new MySet();
        toppings3.add("моцарелла");
        toppings3.add("базилик");
        toppings3.add("помидор");

        MySet toppings4 = new MySet();
        toppings4.add("курица");
        toppings4.add("салат");
        toppings4.add("сыр");
        toppings4.add("помидор");

        boolean hasRequired = fridge.containsAll(required);

 if (!hasRequired) {
            System.out.println("\nНе хватает обязательных продуктов для бутерброда!");
            MySet missing = new MySet();
            missing.addAll(required);
            missing.removeAll(fridge);
            System.out.println("Отсутствует: " + missing);
            scanner.close();
            return;
        }


        System.out.println("\nМожно сделать бутерброд.");
        System.out.println("Доступные варианты начинок:");
        
        if (fridge.containsAll(toppings1)) {
            System.out.println("- " + toppings1);
        }
        if (fridge.containsAll(toppings2)) {
            System.out.println("- " + toppings2);
        }
        if (fridge.containsAll(toppings3)) {
            System.out.println("- " + toppings3);
        }
        if (fridge.containsAll(toppings4)) {
            System.out.println("- " + toppings4);
        }
        
        MySet availableToppings = new MySet();
        availableToppings.addAll(toppings1);
        availableToppings.addAll(toppings2);
        availableToppings.addAll(toppings3);
        availableToppings.addAll(toppings4);
        availableToppings.retainAll(fridge);
        
        if (availableToppings.isEmpty()) {
            System.out.println("Ты можешь сделать бутерброд с ничем");
        } else {
            System.out.println("\nДоступные ингредиенты для начинки: " + availableToppings);
            System.out.println("Всего ингредиентов: " + availableToppings.size());
            
            System.out.println("\nДля полных рецептов не хватает:");
            MySet[] allToppings = {toppings1, toppings2, toppings3, toppings4};
            String[] names = {"Классический", "С помидором", "Итальянский", "Куриный"};
            
            for (int i = 0; i < allToppings.length; i++) {
                if (!fridge.containsAll(allToppings[i])) {
                    MySet missing = new MySet();
                    missing.addAll(allToppings[i]);
                    missing.removeAll(fridge);
                    System.out.println("- Для \"" + names[i] + "\" (" + allToppings[i] + "): не хватает " + missing);
                }
            }
        }
        
        scanner.close();
    }
}
