FROM openjdk:8-jre-slim
LABEL author="daijiong"
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /springboot-demo
WORKDIR /springboot-demo
EXPOSE 8888
ADD ./target/springboot-demo.jar ./
CMD sleep 10;java -Dfile.encoding=utf-8 -Djava.security.egd=file:/dev/./urandom -jar springboot-demo.jar