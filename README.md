# Introduction #

A sample spider application to crawl SLI-demo pages.

# Key Technology #
* Java
* Selenium
* Jsoup
* HttpClient
* Maven
* Eclipse

# Key Modules #

This project contains three main modules: scheduler, fetcher, parser.

## scheduler ##
* schedule new jobs
* dispatch job objects

## fetcher ##
* common crawler
* multithread

## parser ##
* parse information from fetcher results
* storage

# About SLI-demo #
* use Selenium to crawl dynamic pages
* disable image to increase performance
* add custom cookies

# Dynamic pages crawling methods #
* Analyze AJAX links and parameters
* Use phantomjs to parse JS
* Use Selenium to simulate
