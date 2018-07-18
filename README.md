
# Information leakage

This is a scenario which was implemented within the scope of the `Insekta` project. Insekta is a platform for teaching security by providing text based scenarios that are supported by virtual machine images. The virtual machine image is built with [mkali](https://github.com/johnp/mkali/). You have to clone `mkali` if you want to generate a virtual machine image. The image contains a preconfigured Apache2 webserver.

This scenario provides a weakly configured Apache2 webserver, where the server-status and info page are publicly accessible. In the web application a user is active and therefore, his session id is leaked to the outside. An adversary can steal the session id and use the service. The service itself is vulnerable to XML External Entities (XXE) attacks. For this purpose the developer had to turn off some security features of the WildFly application server (see web.xml within src/main/webapp/WEB-INF/).

## Getting Started

These steps are required to install and run the service on your local machine. 

### Prerequisites

To build the project you need the following modules/tools:

*  Maven
*  Java8

### Installing and Deployment

To install the information-leakage service, you have to follow these steps:

```
git clone https://github.com/mprechtl/information-leakage.git
cd information-leakage/
mvn install
```

To run the service:

```
java -jar target/xxe-1.0-rc1-thorntail.jar
```

After that, you can access the service at localhost:8080/songbook.

### Building the mkali image

To build the mkali virtual machine image, you have to build the service first (see above). Then, you have to copy the JAR to the /mkali/xxe/opt/information-leakage folder:

```
cp target/xxe-1.0-rc1-thorntail.jar mkali/xxe/opt/information-leakage/xxe-thorntail.jar
```

After that, you have to clone the mkali build tool:

```
cd ..
git clone https://github.com/johnp/mkali/
cd mkali
./mkali ../information-leakage/mkali/xxe
```

Now there should be a file called `xxe.qcow2`. Have fun! ;)
