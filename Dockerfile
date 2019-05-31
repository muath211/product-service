FROM openjdk:8   

COPY ./product-api/target/product-api-*.jar /opt/

CMD java -jar -Dspring.profiles.active=server /opt/product-api-*.jar