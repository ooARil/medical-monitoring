package liga.medical.medicalmonitoring.core.antisolid;

// Interface Segregation
public class AntiI {

    // Интерфейс, который содержит
    // 3 метода способа создания
    // отчета: excel, pdf, docx
    private interface Report {

        void generateExcel();

        void generatePdf();

        void generateDocx();
    }

    // Клиент хочет создать отчет, но
    // ему приходиться реализовать все методы
    // способа создания отчета: pdf, docx, excel, -
    // даже если используется один способ создания
    private class Client implements Report {

        @Override
        public void generateExcel() {
            System.out.println("Отчет типа Excel");
        }

        @Override
        public void generatePdf() {
            System.out.println("Отчет типа PDF");
        }

        @Override
        public void generateDocx() {
            System.out.println("Отчет типа Docx");
        }
    }
}
