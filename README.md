### ExploreCountries
ExploreCountries is a mobile app that enables a user to explore basic information about all the countries in the world, including gaining access to such informations as the country's capital, population, official language, religion, timezone, ethnic groups, system of government, gdp, dialing code and lots more.
### App Description
* The the primary language used for building the app is Kotlin, with all the views designed in XML.
* The app is made up of 2 screens - The Home Screen that displays a scrollable list of all the countries, and a Details Screen that displays a detailed information about any country selected from the list of countries on the Home Screen
### Design
The app follows a UI design as provided in the link below
* <a href="https://www.figma.com/proto/v9AXj4VZNnx26fTthrPbhX/Explore?node-id=33%3A1390&scaling=scale-down&page-id=0%3A1&starting-point-node-id=33%3A1390&show-proto-sidebar=1">Figma Link to UI Design</a>
### Data Source
The data fetched in the app, including the list of all the countries and the details of each country were gotten from this <a href="https://restcountries.com/v3.1/all">REST API</a>
### Features
* Search Feature: To enable a user search through the list of available countries
* Automatic Dark/Light Mode - which follows system settings on user device
* Error Handling
### Wishlist Features
For the sake of time constraints, I have not been able to implement all the features I wish to add to this app. If I have more time, I should be able to implement the following features:
* Filtering of the list of countries by Region, Subregion, Capital, Currency & Language.
* More responsive search feature.
* Adding Unit & widget tests.
### Libraries
* <a href="https://square.github.io/retrofit/">RETROFIT</a>: A type-safe HTTP client for making HTTP calls. I used Retrofit for this application as it is quite simple and fast compared to Volley.
* <a href="https://github.com/google/gson">GSON</a>: Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. This was used to make it easier to For serialize requests and responses.
* <a href="https://github.com/bumptech/glide">GLIDE</a>: Glide is an Image Loader Library for Android developed by bumptech and is a library that is recommended by Google. I used this library for loading images within the app since Retrofit does not support image loading.
* GOOGLE <a href="https://m3.material.io/">MATERIAL DESIGN LIBRARY</a>: For design components, app customization and theming
### Challenges
* The first challenge I had of course was time constraints, for which if I had more time, would have been able to implement all the mouth-watering features to deliver a top-notch application.
* The second challenge was that of fetching needed data from the API, loading images using the Glide library, for which I had to brush up my knowledge with various learning resources, and consulting official documentations.
### Run the App
The app has been made available on Appetize.io to make it possible for you to demo the app on your web browser with just a click. To run the app, click on the link below.
* <a href="https://appetize.io/app/zfufkbxe5hnkubnntou3wqysgi">RUN APP DEMO NOW</a>
* OR <a href="https://drive.google.com/file/d/1Pdx3a86owNudf2J-8Y4Zl7W6X5xouxWV/view?usp=sharing">DOWNLOAD APK HERE</a>
