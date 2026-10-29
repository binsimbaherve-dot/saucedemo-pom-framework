# SauceDemo POM Framework

A UI test automation framework for [saucedemo.com](https://www.saucedemo.com), a public practice shop, built to demonstrate the Page Object Model with Selenium WebDriver, Java, TestNG and Maven.

## Tech stack
Java 17 · Selenium WebDriver 4 · TestNG · Maven · GitHub Actions

## Project structure
```
src/test/java/com/herve/saucedemo/
├── base/
│   ├── BasePage.java      Shared waits and actions for all pages
│   └── BaseTest.java      Browser setup and teardown for every test
├── pages/                 One class per page: locators + page actions
│   ├── LoginPage.java
│   ├── InventoryPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
├── tests/                 Test classes: only call page methods and assert
│   ├── LoginTest.java
│   ├── CartTest.java
│   └── CheckoutTest.java
└── utils/
    └── ConfigReader.java  Reads config.properties, overridable with -D flags
src/test/resources/
├── config.properties      URL, browser, timeouts, test users
└── testng.xml             Smoke and regression suites
```

## Design decisions
- **Page Object Model:** locators live only in page classes, so a UI change is fixed in one place.
- **Fluent page methods:** each action returns the next page, so tests read like a user journey.
- **Explicit waits only:** `BasePage` waits for elements to be visible or clickable. No `Thread.sleep`.
- **Independent tests:** a fresh browser for every test, so one failure can't break the next.
- **Data-driven negative tests:** a TestNG `@DataProvider` covers several invalid logins with one test.
- **Config outside the code:** URLs, users and timeouts live in `config.properties`.

## Running the tests
```bash
mvn test                    # runs the full suite with a visible browser
mvn test -Dheadless=true    # runs headless, as in CI
```
Selenium Manager downloads the matching ChromeDriver automatically. You only need Chrome installed.

## CI
Every push to `main` runs the suite in GitHub Actions with headless Chrome and uploads the TestNG reports.
