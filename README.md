#  TestTaskWebApp: Read-Write in DB (Spring Boot App)

Веб-приложение для чтения и записи данных в БД при помощи GET и POST запросов.

## Инструкция по запуску приложения

- В командной строке запустить команду "git clone https://github.com/immagixe/TestTaskWebApp"
- После клонирования в папке TestTaskWebApp запустить скрипт **mvnw**
- Перейти в папку target и запустить приложение при помощи команды "java -jar TestTaskWebApp-1.0.0-SNAPSHOT.jar"


## Описание веб-сервиса

-  Для запуска необходимо установить [Java SE - 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Страница Swagger доступна по адресу http://localhost:8080/swagger-ui/
- В папке controllers находится REST controller (**MainController**)
- FlyWay миграция (создание таблицы **People**) лежит в папке resources/db.migration
- Данные GET и POST запросов валидируются с помощью аннотаций
- Работа валидатора POST запросов покрыта Unit тестами в классе **PersonTests**
- Для простого и быстрого запуска с минимальными усилиями выбрана in-memory DB H2
- Для избавления от большого количества геттеров и сеттеров была использована библиотека Lombok
