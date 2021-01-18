# Shopping List Application

This small app exposes several REST endpoints to allow to:

* add user, delete user
* start a shopping list for a user, delete a shopping list
* add an item to a list, remove an item, replace an item

The below examples of use assume that the app is deployed under http://localhost:8080/logmein/

## Add user 'bogdan'
curl -X POST -d '' http://localhost:8080/logmein/shoplist/user/bogdan/

## Get all shopping lists for user 'bogdan'
curl -G http://localhost:8080/logmein/shoplist/bogdan/

## Add 2 shopping lists - 99, 43
curl -X POST -d '' http://localhost:8080/logmein/shoplist/list/bogdan/99/
curl -X POST -d '' http://localhost:8080/logmein/shoplist/list/bogdan/43/

## Get one shopping list for user 'bogdan' - by ID
curl -G http://localhost:8080/logmein/shoplist/bogdan/43

## Add two items to list 43 (w/o price)
curl -X POST -H 'Content-Type: application/json' -d '{"name": "apple"}' http://localhost:8080/logmein/shoplist/item/bogdan/43/
curl -X POST -H 'Content-Type: application/json' -d '{"name": "vodka"}' http://localhost:8080/logmein/shoplist/item/bogdan/43/

## Add one item to list 99 (w/ price)
curl -X POST -H 'Content-Type: application/json' -d '{"name": "pear", "price": 1.75}' http://localhost:8080/logmein/shoplist/item/bogdan/99/

## Add one item to list 99 (w/ price and quantity)
curl -X POST -H 'Content-Type: application/json' -d '{"name": "pilsner urquell", "price": 0.80, "quantity": 3}' http://localhost:8080/logmein/shoplist/item/bogdan/99/

## Add one item to list 99 (with name already existing in the list) - should update quantity
curl -X POST -H 'Content-Type: application/json' -d '{"name": "pear"}' http://localhost:8080/logmein/shoplist/item/bogdan/99/

## Replace one item in list 43 - use the itemID returned in one of the previous steps for list 43
curl -X PUT -H 'Content-Type: application/json' -d '{"name": "rum", "price": 10.30}' http://localhost:8080/logmein/shoplist/item/bogdan/43/3/

## Delete one item from list 43 - use the itemID returned in one of the previous steps for list 43
curl -X DELETE -H 'Content-Type: application/json' http://localhost:8080/logmein/shoplist/item/bogdan/43/3/

## Delete one list - by ID
curl -X DELETE -H 'Content-Type: application/json' http://localhost:8080/logmein/shoplist/list/bogdan/43/

## Delete user
curl -X DELETE -H 'Content-Type: application/json' http://localhost:8080/logmein/shoplist/user/bogdan/

