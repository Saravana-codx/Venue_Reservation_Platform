👥 Roles
1. Admin

System administrator.

Can:

Manage users
View all venues
View all bookings
Suspend users
View reports
2. Owner

Can:

Login
View dashboard
Manage their hall
Add/Edit hall details
Add hall images
Manage amenities
View bookings
View revenue
View payments
Add one manager

Cannot:

Access other halls
3. Manager

Can:

Login
View bookings
Create walk-in bookings
Check availability
Record advance payment
Record remaining payment
Cancel bookings
Update booking status

Cannot:

Change pricing
Edit hall details
Add managers
View business reports
4. Customer

Can:

Register
Login
Search halls
View hall
Check availability
Book hall
Pay advance
Pay remaining amount
View booking history
Download receipt
Review hall
📋 Business Rules
Hall
One Owner → One Hall
One Hall → One Manager
One Hall → Many Customers
One Hall → Many Bookings
One Hall → Many Images
One Hall → Many Reviews
Customer
One Customer → Many Bookings
One Customer → Many Reviews
Booking
Customer selects a date.
System checks availability.
If available, booking proceeds.
Customer pays the advance.
Booking is instantly confirmed.
Remaining payment is collected later.
After the event, booking is marked completed.
Payments

Support:

Advance payment
Remaining payment

Payment status:

NOT_PAID
PARTIALLY_PAID
PAID
REFUNDED
Booking Status
PENDING
CONFIRMED
COMPLETED
CANCELLED