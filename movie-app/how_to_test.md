#How to test
For auth request i use basic auth:
* login: user
* password: password

###Endpoints:
* *Check if movie won best picture oscar(no auth required):*  
GET localhost:8080/movies/best-picture/{title}
* *Save movie to lib and add rating(auth required)*  
POST http://localhost:8080/user/movies/rating  
RequestBody: { "title": "Slumdog","rating": "3" }
* *Get top ten rated movies ordered by box office(auth required)*
GET http://localhost:8080/user/movies/top-ten-rated
