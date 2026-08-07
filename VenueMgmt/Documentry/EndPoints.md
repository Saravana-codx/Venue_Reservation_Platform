# 🏛️ VenueReserve API Reference

> **Base URL:** `http://localhost:8080/api`  
> **Alternative IP:** `http://127.0.0.1:8080/api`

---

## 🔐 1. Authentication Endpoints

### 🟢 `POST /auth/login`
Authenticates user credentials and generates a stateless JWT token.

* **URL:** `http://localhost:8080/api/auth/login`
* **Auth Required:** No

#### Headers
```http
Content-Type: application/json
{
  "email": "user@example.com",
  "password": "yourpassword123"
}

🟢 POST /bookings
Reserves a venue hall for specific dates. Triggers JPQL overlapping-range check to prevent double-booking.

URL: http://localhost:8080/api/bookings

Auth Required: Yes (Bearer <JWT_TOKEN>)
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN_HERE>
{
  "hallId": 1,
  "checkInDate": "2026-09-01",
  "checkOutDate": "2026-09-05"
}
Response
{
  "id": 101,
  "hallId": 1,
  "checkInDate": "2026-09-01",
  "checkOutDate": "2026-09-05",
  "totalPrice": 1200.0,
  "bookingStatus": "CONFIRMED"
}
if Conflict
{
  "message": "Selected dates are already reserved for this hall."
}

🔵 GET /bookings/my-bookings
Fetches all active and historical bookings belonging to the authenticated user.

URL: http://localhost:8080/api/bookings/my-bookings

Auth Required: Yes (Bearer <JWT_TOKEN>)
Authorization: Bearer <YOUR_JWT_TOKEN_HERE>
Response
[
  {
    "id": 101,
    "hallId": 1,
    "checkInDate": "2026-09-01",
    "checkOutDate": "2026-09-05",
    "totalPrice": 1200.0,
    "bookingStatus": "CONFIRMED"
  }
]

🔵 GET /halls
Retrieves a list of available venue halls, capacities, and pricing.

URL: http://localhost:8080/api/halls

Auth Required: No

Response
[
  {
    "id": 1,
    "name": "Grand Ballroom",
    "capacity": 500,
    "pricePerDay": 300.0
  }
]