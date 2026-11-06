/*Пример вреда наследования в ООП
Здесь у нас есть класс Plant, являющийся родителем для класса Mushroom
Mushroom и растения относятся к разным царствам, но они ведут себя достаточно похоже чтобы иногда люди пренебрегали их различиями.
Если мы также пренебрежём отличиями, и сделаем чтобы Mushroom наследовал класс Plant, то могут возникнуть проблемы.
У Plant есть метод который Mushroom не использует (photosynthesize()) и метод который работает по-другому (grow())
В итоге классу Mushroom приходится переопределять функции и переписывать значения переменных (hasChlorophyll),
такая избыточность может привести к неприятным ситуациям, например,
когда код написанный для родительского класса не сможет выполнится для наследника и выдаст ошибку
или к Plant мы захотим добавить новый метод, и теперь, если он тоже экслюзивен только для растений, придётся опять изменять Mushroom,
(если и вовсе не забудем). Помимо этого, избыточность просто путает.
 */
class Plant {
    protected boolean hasChlorophyll = true;

    public void photosynthesize() {
        System.out.println("Поглощаю свет и произвожу кислород");
    }

    public void grow() {
        System.out.println("Расту вверх к свету");
    }
}

class Mushroom extends Plant {
    public Mushroom() {
        this.hasChlorophyll = false; // грибы не содержат хлорофилла
    }

    @Override
    public void photosynthesize() {
        throw new UnsupportedOperationException("Грибы не фотосинтезируют!");
    }

    @Override
    public void grow() {
        System.out.println("Расту мицелием в темноте");
    }

    public void absorbNutrients() {
        System.out.println("Всасываю органику гифами");
    }
}
