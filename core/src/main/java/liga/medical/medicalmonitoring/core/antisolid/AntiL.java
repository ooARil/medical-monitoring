package liga.medical.medicalmonitoring.core.antisolid;

// Liskov Substitution
public class AntiL {

    private class Animal {
    }

    private class Dog extends Animal {
    }

    private class Cat extends Animal {
    }

    private class Giraffe extends Animal {
    }

    // Если мы создадим объект Giraffe,
    // то нам придется менять логику метода и
    // добавлять проверку для Giraffe
    public String getAnimalSound(Animal animal) {
        if (animal instanceof Dog) {
            return "Гав-гав";
        } else if (animal instanceof Cat) {
            return "Мяу-Мяу";
        }
        return null;
    }
}
