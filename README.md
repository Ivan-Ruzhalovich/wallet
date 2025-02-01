<h1 align="center">Hi there, I'm <a>Ruzhalovich Ivan</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

<h3 align="center">Приложение wallet, которое по REST принимает запрос вида:</h3>

<h3 align="center">POST api/v1/wallet { valletId: UUID, operationType: DEPOSIT or WITHDRAW, amount: 1000 }</h3>

<h3 align="center">После выполнятся логика по изменению счета в базе данных, также есть возможность получить баланс кошелька : GET api/v1/wallets/{WALLET_UUID}</h3>

<h3 align="center">Стек:java 17 | Spring 3 | Postgresql</h3>

<h3 align="center">Написаны миграции для базы данных с помощью liquibase.</h3>

<h3 align="center">Особое внимание уделено проблемам при работе в конкурентной среде (1000 RPS по одному кошельку). Ни один запрос не будет не обработан (50Х error).</h3>

<h3 align="center">Предусмотрено соблюдение формата ответа для заведомо неверных запросов, когда кошелька не существует, не валидный json, или недостаточно средств.</h3>

<h3 align="center">Приложение запускается в докер контейнере, база данных тоже, вся система поднимается с помощью docker-compose.</h3>

<h3 align="center">Эндпоинты покрыты тестами.</h3>
