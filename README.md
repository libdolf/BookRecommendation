# 📚 BookRecommendation
![MIT License](https://img.shields.io/badge/license-MIT-green)
![Java](https://img.shields.io/badge/java-22-blue)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.3.1-brightgreen)
![Maven](https://img.shields.io/badge/maven-3.9.8-blue)

Welcome to **BookRecommendation**! This project was created to study Spring and demonstrate my skills by consuming the Google Books API and returning information about books. The application supports searching for books by ID, title, and providing recommendations based on book ID.

## 🌟 Features

- **Search by ID**: Retrieve detailed information about a specific book.
- **Search by Title**: Get a list of books that match a given title.
- **Recommendations**: Get book recommendations based on authors and categories of a given book.

## 🔥 Endpoints

All endpoints are HTTP GET requests.

### Get Book by ID

- **Endpoint**: `/v1/book/{id}`
- **Description**: Fetch a book by its ID.
- **Response**:
    ```json
    {
      "bookId": "string",
      "title": "string",
      "authors": [
        "string"
      ],
      "publisher": "string",
      "publishedDate": "string",
      "description": "string",
      "pageCount": 0,
      "categories": [
        "string"
      ]
    }
    ```

### Get Books by Title

- **Endpoint**: `/v1/book?title={title}`
- **Description**: Fetch a list of books containing the specified title.
- **Response**:
    ```json
    [
      {
        "bookId": "string",
        "title": "string",
        "authors": [
          "string"
        ],
        "publisher": "string",
        "publishedDate": "string",
        "description": "string",
        "pageCount": 0,
        "categories": [
          "string"
        ]
      }
    ]
    ```

### Get Recommendations by Book ID

- **Endpoint**: `/v1/recommendations/{id}`
- **Description**: Fetch a list of recommended books based on the authors and categories of the given book.
- **Response**:
    ```json
    [
      {
        "bookId": "string",
        "title": "string",
        "authors": [
          "string"
        ],
        "publisher": "string",
        "publishedDate": "string",
        "description": "string",
        "pageCount": 0,
        "categories": [
          "string"
        ]
      }
    ]
    ```

## 🚀 Getting Started

### Prerequisites

- Java 22
- Maven

### Running the Project

1. **Clone the repository**:
    ```sh
    git clone https://github.com/libdolf/BookRecommendation.git
    cd BookRecommendation
    ```

2. **Build the project**:
    ```sh
    mvn clean install
    ```

3. **Run the project**:
    ```sh
    mvn spring-boot:run
    ```

4. **Access Swagger Documentation**:
   Open your browser and navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to explore the API endpoints.

## 🛠️ Technologies Used

- **Java**
- **Spring Boot**
- **Maven**
- **Swagger** for API documentation

## 🤝 Contributing

Feel free to fork this project, make improvements, and submit pull requests. Any contributions are greatly appreciated!

## 📄 License

This project is licensed under the MIT License.

---

Enjoy exploring the world of books with **BookRecommendation**! 📖✨
