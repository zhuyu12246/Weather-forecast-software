[English](README.en.md) | [中文](README.md)

# Weather App

This is a simple weather application that allows users to input a city name to retrieve weather information. The app uses Android's RecyclerView to display weather forecast data and fetches weather information from a server via HTTP requests.

## Features

- Input a city name to retrieve weather information
- Display detailed weather forecasts, including temperature, weather conditions, etc.
- Uses RecyclerView to display list data
- Includes an "About" page

## Installation Steps

1. Ensure you have Android Studio installed.
2. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/zhuyu12246/Weather-forecast-software.git
   ```
3. Open Android Studio, select "Open an existing Android Studio project", then choose the cloned project folder.
4. Sync the Gradle project.
5. Connect your Android device or launch an emulator.
6. Click the "Run" button to build and run the app.

## Usage Instructions

1. After opening the app, enter a city name in the input field.
2. Click the "Search" button to retrieve and display the weather information for that city.
3. Click the "About" button to view the app's about information.

## Project Structure

- `MainActivity.java`: Main interface handling user input and displaying weather information.
- `MyHttpConnection.java`: Handles HTTP requests to fetch weather data.
- `CustomAdapter.java`: RecyclerView adapter for binding weather data.
- `ForeCastClass.java`: Model class for weather data.
- `Item.java`: Data model class for list items.
- `AboutActivity.java`: About page.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
