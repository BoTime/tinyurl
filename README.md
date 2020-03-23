# Try it
http://boliu.site

# Quick Start

1. Start server
```bash
./mvnw spring-boot:run
```
2. Open `localhost:8080/tinyurl` in the browser
3. Enter the url you want to shorten
4. Copy paste the tinyurl to address bar of the browser to be redirected

Enjoy!

# Deployment on Google Cloud Platform
Install JDK
```bash
# reference
# https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04
sudo apt udpate
sudp apt install default-jdk
java -version
```

Install Maven
```bash
sudo apt install maven
mvn -version
```

Install mysql
```bash
sudo apt install mysql-server
```

Forward port 80 to 8080: 
```bash
iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
```