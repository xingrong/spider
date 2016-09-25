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
* dispath job objects

## fetcher ##
* common crawler
* mutilthreads

## parser ##
* parse infomation from fetcher results
* storage

# About SLI-demo #
* use Selenium to crawl dynamic pages
* disable image to increase performance
* add custom cookies

# Dynamic pages crawling methods #
* Analze AJAX links and parameters
* Use phantomjs to parse JS
* Use Selenium to simulate