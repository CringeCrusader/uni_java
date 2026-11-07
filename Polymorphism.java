import java.util.ArrayList;
import java.util.List;

/*
Допустим у нас есть класс для различных ящиков для хранения StorageBox
У него есть подклассы Drawer (выдвижной ящик), Chest (сундук), Safe (сейф).
Каждый из подклассов может хранить вещи внутри себя и открываться (метод open() ), но открываются они по-разному.
 В этом здесь и проявляется полиморфизм.
Польза полиморфизма здесь в том, что клиентский код вызывает единый для всех метод open(),
не зная конкретного ящика. Вся специфика уточняется в подклассах,
что делает удобным добавление новых типов. Это также устраняет появление строчек "if", без полиморфизма
для разграничения открывания разных типов ящиков были бы строчки "if (box instanceof Drawer) ... else if..."
*/

abstract class StorageBox {
    protected String name;
    protected boolean isOpen = false;
    protected final List<String> items = new ArrayList<>();

    public StorageBox(String name) {
        this.name = name;
    }

    public abstract void open();

    public void put(String item) {
        ensureOpen();
        items.add(item);
        System.out.printf("[%s] Положил: %s%n", name, item);
    }

    public String take(String item) {
        ensureOpen();
        if (items.remove(item)) {
            System.out.printf("[%s] Достал: %s%n", name, item);
            return item;
        }
        System.out.printf("[%s] Не нашёл: %s%n", name, item);
        return null;
    }

    public List<String> getContents() {
        return List.copyOf(items);
    }

    public int size() {
        return items.size();
    }

    protected void ensureOpen() {
        if (!isOpen) {
            throw new IllegalStateException(
                    "Ящик '" + name + "' закрыт - сначала откройте его");
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getName() {
        return name;
    }
}

class Drawer extends StorageBox {
    public Drawer(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.printf("[%s] Выдвигаю ящик вперёд%n", name);
        isOpen = true;
    }
}

class Chest extends StorageBox {
    public Chest(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.printf("[%s] Откидываю крышку назад%n", name);
        isOpen = true;
    }
}

class Safe extends StorageBox {
    public Safe(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.printf("[%s] Открываю дверцу%n", name);
        isOpen = true;
    }
}

public class StorageRoom {
    public static void openAll(List<StorageBox> boxes) {
        for (StorageBox box : boxes) {
            box.open();
        }
    }

    public static void main(String[] args) {
        List<StorageBox> boxes = List.of(
                new Drawer("Ящик комода"),
                new Chest("Бабушкин сундук"),
                new Safe("Офисный сейф")
        );

        openAll(boxes);

        boxes.get(0).put("носки");
        boxes.get(0).put("футболка");

        boxes.get(1).put("письма");
        boxes.get(1).put("фотографии");

        boxes.get(2).put("паспорт");
        boxes.get(2).put("деньги");

        System.out.println("---- СОДЕРЖИМОЕ ----");
        boxes.forEach(b -> System.out.printf("%s: %s (%d шт.)%n",
                b.getName(), b.getContents(), b.size()));

        System.out.println("---- ДОСТАЁМ ----");
        boxes.get(1).take("письма");
        boxes.get(2).take("золото");

        boxes.forEach(b -> System.out.printf("%s открыт? %b%n",
                b.getName(), b.isOpen()));
    }
}