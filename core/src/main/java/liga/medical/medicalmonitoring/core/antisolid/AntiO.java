package liga.medical.medicalmonitoring.core.antisolid;

// Open-Closed
public class AntiO {

    // Но если мы создадим объект (к примеру: Giraffe),
    // то он является "Травоядным" и нам придется изменить
    // его локику
    // Но если он соблюдал Open-Closed, то мы могли бы наследоваться
    // от него и опредлить классы "Хищники" и "Травоядные"
    public String getTypeOfAnimal(Animal animal) {
        return animal.getTypeOfAnimal();
    }

    // Класс, который определяет
    // тип животных
    private class Animal {

        final String getTypeOfAnimal() {
            return "Хищник";
        }
    }

    private class Dog extends Animal {
    }

    private class Cat extends Animal {
    }

    private class Giraffe extends Animal {
    }
}
