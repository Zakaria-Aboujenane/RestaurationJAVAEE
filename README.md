# RestaurationJAVAEE
- JAVA EE Project that uses this EJB project : https://github.com/Zakaria-Aboujenane/ejbecom and Produces :
  - Servlet/JSP(jstl) pages
  - Web Services REST 
- JAVA
- `JWT` Authentication in JAVAEE
## Built with
[![java][java.com]][java-url]
[![ejb][ejb.com]][ejb-url]
[![JWT][JWT.com]][JWT-url]


## Installation

1. clone the project
2. create the `JBOSS Server` and configure it with a user and a datasource 
3. in `java build path` , specify a `Java version` `<=` java version of the server
4. specify `Server Runtime` as the created `JBOSS Server`
5. create EAR Project add :
      - add this JAVA EE Project 
      - configure the EJB As specified in the directory  https://github.com/Zakaria-Aboujenane/ejbecom
      - add this EJB to the EAR Project
10. deploy` the EAR Project in the jboss server it will be automatically deployed in :
    - Web version  (Servlet/JSP(jstl)): `localhost:8080/restauration` 
    - REST API : `localhost:8080/restauration`



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[ejb.com]:https://img.shields.io/badge/Specifications%20-EJB%20,%20JPA%20,%20JAVA%20EE-red
[java.com]:	https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]:https://docs.oracle.com/en/java/
[ejb-url]:https://gayerie.dev/epsi-b3-orm/javaee_orm/ejb.html
[JWT.com]:https://img.shields.io/badge/Authentication%20-JWT-green
[JWT-url]:https://jwt.io/
