# Контакты

## Описание

Веб-приложение "Контакты" - это удобный способ управления вашими контактными данными. С его помощью вы можете легко добавлять, просматривать, изменять и удалять информацию о контактах.

## Функциональность

- **Добавление контактов**: Создайте новый контакт, заполнив необходимые поля.
- **Просмотр контактов**: Просматривайте все сохраненные контакты в удобном интерфейсе.
- **Редактирование контактов**: Обновляйте информацию о контактах по мере необходимости.
- **Удаление контактов**: Удаляйте контакты, которые больше не актуальны.

## Технологии
- Язык: Java
- Библиотеки: spring-boot-starter(web,thymeleaf,data-jdbc)
- База данных: Docker compose container, Postgres 12.3

## Начало работы

Чтобы начать работу с приложением, клонируйте репозиторий и установите зависимости:

```bash
https://github.com/Bunchiek/list-contacts-web.git
```
Запустите контейнер с базой данных командой:
```bash
docker compose up
```
Запустите приложение, приложение запуститься по адресу http://localhost:8080/

## Использование 
- При нажатии кнопки "Create Contact", вызовется форма для добавления нового контакта.
- После добавления контакт будет отображаться списком на главной странице
- Чтобы отредактировать поля контакта, нажмите кнопу "Edit". Чтобы удалить, нажмите "Delete".