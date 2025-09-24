# Cinema Tickets API

## Описание
REST API для управления фильмами, сеансами, местами и бронированиями билетов в кинотеатре.

## Стек
- Java 17
- Spring Boot
- Maven
- Lombok
- PostgreSQL (или H2 для разработки)
- Docker (опционально)

## Структура ресурсов
- Movies (Фильмы)
- Sessions (Сеансы)
- Seats (Места для сеансов)
- Bookings / Tickets (Бронирования)

## Запуск локально
```bash
mvn clean install
mvn spring-boot:run

```

## Приложение будет доступно по адресу
http://localhost:8080



## Примеры запросов
### Админ
- Создать фильм
```http
POST /api/admin/movies
Content-Type: application/json
{
  "title": "Inception",
  "description": "A mind-bending thriller",
  "duration": 148
}
```
- Создать сеанс
```http
POST /api/admin/sessions
Content-Type: application/json
{
  "movieId": 1,
  "hallId": 1,
  "startTime": "2023-10-01T19:00:00",
  "price": 10
}   

```
- Добавить зал
```http
POST /api/admin/halls
Content-Type: application/json
{
  "name": "Main Hall",
  "rows": 10,
  "seatsPerRow": 15
}
```


### Пользователь

- Получить список фильмов
```http
GET /api/sessions/all
```
- Получить сеансы по фильму
```http
GET /api/sessions/movie/{movieId}
```
- Получить ceансы по залу
```http
GET /api/sessions/hall/{hallId}
```
- Получить cеанс
- ```http
GET /api/sessions/{sessionId}
```
- Забронировать билет
```http
POST /api/bookings
Content-Type: application/json
{
  "sessionId": 1,
  "seatId": 5,
  "customerName": "John Doe",
  "customerEmail": "email @example.com"
}   
  
```
- Получить билеты пользователя
```http
GET /api/bookings/{ticketId} 
```