# Дипломная работа «Облачное хранилище»
## Описание
Проект предоставляет собой REST сервис для загрузки, скачивания, переименования, удаления и вывода списка загруженных файлов пользователя.
Все запросы к сервису авторизованы.
Веб приложение (FRONT) подключается к сервису и использует его функционал для управления файлами.
## Запуск FRONT приложения
Для запуска FRONT приложения необходимо:
1. Установите nodejs (версия не ниже 19.7.0) на компьютер.
2. Скачайте FRONT (JavaScript).
3. Перейти в папку FRONT приложения в командной строке и выполнить команды `npm install` затем `npm run serve`
4. Открыть в браузере страницу по адресу http://localhost:8080/

FRONT отправляет запросы по адресу http://localhost:7070/ на котором стартует REST приложение.
## Запуск REST приложения
Для запуска приложения используются команды Docker
1. Скачать или склонировать репозиторий приложения в программу IntelliJ IDEA
2. Упаковать проект в Maven
3. В терминале приложения командой `docker build -t restapp .` создать образ приложения
4. Упаковать образ в контейнер командой `docker-compose build`
5. Запустить контейнер командой `docker-compose up`

## Пример использования
В приложении можно авторизоваться используя данные: имя пользователя - `user`, пароль `password`

## Взаимодействие приложений
FRONT приложение отправляет credentials в теле запроса на эндпоинт /login BACKEND приложения.
Запрос проходит через CORS filter, CSRF filter, перехватывается JwtRequestFilter и передается далее на аутентификацию.
В случае успешной аутентификации генерируется токен, которые возвращается на FRONT в ответе с заголовком `auth-token`
Все дальнешие запросы FRONT делает с этим токеном в заголовке `auth-token`.
При выходе из приложения вызывается метод BACKEND /logout, который удаляет токен из TokenStore и все дальнейшие запросы с этим токеном не действительны.

## Ссылка на Frontend
https://github.com/netology-code/jd-homeworks/tree/8f07696982d452908148330456fda39abf01b0cf/diploma/netology-diplom-frontend

