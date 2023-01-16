ARG BUILD_IMAGE=maven:3.5-jdk-11
ARG RUNTIME_IMAGE=adoptopenjdk/openjdk11:alpine

FROM ${BUILD_IMAGE} AS dependencies

ARG APPLICATION_ENV=dev
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
ARG AWS_DEFAULT_REGION=us-east-1

ENV AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
ENV AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
ENV AWS_DEFAULT_REGION=${AWS_DEFAULT_REGION}
ENV APPLICATION_ENV=${APPLICATION_ENV}

RUN apt-get update; \
    apt-get -y install groff; \
    curl --silent --show-error --fail "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"; \
    unzip awscliv2.zip; \
    ./aws/install

COPY pom.xml ./pom.xml
COPY settings.xml ./settings.xml
COPY service/pom.xml ./service/pom.xml
COPY modules/core/pom.xml ./modules/core/pom.xml
COPY modules/orderpackage/pom.xml ./modules/orderpackage/pom.xml
COPY modules/integration/pom.xml ./modules/integration/pom.xml
COPY modules/financial-request/pom.xml ./modules/financial-request/pom.xml
COPY keystore/keystore.p12 ./keystore/keystore.p12


FROM dependencies as build
COPY pom.xml ./pom.xml
COPY settings.xml ./settings.xml
COPY service/src ./service/src
COPY modules/orderpackage/src ./modules/orderpackage/src
COPY modules/core/src ./modules/core/src
COPY modules/integration/src ./modules/integration/src
COPY modules/financial-request/src ./modules/financial-request/src

COPY keystore/keystore.p12 ./keystore/keystore.p12
RUN mvn -B clean package -s settings.xml --file pom.xml -P ${APPLICATION_ENV}

FROM ${RUNTIME_IMAGE}
ARG APPLICATION_ENV
COPY --from=build keystore/keystore.p12 keystore/keystore.p12
COPY --from=build /service/target/service-*.jar /service.jar
CMD ["java", "-jar", "-Dspring.profiles.active=${APPLICATION_ENV}","/service.jar"]
EXPOSE 8080