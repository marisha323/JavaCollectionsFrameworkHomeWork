import java.util.*;

public class DictionaryApp {
    private Map<String, Set<String>> dictionary;
    private Map<String, Integer> popularity;

    public DictionaryApp() {
        dictionary = new HashMap<>();
        popularity = new HashMap<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Введіть номер опції: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    addTranslation(scanner);
                    break;
                }
                case 2: {
                    showTranslations(scanner);
                    break;
                }
                case 3: {
                    addWord(scanner);
                    break;
                }
                case 4: {
                    updateWord(scanner);
                    break;
                }
                case 5: {
                    deleteWord(scanner);
                    break;
                }
                case 6: {
                    showTopPopularWords();
                    break;
                }
                case 7: {
                    showTopUnpopularWords();
                    break;
                }
                case 0: {
                    System.out.println("Програма завершується.");
                    break;
                }
                default: {
                    System.out.println("Недійсний вибір. Введіть номер опції зі списку.");
                    break;
                }
            }
            System.out.println();
        } while (choice != 0);
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Додати переклад слова");
        System.out.println("2. Відобразити переклади слова");
        System.out.println("3. Додати слово");
        System.out.println("4. Замінити переклади слова");
        System.out.println("5. Видалити слово");
        System.out.println("6. Відобразити топ-10 популярних слів");
        System.out.println("7. Відобразити топ-10 непопулярних слів");
        System.out.println("0. Вийти");
    }

    private void addTranslation(Scanner scanner) {
        System.out.print("Введіть слово: ");
        String word = scanner.nextLine().toLowerCase();
        System.out.print("Введіть переклади (через кому): ");
        String translationsInput = scanner.nextLine().toLowerCase();
        String[] translationsArray = translationsInput.split(",");
        Set<String> translations = new HashSet<>(Arrays.asList(translationsArray));
        if (dictionary.containsKey(word)) {
            Set<String> existingTranslations = dictionary.get(word);
            existingTranslations.addAll(translations);
            dictionary.put(word, existingTranslations);
        } else {
            dictionary.put(word, translations);
        }
        System.out.println("Переклади слова " + word + " додано.");
    }

    private void showTranslations(Scanner scanner) {
        System.out.print("Введіть слово: ");
        String word = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(word)) {
            Set<String> translations = dictionary.get(word);
            System.out.println("Переклади слова " + word + ":");
            for (String translation : translations) {
                System.out.println(translation);
            }
        } else {
            System.out.println("Слово " + word + " не знайдено.");
        }
    }

    private void addWord(Scanner scanner) {
        System.out.print("Введіть слово: ");
        String word = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(word)) {
            System.out.println("Слово " + word + " вже існує.");
        } else {
            dictionary.put(word, new HashSet<>());
            System.out.println("Слово " + word + " додано.");
        }
    }

    private void updateWord(Scanner scanner) {
        System.out.print("Введіть слово: ");
        String word = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(word)) {
            System.out.print("Введіть нові переклади (через кому): ");
            String translationsInput = scanner.nextLine().toLowerCase();
            String[] translationsArray = translationsInput.split(",");
            Set<String> translations = new HashSet<>(Arrays.asList(translationsArray));

            dictionary.put(word, translations);
            System.out.println("Переклади слова " + word + " змінено.");
        } else {
            System.out.println("Слово " + word + " не знайдено.");
        }
    }

    private void deleteWord(Scanner scanner) {
        System.out.print("Введіть слово: ");
        String word = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
            System.out.println("Слово " + word + " видалено.");
        } else {
            System.out.println("Слово " + word + " не знайдено.");
        }
    }

    private void showTopPopularWords() {
        List<Map.Entry<String, Integer>> popularWordsList = new ArrayList<>(popularity.entrySet());
        popularWordsList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<String> popularWords = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry : popularWordsList) {
            popularWords.add(entry.getKey());
            count++;
            if (count >= 10) {
                break;
            }
        }
        System.out.println("Топ-10 популярних слів:");
        for (String word : popularWords) {
            System.out.println(word);
        }
    }

    private void showTopUnpopularWords() {
        List<Map.Entry<String, Integer>> unpopularWordsList = new ArrayList<>(popularity.entrySet());
        unpopularWordsList.sort(Map.Entry.comparingByValue());
        List<String> unpopularWords = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry : unpopularWordsList) {
            unpopularWords.add(entry.getKey());
            count++;
            if (count >= 10) {
                break;
            }
        }
        System.out.println("Топ-10 непопулярних слів:");
        for (String word : unpopularWords) {
            System.out.println(word);
        }
    }
}
