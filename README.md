# Kata WebFlux

## Requirements

#### POST for a new user
```
POST /users
{
    "name": "Max Mustermann" 
}
```

#### PUT for an existing user
```
PUT /users/<ID>
{
    "id":  <ID>,
    "name: "Max Power"
}
```
- respond with `BAD_REQUEST` if IDs do not match
- respond with `NOT_FOUND` if user with ID does not exist

#### DELETE for a user
```
DELETE /users/<ID>
```
- respond with `NOT_FOUND` if user with ID does not exist

#### GET for books
```
GET /books
```
Response
```
[
    {
        "id":    <ID>,
        "title": "Biene Maja"
    },
    {
        "id":    <ID>,
        "title": "Pippi Langstrumpf"
    }
]
```

#### POST to connect books with a user
```
POST /users/<User-ID>/book/<Book-ID>
```
- respond with `NOT_FOUND` if user or book does not exist
 
#### GET books of a user
but include user in response JSON
```
GET /users/<ID>/books
```
Response
```
{
    "user": {
        "id":   <ID>,
        "name": "Max Power"
    },
    "books": [
        {
            "id":    <ID>,
            "title": "Biene Maja",
            "since": "2020-03-01" 
        },
        {
            "id:     <ID>,
            "title": "Pippi Langstrumpf",
            "since": "2020-02-20"
        }
    ]
}
```