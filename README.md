# MIFI_Testing_Final

Проект для автоматизированного тестирования Wikipedia на двух платформах:
- **Веб-версия** (Selenium WebDriver)
- **Мобильное приложение Android** (Appium)

## Требования

### Системные требования
- Windows 10/11
- Java 23 или выше
- Maven 3.6 или выше
- Google Chrome (последняя версия)

### Для мобильного тестирования
- Appium 2.x
- Android SDK
- Android Emulator или физическое устройство
- Установленное приложение Wikipedia на устройстве

## Установка и настройка

### 1. Установка Java
1. Скачайте JDK 23 с [официального сайта Oracle](https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html)
2. Установите JDK
3. Настройте переменные окружения:
    - `JAVA_HOME` = путь к установленному JDK
    - Добавьте `%JAVA_HOME%\bin` в `PATH`

### 2. Установка Maven
1. Скачайте Maven с [официального сайта](https://maven.apache.org/download.cgi)
2. Распакуйте архив
3. Настройте переменные окружения:
    - `MAVEN_HOME` = путь к распакованному Maven
    - Добавьте `%MAVEN_HOME%\bin` в `PATH`

### 3. Установка Appium и драйверов
1. Установите Node.js с [официального сайта](https://nodejs.org/)
2. Установите Appium через npm:
   ```bash
   npm install -g appium
   ```
3. Установите драйвер UiAutomator2:
   ```bash
   appium driver install uiautomator2
   ```
4. Установите Appium Doctor для проверки окружения:
   ```bash
   npm install -g @appium/doctor
   appium-doctor --android
   ```

### 4. Настройка Android окружения
1. Установите [Android Studio](https://developer.android.com/studio)
2. Настройте переменные окружения:
    - `ANDROID_HOME` = путь к Android SDK (обычно `C:\Users\%USERNAME%\AppData\Local\Android\Sdk`)
    - Добавьте в `PATH`:
        - `%ANDROID_HOME%\tools`
        - `%ANDROID_HOME%\platform-tools`
        - `%ANDROID_HOME%\emulator`
3. Создайте эмулятор или подключите физическое устройство
4. Установите приложение Wikipedia на устройство

### 5. Клонирование и сборка проекта
1. Клонируйте репозиторий проекта
2. Перейдите в директорию проекта:
   ```bash
   cd MIFI_Testing_Final
   ```
3. Соберите проект с помощью Maven:
   ```bash
   mvn clean compile
   ```

## Запуск тестов

### Запуск веб-тестов
```bash
mvn test -Dtest=WikipediaWebTest
```

### Запуск мобильных тестов
1. Запустите Appium сервер:
   ```bash
   appium
   ```
2. Запустите Android эмулятор или подключите устройство
3. В отдельном терминале запустите тесты:
   ```bash
   mvn test -Dtest=WikipediaAndroidTests
   ```

### Запуск всех тестов
```bash
mvn test
```

## Структура проекта

```
MIFI_Testing_Final/
├── pom.xml                     # Конфигурация Maven и зависимости
├── src/main/java/
│   └── fun/justdevelops/
│       ├── driver/
│       │   └── TestDriverFactory.java  # Фабрика для создания драйверов
│       └── pages/
│           ├── WikipediaAndroidPage.java  # Page Object для Android приложения
│           └── WikipediaWebPage.java      # Page Object для веб-версии
├── src/test/java/
│   └── fun/justdevelops/
│       ├── android/
│       │   └── WikipediaAndroidTests.java  # Тесты для Android
│       └── web/
│           └── WikipediaWebTest.java       # Тесты для веб-версии
└── README.md                    # Документация
```

## Конфигурация

### Настройка Android тестов
В файле `TestDriverFactory.java` настройте параметры для вашего устройства:

```java
options.setDeviceName("Pixel7pro");  // Замените на имя вашего устройства/эмулятора
options.setAppPackage("org.wikipedia");
options.setAppActivity("org.wikipedia.main.MainActivity");
```

### Настройка URL Appium
По умолчанию используется `http://127.0.0.1:4723`. При необходимости измените в `TestDriverFactory.java`.

## Устранение неполадок

### Проблемы с Android тестами
1. **Устройство не найдено**: Проверьте, что устройство подключено и видно через `adb devices`
2. **AppPackage не найден**: Убедитесь, что приложение Wikipedia установлено
3. **Сервер Appium не отвечает**: Убедитесь, что Appium запущен на порту 4723

### Проблемы с веб-тестами
1. **ChromeDriver несовместим**: WebDriverManager автоматически загрузит правильную версию
2. **Сайт не доступен**: Проверьте подключение к интернету

## Зависимости проекта

- Selenium WebDriver 4.10.0
- Appium Java Client 8.5.1
- TestNG 7.9.0
- WebDriverManager 5.7.0

## Дополнительная информация

- **Логирование**: Все действия логируются в консоль
- **Таймауты**: Неявные ожидания установлены на 5 секунд
- **Параллельный запуск**: Тесты могут быть настроены для параллельного выполнения
