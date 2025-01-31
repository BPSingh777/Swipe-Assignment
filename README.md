# Swipe-Assignment
# Android Product Listing App

## Overview
This Android application is built as part of an assignment for the Android Engineer Internship. The app consists of two main screens:

1. **Product Listing Screen** - Displays a list of products fetched from an API, allowing users to search and navigate to the Add Product screen.
2. **Add Product Screen** - Allows users to add a new product with necessary details, including product type, name, price, tax, and an optional image.

The app follows modern Android development best practices, including MVVM architecture, Retrofit for API calls, KOIN for dependency injection, and Kotlin Coroutines for lifecycle management.

## Features
### Product Listing Screen
- Fetches and displays a list of products from the API: `https://app.getswipe.in/api/public/get`
- Features a **search functionality** to filter products
- Displays images for each product, using a default image if none is provided
- Includes a progress indicator while data is loading
- Provides a button to navigate to the Add Product screen

### Add Product Screen
- Implemented as a **BottomSheetDialogFragment**
- Allows users to add a new product with:
  - Product type selection
  - Product name input
  - Selling price input (decimal validation)
  - Tax rate input (decimal validation)
  - Optional image selection (JPEG/PNG, 1:1 ratio)
- Performs form validation to ensure required fields are filled
- Uses Retrofit to send a **POST request** to `https://app.getswipe.in/api/public/add`
- Shows a progress indicator during submission and provides feedback via a dialog or notification

### Offline Functionality
- When the internet is unavailable, created products are saved locally
- Once a connection is restored, the products are automatically added to the system

## Tech Stack
- **Language:** Kotlin
- **Architecture:** MVVM
- **Networking:** Retrofit
- **Dependency Injection:** KOIN
- **UI Components:** Jetpack Navigation, RecyclerView, LiveData
- **Offline Storage:** Room Database / SharedPreferences (for offline support)

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/BPSingh777/Swipe-Assignment.git
   cd Swipe-Assignment
   ```
2. Open the project in Android Studio
3. Sync Gradle dependencies
4. Run the application on an emulator or a physical device

## API Endpoints
### Get Products
- **Method:** `GET`
- **URL:** `https://app.getswipe.in/api/public/get`
- **Response Format:**
  ```json
  [
    {
      "image": "https://example.com/image.png",
      "price": 100.0,
      "product_name": "Sample Product",
      "product_type": "Category",
      "tax": 18.0
    }
  ]
  ```

### Add Product
- **Method:** `POST`
- **URL:** `https://app.getswipe.in/api/public/add`
- **Body Type:** `form-data`
- **Parameters:**
  - `product_name` (text)
  - `product_type` (text)
  - `price` (text)
  - `tax` (text)
  - `files[]` (image, optional)
- **Response Format:**
  ```json
  {
    "message": "Product added Successfully!",
    "product_details": { ... },
    "product_id": 2657,
    "success": true
  }
  ```

## Contribution
Feel free to fork this repository and submit a pull request if you would like to contribute!

## Contact
For any inquiries or issues, please contact:
- **Bhanu Pratap Singh**
- Email: `pratapsinghbhanu404@gmail.com`
- GitHub: [BPSingh777](https://github.com/BPSingh777)
