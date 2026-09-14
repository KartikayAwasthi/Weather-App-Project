# Weather App

A Java web application (Servlet-based) that shows a weather forecast — built with the classic Java EE stack (Servlets + JSP).

## Tech stack

- Java Servlets (`Servlet1.java`) + JSP
- Deployed as a standard Java web app (`WEB-INF/web.xml`, `webapp/` structure)

## Project structure

```
MyWatherApp/
  src/main/java/com/WeatherApp/
    Servlet1.java        Handles weather requests
  src/main/webapp/
    index.html, index.jsp   Entry pages
    WEB-INF/web.xml           Servlet/deployment config
    images/weather-logo.png   App logo
```

## Running

Build and deploy as a WAR to a servlet container (e.g. Apache Tomcat) — import into your IDE (Eclipse/NetBeans) as a Dynamic Web Project, or package with Ant/the IDE's build tooling and drop the resulting WAR into Tomcat's `webapps/` folder.
