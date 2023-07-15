import java.util.*;

public class TaxInspectionDatabase {
    private Map<String, List<String>> database;
    private Scanner scanner;

    public TaxInspectionDatabase() {
        database = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {

            displayMenu();
            System.out.print("Введіть номер опції: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    printDatabase();
                    break;
                }
                case 2: {
                    System.out.print("Введіть персональний код: ");
                    String personalId = scanner.nextLine();
                    printDataByPersonalId(personalId);
                    break;
                }
                case 3:{
                    System.out.print("Введіть тип штрафу: ");
                    String fineType=scanner.nextLine();
                    printDataByPersonalId(fineType);
                    break;
                }
                case 4:{
                    System.out.print("Введіть місто: ");
                    String city = scanner.nextLine();
                    printDataByCity(city);
                }
                case 5:{
                    System.out.print("Введіть персональний код: ");
                    String personalId = scanner.nextLine();
                    addPerson(personalId);
                    break;
                }
                case 6:{
                    System.out.print("Введіть персональний код: ");
                    String personalId = scanner.nextLine();
                    System.out.print("Введіть новий штраф: ");
                    String fine = scanner.nextLine();
                    addFine(personalId, fine);
                    break;
                }
                case 7:{


                    System.out.print("Введіть персональний код: ");
                    String personalId = scanner.nextLine();
                    System.out.print("Введіть штраф, який потрібно видалити: ");
                    String fine = scanner.nextLine();
                    deleteFine(personalId, fine);
                    break;
                }
                case 8:{
                    System.out.print("Введіть персональний код: ");
                    String personalId = scanner.nextLine();
                    System.out.print("Введіть новий персональний код: ");
                    String newPersonalId = scanner.nextLine();
                    updatePerson(personalId, newPersonalId);
                    break;
                }
                case 9:{
                    System.out.print("Введіть персональний код: ");
                    String personalId = scanner.nextLine();
                    System.out.print("Введіть штраф, який потрібно оновити: ");
                    String oldFine = scanner.nextLine();
                    System.out.print("Введіть новий штраф: ");
                    String newFine = scanner.nextLine();
                    updateFine(personalId, oldFine, newFine);
                    break;
                }
                case 0:{
                    System.out.println("Програма завершила роботу.");
                    break;
                }
                default:
                {
                    System.out.println("Введена неправильна опція. Спробуйте ще раз.");
                    break;
                }
            }
        } while (choice != 0);
    }

    public void printDatabase() {
        if (database.isEmpty()) {
            System.out.println("База даних порожня.");
        } else {
            for (Map.Entry<String, List<String>> entry : database.entrySet()) {
                String personalId = entry.getKey();
                List<String> fines = entry.getValue();
                System.out.println("Код: " + personalId);
                System.out.println("Штрафи: " + fines);
                System.out.println();
            }
        }
    }

    public void printDataByPersonalId(String personalId) {
        if (database.containsKey(personalId)) {
            List<String> fines = database.get(personalId);
            System.out.println("Код: " + personalId);
            System.out.println("Штрафи: " + fines);
        } else {
            System.out.println("Запис з кодом " + personalId + " не знайдено.");
        }

    }

    public void printDataByFineType(String fineType) {
        boolean found = false;
        for (Map.Entry<String, List<String>> entry : database.entrySet()) {
            String personalId = entry.getKey();
            List<String> fines = entry.getValue();
            for (String fine : fines) {
                if (fine.contains(fineType)) {
                    if (!found) {
                        System.out.println("Штрафи типу " + fineType + ":");
                        found = true;
                    }
                    System.out.println("Код: " + personalId);
                    System.out.println("Штраф: " + fine);
                    System.out.println();
                }
            }

        }
        if (!found) {
            System.out.println("Штрафи типу " + fineType + " не знайдено.");
        }
    }

    public void printDataByCity(String city) {
        boolean found = false;
        for (Map.Entry<String, List<String>> entry : database.entrySet()) {
            String personalId = entry.getKey();
            List<String> fines = entry.getValue();
            for (String fine : fines) {
                if (fine.contains(city)) {
                    if (!found) {
                        System.out.println("Штрафи у місті " + city + ":");
                        found = true;
                    }
                    System.out.println("Код: " + personalId);
                    System.out.println("Штраф: " + fine);
                    System.out.println();
                }
            }
        }
        if (!found) {
            System.out.println("Штрафи у місті " + city + " не знайдено.");
        }
    }

    public void addPerson(String personalId) {
        if (database.containsKey(personalId)) {
            System.out.println("Запис з кодом " + personalId + " вже існує.");
        } else {
            database.put(personalId, new ArrayList<>());
            System.out.println("Додано нову людину з кодом " + personalId + ".");
        }
    }

    public void addFine(String personalId, String fine) {
        if (database.containsKey(personalId)) {
            List<String> fines = database.get(personalId);
            fines.add(fine);
            System.out.println("Додано штраф для запису з кодом " + personalId + ".");
        } else {
            System.out.println("Запис з кодом " + personalId + " не знайдено.");
        }
    }

    public void deleteFine(String personalId, String fine) {
        if (database.containsKey(personalId)) {
            List<String> fines = database.get(personalId);
            if (fines.contains(fine)) {
                fines.remove(fine);
                System.out.println("Штраф видалено з запису з кодом " + personalId + ".");
            } else {
                System.out.println("Штраф не знайдено у записі з кодом " + personalId + ".");
            }
        } else {
            System.out.println("Запис з кодом " + personalId + " не знайдено.");
        }
    }

    public void updatePerson(String personalId, String newPersonalId) {
        if (database.containsKey(personalId)) {
            List<String> fines = database.remove(personalId);
            database.put(newPersonalId, fines);
            System.out.println("Інформацію про людину оновлено. Запис з кодом " + personalId + " замінено на запис з кодом " + newPersonalId + ".");
        } else {
            System.out.println("Запис з кодом " + personalId + " не знайдено.");
        }
    }

    public void updateFine(String personalId, String oldFine, String newFine) {
        if (database.containsKey(personalId)) {
            List<String> fines = database.get(personalId);
            if (fines.contains(oldFine)) {
                int index = fines.indexOf(oldFine);
                fines.set(index, newFine);
                System.out.println("Інформацію про штраф оновлено.");
            } else {
                System.out.println("Штраф не знайдено у записі з кодом " + personalId + ".");
            }
        } else {
            System.out.println("Запис з кодом " + personalId + " не знайдено.");
        }
    }

    private void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Повний друк бази даних");
        System.out.println("2. Роздрукування даних по конкретному коду");
        System.out.println("3. Роздрукування даних по конкретному типу штрафу");
        System.out.println("4. Роздрукування даних по конкретному місту");
        System.out.println("5. Додавання нової людини з інформацією про неї");
        System.out.println("6. Додавання нових штрафів для існуючого запису");
        System.out.println("7. Видалення штрафу");
        System.out.println("8. Заміна інформації про людину і її штрафи");
        System.out.println("0. Вихід з програми");
    }
}
