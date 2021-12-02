# api-weather

## ABM PARA MANEJO WEATHER

### Creación de Weather

Método: POST

URL: http://localhost:8080/weather

Request Ejemplo:
{
    "date": "2002-10-20",
    "lat": 36.1189,
    "lon": -86.6892,
    "city": "Moscu",
    "state": 1,
    "temperatures": [17.3, 16.8, 16.4, 16.0, 15.6, 15.4]
}


### Consulta de Weather

Método: GET

URL: http://localhost:8080/weather

Filtros opcionales: date (yyyy-MM-dd), city, sort (data o -data)

Ejemplos:

http://localhost:8080/weather?date=2021-04-04

http://localhost:8080/weather?city=Moscu

http://localhost:8080/weather?sort=data
