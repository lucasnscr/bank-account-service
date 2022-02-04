# bank-account-service

##Project description
🚀 Project that simulates the creation of a digital account and transfer between the created accounts.


## Installation ##

It is necessary to install some items:
- Docker
- Java 11
- Maven

### Commands needed to run Redis and Postgres ###

After installing Docker, open the terminal in the root directory of the project, which has already configured a docker-compose. This file serves to upload a Redis container, the database used to store the data. The command that needs to be run in the terminal is as follows: docker-compose up -d

### Redis
Redis is an open source (BSD licensed), in-memory data structure store, used as a database, cache, and message broker. Redis provides data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs, geospatial indexes, and streams. Redis has built-in replication, Lua scripting, LRU eviction, transactions, and different levels of on-disk persistence, and provides high availability via Redis Sentinel and automatic partitioning with Redis Cluster.

### Caching Definition
"What is caching data? and "how does a cache work? are common questions in computing. Caching Data is a process that stores multiple copies of data or files in a temporary storage location—or cache—so they can be accessed faster. It saves data for software applications, servers, and web browsers, which ensures users need not download information every time they access a website or application to speed up site loading.

Cached data typically includes multimedia such as images, files, and scripts, which are automatically stored on a device the first time a user opens an application or visits a website. This is used to quickly load the application or website’s information every time the user subsequently opens or visits it. Caching is a good solution for the von Neumann bottleneck, which looks at ways to better serve faster memory access.

A Domain Name System (DNS) caches DNS records to perform faster lookups, content delivery networks (CDNs) use caching to reduce latency, and web browsers cache requested Hyper Text Markup Language (HTML) files, images, and JavaScript to load websites faster. For example, when a user visits a website for the first time, an application or browser retains information to help them access it faster and more efficiently.

### How Does Caching Work?
Cached data works by storing data for re-access in a device’s memory. The data is stored high up in a computer’s memory just below the central processing unit (CPU). It is stored in a few layers, with the primary cache level built into a device’s microprocessor chip, then two more secondary levels that feed the primary level. This data is stored until it's time to live (TTL), which indicates how long content needs to be cached for, expires or the device’s disk or hard drive cache fills up.

Data is typically cached in two ways, through browser or memory caching or through CDNs.

- Browser and memory caching: Memory caches store data locally on the computer that an application or browser runs on. When the browser is active, the resources it retrieves are stored in its random access memory (RAM) or its hard drive. The next time the resources are needed to load a webpage, the browser pulls them from the cache rather than a remote server, which makes it quicker to retrieve resources and load the page.

- CDNs: Caching is one job of a CDN, which stores data in geographically distributed locations to reduce load times, handle vast amounts of traffic, and protect against cyberattacks. Browser requests get routed to a local CDN, which shortens the distance that response data travels and transfers resources faster.

### Technologies

The following technologies were used to carry out the project:
- Java 11
- Maven
- SpringBoot
- Spring Data JPA
- Spring Data Redis
- Redis
- Lombok
- Postman
- Docker-compose
