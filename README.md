

# **Post Management API**

This is a Spring Boot RESTful API for managing posts, likes, and replies. The API allows users to create posts, like/unlike posts, and reply to posts.

## **Note**
This project is actually a Nodejs-Express project. I converted the project to a Spring boot project. Here is the <a href="https://github.com/Doguhannilt/MediaPulse-backend-Nodejs.git">link to the actual project.</a>

## **Technologies Used**

- **Java 17**
- **Spring Boot 3.x**
- **MongoDB**
- **Maven**

---

## **Endpoints**

### **1. Create a Post**

**Endpoint**: `POST /posts`

**Description**: Create a new post.

**Request Body**:

```json
{
  "postedBy": "66d81c0d76e99961907e7304",
  "text": "This is a new post",
  "img": "https://example.com/image.jpg",
  "likes": [],
  "replies": []
}
```

**Response**:
- `200 OK`: Post created successfully.
- `400 Bad Request`: Validation error.

---

### **2. Like/Unlike a Post**

**Endpoint**: `PUT /posts/{id}/like`

**Description**: Like or unlike a post based on the user's previous action.

**Path Parameters**:
- `id`: Post ID.

**Response**:
- `200 OK`: Successfully liked/unliked the post.
- `400 Bad Request`: Post not found or other validation issues.

---

### **3. Reply to a Post**

**Endpoint**: `POST /posts/{id}/reply`

**Description**: Add a reply to a post.

**Request Body**:

```json
{
  "text": "Nice post!"
}
```

**Path Parameters**:
- `id`: Post ID.

**Response**:
- `200 OK`: Reply posted successfully.
- `400 Bad Request`: Validation error (e.g., text too short).
- `500 Internal Server Error`: An issue occurred during reply creation.

---

## **Directory Structure**

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.mediapulse.springboot
│   │   │       ├── controllers
│   │   │       │   └── PostController.java
│   │   │       ├── models
│   │   │       │   ├── Post.java
│   │   │       │   ├── Reply.java
│   │   │       │   └── User.java
│   │   │       ├── repositories
│   │   │       │   ├── PostRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       └── services
│   │   │           └── PostService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
```

---

## **Models**

### **Post**

- **`id`**: Unique ID of the post.
- **`postedBy`**: Reference to the `User` who created the post.
- **`text`**: Text content of the post.
- **`img`**: Image URL associated with the post.
- **`likes`**: List of `User` references who liked the post.
- **`replies`**: List of `Reply` objects associated with the post.

### **Reply**

- **`id`**: Unique ID of the reply.
- **`userId`**: Reference to the `User` who posted the reply.
- **`text`**: Text content of the reply.
- **`userProfilePic`**: Profile picture URL of the user.
- **`username`**: Username of the user.

### **User**

- **`id`**: Unique ID of the user.
- **`name`**: Full name of the user.
- **`username`**: Username.
- **`email`**: User's email.
- **`password`**: Hashed password.
- **`profilePic`**: URL of the profile picture.
- **`followers`**: List of users following this user.
- **`following`**: List of users this user is following.

---

## **How to Run**

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-repository.git
    cd your-repository
    ```

2. **Configure MongoDB**:
   - Update your MongoDB connection in `application.properties`:
     ```properties
     spring.data.mongodb.uri=mongodb://localhost:27017/yourDatabase
     ```

3. **Build and run the application**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Access the API** at:
    ```
    http://localhost:8080
    ```

---

## **Error Handling**

- **400 Bad Request**: Invalid input (e.g., missing required fields or invalid ID).
- **500 Internal Server Error**: Generic error, usually due to a server-side issue during execution.

---

## **Future Enhancements**

- **Authentication**: Use JWT tokens for authentication.


## **License**

This project is licensed under the MIT License.



