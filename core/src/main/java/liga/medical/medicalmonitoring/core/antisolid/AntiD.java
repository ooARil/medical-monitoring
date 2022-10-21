package liga.medical.medicalmonitoring.core.antisolid;

// Dependency Inversion
public class AntiD {

    // Данный класс зависит
    // на прямую от класс DAO,
    // а если был бы реализован
    // принцип Dependency Inversion,
    // то он должен был зависить от абстракции
    // (к примеру: интерфейс), которую
    // реализует класс DAO
    private class Service {

        private DAO dao;

        Service() {
            dao = new DAO();
        }
    }

    private class DAO {
    }
}