# Jweather Info

JWeather is weather app, has ability to get weather in current location along with 3 days forecast.

JWeather develop under custom mvvm architecture, called Catapult - MVVM 
where all data feed to view by EventObservable from Catapult

View is set in a form of component, where each component is design to be reusable

JWeather integrate Hilt for dependency injection and also integrate Room database to save favorite city.

Install debug version

```sh
./gradlew assembleDebug installDebug

```