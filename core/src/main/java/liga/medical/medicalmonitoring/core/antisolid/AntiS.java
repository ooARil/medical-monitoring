package liga.medical.medicalmonitoring.core.antisolid;

// Single Responsibility

// Содержит информацию о сотруднике,
// чья профессия (departmentEmployee) -
// Водитель, или Маляр, или Пожарный, или Врач
public class AntiS {

    private String nameEmployee;
    private String surnameEmployee;
    private String departmentEmployee;

    // Метод, который относиться к departmentEmployee = "Водитель"
    void toDrive() {
        System.out.println("Водитель водит машину");
    }

    // Метод, который относиться к departmentEmployee = "Маляр"
    void toPaintingTheWalls() {
        System.out.println("Маляр красит стены");
    }

    // Метод, который относиться к departmentEmployee = "Пожарный"
    void toExtinguishTheFire() {
        System.out.println("Пожарный тушит пожар");
    }

    // Метод, который относиться к departmentEmployee = "Врач"
    void toHealPeople() {
        System.out.println("Врач лечит пациентов");
    }
}

