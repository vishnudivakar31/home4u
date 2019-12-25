FROM java:8

EXPOSE 6060

ENV POSTGRES_URL postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PWD postgres_pwd

ADD usermanagement.jar usermanagement.jar

CMD ["java", "-jar", "usermanagement.jar"]