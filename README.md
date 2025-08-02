#  Delivery Partner Tracker

A backend REST API application built using **Spring Boot**, **JWT Authentication**, and **MySQL** for managing delivery partners, orders, customers, and restaurants—similar to Zomato or Swiggy backend systems.

---

##  Features

- 🔐 JWT-based Authentication and Role-based Authorization
- 🧾 User Registration and Login (Customer, Delivery Partner)
- 🍽️ Restaurant Management with Menu Items
- 🛒 Order Placement and Tracking
- 📦 Delivery Assignment and Status Updates
- 🧑‍💼 Admin Access to Swagger Docs
- 📘 Swagger UI API Documentation

---

## 📌 Tech Stack

- **Backend**: Java, Spring Boot, Spring Security
- **Database**: MySQL
- **Security**: JWT (JSON Web Tokens)
- **API Docs**: Swagger (OpenAPI v3)
- **Tooling**: Maven, Git

---

## 🔐 Roles

- **CUSTOMER**: Can view restaurants, place orders
- **DELIVERY**: Can accept and update delivery status
- **ADMIN**: Access to all data + Swagger documentation

---

## 🔧 Setup & Run

1. **Clone the repo**:
   ```bash
   git clone https://github.com/Shaad27s/delivery-partner-tracker.git
   cd delivery-partner-tracker
